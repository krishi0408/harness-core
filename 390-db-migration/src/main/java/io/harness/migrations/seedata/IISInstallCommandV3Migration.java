/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

package io.harness.migrations.seedata;

import static io.harness.exception.WingsException.ExecutionContext.MANAGER;

import static software.wings.beans.Account.GLOBAL_ACCOUNT_ID;
import static software.wings.beans.CGConstants.GLOBAL_APP_ID;
import static software.wings.common.TemplateConstants.HARNESS_GALLERY;
import static software.wings.common.TemplateConstants.POWER_SHELL_IIS_APP_V3_INSTALL_PATH;
import static software.wings.common.TemplateConstants.POWER_SHELL_IIS_V4_INSTALL_PATH;
import static software.wings.common.TemplateConstants.POWER_SHELL_IIS_WEBSITE_V3_INSTALL_PATH;

import io.harness.exception.ExceptionLogger;
import io.harness.exception.WingsException;
import io.harness.migrations.SeedDataMigration;

import software.wings.beans.template.Template;
import software.wings.beans.template.TemplateFolder;
import software.wings.beans.template.TemplateGallery;
import software.wings.service.intfc.template.TemplateFolderService;
import software.wings.service.intfc.template.TemplateGalleryService;
import software.wings.service.intfc.template.TemplateService;

import com.google.inject.Inject;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IISInstallCommandV3Migration implements SeedDataMigration {
  @Inject private TemplateService templateService;
  @Inject private TemplateGalleryService templateGalleryService;
  @Inject private TemplateFolderService templateFolderService;

  @Override
  public void migrate() {
    try {
      log.info("Migrating Install Command for IIS to V4");
      updateExistingInstallCommand(POWER_SHELL_IIS_V4_INSTALL_PATH, "iis");

      log.info("Migrating Install Website Command for IIS to V3");
      updateExistingInstallCommand(POWER_SHELL_IIS_WEBSITE_V3_INSTALL_PATH, "iiswebsite");

      log.info("Migrating Install App Command for IIS to V3");
      updateExistingInstallCommand(POWER_SHELL_IIS_APP_V3_INSTALL_PATH, "iisapp");
    } catch (WingsException e) {
      ExceptionLogger.logProcessedMessages(e, MANAGER, log);
      log.error("Migration failed: ", e);
    } catch (Exception e) {
      log.error("Migration failed: ", e);
    }
  }

  public void updateExistingInstallCommand(String commandType, String keyword) throws IOException {
    TemplateGallery harnessTemplateGallery = templateGalleryService.get(GLOBAL_ACCOUNT_ID, HARNESS_GALLERY);
    if (harnessTemplateGallery == null) {
      log.info("Harness global gallery does not exist. Not copying templates");
      return;
    }

    Template globalTemplate = templateService.convertYamlToTemplate(commandType);
    globalTemplate.setAppId(GLOBAL_APP_ID);
    globalTemplate.setAccountId(GLOBAL_ACCOUNT_ID);
    log.info("Folder path for global account id: " + globalTemplate.getFolderPath());
    TemplateFolder destTemplateFolder = templateFolderService.getByFolderPath(
        GLOBAL_ACCOUNT_ID, globalTemplate.getFolderPath(), harnessTemplateGallery.getUuid());
    if (destTemplateFolder != null) {
      log.info("Template folder found for global account");

      // Get existing template
      Template existingTemplate = templateService.fetchTemplateByKeywordForAccountGallery(GLOBAL_ACCOUNT_ID, keyword);
      if (existingTemplate != null) {
        log.info("IIS Install template found in Global account");
        globalTemplate.setUuid(existingTemplate.getUuid());
        globalTemplate.setVersion(null);
        globalTemplate.setGalleryId(harnessTemplateGallery.getUuid());
        globalTemplate.setFolderId(existingTemplate.getFolderId());
        globalTemplate = templateService.update(globalTemplate);
        log.info("Global IIS Install template updated in account [{}]", GLOBAL_ACCOUNT_ID);
        templateGalleryService.copyNewVersionFromGlobalToAllAccounts(globalTemplate, keyword);
      } else {
        log.error("IIS Install template not found in Global account");
      }
    } else {
      log.error("Template folder doesn't exist for account " + GLOBAL_ACCOUNT_ID);
    }
  }
}
