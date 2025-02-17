/*
 * Copyright 2023 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Free Trial 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/05/PolyForm-Free-Trial-1.0.0.txt.
 */

/*
 * This file is generated by jOOQ.
 */
package io.harness.timescaledb.tables.pojos;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ModuleLicenses implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String accountIdentifier;
  private String moduleType;
  private String edition;
  private String licenseType;
  private Long expiryTime;
  private Long startTime;
  private Boolean premiumSupport;
  private Boolean trialExtended;
  private Boolean selfService;
  private Long createdAt;
  private Long lastUpdatedAt;
  private Long chaosTotalExperimentRuns;
  private Long chaosTotalInfrastructures;
  private String cdLicenseType;
  private String cdWorkloads;
  private String cdServiceInstances;
  private Long ceSpendLimit;
  private Long cfNumberOfUsers;
  private Long cfNumberOfClientMaus;
  private Long ciNumberOfCommitters;
  private Long ciCacheAllowance;
  private Long ciHostingCredits;
  private Long srmNumberOfServices;
  private Long stoNumberOfDevelopers;
  private String status;
  private String createdByUuid;
  private String createdByName;
  private String createdByEmail;
  private String createdByExternalUserId;
  private String lastUpdatedByUuid;
  private String lastUpdatedByName;
  private String lastUpdatedByEmail;
  private String lastUpdatedByExternalUserId;

  public ModuleLicenses() {}

  public ModuleLicenses(ModuleLicenses value) {
    this.id = value.id;
    this.accountIdentifier = value.accountIdentifier;
    this.moduleType = value.moduleType;
    this.edition = value.edition;
    this.licenseType = value.licenseType;
    this.expiryTime = value.expiryTime;
    this.startTime = value.startTime;
    this.premiumSupport = value.premiumSupport;
    this.trialExtended = value.trialExtended;
    this.selfService = value.selfService;
    this.createdAt = value.createdAt;
    this.lastUpdatedAt = value.lastUpdatedAt;
    this.chaosTotalExperimentRuns = value.chaosTotalExperimentRuns;
    this.chaosTotalInfrastructures = value.chaosTotalInfrastructures;
    this.cdLicenseType = value.cdLicenseType;
    this.cdWorkloads = value.cdWorkloads;
    this.cdServiceInstances = value.cdServiceInstances;
    this.ceSpendLimit = value.ceSpendLimit;
    this.cfNumberOfUsers = value.cfNumberOfUsers;
    this.cfNumberOfClientMaus = value.cfNumberOfClientMaus;
    this.ciNumberOfCommitters = value.ciNumberOfCommitters;
    this.ciCacheAllowance = value.ciCacheAllowance;
    this.ciHostingCredits = value.ciHostingCredits;
    this.srmNumberOfServices = value.srmNumberOfServices;
    this.stoNumberOfDevelopers = value.stoNumberOfDevelopers;
    this.status = value.status;
    this.createdByUuid = value.createdByUuid;
    this.createdByName = value.createdByName;
    this.createdByEmail = value.createdByEmail;
    this.createdByExternalUserId = value.createdByExternalUserId;
    this.lastUpdatedByUuid = value.lastUpdatedByUuid;
    this.lastUpdatedByName = value.lastUpdatedByName;
    this.lastUpdatedByEmail = value.lastUpdatedByEmail;
    this.lastUpdatedByExternalUserId = value.lastUpdatedByExternalUserId;
  }

  public ModuleLicenses(String id, String accountIdentifier, String moduleType, String edition, String licenseType,
      Long expiryTime, Long startTime, Boolean premiumSupport, Boolean trialExtended, Boolean selfService,
      Long createdAt, Long lastUpdatedAt, Long chaosTotalExperimentRuns, Long chaosTotalInfrastructures,
      String cdLicenseType, String cdWorkloads, String cdServiceInstances, Long ceSpendLimit, Long cfNumberOfUsers,
      Long cfNumberOfClientMaus, Long ciNumberOfCommitters, Long ciCacheAllowance, Long ciHostingCredits,
      Long srmNumberOfServices, Long stoNumberOfDevelopers, String status, String createdByUuid, String createdByName,
      String createdByEmail, String createdByExternalUserId, String lastUpdatedByUuid, String lastUpdatedByName,
      String lastUpdatedByEmail, String lastUpdatedByExternalUserId) {
    this.id = id;
    this.accountIdentifier = accountIdentifier;
    this.moduleType = moduleType;
    this.edition = edition;
    this.licenseType = licenseType;
    this.expiryTime = expiryTime;
    this.startTime = startTime;
    this.premiumSupport = premiumSupport;
    this.trialExtended = trialExtended;
    this.selfService = selfService;
    this.createdAt = createdAt;
    this.lastUpdatedAt = lastUpdatedAt;
    this.chaosTotalExperimentRuns = chaosTotalExperimentRuns;
    this.chaosTotalInfrastructures = chaosTotalInfrastructures;
    this.cdLicenseType = cdLicenseType;
    this.cdWorkloads = cdWorkloads;
    this.cdServiceInstances = cdServiceInstances;
    this.ceSpendLimit = ceSpendLimit;
    this.cfNumberOfUsers = cfNumberOfUsers;
    this.cfNumberOfClientMaus = cfNumberOfClientMaus;
    this.ciNumberOfCommitters = ciNumberOfCommitters;
    this.ciCacheAllowance = ciCacheAllowance;
    this.ciHostingCredits = ciHostingCredits;
    this.srmNumberOfServices = srmNumberOfServices;
    this.stoNumberOfDevelopers = stoNumberOfDevelopers;
    this.status = status;
    this.createdByUuid = createdByUuid;
    this.createdByName = createdByName;
    this.createdByEmail = createdByEmail;
    this.createdByExternalUserId = createdByExternalUserId;
    this.lastUpdatedByUuid = lastUpdatedByUuid;
    this.lastUpdatedByName = lastUpdatedByName;
    this.lastUpdatedByEmail = lastUpdatedByEmail;
    this.lastUpdatedByExternalUserId = lastUpdatedByExternalUserId;
  }

  /**
   * Getter for <code>public.module_licenses.id</code>.
   */
  public String getId() {
    return this.id;
  }

  /**
   * Setter for <code>public.module_licenses.id</code>.
   */
  public ModuleLicenses setId(String id) {
    this.id = id;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.account_identifier</code>.
   */
  public String getAccountIdentifier() {
    return this.accountIdentifier;
  }

  /**
   * Setter for <code>public.module_licenses.account_identifier</code>.
   */
  public ModuleLicenses setAccountIdentifier(String accountIdentifier) {
    this.accountIdentifier = accountIdentifier;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.module_type</code>.
   */
  public String getModuleType() {
    return this.moduleType;
  }

  /**
   * Setter for <code>public.module_licenses.module_type</code>.
   */
  public ModuleLicenses setModuleType(String moduleType) {
    this.moduleType = moduleType;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.edition</code>.
   */
  public String getEdition() {
    return this.edition;
  }

  /**
   * Setter for <code>public.module_licenses.edition</code>.
   */
  public ModuleLicenses setEdition(String edition) {
    this.edition = edition;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.license_type</code>.
   */
  public String getLicenseType() {
    return this.licenseType;
  }

  /**
   * Setter for <code>public.module_licenses.license_type</code>.
   */
  public ModuleLicenses setLicenseType(String licenseType) {
    this.licenseType = licenseType;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.expiry_time</code>.
   */
  public Long getExpiryTime() {
    return this.expiryTime;
  }

  /**
   * Setter for <code>public.module_licenses.expiry_time</code>.
   */
  public ModuleLicenses setExpiryTime(Long expiryTime) {
    this.expiryTime = expiryTime;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.start_time</code>.
   */
  public Long getStartTime() {
    return this.startTime;
  }

  /**
   * Setter for <code>public.module_licenses.start_time</code>.
   */
  public ModuleLicenses setStartTime(Long startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.premium_support</code>.
   */
  public Boolean getPremiumSupport() {
    return this.premiumSupport;
  }

  /**
   * Setter for <code>public.module_licenses.premium_support</code>.
   */
  public ModuleLicenses setPremiumSupport(Boolean premiumSupport) {
    this.premiumSupport = premiumSupport;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.trial_extended</code>.
   */
  public Boolean getTrialExtended() {
    return this.trialExtended;
  }

  /**
   * Setter for <code>public.module_licenses.trial_extended</code>.
   */
  public ModuleLicenses setTrialExtended(Boolean trialExtended) {
    this.trialExtended = trialExtended;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.self_service</code>.
   */
  public Boolean getSelfService() {
    return this.selfService;
  }

  /**
   * Setter for <code>public.module_licenses.self_service</code>.
   */
  public ModuleLicenses setSelfService(Boolean selfService) {
    this.selfService = selfService;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.created_at</code>.
   */
  public Long getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Setter for <code>public.module_licenses.created_at</code>.
   */
  public ModuleLicenses setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.last_updated_at</code>.
   */
  public Long getLastUpdatedAt() {
    return this.lastUpdatedAt;
  }

  /**
   * Setter for <code>public.module_licenses.last_updated_at</code>.
   */
  public ModuleLicenses setLastUpdatedAt(Long lastUpdatedAt) {
    this.lastUpdatedAt = lastUpdatedAt;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.chaos_total_experiment_runs</code>.
   */
  public Long getChaosTotalExperimentRuns() {
    return this.chaosTotalExperimentRuns;
  }

  /**
   * Setter for <code>public.module_licenses.chaos_total_experiment_runs</code>.
   */
  public ModuleLicenses setChaosTotalExperimentRuns(Long chaosTotalExperimentRuns) {
    this.chaosTotalExperimentRuns = chaosTotalExperimentRuns;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.chaos_total_infrastructures</code>.
   */
  public Long getChaosTotalInfrastructures() {
    return this.chaosTotalInfrastructures;
  }

  /**
   * Setter for <code>public.module_licenses.chaos_total_infrastructures</code>.
   */
  public ModuleLicenses setChaosTotalInfrastructures(Long chaosTotalInfrastructures) {
    this.chaosTotalInfrastructures = chaosTotalInfrastructures;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.cd_license_type</code>.
   */
  public String getCdLicenseType() {
    return this.cdLicenseType;
  }

  /**
   * Setter for <code>public.module_licenses.cd_license_type</code>.
   */
  public ModuleLicenses setCdLicenseType(String cdLicenseType) {
    this.cdLicenseType = cdLicenseType;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.cd_workloads</code>.
   */
  public String getCdWorkloads() {
    return this.cdWorkloads;
  }

  /**
   * Setter for <code>public.module_licenses.cd_workloads</code>.
   */
  public ModuleLicenses setCdWorkloads(String cdWorkloads) {
    this.cdWorkloads = cdWorkloads;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.cd_service_instances</code>.
   */
  public String getCdServiceInstances() {
    return this.cdServiceInstances;
  }

  /**
   * Setter for <code>public.module_licenses.cd_service_instances</code>.
   */
  public ModuleLicenses setCdServiceInstances(String cdServiceInstances) {
    this.cdServiceInstances = cdServiceInstances;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.ce_spend_limit</code>.
   */
  public Long getCeSpendLimit() {
    return this.ceSpendLimit;
  }

  /**
   * Setter for <code>public.module_licenses.ce_spend_limit</code>.
   */
  public ModuleLicenses setCeSpendLimit(Long ceSpendLimit) {
    this.ceSpendLimit = ceSpendLimit;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.cf_number_of_users</code>.
   */
  public Long getCfNumberOfUsers() {
    return this.cfNumberOfUsers;
  }

  /**
   * Setter for <code>public.module_licenses.cf_number_of_users</code>.
   */
  public ModuleLicenses setCfNumberOfUsers(Long cfNumberOfUsers) {
    this.cfNumberOfUsers = cfNumberOfUsers;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.cf_number_of_client_maus</code>.
   */
  public Long getCfNumberOfClientMaus() {
    return this.cfNumberOfClientMaus;
  }

  /**
   * Setter for <code>public.module_licenses.cf_number_of_client_maus</code>.
   */
  public ModuleLicenses setCfNumberOfClientMaus(Long cfNumberOfClientMaus) {
    this.cfNumberOfClientMaus = cfNumberOfClientMaus;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.ci_number_of_committers</code>.
   */
  public Long getCiNumberOfCommitters() {
    return this.ciNumberOfCommitters;
  }

  /**
   * Setter for <code>public.module_licenses.ci_number_of_committers</code>.
   */
  public ModuleLicenses setCiNumberOfCommitters(Long ciNumberOfCommitters) {
    this.ciNumberOfCommitters = ciNumberOfCommitters;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.ci_cache_allowance</code>.
   */
  public Long getCiCacheAllowance() {
    return this.ciCacheAllowance;
  }

  /**
   * Setter for <code>public.module_licenses.ci_cache_allowance</code>.
   */
  public ModuleLicenses setCiCacheAllowance(Long ciCacheAllowance) {
    this.ciCacheAllowance = ciCacheAllowance;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.ci_hosting_credits</code>.
   */
  public Long getCiHostingCredits() {
    return this.ciHostingCredits;
  }

  /**
   * Setter for <code>public.module_licenses.ci_hosting_credits</code>.
   */
  public ModuleLicenses setCiHostingCredits(Long ciHostingCredits) {
    this.ciHostingCredits = ciHostingCredits;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.srm_number_of_services</code>.
   */
  public Long getSrmNumberOfServices() {
    return this.srmNumberOfServices;
  }

  /**
   * Setter for <code>public.module_licenses.srm_number_of_services</code>.
   */
  public ModuleLicenses setSrmNumberOfServices(Long srmNumberOfServices) {
    this.srmNumberOfServices = srmNumberOfServices;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.sto_number_of_developers</code>.
   */
  public Long getStoNumberOfDevelopers() {
    return this.stoNumberOfDevelopers;
  }

  /**
   * Setter for <code>public.module_licenses.sto_number_of_developers</code>.
   */
  public ModuleLicenses setStoNumberOfDevelopers(Long stoNumberOfDevelopers) {
    this.stoNumberOfDevelopers = stoNumberOfDevelopers;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.status</code>.
   */
  public String getStatus() {
    return this.status;
  }

  /**
   * Setter for <code>public.module_licenses.status</code>.
   */
  public ModuleLicenses setStatus(String status) {
    this.status = status;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.created_by_uuid</code>.
   */
  public String getCreatedByUuid() {
    return this.createdByUuid;
  }

  /**
   * Setter for <code>public.module_licenses.created_by_uuid</code>.
   */
  public ModuleLicenses setCreatedByUuid(String createdByUuid) {
    this.createdByUuid = createdByUuid;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.created_by_name</code>.
   */
  public String getCreatedByName() {
    return this.createdByName;
  }

  /**
   * Setter for <code>public.module_licenses.created_by_name</code>.
   */
  public ModuleLicenses setCreatedByName(String createdByName) {
    this.createdByName = createdByName;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.created_by_email</code>.
   */
  public String getCreatedByEmail() {
    return this.createdByEmail;
  }

  /**
   * Setter for <code>public.module_licenses.created_by_email</code>.
   */
  public ModuleLicenses setCreatedByEmail(String createdByEmail) {
    this.createdByEmail = createdByEmail;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.created_by_external_user_id</code>.
   */
  public String getCreatedByExternalUserId() {
    return this.createdByExternalUserId;
  }

  /**
   * Setter for <code>public.module_licenses.created_by_external_user_id</code>.
   */
  public ModuleLicenses setCreatedByExternalUserId(String createdByExternalUserId) {
    this.createdByExternalUserId = createdByExternalUserId;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.last_updated_by_uuid</code>.
   */
  public String getLastUpdatedByUuid() {
    return this.lastUpdatedByUuid;
  }

  /**
   * Setter for <code>public.module_licenses.last_updated_by_uuid</code>.
   */
  public ModuleLicenses setLastUpdatedByUuid(String lastUpdatedByUuid) {
    this.lastUpdatedByUuid = lastUpdatedByUuid;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.last_updated_by_name</code>.
   */
  public String getLastUpdatedByName() {
    return this.lastUpdatedByName;
  }

  /**
   * Setter for <code>public.module_licenses.last_updated_by_name</code>.
   */
  public ModuleLicenses setLastUpdatedByName(String lastUpdatedByName) {
    this.lastUpdatedByName = lastUpdatedByName;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.last_updated_by_email</code>.
   */
  public String getLastUpdatedByEmail() {
    return this.lastUpdatedByEmail;
  }

  /**
   * Setter for <code>public.module_licenses.last_updated_by_email</code>.
   */
  public ModuleLicenses setLastUpdatedByEmail(String lastUpdatedByEmail) {
    this.lastUpdatedByEmail = lastUpdatedByEmail;
    return this;
  }

  /**
   * Getter for <code>public.module_licenses.last_updated_by_external_user_id</code>.
   */
  public String getLastUpdatedByExternalUserId() {
    return this.lastUpdatedByExternalUserId;
  }

  /**
   * Setter for <code>public.module_licenses.last_updated_by_external_user_id</code>.
   */
  public ModuleLicenses setLastUpdatedByExternalUserId(String lastUpdatedByExternalUserId) {
    this.lastUpdatedByExternalUserId = lastUpdatedByExternalUserId;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ModuleLicenses other = (ModuleLicenses) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (accountIdentifier == null) {
      if (other.accountIdentifier != null)
        return false;
    } else if (!accountIdentifier.equals(other.accountIdentifier))
      return false;
    if (moduleType == null) {
      if (other.moduleType != null)
        return false;
    } else if (!moduleType.equals(other.moduleType))
      return false;
    if (edition == null) {
      if (other.edition != null)
        return false;
    } else if (!edition.equals(other.edition))
      return false;
    if (licenseType == null) {
      if (other.licenseType != null)
        return false;
    } else if (!licenseType.equals(other.licenseType))
      return false;
    if (expiryTime == null) {
      if (other.expiryTime != null)
        return false;
    } else if (!expiryTime.equals(other.expiryTime))
      return false;
    if (startTime == null) {
      if (other.startTime != null)
        return false;
    } else if (!startTime.equals(other.startTime))
      return false;
    if (premiumSupport == null) {
      if (other.premiumSupport != null)
        return false;
    } else if (!premiumSupport.equals(other.premiumSupport))
      return false;
    if (trialExtended == null) {
      if (other.trialExtended != null)
        return false;
    } else if (!trialExtended.equals(other.trialExtended))
      return false;
    if (selfService == null) {
      if (other.selfService != null)
        return false;
    } else if (!selfService.equals(other.selfService))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (lastUpdatedAt == null) {
      if (other.lastUpdatedAt != null)
        return false;
    } else if (!lastUpdatedAt.equals(other.lastUpdatedAt))
      return false;
    if (chaosTotalExperimentRuns == null) {
      if (other.chaosTotalExperimentRuns != null)
        return false;
    } else if (!chaosTotalExperimentRuns.equals(other.chaosTotalExperimentRuns))
      return false;
    if (chaosTotalInfrastructures == null) {
      if (other.chaosTotalInfrastructures != null)
        return false;
    } else if (!chaosTotalInfrastructures.equals(other.chaosTotalInfrastructures))
      return false;
    if (cdLicenseType == null) {
      if (other.cdLicenseType != null)
        return false;
    } else if (!cdLicenseType.equals(other.cdLicenseType))
      return false;
    if (cdWorkloads == null) {
      if (other.cdWorkloads != null)
        return false;
    } else if (!cdWorkloads.equals(other.cdWorkloads))
      return false;
    if (cdServiceInstances == null) {
      if (other.cdServiceInstances != null)
        return false;
    } else if (!cdServiceInstances.equals(other.cdServiceInstances))
      return false;
    if (ceSpendLimit == null) {
      if (other.ceSpendLimit != null)
        return false;
    } else if (!ceSpendLimit.equals(other.ceSpendLimit))
      return false;
    if (cfNumberOfUsers == null) {
      if (other.cfNumberOfUsers != null)
        return false;
    } else if (!cfNumberOfUsers.equals(other.cfNumberOfUsers))
      return false;
    if (cfNumberOfClientMaus == null) {
      if (other.cfNumberOfClientMaus != null)
        return false;
    } else if (!cfNumberOfClientMaus.equals(other.cfNumberOfClientMaus))
      return false;
    if (ciNumberOfCommitters == null) {
      if (other.ciNumberOfCommitters != null)
        return false;
    } else if (!ciNumberOfCommitters.equals(other.ciNumberOfCommitters))
      return false;
    if (ciCacheAllowance == null) {
      if (other.ciCacheAllowance != null)
        return false;
    } else if (!ciCacheAllowance.equals(other.ciCacheAllowance))
      return false;
    if (ciHostingCredits == null) {
      if (other.ciHostingCredits != null)
        return false;
    } else if (!ciHostingCredits.equals(other.ciHostingCredits))
      return false;
    if (srmNumberOfServices == null) {
      if (other.srmNumberOfServices != null)
        return false;
    } else if (!srmNumberOfServices.equals(other.srmNumberOfServices))
      return false;
    if (stoNumberOfDevelopers == null) {
      if (other.stoNumberOfDevelopers != null)
        return false;
    } else if (!stoNumberOfDevelopers.equals(other.stoNumberOfDevelopers))
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    if (createdByUuid == null) {
      if (other.createdByUuid != null)
        return false;
    } else if (!createdByUuid.equals(other.createdByUuid))
      return false;
    if (createdByName == null) {
      if (other.createdByName != null)
        return false;
    } else if (!createdByName.equals(other.createdByName))
      return false;
    if (createdByEmail == null) {
      if (other.createdByEmail != null)
        return false;
    } else if (!createdByEmail.equals(other.createdByEmail))
      return false;
    if (createdByExternalUserId == null) {
      if (other.createdByExternalUserId != null)
        return false;
    } else if (!createdByExternalUserId.equals(other.createdByExternalUserId))
      return false;
    if (lastUpdatedByUuid == null) {
      if (other.lastUpdatedByUuid != null)
        return false;
    } else if (!lastUpdatedByUuid.equals(other.lastUpdatedByUuid))
      return false;
    if (lastUpdatedByName == null) {
      if (other.lastUpdatedByName != null)
        return false;
    } else if (!lastUpdatedByName.equals(other.lastUpdatedByName))
      return false;
    if (lastUpdatedByEmail == null) {
      if (other.lastUpdatedByEmail != null)
        return false;
    } else if (!lastUpdatedByEmail.equals(other.lastUpdatedByEmail))
      return false;
    if (lastUpdatedByExternalUserId == null) {
      if (other.lastUpdatedByExternalUserId != null)
        return false;
    } else if (!lastUpdatedByExternalUserId.equals(other.lastUpdatedByExternalUserId))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    result = prime * result + ((this.accountIdentifier == null) ? 0 : this.accountIdentifier.hashCode());
    result = prime * result + ((this.moduleType == null) ? 0 : this.moduleType.hashCode());
    result = prime * result + ((this.edition == null) ? 0 : this.edition.hashCode());
    result = prime * result + ((this.licenseType == null) ? 0 : this.licenseType.hashCode());
    result = prime * result + ((this.expiryTime == null) ? 0 : this.expiryTime.hashCode());
    result = prime * result + ((this.startTime == null) ? 0 : this.startTime.hashCode());
    result = prime * result + ((this.premiumSupport == null) ? 0 : this.premiumSupport.hashCode());
    result = prime * result + ((this.trialExtended == null) ? 0 : this.trialExtended.hashCode());
    result = prime * result + ((this.selfService == null) ? 0 : this.selfService.hashCode());
    result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
    result = prime * result + ((this.lastUpdatedAt == null) ? 0 : this.lastUpdatedAt.hashCode());
    result = prime * result + ((this.chaosTotalExperimentRuns == null) ? 0 : this.chaosTotalExperimentRuns.hashCode());
    result =
        prime * result + ((this.chaosTotalInfrastructures == null) ? 0 : this.chaosTotalInfrastructures.hashCode());
    result = prime * result + ((this.cdLicenseType == null) ? 0 : this.cdLicenseType.hashCode());
    result = prime * result + ((this.cdWorkloads == null) ? 0 : this.cdWorkloads.hashCode());
    result = prime * result + ((this.cdServiceInstances == null) ? 0 : this.cdServiceInstances.hashCode());
    result = prime * result + ((this.ceSpendLimit == null) ? 0 : this.ceSpendLimit.hashCode());
    result = prime * result + ((this.cfNumberOfUsers == null) ? 0 : this.cfNumberOfUsers.hashCode());
    result = prime * result + ((this.cfNumberOfClientMaus == null) ? 0 : this.cfNumberOfClientMaus.hashCode());
    result = prime * result + ((this.ciNumberOfCommitters == null) ? 0 : this.ciNumberOfCommitters.hashCode());
    result = prime * result + ((this.ciCacheAllowance == null) ? 0 : this.ciCacheAllowance.hashCode());
    result = prime * result + ((this.ciHostingCredits == null) ? 0 : this.ciHostingCredits.hashCode());
    result = prime * result + ((this.srmNumberOfServices == null) ? 0 : this.srmNumberOfServices.hashCode());
    result = prime * result + ((this.stoNumberOfDevelopers == null) ? 0 : this.stoNumberOfDevelopers.hashCode());
    result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
    result = prime * result + ((this.createdByUuid == null) ? 0 : this.createdByUuid.hashCode());
    result = prime * result + ((this.createdByName == null) ? 0 : this.createdByName.hashCode());
    result = prime * result + ((this.createdByEmail == null) ? 0 : this.createdByEmail.hashCode());
    result = prime * result + ((this.createdByExternalUserId == null) ? 0 : this.createdByExternalUserId.hashCode());
    result = prime * result + ((this.lastUpdatedByUuid == null) ? 0 : this.lastUpdatedByUuid.hashCode());
    result = prime * result + ((this.lastUpdatedByName == null) ? 0 : this.lastUpdatedByName.hashCode());
    result = prime * result + ((this.lastUpdatedByEmail == null) ? 0 : this.lastUpdatedByEmail.hashCode());
    result =
        prime * result + ((this.lastUpdatedByExternalUserId == null) ? 0 : this.lastUpdatedByExternalUserId.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ModuleLicenses (");

    sb.append(id);
    sb.append(", ").append(accountIdentifier);
    sb.append(", ").append(moduleType);
    sb.append(", ").append(edition);
    sb.append(", ").append(licenseType);
    sb.append(", ").append(expiryTime);
    sb.append(", ").append(startTime);
    sb.append(", ").append(premiumSupport);
    sb.append(", ").append(trialExtended);
    sb.append(", ").append(selfService);
    sb.append(", ").append(createdAt);
    sb.append(", ").append(lastUpdatedAt);
    sb.append(", ").append(chaosTotalExperimentRuns);
    sb.append(", ").append(chaosTotalInfrastructures);
    sb.append(", ").append(cdLicenseType);
    sb.append(", ").append(cdWorkloads);
    sb.append(", ").append(cdServiceInstances);
    sb.append(", ").append(ceSpendLimit);
    sb.append(", ").append(cfNumberOfUsers);
    sb.append(", ").append(cfNumberOfClientMaus);
    sb.append(", ").append(ciNumberOfCommitters);
    sb.append(", ").append(ciCacheAllowance);
    sb.append(", ").append(ciHostingCredits);
    sb.append(", ").append(srmNumberOfServices);
    sb.append(", ").append(stoNumberOfDevelopers);
    sb.append(", ").append(status);
    sb.append(", ").append(createdByUuid);
    sb.append(", ").append(createdByName);
    sb.append(", ").append(createdByEmail);
    sb.append(", ").append(createdByExternalUserId);
    sb.append(", ").append(lastUpdatedByUuid);
    sb.append(", ").append(lastUpdatedByName);
    sb.append(", ").append(lastUpdatedByEmail);
    sb.append(", ").append(lastUpdatedByExternalUserId);

    sb.append(")");
    return sb.toString();
  }
}
