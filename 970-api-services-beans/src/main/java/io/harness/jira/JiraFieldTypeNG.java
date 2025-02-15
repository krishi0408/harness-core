/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.jira;

import static io.harness.annotations.dev.HarnessTeam.CDC;

import io.harness.annotations.dev.OwnedBy;
import io.harness.exception.InvalidArgumentsException;

import com.fasterxml.jackson.annotation.JsonProperty;

@OwnedBy(CDC)
public enum JiraFieldTypeNG {
  @JsonProperty("string") STRING,
  @JsonProperty("number") NUMBER,
  @JsonProperty("date") DATE,
  @JsonProperty("datetime") DATETIME,
  @JsonProperty("timetracking") TIME_TRACKING,
  @JsonProperty("option") OPTION,
  @JsonProperty("user") USER,
  @JsonProperty("issuelink") ISSUE_LINK,
  @JsonProperty("issuetype") ISSUE_TYPE;

  public static JiraFieldTypeNG fromTypeString(String typeStr) {
    if (typeStr == null) {
      throw new InvalidArgumentsException("Empty type");
    }

    switch (typeStr) {
      case "user":
        return USER;
      case "any":
      case "string":
        return STRING;
      case "number":
        return NUMBER;
      case "date":
        return DATE;
      case "datetime":
        return DATETIME;
      case "timetracking":
        return TIME_TRACKING;
      case "option":
      case "resolution":
      case "component":
      case "priority":
      case "version":
        return OPTION;
      case "issuelink":
        return ISSUE_LINK;
      case JiraConstantsNG.ISSUE_TYPE_KEY:
        return ISSUE_TYPE;
      default:
        // Special fields (project) and unknown fields throw this exception and are not part of issue create
        // meta.
        throw new InvalidArgumentsException(String.format("Unsupported type: %s", typeStr));
    }
  }
}
