/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.cdng.creator.plan.steps;

import static io.harness.data.structure.EmptyPredicate.isEmpty;
import static io.harness.pms.yaml.YAMLFieldNameConstants.EXECUTION;
import static io.harness.pms.yaml.YAMLFieldNameConstants.PARALLEL;
import static io.harness.pms.yaml.YAMLFieldNameConstants.ROLLBACK_STEPS;
import static io.harness.pms.yaml.YAMLFieldNameConstants.STAGE;
import static io.harness.pms.yaml.YAMLFieldNameConstants.STEP;
import static io.harness.pms.yaml.YAMLFieldNameConstants.STEPS;
import static io.harness.pms.yaml.YAMLFieldNameConstants.STEP_GROUP;

import io.harness.advisers.manualIntervention.ManualInterventionAdviserRollbackParameters;
import io.harness.advisers.manualIntervention.ManualInterventionAdviserWithRollback;
import io.harness.advisers.nextstep.NextStepAdviserParameters;
import io.harness.advisers.pipelinerollback.OnFailPipelineRollbackAdviser;
import io.harness.advisers.pipelinerollback.OnFailPipelineRollbackParameters;
import io.harness.advisers.retry.RetryAdviserRollbackParameters;
import io.harness.advisers.retry.RetryAdviserWithRollback;
import io.harness.advisers.rollback.OnFailRollbackAdviser;
import io.harness.advisers.rollback.OnFailRollbackParameters;
import io.harness.advisers.rollback.RollbackStrategy;
import io.harness.annotations.dev.HarnessTeam;
import io.harness.annotations.dev.OwnedBy;
import io.harness.cdng.pipeline.steps.CDAbstractStepInfo;
import io.harness.cdng.pipeline.steps.CdAbstractStepNode;
import io.harness.exception.InvalidRequestException;
import io.harness.govern.Switch;
import io.harness.plancreator.steps.AbstractStepPlanCreator;
import io.harness.plancreator.steps.FailureStrategiesUtils;
import io.harness.plancreator.steps.GenericPlanCreatorUtils;
import io.harness.plancreator.steps.common.WithStepElementParameters;
import io.harness.plancreator.strategy.StrategyUtils;
import io.harness.pms.contracts.advisers.AdviserObtainment;
import io.harness.pms.contracts.advisers.AdviserType;
import io.harness.pms.contracts.execution.failure.FailureType;
import io.harness.pms.contracts.facilitators.FacilitatorObtainment;
import io.harness.pms.contracts.facilitators.FacilitatorType;
import io.harness.pms.contracts.plan.Dependency;
import io.harness.pms.contracts.plan.ExecutionMode;
import io.harness.pms.contracts.plan.PlanCreationContextValue;
import io.harness.pms.execution.utils.SkipInfoUtils;
import io.harness.pms.sdk.core.adviser.OrchestrationAdviserTypes;
import io.harness.pms.sdk.core.adviser.abort.OnAbortAdviser;
import io.harness.pms.sdk.core.adviser.abort.OnAbortAdviserParameters;
import io.harness.pms.sdk.core.adviser.ignore.IgnoreAdviser;
import io.harness.pms.sdk.core.adviser.ignore.IgnoreAdviserParameters;
import io.harness.pms.sdk.core.adviser.markFailure.OnMarkFailureAdviser;
import io.harness.pms.sdk.core.adviser.markFailure.OnMarkFailureAdviserParameters;
import io.harness.pms.sdk.core.adviser.marksuccess.OnMarkSuccessAdviser;
import io.harness.pms.sdk.core.adviser.marksuccess.OnMarkSuccessAdviserParameters;
import io.harness.pms.sdk.core.adviser.proceedwithdefault.ProceedWithDefaultAdviserParameters;
import io.harness.pms.sdk.core.adviser.proceedwithdefault.ProceedWithDefaultValueAdviser;
import io.harness.pms.sdk.core.adviser.success.OnSuccessAdviserParameters;
import io.harness.pms.sdk.core.plan.PlanNode;
import io.harness.pms.sdk.core.plan.creation.beans.PlanCreationContext;
import io.harness.pms.sdk.core.plan.creation.beans.PlanCreationResponse;
import io.harness.pms.sdk.core.plan.creation.yaml.StepOutcomeGroup;
import io.harness.pms.sdk.core.steps.io.StepParameters;
import io.harness.pms.timeout.AbsoluteSdkTimeoutTrackerParameters;
import io.harness.pms.timeout.SdkTimeoutObtainment;
import io.harness.pms.yaml.DependenciesUtils;
import io.harness.pms.yaml.ParameterField;
import io.harness.pms.yaml.YamlField;
import io.harness.pms.yaml.YamlNode;
import io.harness.pms.yaml.YamlUtils;
import io.harness.timeout.trackers.absolute.AbsoluteTimeoutTrackerFactory;
import io.harness.utils.PlanCreatorUtilsCommon;
import io.harness.utils.TimeoutUtils;
import io.harness.when.utils.RunInfoUtils;
import io.harness.yaml.core.failurestrategy.FailureStrategyActionConfig;
import io.harness.yaml.core.failurestrategy.FailureStrategyConfig;
import io.harness.yaml.core.failurestrategy.NGFailureActionType;
import io.harness.yaml.core.failurestrategy.manualintervention.ManualInterventionFailureActionConfig;
import io.harness.yaml.core.failurestrategy.retry.RetryFailureActionConfig;
import io.harness.yaml.core.timeout.Timeout;

