/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.delegate.task.customdeployment;

import static io.harness.annotations.dev.HarnessTeam.CDP;
import static io.harness.rule.OwnerRule.RISHABH;

import static java.time.Duration.ofMinutes;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.harness.CategoryTest;
import io.harness.annotations.dev.OwnedBy;
import io.harness.category.element.UnitTests;
import io.harness.delegate.beans.DelegateTaskPackage;
import io.harness.delegate.beans.TaskData;
import io.harness.delegate.beans.logstreaming.ILogStreamingTaskClient;
import io.harness.delegate.task.shell.ShellExecutorFactoryNG;
import io.harness.exception.InvalidRequestException;
import io.harness.logging.CommandExecutionStatus;
import io.harness.logging.LogCallback;
import io.harness.rule.Owner;
import io.harness.shell.ExecuteCommandResponse;
import io.harness.shell.ScriptProcessExecutor;
import io.harness.shell.ScriptType;
import io.harness.shell.ShellExecutorConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

@OwnedBy(CDP)
public class FetchInstanceScriptTaskNGTest extends CategoryTest {
  private final String OUTPUT_PATH_KEY = "INSTANCE_OUTPUT_PATH";
  @Mock private ShellExecutorFactoryNG shellExecutorFactory;
  @Mock private ILogStreamingTaskClient logStreamingTaskClient;

  @Spy
  @InjectMocks
  FetchInstanceScriptTaskNG fetchInstanceScriptTaskNG =
      new FetchInstanceScriptTaskNG(DelegateTaskPackage.builder().data(TaskData.builder().build()).build(),
          logStreamingTaskClient, mock(Consumer.class), mock(BooleanSupplier.class));

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @Owner(developers = RISHABH)
  @Category(UnitTests.class)
  public void testShouldExecuteScript() {
    ScriptProcessExecutor scriptProcessExecutor = mock(ScriptProcessExecutor.class);
    Map<String, String> variables = new HashMap<>();
    variables.put("key", "value");
    FetchInstanceScriptTaskNGRequest request = FetchInstanceScriptTaskNGRequest.builder()
                                                   .accountId("accountId")
                                                   .scriptBody("test")
                                                   .executionId("executionId")
                                                   .variables(variables)
                                                   .outputPathKey(OUTPUT_PATH_KEY)
                                                   .timeoutInMillis(ofMinutes(10).toMillis())
                                                   .build();
    doReturn(scriptProcessExecutor).when(shellExecutorFactory).getExecutor(any(), any(), any());
    doReturn(mock(LogCallback.class)).when(fetchInstanceScriptTaskNG).getLogCallback(any(), any(), anyBoolean(), any());
    doReturn(ExecuteCommandResponse.builder().status(CommandExecutionStatus.SUCCESS).build())
        .when(scriptProcessExecutor)
        .executeCommandString(eq("test"), eq(emptyList()), eq(emptyList()), any());
    ArgumentCaptor<ShellExecutorConfig> shellExecutorConfigArgumentCaptor =
        ArgumentCaptor.forClass(ShellExecutorConfig.class);

    FetchInstanceScriptTaskNGResponse response = fetchInstanceScriptTaskNG.run(request);

    verify(shellExecutorFactory).getExecutor(shellExecutorConfigArgumentCaptor.capture(), any(), any());
    verify(scriptProcessExecutor)
        .executeCommandString(eq("test"), eq(emptyList()), eq(emptyList()), eq(ofMinutes(10).toMillis()));
    ShellExecutorConfig shellExecutorConfig = shellExecutorConfigArgumentCaptor.getValue();
    assertThat(shellExecutorConfig.getScriptType()).isEqualTo(ScriptType.BASH);
    assertThat(shellExecutorConfig.getExecutionId()).isEqualTo("executionId");
    assertThat(shellExecutorConfig.getAccountId()).isEqualTo("accountId");
    assertThat(shellExecutorConfig.getEnvironment().get("key")).isEqualTo("value");
    assertThat(shellExecutorConfig.getEnvironment().get(OUTPUT_PATH_KEY)).isNotEmpty();
    assertThat(response.getCommandExecutionStatus()).isEqualTo(CommandExecutionStatus.SUCCESS);
    assertThat(response.getUnitProgressData()).isNotNull();
  }

