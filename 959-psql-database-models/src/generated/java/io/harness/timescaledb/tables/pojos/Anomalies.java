/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

/*
 * This file is generated by jOOQ.
 */
package io.harness.timescaledb.tables.pojos;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Anomalies implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String accountid;
  private Double actualcost;
  private Double expectedcost;
  private OffsetDateTime anomalytime;
  private String timegranularity;
  private String note;
  private String clusterid;
  private String clustername;
  private String workloadname;
  private String workloadtype;
  private String namespace;
  private String region;
  private String gcpproduct;
  private String gcpskuid;
  private String gcpskudescription;
  private String gcpproject;
  private String awsservice;
  private String awsaccount;
  private String awsinstancetype;
  private String awsusagetype;
  private Double anomalyscore;
  private String reportedby;
  private String feedback;
  private Boolean slackdailynotification;
  private Boolean slackinstantnotification;
  private Boolean slackweeklynotification;
  private Boolean newentity;
  private String azuresubscriptionguid;
  private String azureresourcegroup;
  private String azuremetercategory;

  public Anomalies() {}

  public Anomalies(Anomalies value) {
    this.id = value.id;
    this.accountid = value.accountid;
    this.actualcost = value.actualcost;
    this.expectedcost = value.expectedcost;
    this.anomalytime = value.anomalytime;
    this.timegranularity = value.timegranularity;
    this.note = value.note;
    this.clusterid = value.clusterid;
    this.clustername = value.clustername;
    this.workloadname = value.workloadname;
    this.workloadtype = value.workloadtype;
    this.namespace = value.namespace;
    this.region = value.region;
    this.gcpproduct = value.gcpproduct;
    this.gcpskuid = value.gcpskuid;
    this.gcpskudescription = value.gcpskudescription;
    this.gcpproject = value.gcpproject;
    this.awsservice = value.awsservice;
    this.awsaccount = value.awsaccount;
    this.awsinstancetype = value.awsinstancetype;
    this.awsusagetype = value.awsusagetype;
    this.anomalyscore = value.anomalyscore;
    this.reportedby = value.reportedby;
    this.feedback = value.feedback;
    this.slackdailynotification = value.slackdailynotification;
    this.slackinstantnotification = value.slackinstantnotification;
    this.slackweeklynotification = value.slackweeklynotification;
    this.newentity = value.newentity;
    this.azuresubscriptionguid = value.azuresubscriptionguid;
    this.azureresourcegroup = value.azureresourcegroup;
    this.azuremetercategory = value.azuremetercategory;
  }

  public Anomalies(String id, String accountid, Double actualcost, Double expectedcost, OffsetDateTime anomalytime,
      String timegranularity, String note, String clusterid, String clustername, String workloadname,
      String workloadtype, String namespace, String region, String gcpproduct, String gcpskuid,
      String gcpskudescription, String gcpproject, String awsservice, String awsaccount, String awsinstancetype,
      String awsusagetype, Double anomalyscore, String reportedby, String feedback, Boolean slackdailynotification,
      Boolean slackinstantnotification, Boolean slackweeklynotification, Boolean newentity,
      String azuresubscriptionguid, String azureresourcegroup, String azuremetercategory) {
    this.id = id;
    this.accountid = accountid;
    this.actualcost = actualcost;
    this.expectedcost = expectedcost;
    this.anomalytime = anomalytime;
    this.timegranularity = timegranularity;
    this.note = note;
    this.clusterid = clusterid;
    this.clustername = clustername;
    this.workloadname = workloadname;
    this.workloadtype = workloadtype;
    this.namespace = namespace;
    this.region = region;
    this.gcpproduct = gcpproduct;
    this.gcpskuid = gcpskuid;
    this.gcpskudescription = gcpskudescription;
    this.gcpproject = gcpproject;
    this.awsservice = awsservice;
    this.awsaccount = awsaccount;
    this.awsinstancetype = awsinstancetype;
    this.awsusagetype = awsusagetype;
    this.anomalyscore = anomalyscore;
    this.reportedby = reportedby;
    this.feedback = feedback;
    this.slackdailynotification = slackdailynotification;
    this.slackinstantnotification = slackinstantnotification;
    this.slackweeklynotification = slackweeklynotification;
    this.newentity = newentity;
    this.azuresubscriptionguid = azuresubscriptionguid;
    this.azureresourcegroup = azureresourcegroup;
    this.azuremetercategory = azuremetercategory;
  }

  /**
   * Getter for <code>public.anomalies.id</code>.
   */
  public String getId() {
    return this.id;
  }

  /**
   * Setter for <code>public.anomalies.id</code>.
   */
  public Anomalies setId(String id) {
    this.id = id;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.accountid</code>.
   */
  public String getAccountid() {
    return this.accountid;
  }

  /**
   * Setter for <code>public.anomalies.accountid</code>.
   */
  public Anomalies setAccountid(String accountid) {
    this.accountid = accountid;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.actualcost</code>.
   */
  public Double getActualcost() {
    return this.actualcost;
  }

  /**
   * Setter for <code>public.anomalies.actualcost</code>.
   */
  public Anomalies setActualcost(Double actualcost) {
    this.actualcost = actualcost;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.expectedcost</code>.
   */
  public Double getExpectedcost() {
    return this.expectedcost;
  }

  /**
   * Setter for <code>public.anomalies.expectedcost</code>.
   */
  public Anomalies setExpectedcost(Double expectedcost) {
    this.expectedcost = expectedcost;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.anomalytime</code>.
   */
  public OffsetDateTime getAnomalytime() {
    return this.anomalytime;
  }

  /**
   * Setter for <code>public.anomalies.anomalytime</code>.
   */
  public Anomalies setAnomalytime(OffsetDateTime anomalytime) {
    this.anomalytime = anomalytime;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.timegranularity</code>.
   */
  public String getTimegranularity() {
    return this.timegranularity;
  }

  /**
   * Setter for <code>public.anomalies.timegranularity</code>.
   */
  public Anomalies setTimegranularity(String timegranularity) {
    this.timegranularity = timegranularity;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.note</code>.
   */
  public String getNote() {
    return this.note;
  }

  /**
   * Setter for <code>public.anomalies.note</code>.
   */
  public Anomalies setNote(String note) {
    this.note = note;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.clusterid</code>.
   */
  public String getClusterid() {
    return this.clusterid;
  }

  /**
   * Setter for <code>public.anomalies.clusterid</code>.
   */
  public Anomalies setClusterid(String clusterid) {
    this.clusterid = clusterid;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.clustername</code>.
   */
  public String getClustername() {
    return this.clustername;
  }

  /**
   * Setter for <code>public.anomalies.clustername</code>.
   */
  public Anomalies setClustername(String clustername) {
    this.clustername = clustername;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.workloadname</code>.
   */
  public String getWorkloadname() {
    return this.workloadname;
  }

  /**
   * Setter for <code>public.anomalies.workloadname</code>.
   */
  public Anomalies setWorkloadname(String workloadname) {
    this.workloadname = workloadname;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.workloadtype</code>.
   */
  public String getWorkloadtype() {
    return this.workloadtype;
  }

  /**
   * Setter for <code>public.anomalies.workloadtype</code>.
   */
  public Anomalies setWorkloadtype(String workloadtype) {
    this.workloadtype = workloadtype;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.namespace</code>.
   */
  public String getNamespace() {
    return this.namespace;
  }

  /**
   * Setter for <code>public.anomalies.namespace</code>.
   */
  public Anomalies setNamespace(String namespace) {
    this.namespace = namespace;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.region</code>.
   */
  public String getRegion() {
    return this.region;
  }

  /**
   * Setter for <code>public.anomalies.region</code>.
   */
  public Anomalies setRegion(String region) {
    this.region = region;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.gcpproduct</code>.
   */
  public String getGcpproduct() {
    return this.gcpproduct;
  }

  /**
   * Setter for <code>public.anomalies.gcpproduct</code>.
   */
  public Anomalies setGcpproduct(String gcpproduct) {
    this.gcpproduct = gcpproduct;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.gcpskuid</code>.
   */
  public String getGcpskuid() {
    return this.gcpskuid;
  }

  /**
   * Setter for <code>public.anomalies.gcpskuid</code>.
   */
  public Anomalies setGcpskuid(String gcpskuid) {
    this.gcpskuid = gcpskuid;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.gcpskudescription</code>.
   */
  public String getGcpskudescription() {
    return this.gcpskudescription;
  }

  /**
   * Setter for <code>public.anomalies.gcpskudescription</code>.
   */
  public Anomalies setGcpskudescription(String gcpskudescription) {
    this.gcpskudescription = gcpskudescription;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.gcpproject</code>.
   */
  public String getGcpproject() {
    return this.gcpproject;
  }

  /**
   * Setter for <code>public.anomalies.gcpproject</code>.
   */
  public Anomalies setGcpproject(String gcpproject) {
    this.gcpproject = gcpproject;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.awsservice</code>.
   */
  public String getAwsservice() {
    return this.awsservice;
  }

  /**
   * Setter for <code>public.anomalies.awsservice</code>.
   */
  public Anomalies setAwsservice(String awsservice) {
    this.awsservice = awsservice;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.awsaccount</code>.
   */
  public String getAwsaccount() {
    return this.awsaccount;
  }

  /**
   * Setter for <code>public.anomalies.awsaccount</code>.
   */
  public Anomalies setAwsaccount(String awsaccount) {
    this.awsaccount = awsaccount;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.awsinstancetype</code>.
   */
  public String getAwsinstancetype() {
    return this.awsinstancetype;
  }

  /**
   * Setter for <code>public.anomalies.awsinstancetype</code>.
   */
  public Anomalies setAwsinstancetype(String awsinstancetype) {
    this.awsinstancetype = awsinstancetype;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.awsusagetype</code>.
   */
  public String getAwsusagetype() {
    return this.awsusagetype;
  }

  /**
   * Setter for <code>public.anomalies.awsusagetype</code>.
   */
  public Anomalies setAwsusagetype(String awsusagetype) {
    this.awsusagetype = awsusagetype;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.anomalyscore</code>.
   */
  public Double getAnomalyscore() {
    return this.anomalyscore;
  }

  /**
   * Setter for <code>public.anomalies.anomalyscore</code>.
   */
  public Anomalies setAnomalyscore(Double anomalyscore) {
    this.anomalyscore = anomalyscore;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.reportedby</code>.
   */
  public String getReportedby() {
    return this.reportedby;
  }

  /**
   * Setter for <code>public.anomalies.reportedby</code>.
   */
  public Anomalies setReportedby(String reportedby) {
    this.reportedby = reportedby;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.feedback</code>.
   */
  public String getFeedback() {
    return this.feedback;
  }

  /**
   * Setter for <code>public.anomalies.feedback</code>.
   */
  public Anomalies setFeedback(String feedback) {
    this.feedback = feedback;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.slackdailynotification</code>.
   */
  public Boolean getSlackdailynotification() {
    return this.slackdailynotification;
  }

  /**
   * Setter for <code>public.anomalies.slackdailynotification</code>.
   */
  public Anomalies setSlackdailynotification(Boolean slackdailynotification) {
    this.slackdailynotification = slackdailynotification;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.slackinstantnotification</code>.
   */
  public Boolean getSlackinstantnotification() {
    return this.slackinstantnotification;
  }

  /**
   * Setter for <code>public.anomalies.slackinstantnotification</code>.
   */
  public Anomalies setSlackinstantnotification(Boolean slackinstantnotification) {
    this.slackinstantnotification = slackinstantnotification;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.slackweeklynotification</code>.
   */
  public Boolean getSlackweeklynotification() {
    return this.slackweeklynotification;
  }

  /**
   * Setter for <code>public.anomalies.slackweeklynotification</code>.
   */
  public Anomalies setSlackweeklynotification(Boolean slackweeklynotification) {
    this.slackweeklynotification = slackweeklynotification;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.newentity</code>.
   */
  public Boolean getNewentity() {
    return this.newentity;
  }

  /**
   * Setter for <code>public.anomalies.newentity</code>.
   */
  public Anomalies setNewentity(Boolean newentity) {
    this.newentity = newentity;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.azuresubscriptionguid</code>.
   */
  public String getAzuresubscriptionguid() {
    return this.azuresubscriptionguid;
  }

  /**
   * Setter for <code>public.anomalies.azuresubscriptionguid</code>.
   */
  public Anomalies setAzuresubscriptionguid(String azuresubscriptionguid) {
    this.azuresubscriptionguid = azuresubscriptionguid;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.azureresourcegroup</code>.
   */
  public String getAzureresourcegroup() {
    return this.azureresourcegroup;
  }

  /**
   * Setter for <code>public.anomalies.azureresourcegroup</code>.
   */
  public Anomalies setAzureresourcegroup(String azureresourcegroup) {
    this.azureresourcegroup = azureresourcegroup;
    return this;
  }

  /**
   * Getter for <code>public.anomalies.azuremetercategory</code>.
   */
  public String getAzuremetercategory() {
    return this.azuremetercategory;
  }

  /**
   * Setter for <code>public.anomalies.azuremetercategory</code>.
   */
  public Anomalies setAzuremetercategory(String azuremetercategory) {
    this.azuremetercategory = azuremetercategory;
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
    final Anomalies other = (Anomalies) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (accountid == null) {
      if (other.accountid != null)
        return false;
    } else if (!accountid.equals(other.accountid))
      return false;
    if (actualcost == null) {
      if (other.actualcost != null)
        return false;
    } else if (!actualcost.equals(other.actualcost))
      return false;
    if (expectedcost == null) {
      if (other.expectedcost != null)
        return false;
    } else if (!expectedcost.equals(other.expectedcost))
      return false;
    if (anomalytime == null) {
      if (other.anomalytime != null)
        return false;
    } else if (!anomalytime.equals(other.anomalytime))
      return false;
    if (timegranularity == null) {
      if (other.timegranularity != null)
        return false;
    } else if (!timegranularity.equals(other.timegranularity))
      return false;
    if (note == null) {
      if (other.note != null)
        return false;
    } else if (!note.equals(other.note))
      return false;
    if (clusterid == null) {
      if (other.clusterid != null)
        return false;
    } else if (!clusterid.equals(other.clusterid))
      return false;
    if (clustername == null) {
      if (other.clustername != null)
        return false;
    } else if (!clustername.equals(other.clustername))
      return false;
    if (workloadname == null) {
      if (other.workloadname != null)
        return false;
    } else if (!workloadname.equals(other.workloadname))
      return false;
    if (workloadtype == null) {
      if (other.workloadtype != null)
        return false;
    } else if (!workloadtype.equals(other.workloadtype))
      return false;
    if (namespace == null) {
      if (other.namespace != null)
        return false;
    } else if (!namespace.equals(other.namespace))
      return false;
    if (region == null) {
      if (other.region != null)
        return false;
    } else if (!region.equals(other.region))
      return false;
    if (gcpproduct == null) {
      if (other.gcpproduct != null)
        return false;
    } else if (!gcpproduct.equals(other.gcpproduct))
      return false;
    if (gcpskuid == null) {
      if (other.gcpskuid != null)
        return false;
    } else if (!gcpskuid.equals(other.gcpskuid))
      return false;
    if (gcpskudescription == null) {
      if (other.gcpskudescription != null)
        return false;
    } else if (!gcpskudescription.equals(other.gcpskudescription))
      return false;
    if (gcpproject == null) {
      if (other.gcpproject != null)
        return false;
    } else if (!gcpproject.equals(other.gcpproject))
      return false;
    if (awsservice == null) {
      if (other.awsservice != null)
        return false;
    } else if (!awsservice.equals(other.awsservice))
      return false;
    if (awsaccount == null) {
      if (other.awsaccount != null)
        return false;
    } else if (!awsaccount.equals(other.awsaccount))
      return false;
    if (awsinstancetype == null) {
      if (other.awsinstancetype != null)
        return false;
    } else if (!awsinstancetype.equals(other.awsinstancetype))
      return false;
    if (awsusagetype == null) {
      if (other.awsusagetype != null)
        return false;
    } else if (!awsusagetype.equals(other.awsusagetype))
      return false;
    if (anomalyscore == null) {
      if (other.anomalyscore != null)
        return false;
    } else if (!anomalyscore.equals(other.anomalyscore))
      return false;
    if (reportedby == null) {
      if (other.reportedby != null)
        return false;
    } else if (!reportedby.equals(other.reportedby))
      return false;
    if (feedback == null) {
      if (other.feedback != null)
        return false;
    } else if (!feedback.equals(other.feedback))
      return false;
    if (slackdailynotification == null) {
      if (other.slackdailynotification != null)
        return false;
    } else if (!slackdailynotification.equals(other.slackdailynotification))
      return false;
    if (slackinstantnotification == null) {
      if (other.slackinstantnotification != null)
        return false;
    } else if (!slackinstantnotification.equals(other.slackinstantnotification))
      return false;
    if (slackweeklynotification == null) {
      if (other.slackweeklynotification != null)
        return false;
    } else if (!slackweeklynotification.equals(other.slackweeklynotification))
      return false;
    if (newentity == null) {
      if (other.newentity != null)
        return false;
    } else if (!newentity.equals(other.newentity))
      return false;
    if (azuresubscriptionguid == null) {
      if (other.azuresubscriptionguid != null)
        return false;
    } else if (!azuresubscriptionguid.equals(other.azuresubscriptionguid))
      return false;
    if (azureresourcegroup == null) {
      if (other.azureresourcegroup != null)
        return false;
    } else if (!azureresourcegroup.equals(other.azureresourcegroup))
      return false;
    if (azuremetercategory == null) {
      if (other.azuremetercategory != null)
        return false;
    } else if (!azuremetercategory.equals(other.azuremetercategory))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
    result = prime * result + ((this.accountid == null) ? 0 : this.accountid.hashCode());
    result = prime * result + ((this.actualcost == null) ? 0 : this.actualcost.hashCode());
    result = prime * result + ((this.expectedcost == null) ? 0 : this.expectedcost.hashCode());
    result = prime * result + ((this.anomalytime == null) ? 0 : this.anomalytime.hashCode());
    result = prime * result + ((this.timegranularity == null) ? 0 : this.timegranularity.hashCode());
    result = prime * result + ((this.note == null) ? 0 : this.note.hashCode());
    result = prime * result + ((this.clusterid == null) ? 0 : this.clusterid.hashCode());
    result = prime * result + ((this.clustername == null) ? 0 : this.clustername.hashCode());
    result = prime * result + ((this.workloadname == null) ? 0 : this.workloadname.hashCode());
    result = prime * result + ((this.workloadtype == null) ? 0 : this.workloadtype.hashCode());
    result = prime * result + ((this.namespace == null) ? 0 : this.namespace.hashCode());
    result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
    result = prime * result + ((this.gcpproduct == null) ? 0 : this.gcpproduct.hashCode());
    result = prime * result + ((this.gcpskuid == null) ? 0 : this.gcpskuid.hashCode());
    result = prime * result + ((this.gcpskudescription == null) ? 0 : this.gcpskudescription.hashCode());
    result = prime * result + ((this.gcpproject == null) ? 0 : this.gcpproject.hashCode());
    result = prime * result + ((this.awsservice == null) ? 0 : this.awsservice.hashCode());
    result = prime * result + ((this.awsaccount == null) ? 0 : this.awsaccount.hashCode());
    result = prime * result + ((this.awsinstancetype == null) ? 0 : this.awsinstancetype.hashCode());
    result = prime * result + ((this.awsusagetype == null) ? 0 : this.awsusagetype.hashCode());
    result = prime * result + ((this.anomalyscore == null) ? 0 : this.anomalyscore.hashCode());
    result = prime * result + ((this.reportedby == null) ? 0 : this.reportedby.hashCode());
    result = prime * result + ((this.feedback == null) ? 0 : this.feedback.hashCode());
    result = prime * result + ((this.slackdailynotification == null) ? 0 : this.slackdailynotification.hashCode());
    result = prime * result + ((this.slackinstantnotification == null) ? 0 : this.slackinstantnotification.hashCode());
    result = prime * result + ((this.slackweeklynotification == null) ? 0 : this.slackweeklynotification.hashCode());
    result = prime * result + ((this.newentity == null) ? 0 : this.newentity.hashCode());
    result = prime * result + ((this.azuresubscriptionguid == null) ? 0 : this.azuresubscriptionguid.hashCode());
    result = prime * result + ((this.azureresourcegroup == null) ? 0 : this.azureresourcegroup.hashCode());
    result = prime * result + ((this.azuremetercategory == null) ? 0 : this.azuremetercategory.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Anomalies (");

    sb.append(id);
    sb.append(", ").append(accountid);
    sb.append(", ").append(actualcost);
    sb.append(", ").append(expectedcost);
    sb.append(", ").append(anomalytime);
    sb.append(", ").append(timegranularity);
    sb.append(", ").append(note);
    sb.append(", ").append(clusterid);
    sb.append(", ").append(clustername);
    sb.append(", ").append(workloadname);
    sb.append(", ").append(workloadtype);
    sb.append(", ").append(namespace);
    sb.append(", ").append(region);
    sb.append(", ").append(gcpproduct);
    sb.append(", ").append(gcpskuid);
    sb.append(", ").append(gcpskudescription);
    sb.append(", ").append(gcpproject);
    sb.append(", ").append(awsservice);
    sb.append(", ").append(awsaccount);
    sb.append(", ").append(awsinstancetype);
    sb.append(", ").append(awsusagetype);
    sb.append(", ").append(anomalyscore);
    sb.append(", ").append(reportedby);
    sb.append(", ").append(feedback);
    sb.append(", ").append(slackdailynotification);
    sb.append(", ").append(slackinstantnotification);
    sb.append(", ").append(slackweeklynotification);
    sb.append(", ").append(newentity);
    sb.append(", ").append(azuresubscriptionguid);
    sb.append(", ").append(azureresourcegroup);
    sb.append(", ").append(azuremetercategory);

    sb.append(")");
    return sb.toString();
  }
}
