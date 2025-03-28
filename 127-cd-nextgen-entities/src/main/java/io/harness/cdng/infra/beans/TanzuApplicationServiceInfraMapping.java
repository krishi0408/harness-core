/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */
package io.harness.cdng.infra.beans;

import io.harness.annotation.RecasterAlias;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.morphia.annotations.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@Builder
@TypeAlias("TanzuApplicationServiceInfraMapping")
@JsonTypeName("TanzuApplicationServiceInfraMapping")
@OwnedBy(HarnessTeam.CDP)
@RecasterAlias("io.harness.cdng.infra.beans.TanzuApplicationServiceInfraMapping")
public class TanzuApplicationServiceInfraMapping implements InfraMapping {
  @Id private String uuid;
  private String accountId;
  private String connectorRef;
  private String organization;
  private String space;
}