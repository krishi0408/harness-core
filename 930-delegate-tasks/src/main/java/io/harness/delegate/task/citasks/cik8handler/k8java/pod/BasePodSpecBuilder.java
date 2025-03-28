/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.delegate.task.citasks.cik8handler.k8java.pod;

import static io.harness.data.structure.EmptyPredicate.isEmpty;
import static io.harness.data.structure.EmptyPredicate.isNotEmpty;

import static java.lang.String.format;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.delegate.beans.ci.pod.ContainerParams;
import io.harness.delegate.beans.ci.pod.EmptyDirVolume;
import io.harness.delegate.beans.ci.pod.HostAliasParams;
import io.harness.delegate.beans.ci.pod.HostPathVolume;
import io.harness.delegate.beans.ci.pod.PVCParams;
import io.harness.delegate.beans.ci.pod.PVCVolume;
import io.harness.delegate.beans.ci.pod.PodParams;
import io.harness.delegate.beans.ci.pod.PodToleration;
import io.harness.delegate.beans.ci.pod.PodVolume;
import io.harness.delegate.task.citasks.cik8handler.k8java.container.ContainerSpecBuilder;
import io.harness.delegate.task.citasks.cik8handler.k8java.container.ContainerSpecBuilderResponse;
import io.harness.delegate.task.citasks.cik8handler.params.CIConstants;

import com.google.inject.Inject;
import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1EmptyDirVolumeSourceBuilder;
import io.kubernetes.client.openapi.models.V1HostAlias;
import io.kubernetes.client.openapi.models.V1HostAliasBuilder;
import io.kubernetes.client.openapi.models.V1HostPathVolumeSourceBuilder;
import io.kubernetes.client.openapi.models.V1LocalObjectReference;
import io.kubernetes.client.openapi.models.V1PersistentVolumeClaimVolumeSourceBuilder;
import io.kubernetes.client.openapi.models.V1PodBuilder;
import io.kubernetes.client.openapi.models.V1PodFluent;
import io.kubernetes.client.openapi.models.V1PodSecurityContext;
import io.kubernetes.client.openapi.models.V1PodSecurityContextBuilder;
import io.kubernetes.client.openapi.models.V1Toleration;
import io.kubernetes.client.openapi.models.V1TolerationBuilder;
import io.kubernetes.client.openapi.models.V1Volume;
import io.kubernetes.client.openapi.models.V1VolumeBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract class to generate K8 pod spec based on parameters provided to it. It builds a minimal pod spec essential
 * for creating a pod. This class can be extended to generate a generic pod spec.
 */
@OwnedBy(HarnessTeam.CI)
public abstract class BasePodSpecBuilder {
  @Inject private ContainerSpecBuilder containerSpecBuilder;

  public V1PodBuilder createSpec(PodParams<ContainerParams> podParams) {
    V1PodFluent.SpecNested<V1PodBuilder> podBuilderSpecNested = getBaseSpec(podParams);
    decorateSpec(podParams, podBuilderSpecNested);
    return podBuilderSpecNested.endSpec();
  }

  /**
   * Builds on minimal pod spec generated by getBaseSpec method.
   */
  protected abstract void decorateSpec(
      PodParams<ContainerParams> podParams, V1PodFluent.SpecNested<V1PodBuilder> podBuilderSpecNested);

