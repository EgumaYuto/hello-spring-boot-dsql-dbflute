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
 * The DB meta of todos. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class TodosDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final TodosDbm _instance = new TodosDbm();
    private TodosDbm() {}
    public static TodosDbm getInstance() { return _instance; }

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
        setupEpg(_epgMap, et -> ((Todos)et).getId(), (et, vl) -> ((Todos)et).setId((java.util.UUID)vl), "id");
        setupEpg(_epgMap, et -> ((Todos)et).getUserId(), (et, vl) -> ((Todos)et).setUserId((java.util.UUID)vl), "userId");
        setupEpg(_epgMap, et -> ((Todos)et).getTitle(), (et, vl) -> ((Todos)et).setTitle((String)vl), "title");
        setupEpg(_epgMap, et -> ((Todos)et).getDone(), (et, vl) -> ((Todos)et).setDone((Boolean)vl), "done");
        setupEpg(_epgMap, et -> ((Todos)et).getCreatedAt(), (et, vl) -> ((Todos)et).setCreatedAt(ctldt(vl)), "createdAt");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "todos";
    protected final String _tableDispName = "todos";
    protected final String _tablePropertyName = "todos";
    protected final TableSqlName _tableSqlName = new TableSqlName("todos", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnId = cci("id", "id", null, null, java.util.UUID.class, "id", null, true, false, true, "uuid", 2147483647, 0, null, "gen_random_uuid()", false, null, null, null, null, null, false);
    protected final ColumnInfo _columnUserId = cci("user_id", "user_id", null, null, java.util.UUID.class, "userId", null, false, false, true, "uuid", 2147483647, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnTitle = cci("title", "title", null, null, String.class, "title", null, false, false, true, "varchar", 500, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnDone = cci("done", "done", null, null, Boolean.class, "done", null, false, false, true, "bool", 1, 0, null, "false", false, null, null, null, null, null, false);
    protected final ColumnInfo _columnCreatedAt = cci("created_at", "created_at", null, null, java.time.LocalDateTime.class, "createdAt", null, false, false, false, "timestamptz", 35, 6, null, "now()", false, null, null, null, null, null, false);

    /**
     * id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnId() { return _columnId; }
    /**
     * user_id: {NotNull, uuid(2147483647)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnUserId() { return _columnUserId; }
    /**
     * title: {NotNull, varchar(500)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnTitle() { return _columnTitle; }
    /**
     * done: {NotNull, bool(1), default=[false]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnDone() { return _columnDone; }
    /**
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnCreatedAt() { return _columnCreatedAt; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnId());
        ls.add(columnUserId());
        ls.add(columnTitle());
        ls.add(columnDone());
        ls.add(columnCreatedAt());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() { return hpcpui(columnId()); }
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

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "org.example.dbflute.exentity.Todos"; }
    public String getConditionBeanTypeName() { return "org.example.dbflute.cbean.TodosCB"; }
    public String getBehaviorTypeName() { return "org.example.dbflute.exbhv.TodosBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<Todos> getEntityType() { return Todos.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public Todos newEntity() { return new Todos(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((Todos)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((Todos)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
