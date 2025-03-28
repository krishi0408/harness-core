/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.gitsync.caching.service;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.gitsync.caching.beans.GitFileCacheDeleteResult;
import io.harness.gitsync.caching.beans.GitFileCacheKey;
import io.harness.gitsync.caching.beans.GitFileCacheObject;
import io.harness.gitsync.caching.beans.GitFileCacheResponse;
import io.harness.gitsync.caching.beans.GitFileCacheUpdateRequestKey;
import io.harness.gitsync.caching.beans.GitFileCacheUpdateRequestValues;
import io.harness.gitsync.caching.beans.GitFileCacheUpdateResult;

@OwnedBy(HarnessTeam.PIPELINE)
public interface GitFileCacheService {
  GitFileCacheResponse fetchFromCache(GitFileCacheKey gitFileCacheKey);

  GitFileCacheResponse upsertCache(GitFileCacheKey gitFileCacheKey, GitFileCacheObject gitFileCacheObject);

  GitFileCacheDeleteResult invalidateCache(GitFileCacheKey gitFileCacheKey);

  GitFileCacheUpdateResult updateCache(GitFileCacheUpdateRequestKey key, GitFileCacheUpdateRequestValues values);
}
