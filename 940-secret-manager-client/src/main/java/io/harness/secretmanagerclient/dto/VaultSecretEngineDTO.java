/*
 * Copyright 2020 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.secretmanagerclient.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VaultSecretEngineDTO {
  private String name;
  private String description;
  private String type;
  private Integer version;
}
