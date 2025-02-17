/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.pms.pipeline.governance.service;

import io.harness.governance.GovernanceMetadata;
import io.harness.pms.pipeline.PipelineEntity;

public interface PipelineGovernanceService {
  GovernanceMetadata validateGovernanceRules(
      String accountId, String orgIdentifier, String projectIdentifier, String yamlWithResolvedTemplates);

  GovernanceMetadata validateGovernanceRulesAndThrowExceptionIfDenied(
      String accountId, String orgIdentifier, String projectIdentifier, String yamlWithResolvedTemplates);

  String fetchExpandedPipelineJSONFromYaml(
      String accountId, String orgIdentifier, String projectIdentifier, String pipelineYaml, String action);
  String fetchExpandedPipelineJSONFromYaml(
      PipelineEntity pipelineEntity, String pipelineYaml, String branch, String action);
  String getExpandedPipelineJSONFromYaml(String accountIdentifier, String orgIdentifier, String projectIdentifier,
      String pipelineYaml, String branch, PipelineEntity pipelineEntity);
}
