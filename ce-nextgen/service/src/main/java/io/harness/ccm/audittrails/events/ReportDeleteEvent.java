/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.ccm.audittrails.events;

import io.harness.ccm.views.entities.CEReportSchedule;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportDeleteEvent extends ReportEvent {
  public static final String REPORT_DELETED = "ReportDeleted";

  public ReportDeleteEvent(String accountIdentifier, CEReportSchedule reportDTO) {
    super(accountIdentifier, reportDTO);
  }

  @Override
  public String getEventType() {
    return REPORT_DELETED;
  }
}
