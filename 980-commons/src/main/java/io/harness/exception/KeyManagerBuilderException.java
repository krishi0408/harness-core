/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.exception;

import static io.harness.annotations.dev.HarnessTeam.PL;

import io.harness.annotations.dev.OwnedBy;

/**
 * An exception thrown while building a KeyManager.
 */
@OwnedBy(PL)
public class KeyManagerBuilderException extends Exception {
  public KeyManagerBuilderException(String message) {
    super(message);
  }

  public KeyManagerBuilderException(String message, Throwable cause) {
    super(message, cause);
  }
}
