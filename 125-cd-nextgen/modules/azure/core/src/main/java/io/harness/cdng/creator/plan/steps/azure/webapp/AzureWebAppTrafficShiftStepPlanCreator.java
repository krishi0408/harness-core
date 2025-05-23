/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.cdng.creator.plan.steps.azure.webapp;

import static io.harness.cdng.visitor.YamlTypes.AZURE_SLOT_DEPLOYMENT;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.cdng.azure.webapp.AzureWebAppTrafficShiftStepNode;
import io.harness.cdng.azure.webapp.AzureWebAppTrafficShiftStepParameters;
import io.harness.cdng.creator.plan.steps.CDPMSStepPlanCreatorV2;
import io.harness.executions.steps.StepSpecTypeConstants;
import io.harness.plancreator.steps.common.StepElementParameters;
import io.harness.pms.sdk.core.plan.creation.beans.PlanCreationContext;
import io.harness.pms.sdk.core.plan.creation.beans.PlanCreationResponse;
import io.harness.pms.sdk.core.steps.io.StepParameters;

import com.google.common.collect.Sets;
import java.util.Set;

@OwnedBy(HarnessTeam.CDP)
public class AzureWebAppTrafficShiftStepPlanCreator extends CDPMSStepPlanCreatorV2<AzureWebAppTrafficShiftStepNode> {
  @Override
  public Set<String> getSupportedStepTypes() {
    return Sets.newHashSet(StepSpecTypeConstants.AZURE_TRAFFIC_SHIFT);
  }

  @Override
  public Class<AzureWebAppTrafficShiftStepNode> getFieldClass() {
    return AzureWebAppTrafficShiftStepNode.class;
  }

  @Override
  public PlanCreationResponse createPlanForField(PlanCreationContext ctx, AzureWebAppTrafficShiftStepNode stepElement) {
    return super.createPlanForField(ctx, stepElement);
  }

  @Override
  protected StepParameters getStepParameters(PlanCreationContext ctx, AzureWebAppTrafficShiftStepNode stepElement) {
    final StepParameters stepParameters = super.getStepParameters(ctx, stepElement);
    AzureWebAppTrafficShiftStepParameters azureWebAppTrafficShiftStepParameters =
        (AzureWebAppTrafficShiftStepParameters) ((StepElementParameters) stepParameters).getSpec();
    String slotDeploymentStepFqn = getExecutionStepFqn(ctx.getCurrentField(), AZURE_SLOT_DEPLOYMENT);
    azureWebAppTrafficShiftStepParameters.setSlotDeploymentStepFqn(slotDeploymentStepFqn);
    return stepParameters;
  }
}
