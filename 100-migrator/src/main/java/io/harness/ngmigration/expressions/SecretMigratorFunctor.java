/*
 * Copyright 2022 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.ngmigration.expressions;

import io.harness.data.structure.EmptyPredicate;
import io.harness.encryption.Scope;
import io.harness.expression.functors.ExpressionFunctor;
import io.harness.ngmigration.utils.CaseFormat;
import io.harness.ngmigration.utils.MigratorUtility;

import java.util.HashMap;
import java.util.Map;

public class SecretMigratorFunctor implements ExpressionFunctor {
  private Map<String, String> nameToIdentifier;
  private CaseFormat caseFormat;

  private Scope scope;

  public SecretMigratorFunctor(Map<String, String> nameToIdentifier, CaseFormat caseFormat, Scope scope) {
    this.caseFormat = caseFormat;
    this.scope = Scope.PROJECT;
    this.nameToIdentifier = new HashMap<>();
    if (scope != null) {
      this.scope = scope;
    }
    if (EmptyPredicate.isNotEmpty(nameToIdentifier)) {
      this.nameToIdentifier = nameToIdentifier;
    }
  }

  public Object getValue(String secretName) {
    String secretIdentifier = MigratorUtility.getIdentifierWithScope(this.scope, secretName, caseFormat);
    if (nameToIdentifier.containsKey(secretName)) {
      secretIdentifier = nameToIdentifier.get(secretName);
    }
    return "<+secrets.getValue(\"" + secretIdentifier + "\")>";
  }
}
