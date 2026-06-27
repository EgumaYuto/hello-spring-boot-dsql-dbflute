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
 * The DB meta of users. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class UsersDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final UsersDbm _instance = new UsersDbm();
    private UsersDbm() {}
    public static UsersDbm getInstance() { return _instance; }

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
        setupEpg(_epgMap, et -> ((Users)et).getId(), (et, vl) -> ((Users)et).setId((java.util.UUID)vl), "id");
        setupEpg(_epgMap, et -> ((Users)et).getName(), (et, vl) -> ((Users)et).setName((String)vl), "name");
        setupEpg(_epgMap, et -> ((Users)et).getEmail(), (et, vl) -> ((Users)et).setEmail((String)vl), "email");
        setupEpg(_epgMap, et -> ((Users)et).getCreatedAt(), (et, vl) -> ((Users)et).setCreatedAt(ctldt(vl)), "createdAt");
        setupEpg(_epgMap, et -> ((Users)et).getPasswordHash(), (et, vl) -> ((Users)et).setPasswordHash((String)vl), "passwordHash");
    }
    public PropertyGateway findPropertyGateway(String prop)
    { return doFindEpg(_epgMap, prop); }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "users";
    protected final String _tableDispName = "users";
    protected final String _tablePropertyName = "users";
    protected final TableSqlName _tableSqlName = new TableSqlName("users", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTableDispName() { return _tableDispName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnId = cci("id", "id", null, null, java.util.UUID.class, "id", null, true, false, true, "uuid", 2147483647, 0, null, "gen_random_uuid()", false, null, null, null, null, null, false);
    protected final ColumnInfo _columnName = cci("name", "name", null, null, String.class, "name", null, false, false, true, "varchar", 100, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnEmail = cci("email", "email", null, null, String.class, "email", null, false, false, true, "varchar", 255, 0, null, null, false, null, null, null, null, null, false);
    protected final ColumnInfo _columnCreatedAt = cci("created_at", "created_at", null, null, java.time.LocalDateTime.class, "createdAt", null, false, false, false, "timestamptz", 35, 6, null, "now()", false, null, null, null, null, null, false);
    protected final ColumnInfo _columnPasswordHash = cci("password_hash", "password_hash", null, null, String.class, "passwordHash", null, false, false, false, "varchar", 255, 0, null, null, false, null, null, null, null, null, false);

    /**
     * id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnId() { return _columnId; }
    /**
     * name: {NotNull, varchar(100)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnName() { return _columnName; }
    /**
     * email: {NotNull, varchar(255)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnEmail() { return _columnEmail; }
    /**
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnCreatedAt() { return _columnCreatedAt; }
    /**
     * password_hash: {varchar(255)}
     * @return The information object of specified column. (NotNull)
     */
    public ColumnInfo columnPasswordHash() { return _columnPasswordHash; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnId());
        ls.add(columnName());
        ls.add(columnEmail());
        ls.add(columnCreatedAt());
        ls.add(columnPasswordHash());
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
    public String getEntityTypeName() { return "org.example.dbflute.exentity.Users"; }
    public String getConditionBeanTypeName() { return "org.example.dbflute.cbean.UsersCB"; }
    public String getBehaviorTypeName() { return "org.example.dbflute.exbhv.UsersBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<Users> getEntityType() { return Users.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public Users newEntity() { return new Users(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptPrimaryKeyMap((Users)et, mp); }
    public void acceptAllColumnMap(Entity et, Map<String, ? extends Object> mp)
    { doAcceptAllColumnMap((Users)et, mp); }
    public Map<String, Object> extractPrimaryKeyMap(Entity et) { return doExtractPrimaryKeyMap(et); }
    public Map<String, Object> extractAllColumnMap(Entity et) { return doExtractAllColumnMap(et); }
}
