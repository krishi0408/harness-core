/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package software.wings.graphql.schema.mutation.event.input;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.beans.CgEventRule;
import io.harness.beans.WebHookEventConfig;

import software.wings.graphql.schema.mutation.QLMutationInput;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@OwnedBy(HarnessTeam.CDC)
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class QLUpdateEventsConfigInput implements QLMutationInput {
  String clientMutationId;
  String appId;
  String name;
  WebHookEventConfig webhookConfig;
  CgEventRule rule;
  List<String> delegateSelectors;
  boolean enabled;
  String eventsConfigId;
}
