/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.ccm.commons.entities.recommendations;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationWorkloadId {
  String clusterName;
  String namespace;
  String workloadName;

  @Override
  public int hashCode() {
    return Objects.hash(clusterName, namespace, workloadName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecommendationWorkloadId other = (RecommendationWorkloadId) o;
    return clusterName.equals(other.clusterName) && namespace.equals(other.namespace)
        && workloadName.equals(other.workloadName);
  }
}
