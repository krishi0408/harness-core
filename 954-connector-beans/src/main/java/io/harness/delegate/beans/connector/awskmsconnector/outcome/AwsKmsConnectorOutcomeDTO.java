/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.delegate.beans.connector.awskmsconnector.outcome;

import static io.harness.annotations.dev.HarnessTeam.CDP;

import io.harness.SecretManagerDescriptionConstants;
import io.harness.annotations.dev.OwnedBy;
import io.harness.connector.DelegateSelectable;
import io.harness.delegate.beans.connector.ConnectorConfigOutcomeDTO;
import io.harness.encryption.SecretRefData;
import io.harness.secret.SecretReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@OwnedBy(CDP)
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AwsKmsConnectorOutcomeDTO extends ConnectorConfigOutcomeDTO implements DelegateSelectable {
  @NotNull
  @Valid
  @Schema(description = SecretManagerDescriptionConstants.AWS_AUTH_CRED_KMS)
  AwsKmsConnectorCredentialOutcomeDTO credential;

  @Schema(description = SecretManagerDescriptionConstants.AWS_ARN_KMS) @SecretReference @NotNull SecretRefData kmsArn;

  @NotNull @Schema(description = SecretManagerDescriptionConstants.AWS_REGION_KMS) private String region;
  @Schema(description = SecretManagerDescriptionConstants.DEFAULT) private boolean isDefault;
  @Schema private boolean harnessManaged;
  @Schema(description = SecretManagerDescriptionConstants.DELEGATE_SELECTORS) private Set<String> delegateSelectors;
}
