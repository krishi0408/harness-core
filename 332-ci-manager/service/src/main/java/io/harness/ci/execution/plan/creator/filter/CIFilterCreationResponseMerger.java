/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.ci.plan.creator.filter;

import static io.harness.data.structure.EmptyPredicate.isNotEmpty;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.pms.filter.creation.FilterCreationResponse;
import io.harness.pms.sdk.core.pipeline.filters.FilterCreationResponseMerger;

@OwnedBy(HarnessTeam.CI)
public class CIFilterCreationResponseMerger implements FilterCreationResponseMerger {
  @Override
  public void mergeFilterCreationResponse(FilterCreationResponse finalResponse, FilterCreationResponse current) {
    if (current == null || current.getPipelineFilter() == null) {
      return;
    }

    if (finalResponse.getPipelineFilter() == null) {
      finalResponse.setPipelineFilter(CIFilter.builder().build());
    }

    CIFilter finalCIFilter = (CIFilter) finalResponse.getPipelineFilter();
    CIFilter currentCIFilter = (CIFilter) current.getPipelineFilter();

    if (isNotEmpty(currentCIFilter.getRepoNames())) {
      finalCIFilter.addRepoNames(currentCIFilter.getRepoNames());
    }
  }
}
