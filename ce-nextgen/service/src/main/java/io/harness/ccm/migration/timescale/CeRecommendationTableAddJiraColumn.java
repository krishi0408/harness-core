/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.ccm.migration.timescale;

import io.harness.migration.timescale.NGAbstractTimeScaleMigration;

public class CeRecommendationTableAddJiraColumn extends NGAbstractTimeScaleMigration {
  @Override
  public String getFileName() {
    return "timescaledb/add_jira_column_recommendation_table.sql";
  }
}