  private V1PodFluent.SpecNested<V1PodBuilder> getBaseSpec(PodParams<ContainerParams> podParams) {
    List<V1LocalObjectReference> imageSecrets = new ArrayList<>();

    Map<String, V1Volume> volumesToCreate = getVolumes(podParams.getVolumes());
    Map<String, String> volumeToPVCMap = getPVC(podParams.getPvcParamList());
    Map<String, V1LocalObjectReference> imageSecretByName = new HashMap<>();
    List<V1Container> containers =
        getContainers(podParams.getContainerParamsList(), volumesToCreate, volumeToPVCMap, imageSecretByName);
    List<V1Container> initContainers =
        getContainers(podParams.getInitContainerParamsList(), volumesToCreate, volumeToPVCMap, imageSecretByName);

    imageSecretByName.forEach((imageName, imageSecret) -> imageSecrets.add(imageSecret));

    return new V1PodBuilder()
        .withNewMetadata()
        .withName(podParams.getName())
        .withLabels(podParams.getLabels())
        .withAnnotations(podParams.getAnnotations())
        .withNamespace(podParams.getNamespace())
        .endMetadata()
        .withNewSpec()
        .withContainers(containers)
        .withServiceAccountName(podParams.getServiceAccountName())
        .withAutomountServiceAccountToken(podParams.getAutomountServiceAccountToken())
        .withNodeSelector(podParams.getNodeSelector())
        .withTolerations(getTolerations(podParams))
        .withRuntimeClassName(podParams.getRuntime())
        .withInitContainers(initContainers)
        .withImagePullSecrets(imageSecrets)
        .withHostAliases(getHostAliases(podParams.getHostAliasParamsList()))
        .withVolumes(new ArrayList<>(volumesToCreate.values()))
        .withSecurityContext(getSecurityContext(podParams))
        .withPriorityClassName(podParams.getPriorityClassName());
  }

  private List<V1Toleration> getTolerations(PodParams<ContainerParams> podParams) {
    List<V1Toleration> tolerations = new ArrayList<>();
    if (isEmpty(podParams.getTolerations())) {
      return tolerations;
    }

    for (PodToleration podToleration : podParams.getTolerations()) {
      V1TolerationBuilder tolerationBuilder = new V1TolerationBuilder();
      if (isNotEmpty(podToleration.getEffect())) {
        tolerationBuilder.withEffect(podToleration.getEffect());
      }
      if (isNotEmpty(podToleration.getKey())) {
        tolerationBuilder.withKey(podToleration.getKey());
      }
      if (isNotEmpty(podToleration.getOperator())) {
        tolerationBuilder.withOperator(podToleration.getOperator());
      }
      if (podToleration.getTolerationSeconds() != null) {
        tolerationBuilder.withTolerationSeconds((long) podToleration.getTolerationSeconds());
      }
      if (isNotEmpty(podToleration.getValue())) {
        tolerationBuilder.withValue(podToleration.getValue());
      }

      tolerations.add(tolerationBuilder.build());
    }
    return tolerations;
  }

  private V1PodSecurityContext getSecurityContext(PodParams<ContainerParams> podParams) {
    V1PodSecurityContext podSecurityContext = null;
    if (podParams.getRunAsUser() != null) {
      podSecurityContext = new V1PodSecurityContextBuilder().withRunAsUser((long) podParams.getRunAsUser()).build();
    }
    return podSecurityContext;
  }

  private List<V1Container> getContainers(List<ContainerParams> containerParamsList,
      Map<String, V1Volume> volumesToCreate, Map<String, String> volumeToPVCMap,
      Map<String, V1LocalObjectReference> imageSecretByName) {
    List<V1Container> containers = new ArrayList<>();
    if (containerParamsList == null) {
      return containers;
    }

    for (ContainerParams containerParams : containerParamsList) {
      if (containerParams.getVolumeToMountPath() != null) {
        for (Map.Entry<String, String> entry : containerParams.getVolumeToMountPath().entrySet()) {
          String volumeName = entry.getKey();
          String volumeMountPath = entry.getValue();
          if (!volumesToCreate.containsKey(volumeName)) {
            volumesToCreate.put(volumeName, getVolume(volumeName, volumeToPVCMap));
          }
        }
      }

      ContainerSpecBuilderResponse containerSpecBuilderResponse = containerSpecBuilder.createSpec(containerParams);
      containers.add(containerSpecBuilderResponse.getContainerBuilder().build());
      if (containerSpecBuilderResponse.getImageSecret() != null) {
        V1LocalObjectReference imageSecret = containerSpecBuilderResponse.getImageSecret();
        imageSecretByName.put(imageSecret.getName(), imageSecret);
      }
      if (containerSpecBuilderResponse.getVolumes() != null) {
        List<V1Volume> volumes = containerSpecBuilderResponse.getVolumes();
        for (V1Volume volume : volumes) {
          if (!volumesToCreate.containsKey(volume.getName())) {
            volumesToCreate.put(volume.getName(), volume);
          }
        }
      }
    }
    return containers;
  }

