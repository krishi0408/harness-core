/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.audit.repositories.streaming;

import io.harness.annotation.HarnessRepo;
import io.harness.audit.entities.streaming.StreamingDestination;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

@HarnessRepo
public interface StreamingDestinationRepository
    extends PagingAndSortingRepository<StreamingDestination, String>, StreamingDestinationRepositoryCustom {
  Optional<StreamingDestination> findByAccountIdentifierAndIdentifier(String accountIdentifier, String identifier);
}
