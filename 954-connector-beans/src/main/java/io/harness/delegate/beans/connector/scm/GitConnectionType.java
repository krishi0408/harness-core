/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.delegate.beans.connector.scm;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;

public enum GitConnectionType {
  @JsonProperty(GitConfigConstants.ACCOUNT) ACCOUNT,
  @JsonProperty(GitConfigConstants.REPO) REPO,
  @Hidden @JsonProperty(GitConfigConstants.PROJECT) PROJECT
}
