package org.example.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.example.dbflute.allcommon.DBMetaInstanceHandler;
import org.example.dbflute.exentity.*;

/**
 * The entity of todos as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsTodos extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]} */
    protected java.util.UUID _id;

    /** user_id: {NotNull, uuid(2147483647)} */
    protected java.util.UUID _userId;

    /** title: {NotNull, varchar(500)} */
    protected String _title;

    /** done: {NotNull, bool(1), default=[false]} */
    protected Boolean _done;

    /** created_at: {timestamptz(35, 6), default=[now()]} */
    protected java.time.LocalDateTime _createdAt;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "todos";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_id == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsTodos) {
            BsTodos other = (BsTodos)obj;
            if (!xSV(_id, other._id)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _id);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        return "";
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_id));
        sb.append(dm).append(xfND(_userId));
        sb.append(dm).append(xfND(_title));
        sb.append(dm).append(xfND(_done));
        sb.append(dm).append(xfND(_createdAt));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        return "";
    }

    @Override
    public Todos clone() {
        return (Todos)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]} <br>
     * @return The value of the column 'id'. (basically NotNull if selected: for the constraint)
     */
    public java.util.UUID getId() {
        checkSpecifiedProperty("id");
        return _id;
    }

    /**
     * [set] id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]} <br>
     * @param id The value of the column 'id'. (basically NotNull if update: for the constraint)
     */
    public void setId(java.util.UUID id) {
        registerModifiedProperty("id");
        _id = id;
    }

    /**
     * [get] user_id: {NotNull, uuid(2147483647)} <br>
     * @return The value of the column 'user_id'. (basically NotNull if selected: for the constraint)
     */
    public java.util.UUID getUserId() {
        checkSpecifiedProperty("userId");
        return _userId;
    }

    /**
     * [set] user_id: {NotNull, uuid(2147483647)} <br>
     * @param userId The value of the column 'user_id'. (basically NotNull if update: for the constraint)
     */
    public void setUserId(java.util.UUID userId) {
        registerModifiedProperty("userId");
        _userId = userId;
    }

    /**
     * [get] title: {NotNull, varchar(500)} <br>
     * @return The value of the column 'title'. (basically NotNull if selected: for the constraint)
     */
    public String getTitle() {
        checkSpecifiedProperty("title");
        return _title;
    }

    /**
     * [set] title: {NotNull, varchar(500)} <br>
     * @param title The value of the column 'title'. (basically NotNull if update: for the constraint)
     */
    public void setTitle(String title) {
        registerModifiedProperty("title");
        _title = title;
    }

    /**
     * [get] done: {NotNull, bool(1), default=[false]} <br>
     * @return The value of the column 'done'. (basically NotNull if selected: for the constraint)
     */
    public Boolean getDone() {
        checkSpecifiedProperty("done");
        return _done;
    }

    /**
     * [set] done: {NotNull, bool(1), default=[false]} <br>
     * @param done The value of the column 'done'. (basically NotNull if update: for the constraint)
     */
    public void setDone(Boolean done) {
        registerModifiedProperty("done");
        _done = done;
    }

    /**
     * [get] created_at: {timestamptz(35, 6), default=[now()]} <br>
     * @return The value of the column 'created_at'. (NullAllowed even if selected: for no constraint)
     */
    public java.time.LocalDateTime getCreatedAt() {
        checkSpecifiedProperty("createdAt");
        return _createdAt;
    }

    /**
     * [set] created_at: {timestamptz(35, 6), default=[now()]} <br>
     * @param createdAt The value of the column 'created_at'. (NullAllowed: null update allowed for no constraint)
     */
    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        registerModifiedProperty("createdAt");
        _createdAt = createdAt;
    }
}
