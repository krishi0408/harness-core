/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.repositories;

import io.harness.annotation.HarnessRepo;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.event.QueryStats;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

@HarnessRepo
@OwnedBy(HarnessTeam.PIPELINE)
public interface QueryStatsRepository extends PagingAndSortingRepository<QueryStats, String> {
  <S extends QueryStats> S save(S queryStats);

  List<QueryStats> findByServiceIdAndVersionAndExecutionTimeMillisGreaterThanOrderByExecutionTimeMillisDesc(
      String serviceId, String version, long executionTimeLimit);

  List<QueryStats> findByServiceIdAndVersion(String service, String version);
}
