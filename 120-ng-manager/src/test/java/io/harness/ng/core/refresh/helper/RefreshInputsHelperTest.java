/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.ng.core.refresh.helper;

import static io.harness.rule.OwnerRule.INDER;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joor.Reflect.on;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import io.harness.NgManagerTestBase;
import io.harness.account.AccountClient;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.category.element.UnitTests;
import io.harness.cdng.customdeployment.helper.CustomDeploymentEntitySetupHelper;
import io.harness.cdng.gitops.service.ClusterService;
import io.harness.eventsframework.api.Producer;
import io.harness.exception.InvalidRequestException;
import io.harness.ng.core.entitysetupusage.service.EntitySetupUsageService;
import io.harness.ng.core.environment.services.impl.EnvironmentServiceImpl;
import io.harness.ng.core.infrastructure.dto.NoInputMergeInputAction;
import io.harness.ng.core.infrastructure.services.impl.InfrastructureEntityServiceImpl;
import io.harness.ng.core.service.entity.ServiceEntity;
import io.harness.ng.core.service.services.impl.ServiceEntityServiceImpl;
import io.harness.ng.core.service.services.impl.ServiceEntitySetupUsageHelper;
import io.harness.ng.core.serviceoverride.services.ServiceOverrideService;
import io.harness.ngsettings.client.remote.NGSettingsClient;
import io.harness.outbox.api.OutboxService;
import io.harness.persistence.HPersistence;
import io.harness.repositories.environment.spring.EnvironmentRepository;
import io.harness.repositories.infrastructure.spring.InfrastructureRepository;
import io.harness.repositories.service.spring.ServiceRepository;
import io.harness.rule.Owner;
import io.harness.setupusage.EnvironmentEntitySetupUsageHelper;
import io.harness.setupusage.InfrastructureEntitySetupUsageHelper;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.io.Resources;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.transaction.support.TransactionTemplate;

@OwnedBy(HarnessTeam.CDC)
public class RefreshInputsHelperTest extends NgManagerTestBase {
  private static final String RESOURCE_PATH_PREFIX = "refresh/validate/";
  private static final String ACCOUNT_ID = "accountId";
  private static final String ORG_ID = "orgId";
  private static final String PROJECT_ID = "projectId";
  @InjectMocks RefreshInputsHelper refreshInputsHelper;
  @InjectMocks EntityFetchHelper entityFetchHelper;
  @Mock ServiceRepository serviceRepository;
  @Mock EnvironmentRepository environmentRepository;
  @Mock InfrastructureRepository infrastructureRepository;
  @Mock EntitySetupUsageService entitySetupUsageService;
  @Mock Producer eventProducer;
  @Mock TransactionTemplate transactionTemplate;
  @Mock OutboxService outboxService;
  @Mock ServiceOverrideService serviceOverrideService;
  @Mock ServiceEntitySetupUsageHelper entitySetupUsageHelper;
  @Mock ClusterService clusterService;
  @Mock CustomDeploymentEntitySetupHelper customDeploymentEntitySetupHelper;
  @Mock InfrastructureEntitySetupUsageHelper infrastructureEntitySetupUsageHelper;
  @Mock HPersistence hPersistence;
  ServiceEntityServiceImpl serviceEntityService;
  EnvironmentServiceImpl environmentService;
  InfrastructureEntityServiceImpl infrastructureEntityService;
  EnvironmentRefreshHelper environmentRefreshHelper;
  @Mock AccountClient accountClient;
  @Mock NGSettingsClient settingsClient;
  @Mock EnvironmentEntitySetupUsageHelper environmentEntitySetupUsageHelper;