import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@OwnedBy(HarnessTeam.CDC)
public abstract class CDPMSStepPlanCreatorV2<T extends CdAbstractStepNode> extends AbstractStepPlanCreator<T> {
  public abstract Set<String> getSupportedStepTypes();

  @Override public abstract Class<T> getFieldClass();

  @Override
  public PlanCreationResponse createPlanForField(PlanCreationContext ctx, T stepElement) {
    boolean isStepInsideRollback = false;
    if (YamlUtils.findParentNode(ctx.getCurrentField().getNode(), ROLLBACK_STEPS) != null) {
      isStepInsideRollback = true;
    }
    stepElement.setIdentifier(StrategyUtils.getIdentifierWithExpression(ctx, stepElement.getIdentifier()));
    stepElement.setName(StrategyUtils.getIdentifierWithExpression(ctx, stepElement.getName()));
    List<AdviserObtainment> adviserObtainmentFromMetaData = getAdviserObtainmentFromMetaData(ctx.getCurrentField());
    Map<String, YamlField> dependenciesNodeMap = new HashMap<>();
    Map<String, ByteString> metadataMap = new HashMap<>();
    StepParameters stepParameters = getStepParameters(ctx, stepElement);
    // Adds the strategy field as dependency if present
    StrategyUtils.addStrategyFieldDependencyIfPresent(kryoSerializer, ctx, stepElement.getUuid(),
        stepElement.getIdentifier(), stepElement.getName(), dependenciesNodeMap, metadataMap,
        StrategyUtils.getAdviserObtainmentFromMetaDataForStep(kryoSerializer, ctx.getCurrentField()));
    // We are swapping the uuid with strategy node if present.

    PlanCreationContextValue planCreationContextValue = ctx.getGlobalContext().get("metadata");
    ExecutionMode executionMode = planCreationContextValue.getMetadata().getExecutionMode();
    PlanNode stepPlanNode =
        PlanNode.builder()
            .uuid(StrategyUtils.getSwappedPlanNodeId(ctx, stepElement.getUuid()))
            .name(getName(stepElement))
            .identifier(stepElement.getIdentifier())
            .stepType(stepElement.getStepSpecType().getStepType())
            .group(StepOutcomeGroup.STEP.name())
            .stepParameters(stepParameters)
            .facilitatorObtainment(FacilitatorObtainment.newBuilder()
                                       .setType(FacilitatorType.newBuilder()
                                                    .setType(stepElement.getStepSpecType().getFacilitatorType())
                                                    .build())
                                       .build())
            .adviserObtainments(adviserObtainmentFromMetaData)
            .skipCondition(SkipInfoUtils.getSkipCondition(stepElement.getSkipCondition()))
            .whenCondition(isStepInsideRollback
                    ? RunInfoUtils.getRunConditionForRollback(stepElement.getWhen(), executionMode)
                    : RunInfoUtils.getRunConditionForStep(stepElement.getWhen()))
            .timeoutObtainment(
                SdkTimeoutObtainment.builder()
                    .dimension(AbsoluteTimeoutTrackerFactory.DIMENSION)
                    .parameters(
                        AbsoluteSdkTimeoutTrackerParameters.builder().timeout(getTimeoutString(stepElement)).build())
                    .build())
            .expressionMode(stepElement.getStepSpecType().getExpressionMode())
            .skipUnresolvedExpressionsCheck(stepElement.getStepSpecType().skipUnresolvedExpressionsCheck())
            .build();
    // Add a dependency of strategy if present
    return PlanCreationResponse.builder()
        .planNode(stepPlanNode)
        .dependencies(DependenciesUtils.toDependenciesProto(dependenciesNodeMap)
                          .toBuilder()
                          .putDependencyMetadata(
                              stepElement.getUuid(), Dependency.newBuilder().putAllMetadata(metadataMap).build())
                          .build())
        .build();
  }

