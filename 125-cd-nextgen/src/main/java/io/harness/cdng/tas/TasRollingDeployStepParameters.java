/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.cdng.tas;

import io.harness.annotation.RecasterAlias;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.executions.steps.StepSpecTypeConstants;
import io.harness.plancreator.steps.TaskSelectorYaml;
import io.harness.plancreator.steps.common.SpecParameters;
import io.harness.pms.yaml.ParameterField;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@OwnedBy(HarnessTeam.CDP)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(StepSpecTypeConstants.TAS_ROLLING_DEPLOY)
@TypeAlias("tasRollingDeployStepParameters")
@RecasterAlias("io.harness.cdng.tas.TasRollingDeployStepParameters")
public class TasRollingDeployStepParameters extends TasRollingDeployBaseStepInfo implements SpecParameters {
  @Builder(builderMethodName = "infoBuilder")
  public TasRollingDeployStepParameters(
      ParameterField<List<TaskSelectorYaml>> delegateSelectors, ParameterField<List<String>> additionalRoutes) {
    super(delegateSelectors, additionalRoutes);
  }
}
