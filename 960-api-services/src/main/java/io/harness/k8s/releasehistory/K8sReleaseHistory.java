/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.k8s.releasehistory;

import static io.harness.annotations.dev.HarnessTeam.CDP;

import io.harness.annotations.dev.OwnedBy;
import io.harness.data.structure.EmptyPredicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@OwnedBy(CDP)
public class K8sReleaseHistory implements IK8sReleaseHistory {
  List<K8sRelease> releaseHistory;

  @Override
  public int getAndIncrementLastReleaseNumber() {
    Optional<K8sRelease> lastReleaseOptional =
        releaseHistory.stream().max(Comparator.comparing(K8sRelease::getReleaseNumber));

    int currentReleaseNumber = 1;
    if (lastReleaseOptional.isPresent()) {
      K8sRelease lastRelease = lastReleaseOptional.get();
      currentReleaseNumber = lastRelease.getReleaseNumber() + 1;
    }

    return currentReleaseNumber;
  }

  @Override
  public IK8sRelease getLastSuccessfulRelease(int currentReleaseNumber) {
    Optional<K8sRelease> lastSuccessfulReleaseOptional =
        releaseHistory.stream()
            .filter(release -> release.getReleaseNumber() != currentReleaseNumber)
            .filter(release -> release.getReleaseStatus().equals(IK8sRelease.Status.Succeeded))
            .max(Comparator.comparing(K8sRelease::getReleaseNumber));

    return lastSuccessfulReleaseOptional.orElse(null);
  }

  @Override
  public IK8sRelease getLatestRelease() {
    Optional<K8sRelease> lastRelease = releaseHistory.stream().max(Comparator.comparing(K8sRelease::getReleaseNumber));
    return lastRelease.orElse(null);
  }

  @Override
  public boolean isEmpty() {
    return EmptyPredicate.isEmpty(releaseHistory);
  }

  @Override
  public int size() {
    if (releaseHistory != null) {
      return releaseHistory.size();
    }
    return 0;
  }

  @Override
  public IK8sReleaseHistory cloneInternal() {
    return K8sReleaseHistory.builder().releaseHistory(new ArrayList<>(releaseHistory)).build();
  }

  @Override
  public List<IK8sRelease> getReleasesMatchingColor(String color, int currentReleaseNumber) {
    return releaseHistory.stream()
        .filter(release -> currentReleaseNumber != release.getReleaseNumber())
        .map(K8sRelease.class ::cast)
        .filter(release -> K8sReleaseSecretHelper.checkReleaseColor(release.getReleaseSecret(), color))
        .collect(Collectors.toList());
  }
}
