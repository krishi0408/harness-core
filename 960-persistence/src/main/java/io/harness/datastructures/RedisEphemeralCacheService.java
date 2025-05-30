/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.datastructures;

import java.util.Set;
import org.redisson.api.RedissonClient;

public class RedisEphemeralCacheService implements EphemeralCacheService {
  RedissonClient redissonClient;

  RedisEphemeralCacheService(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
  }

  @Override
  public <V> Set<V> getDistributedSet(String setName) {
    return new RedisHSet<>(redissonClient, setName);
  }
}
