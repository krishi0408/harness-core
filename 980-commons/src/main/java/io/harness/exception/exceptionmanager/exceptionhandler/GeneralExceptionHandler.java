/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.exception.exceptionmanager.exceptionhandler;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.exception.GeneralException;
import io.harness.exception.WingsException;

import com.google.common.collect.ImmutableSet;
import java.util.Set;

@OwnedBy(HarnessTeam.DX)
public class GeneralExceptionHandler implements ExceptionHandler {
  public static Set<Class<? extends Exception>> exceptions() {
    return ImmutableSet.<Class<? extends Exception>>builder().add(NullPointerException.class).build();
  }

  @Override
  public WingsException handleException(Exception exception) {
    if (exception instanceof NullPointerException) {
      return new GeneralException("Null Pointer Exception");
    }
    return new GeneralException(exception.getMessage());
  }
}
