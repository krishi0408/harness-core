/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package software.wings.beans;

import static io.harness.annotations.dev.HarnessTeam.PL;

import io.harness.annotations.StoreIn;
import io.harness.annotations.dev.OwnedBy;
import io.harness.mongo.index.FdIndex;
import io.harness.ng.DbAliases;
import io.harness.persistence.AccountAccess;
import io.harness.persistence.PersistentEntity;
import io.harness.persistence.UuidAware;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@OwnedBy(PL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"runtimeParameters"})
@EqualsAndHashCode
@StoreIn(DbAliases.HARNESS)
@Entity(value = "secretManagerRuntimeParameters", noClassnameStored = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldNameConstants(innerTypeName = "SecretManagerRuntimeParametersKeys")
public class SecretManagerRuntimeParameters implements AccountAccess, PersistentEntity, UuidAware {
  @Id private String uuid;
  private String secretManagerId;
  @FdIndex private String executionId;
  private String runtimeParameters;
  private String accountId;

  @Override
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Override
  public String getUuid() {
    return this.uuid;
  }

  public software.wings.beans.dto.SecretManagerRuntimeParameters toDto() {
    return software.wings.beans.dto.SecretManagerRuntimeParameters.builder()
        .accountId(accountId)
        .uuid(uuid)
        .secretManagerId(secretManagerId)
        .executionId(executionId)
        .runtimeParameters(runtimeParameters)
        .build();
  }
}
