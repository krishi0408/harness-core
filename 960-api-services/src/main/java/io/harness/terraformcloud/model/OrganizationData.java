/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.terraformcloud.model;

import static io.harness.annotations.dev.HarnessTeam.CDP;

import io.harness.annotations.dev.OwnedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Builder;

@OwnedBy(CDP)
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class OrganizationData extends Data {
  private Attributes attributes;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @Builder
  @lombok.Data
  public static class Attributes {
    private String name;
    private Map<String, String> permissions;
  }
}
