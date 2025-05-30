/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.ngtriggers.beans.source.webhook.v2;

import static io.harness.annotations.dev.HarnessTeam.PIPELINE;

import io.harness.annotations.dev.OwnedBy;
import io.harness.ngtriggers.beans.source.artifact.EventCondition;
import io.harness.ngtriggers.conditionchecker.ConditionOperator;

@OwnedBy(PIPELINE)
public interface TriggerEventCondition {
  EventCondition getEventCondition();
  ConditionOperator getConditionOperator();
  String getValue();
}
