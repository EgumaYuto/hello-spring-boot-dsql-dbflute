package org.example.dbflute.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.dbflute.Entity;
import org.dbflute.dbmeta.AbstractDBMeta;
import org.dbflute.dbmeta.info.*;
import org.dbflute.dbmeta.name.*;
import org.dbflute.dbmeta.property.PropertyGateway;
import org.dbflute.dbway.DBDef;
import org.example.dbflute.allcommon.*;
import org.example.dbflute.exentity.*;

/**
 * The DB meta of cls_todo_status. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class ClsTodoStatusDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final ClsTodoStatusDbm _instance = new ClsTodoStatusDbm();
    private ClsTodoStatusDbm() {}
    public static ClsTodoStatusDbm getInstance() { return _instance; }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    public String getProjectName() { return DBCurrent.getInstance().projectName(); }
    public String getProjectPrefix() { return DBCurrent.getInstance().projectPrefix(); }
    public String getGenerationGapBasePrefix() { return DBCurrent.getInstance().generationGapBasePrefix(); }
    public DBDef getCurrentDBDef() { return DBCurrent.getInstance().currentDBDef(); }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    // -----------------------------------------------------
    //                                       Column Property
    //                                       ---------------
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    { xsetupEpg(); }
    protected void xsetupEpg() {
        setupEpg(_epgMap, et -> ((ClsTodoStatus)et).getCode(), (et, vl) -> {
            CDef.TodoStatus cls = (CDef.TodoStatus)gcls(et, columnCode(), vl);
            if (cls != null) {
                ((ClsTodoStatus)et).setCodeAsTodoStatus(cls);
            } else {
                ((ClsTodoStatus)et).mynativeMappingCode((String)vl);
            }
        }, "code");
        setupEpg(_epgMap, et -> ((ClsTodoStatus)et).getName(), (et, vl) -> ((ClsTodoStatus)et).setName((String)vl), "name");
        setupEpg(_epgMap, et -> ((ClsTodoStatus)et).getDispOrder(), (et, vl) -> ((ClsTodoStatus)et).setDispOrder(cti(vl)), "dispOrder");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "cls_todo_status";
    protected final String _tableDispName = "cls_todo_status";
    protected final String _tablePropertyName = "clsTodoStatus";
    protected final TableSqlName _tableSqlName = new TableSqlName("cls_todo_status", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnCode = cci("code", "code", null, null, String.class, "code", null, true, false, true, "varchar", 20, 0, null, null, false, null, null, null, "todosList", CDef.DefMeta.TodoStatus, false);
    protected final ColumnInfo _columnName = cci("name", "name", null, null, String.class, "name", null, false, false, true, "varchar", 50, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnDispOrder = cci("disp_order", "disp_order", null, null, Integer.class, "dispOrder", null, false, false, true, "int4", 10, 0, null, null, false, null, null, null, null, null, false);

    /**
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnCode() { return _columnCode; }
    /**
     * name: {NotNull, varchar(50)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnName() { return _columnName; }
    /**
     * disp_order: {NotNull, int4(10)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnDispOrder() { return _columnDispOrder; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnCode());
        ls.add(columnName());
        ls.add(columnDispOrder());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() { return hpcpui(columnCode()); }
    public boolean hasPrimaryKey() { return true; }
    public boolean hasCompoundPrimaryKey() { return false; }

    // ===================================================================================
    //                                                                       Relation Info
    //                                                                       =============
    // cannot cache because it uses related DB meta instance while booting
    // (instead, cached by super's collection)
    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------

    // -----------------------------------------------------
    //                                     Referrer Property
    //                                     -----------------
    /**
     * todos by status, named 'todosList'.
     * @return The information object of referrer property. (NotNull)
     */
    public ReferrerInfo referrerTodosList() {
        Map<ColumnInfo, ColumnInfo> mp = newLinkedHashMap(columnCode(), TodosDbm.getInstance().columnStatus());
        return cri("FK_TODOS_STATUS", "todosList", this, TodosDbm.getInstance(), mp, false, "clsTodoStatus");
    }

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "org.example.dbflute.exentity.ClsTodoStatus"; }
    public String getConditionBeanTypeName() { return "org.example.dbflute.cbean.ClsTodoStatusCB"; }
    public String getBehaviorTypeName() { return "org.example.dbflute.exbhv.ClsTodoStatusBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<ClsTodoStatus> getEntityType() { return ClsTodoStatus.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public ClsTodoStatus newEntity() { return new ClsTodoStatus(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((ClsTodoStatus)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((ClsTodoStatus)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
