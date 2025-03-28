/*
 * Copyright 2020 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.execution.export;

import static io.harness.rule.OwnerRule.GARVIT;

import static org.assertj.core.api.Assertions.assertThat;

import io.harness.CategoryTest;
import io.harness.category.element.UnitTests;
import io.harness.rule.Owner;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExportExecutionsUtilsTest extends CategoryTest {
  @Test
  @Owner(developers = GARVIT)
  @Category(UnitTests.class)
  public void testUploadFile() {
    assertThat(ExportExecutionsUtils.prepareZonedDateTime(0)).isNull();

    Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    ZonedDateTime zonedDateTime = ExportExecutionsUtils.prepareZonedDateTime(now.toEpochMilli());
    assertThat(zonedDateTime).isNotNull();
    assertThat(zonedDateTime.toInstant()).isEqualTo(now);
  }
}
