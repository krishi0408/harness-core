/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.utils.featureflaghelper;

import io.harness.account.AccountClient;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.beans.FeatureName;
import io.harness.remote.client.CGRestUtils;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.Set;

@OwnedBy(HarnessTeam.PL)
public class NGFeatureFlagHelperService {
  @Inject @Named("PRIVILEGED") AccountClient accountClient;

  public boolean isEnabled(String accountId, FeatureName featureName) {
    return CGRestUtils.getResponse(accountClient.isFeatureFlagEnabled(featureName.name(), accountId));
  }

  public Set<String> getFeatureFlagEnabledAccountIds(String featureName) {
    return CGRestUtils.getResponse(accountClient.featureFlagEnabledAccounts(featureName));
  }
}