  protected List<AdviserObtainment> getAdviserObtainmentFromMetaData(YamlField currentField) {
    boolean isStepInsideRollback = false;
    if (YamlUtils.findParentNode(currentField.getNode(), ROLLBACK_STEPS) != null) {
      isStepInsideRollback = true;
    }

    // Adding adviser obtainment list from the failure strategy.
    List<AdviserObtainment> adviserObtainmentList =
        new ArrayList<>(getAdviserObtainmentForFailureStrategy(currentField, isStepInsideRollback));

    /*
     * Adding OnSuccess adviser if step is inside rollback section else adding NextStep adviser for when condition to
     * work.
     */
    if (isStepInsideRollback) {
      AdviserObtainment onSuccessAdviserObtainment = getOnSuccessAdviserObtainment(currentField);
      if (onSuccessAdviserObtainment != null) {
        adviserObtainmentList.add(onSuccessAdviserObtainment);
      }
    } else {
      // Always add nextStep adviser at last, as its priority is less than, Do not change the order.
      AdviserObtainment nextStepAdviserObtainment = getNextStepAdviserObtainment(currentField);
      if (nextStepAdviserObtainment != null) {
        adviserObtainmentList.add(nextStepAdviserObtainment);
      }
    }

    return adviserObtainmentList;
  }
  private AdviserObtainment getNextStepAdviserObtainment(YamlField currentField) {
    if (currentField != null && currentField.getNode() != null) {
      // If it is wrapped under parallel or strategy then we should not add next step as the next step adviser would be
      // on strategy node
      if (GenericPlanCreatorUtils.checkIfStepIsInParallelSection(currentField)
          || StrategyUtils.isWrappedUnderStrategy(currentField)) {
        return null;
      }
      YamlField siblingField = GenericPlanCreatorUtils.obtainNextSiblingField(currentField);
      if (siblingField != null && siblingField.getNode().getUuid() != null) {
        return AdviserObtainment.newBuilder()
            .setType(AdviserType.newBuilder().setType(OrchestrationAdviserTypes.NEXT_STEP.name()).build())
            .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(
                NextStepAdviserParameters.builder().nextNodeId(siblingField.getNode().getUuid()).build())))
            .build();
      }
    }
    return null;
  }

  private AdviserObtainment getOnSuccessAdviserObtainment(YamlField currentField) {
    if (currentField != null && currentField.getNode() != null) {
      // If it is wrapped under parallel or strategy then we should not add next step as the next step adviser would be
      // on strategy node or parallel node
      if (GenericPlanCreatorUtils.checkIfStepIsInParallelSection(currentField)
          || StrategyUtils.isWrappedUnderStrategy(currentField)) {
        return null;
      }
      YamlField siblingField = GenericPlanCreatorUtils.obtainNextSiblingField(currentField);
      if (siblingField != null && siblingField.getNode().getUuid() != null) {
        return AdviserObtainment.newBuilder()
            .setType(AdviserType.newBuilder().setType(OrchestrationAdviserTypes.ON_SUCCESS.name()).build())
            .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(
                OnSuccessAdviserParameters.builder().nextNodeId(siblingField.getNode().getUuid()).build())))
            .build();
      }
    }
    return null;
  }

  protected StepParameters getStepParameters(PlanCreationContext ctx, T stepElement) {
    if (stepElement.getStepSpecType() instanceof WithStepElementParameters) {
      stepElement.setTimeout(TimeoutUtils.getTimeout(stepElement.getTimeout()));
      return ((CDAbstractStepInfo) stepElement.getStepSpecType())
          .getStepParameters(stepElement,
              PlanCreatorUtilsCommon.getRollbackParameters(
                  ctx.getCurrentField(), Collections.emptySet(), RollbackStrategy.UNKNOWN),
              ctx);
    }

    return stepElement.getStepSpecType().getStepParameters();
  }

  protected AdviserObtainment getRetryAdviserObtainment(Set<FailureType> failureTypes, String nextNodeUuid,
      AdviserObtainment.Builder adviserObtainmentBuilder, RetryFailureActionConfig retryAction,
      ParameterField<Integer> retryCount, FailureStrategyActionConfig actionUnderRetry, YamlField currentField) {
    return adviserObtainmentBuilder.setType(RetryAdviserWithRollback.ADVISER_TYPE)
        .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(
            RetryAdviserRollbackParameters.builder()
                .applicableFailureTypes(failureTypes)
                .nextNodeId(nextNodeUuid)
                .repairActionCodeAfterRetry(GenericPlanCreatorUtils.toRepairAction(actionUnderRetry))
                .retryCount(retryCount.getValue())
                .strategyToUuid(PlanCreatorUtilsCommon.getRollbackStrategyMap(currentField))
                .waitIntervalList(retryAction.getSpecConfig()
                                      .getRetryIntervals()
                                      .getValue()
                                      .stream()
                                      .map(s -> (int) TimeoutUtils.getTimeoutInSeconds(s, 0))
                                      .collect(Collectors.toList()))
                .build())))
        .build();
  }

  protected AdviserObtainment getManualInterventionAdviserObtainment(Set<FailureType> failureTypes,
      AdviserObtainment.Builder adviserObtainmentBuilder, ManualInterventionFailureActionConfig actionConfig,
      FailureStrategyActionConfig actionUnderManualIntervention) {
    return adviserObtainmentBuilder.setType(ManualInterventionAdviserWithRollback.ADVISER_TYPE)
        .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(
            ManualInterventionAdviserRollbackParameters.builder()
                .applicableFailureTypes(failureTypes)
                .timeoutAction(GenericPlanCreatorUtils.toRepairAction(actionUnderManualIntervention))
                .timeout((int) TimeoutUtils.getTimeoutInSeconds(actionConfig.getSpecConfig().getTimeout(), 0))
                .build())))
        .build();
  }

  protected List<AdviserObtainment> getAdviserObtainmentForFailureStrategy(
      YamlField currentField, boolean isStepInsideRollback) {
    List<AdviserObtainment> adviserObtainmentList = new ArrayList<>();
    List<FailureStrategyConfig> stageFailureStrategies =
        PlanCreatorUtilsCommon.getFieldFailureStrategies(currentField, STAGE, isStepInsideRollback);
    List<FailureStrategyConfig> stepGroupFailureStrategies =
        PlanCreatorUtilsCommon.getFieldFailureStrategies(currentField, STEP_GROUP, isStepInsideRollback);
    List<FailureStrategyConfig> stepFailureStrategies =
        PlanCreatorUtilsCommon.getFailureStrategies(currentField.getNode());

    Map<FailureStrategyActionConfig, Collection<FailureType>> actionMap;
    FailureStrategiesUtils.priorityMergeFailureStrategies(
        stepFailureStrategies, stepGroupFailureStrategies, stageFailureStrategies);

    actionMap = FailureStrategiesUtils.priorityMergeFailureStrategies(
        stepFailureStrategies, stepGroupFailureStrategies, stageFailureStrategies);

    for (Map.Entry<FailureStrategyActionConfig, Collection<FailureType>> entry : actionMap.entrySet()) {
      FailureStrategyActionConfig action = entry.getKey();
      Set<FailureType> failureTypes = new HashSet<>(entry.getValue());
      NGFailureActionType actionType = action.getType();

      String nextNodeUuid = null;
      YamlField siblingField = GenericPlanCreatorUtils.obtainNextSiblingField(currentField);
      // Check if step is in parallel section or inside strategy section then dont have nextNodeUUid set.
      if (siblingField != null && !GenericPlanCreatorUtils.checkIfStepIsInParallelSection(currentField)
          && !StrategyUtils.isWrappedUnderStrategy(currentField)) {
        nextNodeUuid = siblingField.getNode().getUuid();
      }

      AdviserObtainment.Builder adviserObtainmentBuilder = AdviserObtainment.newBuilder();
      if (isStepInsideRollback) {
        if (actionType == NGFailureActionType.STAGE_ROLLBACK || actionType == NGFailureActionType.STEP_GROUP_ROLLBACK) {
          throw new InvalidRequestException("Step inside rollback section cannot have Rollback as failure strategy.");
        }
      }

      switch (actionType) {
        case IGNORE:
          adviserObtainmentList.add(
              adviserObtainmentBuilder.setType(IgnoreAdviser.ADVISER_TYPE)
                  .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(IgnoreAdviserParameters.builder()
                                                                                .applicableFailureTypes(failureTypes)
                                                                                .nextNodeId(nextNodeUuid)
                                                                                .build())))
                  .build());
          break;
        case RETRY:
          RetryFailureActionConfig retryAction = (RetryFailureActionConfig) action;
          FailureStrategiesUtils.validateRetryFailureAction(retryAction);
          ParameterField<Integer> retryCount = retryAction.getSpecConfig().getRetryCount();
          FailureStrategyActionConfig actionUnderRetry = retryAction.getSpecConfig().getOnRetryFailure().getAction();
          adviserObtainmentList.add(getRetryAdviserObtainment(failureTypes, nextNodeUuid, adviserObtainmentBuilder,
              retryAction, retryCount, actionUnderRetry, currentField));
          break;
        case MARK_AS_SUCCESS:
          adviserObtainmentList.add(
              adviserObtainmentBuilder.setType(OnMarkSuccessAdviser.ADVISER_TYPE)
                  .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(OnMarkSuccessAdviserParameters.builder()
                                                                                .applicableFailureTypes(failureTypes)
                                                                                .nextNodeId(nextNodeUuid)
                                                                                .build())))
                  .build());

          break;
        case ABORT:
          adviserObtainmentList.add(
              adviserObtainmentBuilder.setType(OnAbortAdviser.ADVISER_TYPE)
                  .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(
                      OnAbortAdviserParameters.builder().applicableFailureTypes(failureTypes).build())))
                  .build());
          break;
        case STAGE_ROLLBACK:
          OnFailRollbackParameters rollbackParameters =
              PlanCreatorUtilsCommon.getRollbackParameters(currentField, failureTypes, RollbackStrategy.STAGE_ROLLBACK);
          adviserObtainmentList.add(adviserObtainmentBuilder.setType(OnFailRollbackAdviser.ADVISER_TYPE)
                                        .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(rollbackParameters)))
                                        .build());
          break;
        case MANUAL_INTERVENTION:
          ManualInterventionFailureActionConfig actionConfig = (ManualInterventionFailureActionConfig) action;
          FailureStrategiesUtils.validateManualInterventionFailureAction(actionConfig);
          FailureStrategyActionConfig actionUnderManualIntervention =
              actionConfig.getSpecConfig().getOnTimeout().getAction();
          adviserObtainmentList.add(getManualInterventionAdviserObtainment(
              failureTypes, adviserObtainmentBuilder, actionConfig, actionUnderManualIntervention));
          break;
        case PROCEED_WITH_DEFAULT_VALUES:
          adviserObtainmentList.add(
              adviserObtainmentBuilder.setType(ProceedWithDefaultValueAdviser.ADVISER_TYPE)
                  .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(
                      ProceedWithDefaultAdviserParameters.builder().applicableFailureTypes(failureTypes).build())))
                  .build());
          break;
        case PIPELINE_ROLLBACK:
          OnFailPipelineRollbackParameters onFailPipelineRollbackParameters =
              GenericPlanCreatorUtils.buildOnFailPipelineRollbackParameters(failureTypes);
          adviserObtainmentList.add(
              adviserObtainmentBuilder.setType(OnFailPipelineRollbackAdviser.ADVISER_TYPE)
                  .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(onFailPipelineRollbackParameters)))
                  .build());
          break;
        case MARK_AS_FAILURE:
          adviserObtainmentList.add(
              adviserObtainmentBuilder.setType(OnMarkFailureAdviser.ADVISER_TYPE)
                  .setParameters(ByteString.copyFrom(kryoSerializer.asBytes(OnMarkFailureAdviserParameters.builder()
                                                                                .applicableFailureTypes(failureTypes)
                                                                                .nextNodeId(nextNodeUuid)
                                                                                .build())))
                  .build());
          break;
        default:
          Switch.unhandled(actionType);
      }
    }
    return adviserObtainmentList;
  }

  protected String getName(T stepElement) {
    String nodeName;
    if (isEmpty(stepElement.getName())) {
      nodeName = stepElement.getIdentifier();
    } else {
      nodeName = stepElement.getName();
    }
    return nodeName;
  }

  protected ParameterField<String> getTimeoutString(T stepElement) {
    ParameterField<Timeout> timeout = TimeoutUtils.getTimeout(stepElement.getTimeout());
    if (timeout.isExpression()) {
      return ParameterField.createExpressionField(
          true, timeout.getExpressionValue(), timeout.getInputSetValidator(), true);
    } else {
      return ParameterField.createValueField(timeout.getValue().getTimeoutString());
    }
  }
  protected String getExecutionStepFqn(YamlField currentField, String stepNodeType) {
    String stepFqn = null;
    YamlNode execution = YamlUtils.findParentNode(currentField.getNode(), EXECUTION);
    List<YamlNode> steps = execution.getField(STEPS).getNode().asArray();
    for (YamlNode stepsNode : steps) {
      YamlNode stepGroup = getStepGroup(stepsNode);
      stepFqn = stepGroup != null ? getStepFqnFromStepGroup(stepGroup, stepNodeType)
                                  : getStepFqnFromStepNode(stepsNode, stepNodeType);
      if (stepFqn != null) {
        return stepFqn;
      }
    }

    return stepFqn;
  }

  private String getStepFqnFromStepGroup(YamlNode stepGroup, String stepNodeType) {
    String stepFqn = null;
    List<YamlNode> stepsInsideStepGroup = stepGroup.getField(STEPS).getNode().asArray();
    for (YamlNode stepsNodeInsideStepGroup : stepsInsideStepGroup) {
      YamlNode parallelStepNode = getParallelStep(stepsNodeInsideStepGroup);
      if (parallelStepNode != null) {
        stepFqn = getStepFqnFromParallelNode(parallelStepNode, stepNodeType);
      } else {
        YamlNode stepGroupInsideStepGroup = getStepGroup(stepsNodeInsideStepGroup);
        stepFqn = stepGroupInsideStepGroup != null ? getStepFqnFromStepGroup(stepGroupInsideStepGroup, stepNodeType)
                                                   : getFqnFromStepNode(stepsNodeInsideStepGroup, stepNodeType);
      }
      if (stepFqn != null) {
        return stepFqn;
      }
    }

    return stepFqn;
  }

  private String getStepFqnFromStepNode(YamlNode stepsNode, String stepNodeType) {
    YamlNode parallelStepNode = getParallelStep(stepsNode);
    return parallelStepNode != null ? getStepFqnFromParallelNode(parallelStepNode, stepNodeType)
                                    : getFqnFromStepNode(stepsNode, stepNodeType);
  }

  private String getStepFqnFromParallelNode(YamlNode parallelStepNode, String stepNodeType) {
    String stepFqn = null;
    List<YamlNode> stepsInParallelNode = parallelStepNode.asArray();
    for (YamlNode stepInParallelNode : stepsInParallelNode) {
      YamlNode stepGroupInsideParallelNode = getStepGroup(stepInParallelNode);
      stepFqn = stepGroupInsideParallelNode != null ? getStepFqnFromStepGroup(stepGroupInsideParallelNode, stepNodeType)
                                                    : getFqnFromStepNode(stepInParallelNode, stepNodeType);
      if (stepFqn != null) {
        return stepFqn;
      }
    }

    return stepFqn;
  }

  private String getFqnFromStepNode(YamlNode stepsNode, String stepNodeType) {
    YamlNode stepNode = stepsNode.getField(STEP).getNode();
    if (stepNodeType.equals(stepNode.getType())) {
      // We are getting the fqn till stage rather than pipeline because with matrix and multi-service/infra, one stage
      // spawns into multiple stages.
      List<String> qualifiedNameList = YamlUtils.getQualifiedNameList(stepNode, STAGE, true);
      qualifiedNameList.set(0, STAGE);
      return qualifiedNameList.stream().collect(Collectors.joining("."));
    }

    return null;
  }

  private YamlNode getStepGroup(YamlNode stepsNode) {
    try {
      return stepsNode.getField(STEP_GROUP).getNode();
    } catch (Exception ex) {
      return null;
    }
  }

  private YamlNode getParallelStep(YamlNode stepsNode) {
    try {
      return stepsNode.getField(PARALLEL).getNode();
    } catch (Exception ex) {
      return null;
    }
  }
}
