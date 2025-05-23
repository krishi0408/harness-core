/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.cdng.artifact;

import static io.harness.annotations.dev.HarnessTeam.PIPELINE;

import io.harness.annotation.RecasterAlias;
import io.harness.annotations.dev.OwnedBy;
import io.harness.delegate.task.artifacts.ArtifactSourceConstants;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;

@OwnedBy(PIPELINE)
@Data
@Builder
@JsonTypeName(ArtifactSourceConstants.GOOGLE_CLOUD_SOURCE_ARTIFACT_NAME)
@RecasterAlias("io.harness.ngpipeline.pipeline.executions.beans.GoogleCloudSourceArtifactSummary")
public class GoogleCloudSourceArtifactSummary implements ArtifactSummary {
  String repository;
  String branch;
  String commitId;
  String tag;
  String sourceDirectory;

  @Override
  public String getDisplayName() {
    return repository + ":" + branch != null ? branch
        : commitId != null                   ? commitId
        : tag != null                        ? tag
                                             : "" + sourceDirectory;
  }

  @Override
  public String getType() {
    return ArtifactSourceConstants.GOOGLE_CLOUD_SOURCE_ARTIFACT_NAME;
  }
}
