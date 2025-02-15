/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.cdng.aws.asg;

import io.harness.annotation.RecasterAlias;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.cdng.infra.beans.InfrastructureOutcome;
import io.harness.pms.sdk.core.steps.io.PassThroughData;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.TypeAlias;

@OwnedBy(HarnessTeam.CDP)
@Value
@Builder
@TypeAlias("asgPrepareRollbackDataPassThroughData")
@RecasterAlias("io.harness.cdng.aws.asg.AsgPrepareRollbackDataPassThroughData")
public class AsgPrepareRollbackDataPassThroughData implements PassThroughData {
  Map<String, List<String>> asgStoreManifestsContent;
  InfrastructureOutcome infrastructureOutcome;
}
