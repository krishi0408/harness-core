/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package software.wings.delegatetasks.aws;

import static io.harness.annotations.dev.HarnessTeam.CDP;
import static io.harness.delegate.beans.TaskData.DEFAULT_ASYNC_CALL_TIMEOUT;
import static io.harness.rule.OwnerRule.ANSHUL;
import static io.harness.rule.OwnerRule.SATYAM;

import static org.joor.Reflect.on;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import io.harness.annotations.dev.HarnessModule;
import io.harness.annotations.dev.OwnedBy;
import io.harness.annotations.dev.TargetModule;
import io.harness.category.element.UnitTests;
import io.harness.delegate.beans.DelegateTaskPackage;
import io.harness.delegate.beans.TaskData;
import io.harness.rule.Owner;

import software.wings.WingsBaseTest;
import software.wings.service.impl.aws.model.AwsS3ListBucketNamesRequest;
import software.wings.service.impl.aws.model.AwsS3Request;
import software.wings.service.intfc.aws.delegate.AwsS3HelperServiceDelegate;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@TargetModule(HarnessModule._930_DELEGATE_TASKS)
@OwnedBy(CDP)
public class AwsS3TaskTest extends WingsBaseTest {
  @Mock private AwsS3HelperServiceDelegate mockS3HelperServiceDelegate;

  @InjectMocks
  private AwsS3Task task =
      new AwsS3Task(DelegateTaskPackage.builder()
                        .delegateId("delegateid")
                        .data(TaskData.builder().async(true).timeout(DEFAULT_ASYNC_CALL_TIMEOUT).build())
                        .build(),
          null, notifyResponseData -> {}, () -> true);

  @Before
  public void setUp() throws Exception {
    on(task).set("s3HelperServiceDelegate", mockS3HelperServiceDelegate);
  }

  @Test
  @Owner(developers = SATYAM)
  @Category(UnitTests.class)
  public void testRunWithTaskParameters() {
    AwsS3Request request = AwsS3ListBucketNamesRequest.builder().build();
    task.run(request);
    verify(mockS3HelperServiceDelegate).listBucketNames(any(), any());
  }

  @Test(expected = NotImplementedException.class)
  @Owner(developers = ANSHUL)
  @Category(UnitTests.class)
  public void testRun() {
    AwsS3Request request = AwsS3ListBucketNamesRequest.builder().build();
    task.run(new Object[] {request});
  }
}
