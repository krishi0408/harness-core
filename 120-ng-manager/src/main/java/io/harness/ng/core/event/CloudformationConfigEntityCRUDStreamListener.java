/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.ng.core.event;

import static io.harness.annotations.dev.HarnessTeam.CDP;
import static io.harness.eventsframework.EventsFrameworkMetadataConstants.ACCOUNT_ENTITY;
import static io.harness.eventsframework.EventsFrameworkMetadataConstants.ACTION;
import static io.harness.eventsframework.EventsFrameworkMetadataConstants.DELETE_ACTION;
import static io.harness.eventsframework.EventsFrameworkMetadataConstants.ENTITY_TYPE;
import static io.harness.eventsframework.EventsFrameworkMetadataConstants.ORGANIZATION_ENTITY;
import static io.harness.eventsframework.EventsFrameworkMetadataConstants.PROJECT_ENTITY;

import io.harness.annotations.dev.OwnedBy;
import io.harness.eventsframework.consumer.Message;
import io.harness.eventsframework.entity_crud.account.AccountEntityChangeDTO;
import io.harness.eventsframework.entity_crud.organization.OrganizationEntityChangeDTO;
import io.harness.eventsframework.entity_crud.project.ProjectEntityChangeDTO;
import io.harness.exception.InvalidRequestException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@OwnedBy(CDP)
@Slf4j
@Singleton
public class CloudformationConfigEntityCRUDStreamListener implements MessageListener {
  private final CloudformationConfigEntityCRUDEventHandler cloudformationConfigEntityCRUDEventHandler;

  @Inject
  public CloudformationConfigEntityCRUDStreamListener(
      CloudformationConfigEntityCRUDEventHandler cloudformationConfigEntityCRUDEventHandler) {
    this.cloudformationConfigEntityCRUDEventHandler = cloudformationConfigEntityCRUDEventHandler;
  }

  @Override
  public boolean handleMessage(Message message) {
    Map<String, String> metadataMap = message.getMessage().getMetadataMap();
    if (metadataMap != null && metadataMap.get(ENTITY_TYPE) != null) {
      String entityType = metadataMap.get(ENTITY_TYPE);
      switch (entityType) {
        case ORGANIZATION_ENTITY:
          return processOrganizationChangeEvent(message);
        case PROJECT_ENTITY:
          return processProjectChangeEvent(message);
        case ACCOUNT_ENTITY:
          return processAccountChangeEvent(message);
        default:
      }
    }
    return true;
  }

  private boolean processAccountChangeEvent(Message message) {
    if (!(message.getMessage().getMetadataMap().containsKey(ACTION)
            && DELETE_ACTION.equals(message.getMessage().getMetadataMap().get(ACTION)))) {
      return true;
    }
    AccountEntityChangeDTO accountEntityChangeDTO;
    try {
      accountEntityChangeDTO = AccountEntityChangeDTO.parseFrom(message.getMessage().getData());
    } catch (InvalidProtocolBufferException e) {
      throw new InvalidRequestException(
          String.format("Exception in unpacking EntityChangeDTO for key %s", message.getId()), e);
    }
    return cloudformationConfigEntityCRUDEventHandler.deleteAssociatedCloudformationConfigForAccount(
        accountEntityChangeDTO.getAccountId());
  }

  private boolean processOrganizationChangeEvent(Message message) {
    if (!(message.getMessage().getMetadataMap().containsKey(ACTION)
            && DELETE_ACTION.equals(message.getMessage().getMetadataMap().get(ACTION)))) {
      return true;
    }
    OrganizationEntityChangeDTO organizationEntityChangeDTO;
    try {
      organizationEntityChangeDTO = OrganizationEntityChangeDTO.parseFrom(message.getMessage().getData());
    } catch (InvalidProtocolBufferException e) {
      throw new InvalidRequestException(
          String.format("Exception in unpacking EntityChangeDTO for key %s", message.getId()), e);
    }
    return cloudformationConfigEntityCRUDEventHandler.deleteAssociatedCloudformationConfigForOrganization(
        organizationEntityChangeDTO.getAccountIdentifier(), organizationEntityChangeDTO.getIdentifier());
  }

  private boolean processProjectChangeEvent(Message message) {
    if (!(message.getMessage().getMetadataMap().containsKey(ACTION)
            && DELETE_ACTION.equals(message.getMessage().getMetadataMap().get(ACTION)))) {
      return true;
    }
    ProjectEntityChangeDTO projectEntityChangeDTO;
    try {
      projectEntityChangeDTO = ProjectEntityChangeDTO.parseFrom(message.getMessage().getData());
    } catch (InvalidProtocolBufferException e) {
      throw new InvalidRequestException(
          String.format("Exception in unpacking ProjectEntityChangeDTO for key %s", message.getId()), e);
    }
    return cloudformationConfigEntityCRUDEventHandler.deleteAssociatedCloudformationConfigForProject(
        projectEntityChangeDTO.getAccountIdentifier(), projectEntityChangeDTO.getOrgIdentifier(),
        projectEntityChangeDTO.getIdentifier());
  }
}