  private Map<String, String> getPVC(List<PVCParams> pvcParamsList) {
    Map<String, String> volumeToPVCMap = new HashMap<>();
    if (pvcParamsList == null) {
      return volumeToPVCMap;
    }

    for (PVCParams pvcParam : pvcParamsList) {
      volumeToPVCMap.put(pvcParam.getVolumeName(), pvcParam.getClaimName());
    }
    return volumeToPVCMap;
  }

  private V1Volume getVolume(String volumeName, Map<String, String> volumeToPVCMap) {
    if (volumeToPVCMap.containsKey(volumeName)) {
      return new V1VolumeBuilder()
          .withName(volumeName)
          .withPersistentVolumeClaim(
              new V1PersistentVolumeClaimVolumeSourceBuilder().withClaimName(volumeToPVCMap.get(volumeName)).build())
          .build();
    } else {
      return new V1VolumeBuilder()
          .withName(volumeName)
          .withEmptyDir(new V1EmptyDirVolumeSourceBuilder().build())
          .build();
    }
  }

  private List<V1HostAlias> getHostAliases(List<HostAliasParams> hostAliasParamsList) {
    List<V1HostAlias> hostAliases = new ArrayList<>();
    if (hostAliasParamsList == null) {
      return hostAliases;
    }

    hostAliasParamsList.forEach(hostAliasParams
        -> hostAliases.add(new V1HostAliasBuilder()
                               .withHostnames(hostAliasParams.getHostnameList())
                               .withIp(hostAliasParams.getIpAddress())
                               .build()));
    return hostAliases;
  }

  private Map<String, V1Volume> getVolumes(List<PodVolume> podVolumes) {
    Map<String, V1Volume> volumesToCreate = new HashMap<>();
    if (isEmpty(podVolumes)) {
      return volumesToCreate;
    }
    for (PodVolume vol : podVolumes) {
      if (vol.getType() == PodVolume.Type.EMPTY_DIR) {
        EmptyDirVolume emptyDirVolume = (EmptyDirVolume) vol;
        volumesToCreate.put(emptyDirVolume.getName(), convertEmptyDir(emptyDirVolume));
      } else if (vol.getType() == PodVolume.Type.PVC) {
        PVCVolume pvcVolume = (PVCVolume) vol;
        volumesToCreate.put(pvcVolume.getName(), convertPVC(pvcVolume));
      } else if (vol.getType() == PodVolume.Type.HOST_PATH) {
        HostPathVolume hostPathVolume = (HostPathVolume) vol;
        volumesToCreate.put(hostPathVolume.getName(), convertHostPath(hostPathVolume));
      }
    }
    return volumesToCreate;
  }

  private V1Volume convertEmptyDir(EmptyDirVolume volume) {
    V1EmptyDirVolumeSourceBuilder emptyDirVolumeSourceBuilder = new V1EmptyDirVolumeSourceBuilder();
    if (isNotEmpty(volume.getMedium())) {
      emptyDirVolumeSourceBuilder.withMedium(volume.getMedium());
    }
    if (volume.getSizeMib() != null) {
      emptyDirVolumeSourceBuilder.withSizeLimit(
          new Quantity(format("%d%s", volume.getSizeMib(), CIConstants.MEMORY_FORMAT)));
    }
    return new V1VolumeBuilder().withName(volume.getName()).withEmptyDir(emptyDirVolumeSourceBuilder.build()).build();
  }

  private V1Volume convertPVC(PVCVolume volume) {
    return new V1VolumeBuilder()
        .withName(volume.getName())
        .withPersistentVolumeClaim(new V1PersistentVolumeClaimVolumeSourceBuilder()
                                       .withClaimName(volume.getClaimName())
                                       .withReadOnly(volume.getReadOnly())
                                       .build())
        .build();
  }

  private V1Volume convertHostPath(HostPathVolume volume) {
    V1HostPathVolumeSourceBuilder hostPathVolumeSourceBuilder =
        new V1HostPathVolumeSourceBuilder().withPath(volume.getPath());
    if (isNotEmpty(volume.getHostPathType())) {
      hostPathVolumeSourceBuilder.withType(volume.getHostPathType());
    }
    return new V1VolumeBuilder().withName(volume.getName()).withHostPath(hostPathVolumeSourceBuilder.build()).build();
  }
}
