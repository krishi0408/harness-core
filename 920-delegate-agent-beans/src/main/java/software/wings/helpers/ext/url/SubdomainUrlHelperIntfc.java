/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package software.wings.helpers.ext.url;

import static io.harness.annotations.dev.HarnessTeam.PL;

import io.harness.annotations.dev.OwnedBy;

import javax.servlet.http.HttpServletRequest;

@OwnedBy(PL)
public interface SubdomainUrlHelperIntfc {
  String getPortalBaseUrl(String accountId);

  String getGatewayBaseUrl(String accountId);

  String getPortalBaseUrl(String accountId, String defaultBaseUrl);

  String getApiBaseUrl(String accountId);

  String getManagerUrl(HttpServletRequest request, String accountId);

  String getDelegateMetadataUrl(String accountId, String managerHost, String deployMode);

  String getWatcherMetadataUrl(String accountId, String managerHost, String deployMode);

  String getPortalBaseUrlWithoutSeparator(String accountId);

  String getVanityUrl(String accountId);
}
