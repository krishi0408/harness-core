/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.beans.steps.nodes.security;

import static io.harness.annotations.dev.HarnessTeam.STO;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import io.harness.annotation.RecasterAlias;
import io.harness.annotations.dev.OwnedBy;
import io.harness.beans.steps.CIAbstractStepNode;
import io.harness.beans.steps.CIStepInfoType;
import io.harness.beans.steps.stepinfo.security.BurpStepInfo;
import io.harness.yaml.core.StepSpecType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("Burp")
@TypeAlias("BurpScanNode")
@OwnedBy(STO)
@RecasterAlias("io.harness.beans.steps.nodes.security.BurpScanNode")
public class BurpScanNode extends CIAbstractStepNode {
  @JsonProperty("type") @NotNull BurpScanNode.StepType type = StepType.Burp;
  @NotNull
  @JsonProperty("spec")
  @JsonTypeInfo(use = NAME, property = "type", include = EXTERNAL_PROPERTY, visible = true)
  BurpStepInfo stepInfo;

  @Override
  public String getType() {
    return CIStepInfoType.BURP.getDisplayName();
  }

  @Override
  public StepSpecType getStepSpecType() {
    return stepInfo;
  }

  enum StepType {
    Burp(CIStepInfoType.BURP.getDisplayName());
    @Getter String name;

    StepType(String name) {
      this.name = name;
    }
  }
}
