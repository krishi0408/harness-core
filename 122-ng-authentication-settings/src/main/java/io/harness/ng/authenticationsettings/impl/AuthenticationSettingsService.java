/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.ng.authenticationsettings.impl;

import io.harness.accesscontrol.AccountIdentifier;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.ng.authenticationsettings.dtos.AuthenticationSettingsResponse;
import io.harness.ng.authenticationsettings.dtos.mechanisms.LDAPSettings;
import io.harness.ng.authenticationsettings.dtos.mechanisms.OAuthSettings;
import io.harness.ng.core.account.AuthenticationMechanism;
import io.harness.ng.core.user.SessionTimeoutSettings;
import io.harness.ng.core.user.TwoFactorAdminOverrideSettings;

import software.wings.beans.loginSettings.LoginSettings;
import software.wings.beans.loginSettings.PasswordStrengthPolicy;
import software.wings.security.authentication.LoginTypeResponse;
import software.wings.security.authentication.SSOConfig;

import java.util.Set;
import javax.validation.constraints.NotNull;
import okhttp3.MultipartBody;

@OwnedBy(HarnessTeam.PL)
public interface AuthenticationSettingsService {
  AuthenticationSettingsResponse getAuthenticationSettings(String accountIdentifier);
  AuthenticationSettingsResponse getAuthenticationSettingsV2(String accountIdentifier);
  void updateOauthProviders(String accountId, OAuthSettings settings);
  void updateAuthMechanism(String accountId, AuthenticationMechanism authenticationMechanism);
  void removeOauthMechanism(String accountId);
  LoginSettings updateLoginSettings(String loginSettingsId, String accountIdentifier, LoginSettings loginSettings);
  void updateWhitelistedDomains(String accountIdentifier, Set<String> whitelistedDomains);
  SSOConfig uploadSAMLMetadata(@NotNull String accountId, @NotNull MultipartBody.Part inputStream,
      @NotNull String displayName, String groupMembershipAttr, @NotNull Boolean authorizationEnabled, String logoutUrl,
      String entityIdentifier, String samlProviderType, String clientId, String clientSecret, String friendlySamlName);
  SSOConfig updateSAMLMetadata(@NotNull String accountId, MultipartBody.Part inputStream, String displayName,
      String groupMembershipAttr, @NotNull Boolean authorizationEnabled, String logoutUrl, String entityIdentifier,
      String samlProviderType, String clientId, String clientSecret);
  // this overloading is for case when updating a SAML setting among list of settings in account {samlSSOId}
  SSOConfig updateSAMLMetadata(@NotNull String accountId, String samlSSOId, MultipartBody.Part inputStream,
      String displayName, String groupMembershipAttr, @NotNull Boolean authorizationEnabled, String logoutUrl,
      String entityIdentifier, String samlProviderType, String clientId, String clientSecret, String friendlySamlName);
  SSOConfig deleteSAMLMetadata(@NotNull String accountIdentifier);
  // this overloading is for case when we delete a SAML setting among list of settings in account {samlSSOId}
  SSOConfig deleteSAMLMetadata(@NotNull String accountIdentifier, @NotNull String samlSSOId);
  LoginTypeResponse getSAMLLoginTest(@NotNull String accountIdentifier);
  // this overloading is for case when we test-connection a SAML setting among list of settings in account {samlSSOId}
  LoginTypeResponse getSAMLLoginTestV2(@NotNull String accountIdentifier, @NotNull String samlSSOId);
  LDAPSettings getLdapSettings(@NotNull String accountIdentifier);
  LDAPSettings createLdapSettings(@NotNull String accountIdentifier, LDAPSettings ldapSettings);
  LDAPSettings updateLdapSettings(@NotNull String accountIdentifier, LDAPSettings ldapSettings);
  void deleteLdapSettings(@NotNull String accountIdentifier);
  boolean setTwoFactorAuthAtAccountLevel(
      String accountIdentifier, TwoFactorAdminOverrideSettings twoFactorAdminOverrideSettings);

  boolean setSessionTimeoutAtAccountLevel(
      @AccountIdentifier String accountIdentifier, SessionTimeoutSettings sessionTimeoutSettings);

  PasswordStrengthPolicy getPasswordStrengthSettings(String accountIdentifier);
  // updates authenticationEnabled value for a SAML setting {samlSSOId} in account
  void updateAuthenticationForSAMLSetting(
      @NotNull String accountId, @NotNull String samlSSOId, @NotNull Boolean enable);
}
