/*
 * Copyright 2020 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package software.wings.service.impl.yaml.handler.InfraDefinition;

import static io.harness.data.structure.EmptyPredicate.isNotEmpty;
import static io.harness.validation.Validator.notNullCheck;

import static java.lang.String.format;

import software.wings.beans.InfrastructureType;
import software.wings.beans.SettingAttribute;
import software.wings.beans.yaml.ChangeContext;
import software.wings.infra.DirectKubernetesInfrastructure;
import software.wings.infra.DirectKubernetesInfrastructure.Yaml;
import software.wings.service.impl.yaml.handler.CloudProviderInfrastructure.CloudProviderInfrastructureYamlHandler;
import software.wings.service.impl.yaml.service.YamlHelper;
import software.wings.service.intfc.SettingsService;

import com.google.inject.Inject;
import java.util.List;

public class DirectKubernetesInfrastructureYamlHandler
    extends CloudProviderInfrastructureYamlHandler<Yaml, DirectKubernetesInfrastructure> {
  @Inject private SettingsService settingsService;
  @Inject private YamlHelper yamlHelper;

  @Override
  public Yaml toYaml(DirectKubernetesInfrastructure bean, String appId) {
    SettingAttribute cloudProvider = settingsService.get(bean.getCloudProviderId());
    Yaml yaml = Yaml.builder()
                    .clusterName(bean.getClusterName())
                    .namespace(bean.getNamespace())
                    .releaseName(bean.getReleaseName())
                    .cloudProviderName(cloudProvider.getName())
                    .type(InfrastructureType.DIRECT_KUBERNETES)
                    .expressions(bean.getExpressions())
                    .build();

    // To prevent default release name from showing in yaml when provisioner
    if (isNotEmpty(bean.getExpressions())) {
      yaml.setReleaseName(null);
    }
    return yaml;
  }

  @Override
  public DirectKubernetesInfrastructure upsertFromYaml(
      ChangeContext<Yaml> changeContext, List<ChangeContext> changeSetContext) {
    DirectKubernetesInfrastructure bean = DirectKubernetesInfrastructure.builder().build();
    toBean(bean, changeContext);
    return bean;
  }

  private void toBean(DirectKubernetesInfrastructure bean, ChangeContext<Yaml> changeContext) {
    Yaml yaml = changeContext.getYaml();
    String accountId = changeContext.getChange().getAccountId();
    String appId = yamlHelper.getAppId(accountId, changeContext.getChange().getFilePath());
    SettingAttribute cloudProvider = settingsService.getSettingAttributeByName(accountId, yaml.getCloudProviderName());
    notNullCheck(format("Cloud Provider with name %s does not exist", yaml.getCloudProviderName()), cloudProvider);
    settingsService.checkRbacOnSettingAttribute(appId, cloudProvider);
    bean.setCloudProviderId(cloudProvider.getUuid());
    bean.setClusterName(yaml.getClusterName());
    bean.setNamespace(yaml.getNamespace());
    bean.setReleaseName(yaml.getReleaseName());
    bean.setExpressions(yaml.getExpressions());
  }

  @Override
  public Class getYamlClass() {
    return Yaml.class;
  }
}