  @Test
  @Owner(developers = RISHABH)
  @Category(UnitTests.class)
  public void testShouldExecuteScriptFailure() {
    ScriptProcessExecutor scriptProcessExecutor = mock(ScriptProcessExecutor.class);
    Map<String, String> variables = new HashMap<>();
    variables.put("key", "value");
    FetchInstanceScriptTaskNGRequest request = FetchInstanceScriptTaskNGRequest.builder()
                                                   .accountId("accountId")
                                                   .scriptBody("test")
                                                   .executionId("executionId")
                                                   .variables(variables)
                                                   .outputPathKey(OUTPUT_PATH_KEY)
                                                   .timeoutInMillis(ofMinutes(10).toMillis())
                                                   .build();
    doReturn(scriptProcessExecutor).when(shellExecutorFactory).getExecutor(any(), any(), any());
    doReturn(mock(LogCallback.class)).when(fetchInstanceScriptTaskNG).getLogCallback(any(), any(), anyBoolean(), any());
    doReturn(ExecuteCommandResponse.builder().status(CommandExecutionStatus.FAILURE).build())
        .when(scriptProcessExecutor)
        .executeCommandString(eq("test"), eq(emptyList()), eq(emptyList()), eq(ofMinutes(10).toMillis()));
    ArgumentCaptor<ShellExecutorConfig> shellExecutorConfigArgumentCaptor =
        ArgumentCaptor.forClass(ShellExecutorConfig.class);

    FetchInstanceScriptTaskNGResponse response = fetchInstanceScriptTaskNG.run(request);

    verify(shellExecutorFactory).getExecutor(shellExecutorConfigArgumentCaptor.capture(), any(), any());
    verify(scriptProcessExecutor)
        .executeCommandString(eq("test"), eq(emptyList()), eq(emptyList()), eq(ofMinutes(10).toMillis()));
    ShellExecutorConfig shellExecutorConfig = shellExecutorConfigArgumentCaptor.getValue();
    assertThat(shellExecutorConfig.getScriptType()).isEqualTo(ScriptType.BASH);
    assertThat(shellExecutorConfig.getExecutionId()).isEqualTo("executionId");
    assertThat(shellExecutorConfig.getAccountId()).isEqualTo("accountId");
    assertThat(shellExecutorConfig.getEnvironment().get("key")).isEqualTo("value");
    assertThat(shellExecutorConfig.getEnvironment().get(OUTPUT_PATH_KEY)).isNotEmpty();
    assertThat(response.getCommandExecutionStatus()).isEqualTo(CommandExecutionStatus.FAILURE);
    assertThat(response.getUnitProgressData()).isNotNull();
  }

  @Test
  @Owner(developers = RISHABH)
  @Category(UnitTests.class)
  public void testShouldExecuteScriptExceptionThrown() {
    FetchInstanceScriptTaskNGRequest request = FetchInstanceScriptTaskNGRequest.builder()
                                                   .accountId("accountId")
                                                   .scriptBody("test")
                                                   .executionId("executionId")
                                                   .outputPathKey(OUTPUT_PATH_KEY)
                                                   .build();
    doReturn(mock(LogCallback.class)).when(fetchInstanceScriptTaskNG).getLogCallback(any(), any(), anyBoolean(), any());
    doThrow(new InvalidRequestException("error")).when(shellExecutorFactory).getExecutor(any(), any(), any());

    FetchInstanceScriptTaskNGResponse response = fetchInstanceScriptTaskNG.run(request);

    assertThat(response.getCommandExecutionStatus()).isEqualTo(CommandExecutionStatus.FAILURE);
    assertThat(response.getErrorMessage()).isEqualTo("error");
    assertThat(response.getUnitProgressData()).isNotNull();
  }
}
