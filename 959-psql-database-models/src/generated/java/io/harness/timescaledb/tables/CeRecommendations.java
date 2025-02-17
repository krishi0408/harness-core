/*
 * Copyright 2021 Harness Inc. All rights reserved.
 * Use of this source code is governed by the PolyForm Shield 1.0.0 license
 * that can be found in the licenses directory at the root of this repository, also available at
 * https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt.
 */

/*
 * This file is generated by jOOQ.
 */
package io.harness.timescaledb.tables;

import io.harness.timescaledb.Keys;
import io.harness.timescaledb.Public;
import io.harness.timescaledb.tables.records.CeRecommendationsRecord;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class CeRecommendations extends TableImpl<CeRecommendationsRecord> {
  private static final long serialVersionUID = 1L;

  /**
   * The reference instance of <code>public.ce_recommendations</code>
   */
  public static final CeRecommendations CE_RECOMMENDATIONS = new CeRecommendations();

  /**
   * The class holding records for this type
   */
  @Override
  public Class<CeRecommendationsRecord> getRecordType() {
    return CeRecommendationsRecord.class;
  }

  /**
   * The column <code>public.ce_recommendations.id</code>.
   */
  public final TableField<CeRecommendationsRecord, String> ID =
      createField(DSL.name("id"), SQLDataType.CLOB.nullable(false), this, "");

  /**
   * The column <code>public.ce_recommendations.name</code>.
   */
  public final TableField<CeRecommendationsRecord, String> NAME =
      createField(DSL.name("name"), SQLDataType.CLOB, this, "");

  /**
   * The column <code>public.ce_recommendations.namespace</code>.
   */
  public final TableField<CeRecommendationsRecord, String> NAMESPACE =
      createField(DSL.name("namespace"), SQLDataType.CLOB, this, "");

  /**
   * The column <code>public.ce_recommendations.monthlycost</code>.
   */
  public final TableField<CeRecommendationsRecord, Double> MONTHLYCOST =
      createField(DSL.name("monthlycost"), SQLDataType.DOUBLE, this, "");

  /**
   * The column <code>public.ce_recommendations.monthlysaving</code>.
   */
  public final TableField<CeRecommendationsRecord, Double> MONTHLYSAVING =
      createField(DSL.name("monthlysaving"), SQLDataType.DOUBLE, this, "");

  /**
   * The column <code>public.ce_recommendations.clustername</code>.
   */
  public final TableField<CeRecommendationsRecord, String> CLUSTERNAME =
      createField(DSL.name("clustername"), SQLDataType.CLOB, this, "");

  /**
   * The column <code>public.ce_recommendations.resourcetype</code>.
   */
  public final TableField<CeRecommendationsRecord, String> RESOURCETYPE =
      createField(DSL.name("resourcetype"), SQLDataType.CLOB.nullable(false), this, "");

  /**
   * The column <code>public.ce_recommendations.accountid</code>.
   */
  public final TableField<CeRecommendationsRecord, String> ACCOUNTID =
      createField(DSL.name("accountid"), SQLDataType.CLOB.nullable(false), this, "");

  /**
   * The column <code>public.ce_recommendations.isvalid</code>.
   */
  public final TableField<CeRecommendationsRecord, Boolean> ISVALID =
      createField(DSL.name("isvalid"), SQLDataType.BOOLEAN, this, "");

  /**
   * The column <code>public.ce_recommendations.lastprocessedat</code>.
   */
  public final TableField<CeRecommendationsRecord, OffsetDateTime> LASTPROCESSEDAT =
      createField(DSL.name("lastprocessedat"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

  /**
   * The column <code>public.ce_recommendations.updatedat</code>.
   */
  public final TableField<CeRecommendationsRecord, OffsetDateTime> UPDATEDAT = createField(DSL.name("updatedat"),
      SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(
          DSL.field("now()", SQLDataType.TIMESTAMPWITHTIMEZONE)),
      this, "");

  /**
   * The column <code>public.ce_recommendations.jiraconnectorref</code>.
   */
  public final TableField<CeRecommendationsRecord, String> JIRACONNECTORREF =
      createField(DSL.name("jiraconnectorref"), SQLDataType.CLOB, this, "");

  /**
   * The column <code>public.ce_recommendations.jiraissuekey</code>.
   */
  public final TableField<CeRecommendationsRecord, String> JIRAISSUEKEY =
      createField(DSL.name("jiraissuekey"), SQLDataType.CLOB, this, "");

  /**
   * The column <code>public.ce_recommendations.jirastatus</code>.
   */
  public final TableField<CeRecommendationsRecord, String> JIRASTATUS =
      createField(DSL.name("jirastatus"), SQLDataType.CLOB, this, "");

  /**
   * The column <code>public.ce_recommendations.recommendationstate</code>.
   */
  public final TableField<CeRecommendationsRecord, String> RECOMMENDATIONSTATE =
      createField(DSL.name("recommendationstate"), SQLDataType.CLOB, this, "");

  private CeRecommendations(Name alias, Table<CeRecommendationsRecord> aliased) {
    this(alias, aliased, null);
  }

  private CeRecommendations(Name alias, Table<CeRecommendationsRecord> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /**
   * Create an aliased <code>public.ce_recommendations</code> table reference
   */
  public CeRecommendations(String alias) {
    this(DSL.name(alias), CE_RECOMMENDATIONS);
  }

  /**
   * Create an aliased <code>public.ce_recommendations</code> table reference
   */
  public CeRecommendations(Name alias) {
    this(alias, CE_RECOMMENDATIONS);
  }

  /**
   * Create a <code>public.ce_recommendations</code> table reference
   */
  public CeRecommendations() {
    this(DSL.name("ce_recommendations"), null);
  }

  public <O extends Record> CeRecommendations(Table<O> child, ForeignKey<O, CeRecommendationsRecord> key) {
    super(child, key, CE_RECOMMENDATIONS);
  }

  @Override
  public Schema getSchema() {
    return Public.PUBLIC;
  }

  @Override
  public UniqueKey<CeRecommendationsRecord> getPrimaryKey() {
    return Keys.CE_RECOMMENDATIONS_PKEY;
  }

  @Override
  public List<UniqueKey<CeRecommendationsRecord>> getKeys() {
    return Arrays.<UniqueKey<CeRecommendationsRecord>>asList(Keys.CE_RECOMMENDATIONS_PKEY);
  }

  @Override
  public CeRecommendations as(String alias) {
    return new CeRecommendations(DSL.name(alias), this);
  }

  @Override
  public CeRecommendations as(Name alias) {
    return new CeRecommendations(alias, this);
  }

  /**
   * Rename this table
   */
  @Override
  public CeRecommendations rename(String name) {
    return new CeRecommendations(DSL.name(name), null);
  }

  /**
   * Rename this table
   */
  @Override
  public CeRecommendations rename(Name name) {
    return new CeRecommendations(name, null);
  }

  // -------------------------------------------------------------------------
  // Row11 type methods
  // -------------------------------------------------------------------------

  @Override
  public Row15<String, String, String, Double, Double, String, String, String, Boolean, OffsetDateTime, OffsetDateTime,
      String, String, String, String>
  fieldsRow() {
    return (Row15) super.fieldsRow();
  }
}
