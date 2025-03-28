/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.ccm.commons.entities.anomaly;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(
    name = "PerspectiveAnomalyData", description = "This object contains the Anomalies associated with a perspective")
public class PerspectiveAnomalyData {
  long timestamp;
  Integer anomalyCount;
  Double actualCost;
  Double differenceFromExpectedCost;
  List<EntityInfo> associatedResources;
  String resourceType;
}
