/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.cdng.elastigroup.beans;

import io.harness.annotation.RecasterAlias;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.delegate.task.aws.LoadBalancerDetailsForBGDeployment;
import io.harness.pms.sdk.core.data.ExecutionSweepingOutput;
import io.harness.pms.sdk.core.data.Outcome;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@OwnedBy(HarnessTeam.CDP)
@Data
@Builder
@TypeAlias("elastigroupSwapRouteDataOutcome")
@JsonTypeName("elastigroupSwapRouteDataOutcome")
@RecasterAlias("io.harness.cdng.elastigroup.beans.ElastigroupSwapRouteDataOutcome")
public class ElastigroupSwapRouteDataOutcome implements Outcome, ExecutionSweepingOutput {
  private String downsizeOldElastigroup;
  private List<LoadBalancerDetailsForBGDeployment> loadBalancerDetails;
  private String newElastigroupId;
  private String newElastigroupName;
  private String oldElastigroupId;
  private String oldElastigroupName;
}
