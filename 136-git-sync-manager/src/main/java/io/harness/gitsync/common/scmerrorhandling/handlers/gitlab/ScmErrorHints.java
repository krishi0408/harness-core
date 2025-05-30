/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.gitsync.common.scmerrorhandling.handlers.gitlab;

import static io.harness.annotations.dev.HarnessTeam.PIPELINE;

import io.harness.annotations.dev.OwnedBy;

import lombok.experimental.UtilityClass;

@UtilityClass
@OwnedBy(PIPELINE)
public class ScmErrorHints {
  public static final String INVALID_CREDENTIALS =
      "Please check if your Gitlab credentials in connector<CONNECTOR> are valid.";
  public static final String REPO_NOT_FOUND = "Please check if the requested Gitlab repository<REPO> exists.";
  public static final String FILE_NOT_FOUND =
      "Please check the requested file path<FILEPATH> / branch<BRANCH> / Gitlab repo name<REPO> if they exist or not.";
}
