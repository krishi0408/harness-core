/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.beans.response;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;

import lombok.Value;
import lombok.experimental.SuperBuilder;

@OwnedBy(HarnessTeam.PIPELINE)
@Value
@SuperBuilder
public class GitFileContentResponse extends ScmBaseResponse {
  String content;
  String branch;
}