  @Before
  public void setup() {
    serviceEntityService = spy(new ServiceEntityServiceImpl(serviceRepository, entitySetupUsageService, eventProducer,
        outboxService, transactionTemplate, serviceOverrideService, entitySetupUsageHelper));
    infrastructureEntityService = spy(new InfrastructureEntityServiceImpl(infrastructureRepository, transactionTemplate,
        outboxService, customDeploymentEntitySetupHelper, infrastructureEntitySetupUsageHelper, hPersistence));
    environmentService = spy(new EnvironmentServiceImpl(environmentRepository, entitySetupUsageService, eventProducer,
        outboxService, transactionTemplate, infrastructureEntityService, clusterService, serviceOverrideService,
        serviceEntityService, accountClient, settingsClient, environmentEntitySetupUsageHelper));

    environmentRefreshHelper =
        spy(new EnvironmentRefreshHelper(environmentService, infrastructureEntityService, serviceOverrideService));
    on(entityFetchHelper).set("serviceEntityService", serviceEntityService);
    on(refreshInputsHelper).set("serviceEntityService", serviceEntityService);
    on(refreshInputsHelper).set("entityFetchHelper", entityFetchHelper);
    on(refreshInputsHelper).set("environmentRefreshHelper", environmentRefreshHelper);
  }

