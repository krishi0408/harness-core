/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.cdng.artifact.resources.googlecloudstorage.service;

import io.harness.beans.IdentifierRef;
import io.harness.cdng.artifact.resources.googlecloudstorage.dtos.GoogleCloudStorageBucketDetails;

import java.util.List;

public interface GoogleCloudStorageArtifactResourceService {
  List<GoogleCloudStorageBucketDetails> listGcsBuckets(
      IdentifierRef connectorRef, String accountId, String orgIdentifier, String projectIdentifier, String project);
}
