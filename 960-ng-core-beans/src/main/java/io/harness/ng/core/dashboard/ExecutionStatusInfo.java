/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.ng.core.dashboard;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@OwnedBy(HarnessTeam.CDC)
@Value
@Builder
public class ExecutionStatusInfo {
  String pipelineName;
  String pipelineIdentifier;
  long startTs;
  long endTs;
  String status;
  String planExecutionId;
  GitInfo gitInfo;
  String triggerType;
  AuthorInfo author;
  List<ServiceDeploymentInfo> serviceInfoList;
  List<EnvironmentDeploymentsInfo> environmentInfoList;
}