  private String readFile(String filename) {
    String relativePath = RESOURCE_PATH_PREFIX + filename;
    ClassLoader classLoader = getClass().getClassLoader();
    try {
      return Resources.toString(Objects.requireNonNull(classLoader.getResource(relativePath)), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new InvalidRequestException("Could not read resource file: " + filename);
    }
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithValidService() {
    String pipelineYmlWithService = readFile("pipeline-with-single-service.yaml");
    String serviceYaml = readFile("serverless-service-valid.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));
    doReturn(null).when(environmentService).createEnvironmentInputsYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv");
    doReturn("infrastructureDefinitions:\n"
        + "  - identifier: \"infra2\"\n")
        .when(infrastructureEntityService)
        .createInfrastructureInputsFromYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv",
            Collections.singletonList("infra2"), false, NoInputMergeInputAction.ADD_IDENTIFIER_NODE);

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull().isNotEmpty();
    assertThat(refreshedYaml).isEqualTo(pipelineYmlWithService);
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithServiceInputsEmptyInServiceAndNoServiceInputsInLinkedYaml() {
    when(environmentRefreshHelper.isEnvironmentField(anyString(), any(JsonNode.class))).thenReturn(false);
    String pipelineYmlWithService = readFile("pipeline-with-no-serviceInputs.yaml");
    String serviceYaml = readFile("serverless-service-with-all-values-fixed.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull().isNotEmpty();
    assertThat(refreshedYaml).isEqualTo(pipelineYmlWithService);
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithInvalidServiceHavingFixedPrimaryArtifactRef() {
    when(environmentRefreshHelper.isEnvironmentField(anyString(), any(JsonNode.class))).thenReturn(false);
    String pipelineYmlWithService = readFile("pipeline-with-single-service.yaml");
    String serviceYaml = readFile("serverless-service.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull().isNotEmpty();
    assertThat(refreshedYaml).isEqualTo(readFile("pipeline-with-single-service-refreshed.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithServiceRuntimeAndServiceInputsFixed() {
    when(environmentRefreshHelper.isEnvironmentField(anyString(), any(JsonNode.class))).thenReturn(false);
    String pipelineYmlWithService = readFile("pipeline-with-svc-runtime-serviceInputs-fixed.yaml");

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml).isEqualTo(readFile("pipeline-with-svc-runtime-serviceInputs-fixed-refreshed.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithPrimaryRefFixedAndSourcesRuntime() {
    when(environmentRefreshHelper.isEnvironmentField(anyString(), any(JsonNode.class))).thenReturn(false);
    String pipelineYmlWithService = readFile("pipeline-with-primaryRef-fixed-source-runtime.yaml");
    String serviceYaml = readFile("serverless-service.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml).isEqualTo(readFile("pipeline-with-primaryRef-fixed-source-runtime-refreshed.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testValidateInputsForPipelineYamlWithServiceInputsEmptyInService() {
    when(environmentRefreshHelper.isEnvironmentField(anyString(), any(JsonNode.class))).thenReturn(false);
    String pipelineYmlWithService = readFile("pipeline-with-single-service.yaml");
    String serviceYaml = readFile("serverless-service-with-all-values-fixed.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml).isEqualTo(readFile("pipeline-with-single-service-refreshed-with-no-serviceInputs.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithEnvRefRuntimeButInfraDefsFixed() {
    String pipelineYmlWithService = readFile("env/pipeline-with-env-ref-runtime-and-envInputs-infraDefs-fixed.yaml");
    String serviceYaml = readFile("serverless-service-with-all-values-fixed.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml)
        .isEqualTo(readFile("env/refresh-pipeline-with-env-ref-runtime-and-envInputs-infraDefs-fixed.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithEnvRefInfraDefsAndEnvInputsRuntime() {
    String pipelineYmlWithService = readFile("env/pipeline-with-envRef-envInputs-infraDefs-runtime.yaml");
    String serviceYaml = readFile("serverless-service-with-all-values-fixed.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml).isEqualTo(readFile("env/pipeline-with-envRef-envInputs-infraDefs-runtime.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithEnvRefFixedAndEnvInputsIncorrect() {
    String pipelineYmlWithService = readFile("env/pipeline-with-fixed-envRef-incorrect-envInputs.yaml");
    String serviceYaml = readFile("serverless-service-with-all-values-fixed.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));
    doReturn(null).when(environmentService).createEnvironmentInputsYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv");
    doReturn("infrastructureDefinitions:\n"
        + "- identifier: \"infra2\"")
        .when(infrastructureEntityService)
        .createInfrastructureInputsFromYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv",
            Collections.singletonList("infra2"), false, NoInputMergeInputAction.ADD_IDENTIFIER_NODE);

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml).isEqualTo(readFile("env/refresh-pipeline-with-fixed-envRef-incorrect-envInputs.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInputsForPipelineYamlWithEnvRefFixedAndInfraDefsIncorrect() {
    String pipelineYmlWithService = readFile("env/pipeline-with-env-ref-fixed-and-infraDefs-incorrect.yaml");
    String serviceYaml = readFile("serverless-service-with-all-values-fixed.yaml");

    when(serviceEntityService.get(ACCOUNT_ID, ORG_ID, PROJECT_ID, "serverless", false))
        .thenReturn(Optional.of(ServiceEntity.builder().yaml(serviceYaml).build()));
    doReturn(null).when(environmentService).createEnvironmentInputsYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv");
    doReturn("infrastructureDefinitions:\n"
        + "- identifier: \"IDENTIFIER\"")
        .when(infrastructureEntityService)
        .createInfrastructureInputsFromYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv",
            Collections.singletonList("IDENTIFIER"), false, NoInputMergeInputAction.ADD_IDENTIFIER_NODE);

    String refreshedYaml =
        refreshInputsHelper.refreshInputs(ACCOUNT_ID, ORG_ID, PROJECT_ID, pipelineYmlWithService, null);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml)
        .isEqualTo(readFile("env/refresh-pipeline-with-env-ref-fixed-and-infraDefs-incorrect.yaml"));
  }

  @Test
  @Owner(developers = INDER)
  @Category(UnitTests.class)
  public void testRefreshInfraInTemplateInputsWithNoEnvRef() {
    String templateWithInfraFixed = readFile("env/pipTemplate-with-infra-fixed.yaml");
    String resolvedTemplateWithInfraFixed = readFile("env/pipTemplate-with-infra-fixed-resoved.yaml");

    doReturn("infrastructureDefinitions:\n"
        + "- identifier: \"infra1\"")
        .when(infrastructureEntityService)
        .createInfrastructureInputsFromYaml(ACCOUNT_ID, ORG_ID, PROJECT_ID, "testenv",
            Collections.singletonList("infra1"), false, NoInputMergeInputAction.ADD_IDENTIFIER_NODE);

    String refreshedYaml = refreshInputsHelper.refreshInputs(
        ACCOUNT_ID, ORG_ID, PROJECT_ID, templateWithInfraFixed, resolvedTemplateWithInfraFixed);
    assertThat(refreshedYaml).isNotNull();
    assertThat(refreshedYaml).isEqualTo(readFile("env/refresh-pipTemplate-with-infra-fixed.yaml"));
  }
}
