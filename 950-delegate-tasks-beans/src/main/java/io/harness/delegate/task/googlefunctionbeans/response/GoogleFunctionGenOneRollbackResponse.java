/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.delegate.task.googlefunctionbeans.response;

import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.delegate.beans.DelegateMetaInfo;
import io.harness.delegate.beans.logstreaming.UnitProgressData;
import io.harness.delegate.task.googlefunctionbeans.GoogleFunction;
import io.harness.logging.CommandExecutionStatus;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@Builder
@OwnedBy(HarnessTeam.CDP)
public class GoogleFunctionGenOneRollbackResponse implements GoogleFunctionCommandResponse {
  @NonFinal DelegateMetaInfo delegateMetaInfo;
  @NonFinal UnitProgressData unitProgressData;
  CommandExecutionStatus commandExecutionStatus;
  String errorMessage;
  GoogleFunction function;
  boolean isFunctionDeleted;

  @Override
  public void setDelegateMetaInfo(DelegateMetaInfo metaInfo) {
    this.delegateMetaInfo = metaInfo;
  }

  @Override
  public void setCommandUnitsProgress(UnitProgressData unitProgressData) {
    this.unitProgressData = unitProgressData;
  }
}
