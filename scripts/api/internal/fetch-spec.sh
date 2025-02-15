#!/bin/sh
# Copyright 2022 Harness Inc. All rights reserved.
# Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
# that can be found in the licenses directory at the root of this repository, also available at
# https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.

path=''
FetchPath() {
  while read line
  do
    check_bool=false
      for word in $line; do
          if [ $check_bool = true ]
          then
            path=$word
          fi
          if [ "$1:" = "$word" ]
          then
            check_bool=true
          fi
      done
  done < "paths.txt"
}

CopyToPipeline() {
  case $1 in
  ng-manager)
    cp $path ../../../../../ng-manager/openapi.yaml
    ;;
  access-control)
    cp $path ../../../../../access-control/openapi.yaml
    ;;
  pipeline-service)
    cp $path ../../../../../pipeline-service/openapi.yaml
    ;;
  template-service)
    cp $path ../../../../../template-service/openapi.yaml
    ;;
  commons)
    cp $path ../../../../../commons/openapi.yaml
    ;;
  connectors)
    cp $path ../../../../../ng-manager/connectors/openapi.yaml
    ;;
  resource-groups)
    cp $path ../../../../../platform-service/resource-groups/openapi.yaml
    ;;
  audit-service)
    cp $path ../../../../../platform-service/audit-service/openapi.yaml
    ;;
  esac
}

FetchPath $1
CopyToPipeline $1
node version.js $path
