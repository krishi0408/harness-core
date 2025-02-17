/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

package io.harness.pms.yaml.utils;

import io.harness.data.structure.EmptyPredicate;
import io.harness.exception.InvalidRequestException;
import io.harness.pms.yaml.ParameterField;
import io.harness.utils.YamlPipelineUtils;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParameterFieldUtils {
  public static boolean containsInputSetValidator(String value, String fqnForNode) {
    try {
      ParameterField<?> parameterField = YamlPipelineUtils.read(value, ParameterField.class);
      return parameterField.getInputSetValidator() != null;
    } catch (IOException e) {
      if (EmptyPredicate.isEmpty(value)) {
        throw new InvalidRequestException("Value for the field at path [" + fqnForNode + "] is not provided!");
      } else {
        throw new InvalidRequestException(value + " is not a valid value for runtime input");
      }
    }
  }

  public static String getValueFromParameterFieldWithInputSetValidator(String value, String fqnForNode) {
    try {
      ParameterField<?> parameterField = YamlPipelineUtils.read(value, ParameterField.class);
      if (parameterField.getInputSetValidator() != null) {
        return parameterField.getValue().toString();
      }
      log.error("getValueFromParameterFieldWithInputSetValidator was called for value [" + value
          + "] that does not have an input set validator");
      return null;
    } catch (IOException e) {
      if (EmptyPredicate.isEmpty(value)) {
        throw new InvalidRequestException("Value for the field at path [" + fqnForNode + "] is not provided!");
      } else {
        throw new InvalidRequestException(value + " is not a valid value for runtime input");
      }
    }
  }
}
