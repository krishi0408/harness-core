/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.pms.sdk.core.plugin;

import static io.harness.data.structure.CollectionUtils.emptyIfNull;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.pms.contracts.plan.PluginCreationRequest;
import io.harness.pms.contracts.plan.PluginCreationResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Singleton
@OwnedBy(HarnessTeam.PIPELINE)
public class PluginInfoProviderHelper {
  @Inject private DefaultPluginInfoProvider defaultPluginInfoProvider;
  @Inject(optional = true) private Set<PluginInfoProvider> pluginInfoProviderSet;

  public PluginCreationResponse getPluginInfo(PluginCreationRequest request) {
    Optional<PluginInfoProvider> pluginInfoProvider = emptyIfNull(pluginInfoProviderSet)
                                                          .stream()
                                                          .map(provider -> {
                                                            boolean supported = provider.isSupported(request.getType());
                                                            if (supported) {
                                                              return provider;
                                                            }
                                                            return null;
                                                          })
                                                          .filter(Objects::nonNull)
                                                          .findFirst();
    if (pluginInfoProvider.isPresent()) {
      return pluginInfoProvider.get().getPluginInfo(request);
    }
    return defaultPluginInfoProvider.getPluginInfo(request);
  }
}
