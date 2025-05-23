/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.common;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;

@OwnedBy(HarnessTeam.IACM)
public class IACMExecutionConstants {
  // These are environment variables to be set on the pod for talking to the IACM service.
  public static final String IACM_SERVICE_ENDPOINT_VARIABLE = "HARNESS_IACM_SERVICE_ENDPOINT";
  public static final String IACM_SERVICE_TOKEN_VARIABLE = "HARNESS_IACM_SERVICE_TOKEN";
}
