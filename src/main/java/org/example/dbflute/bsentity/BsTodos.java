package org.example.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.Entity;
import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.dbflute.optional.OptionalEntity;
import org.example.dbflute.allcommon.DBMetaInstanceHandler;
import org.example.dbflute.allcommon.CDef;
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

    /** status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} */
    protected String _status;

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
    //                                                             Classification Property
    //                                                             =======================
    /**
     * Get the value of status as the classification of TodoStatus. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * TODO status
     * <p>It's treated as case insensitive and if the code value is null, it returns null.</p>
     * @return The instance of classification definition (as ENUM type). (NullAllowed: when the column value is null)
     */
    public CDef.TodoStatus getStatusAsTodoStatus() {
        return CDef.TodoStatus.of(getStatus()).orElse(null);
    }

    /**
     * Set the value of status as the classification of TodoStatus. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * TODO status
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the column)
     */
    public void setStatusAsTodoStatus(CDef.TodoStatus cdef) {
        setStatus(cdef != null ? cdef.code() : null);
    }

    // ===================================================================================
    //                                                              Classification Setting
    //                                                              ======================
    /**
     * Set the value of status as ToDo (TODO). <br>
     * To Do
     */
    public void setStatus_ToDo() {
        setStatusAsTodoStatus(CDef.TodoStatus.ToDo);
    }

    /**
     * Set the value of status as Doing (DOING). <br>
     * Doing
     */
    public void setStatus_Doing() {
        setStatusAsTodoStatus(CDef.TodoStatus.Doing);
    }

    /**
     * Set the value of status as Done (DONE). <br>
     * Done
     */
    public void setStatus_Done() {
        setStatusAsTodoStatus(CDef.TodoStatus.Done);
    }

    // ===================================================================================
    //                                                        Classification Determination
    //                                                        ============================
    /**
     * Is the value of status ToDo? <br>
     * To Do
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isStatusToDo() {
        CDef.TodoStatus cdef = getStatusAsTodoStatus();
        return cdef != null ? cdef.equals(CDef.TodoStatus.ToDo) : false;
    }

    /**
     * Is the value of status Doing? <br>
     * Doing
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isStatusDoing() {
        CDef.TodoStatus cdef = getStatusAsTodoStatus();
        return cdef != null ? cdef.equals(CDef.TodoStatus.Doing) : false;
    }

    /**
     * Is the value of status Done? <br>
     * Done
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isStatusDone() {
        CDef.TodoStatus cdef = getStatusAsTodoStatus();
        return cdef != null ? cdef.equals(CDef.TodoStatus.Done) : false;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    /** cls_todo_status by my status, named 'clsTodoStatus'. */
    protected OptionalEntity<ClsTodoStatus> _clsTodoStatus;

    /**
     * [get] cls_todo_status by my status, named 'clsTodoStatus'. <br>
     * Optional: alwaysPresent(), ifPresent().orElse(), get(), ...
     * @return The entity of foreign property 'clsTodoStatus'. (NotNull, EmptyAllowed: when e.g. null FK column, no setupSelect)
     */
    public OptionalEntity<ClsTodoStatus> getClsTodoStatus() {
        if (_clsTodoStatus == null) { _clsTodoStatus = OptionalEntity.relationEmpty(this, "clsTodoStatus"); }
        return _clsTodoStatus;
    }

    /**
     * [set] cls_todo_status by my status, named 'clsTodoStatus'.
     * @param clsTodoStatus The entity of foreign property 'clsTodoStatus'. (NullAllowed)
     */
    public void setClsTodoStatus(OptionalEntity<ClsTodoStatus> clsTodoStatus) {
        _clsTodoStatus = clsTodoStatus;
    }

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
        StringBuilder sb = new StringBuilder();
        if (_clsTodoStatus != null && _clsTodoStatus.isPresent())
        { sb.append(li).append(xbRDS(_clsTodoStatus, "clsTodoStatus")); }
        return sb.toString();
    }
    protected <ET extends Entity> String xbRDS(org.dbflute.optional.OptionalEntity<ET> et, String name) { // buildRelationDisplayString()
        return et.get().buildDisplayString(name, true, true);
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_id));
        sb.append(dm).append(xfND(_userId));
        sb.append(dm).append(xfND(_title));
        sb.append(dm).append(xfND(_status));
        sb.append(dm).append(xfND(_createdAt));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_clsTodoStatus != null && _clsTodoStatus.isPresent())
        { sb.append(dm).append("clsTodoStatus"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
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
     * shalias:{}<br>
     * 絡むコメント
     * @return The value of the column 'id'. (basically NotNull if selected: for the constraint)
     */
    public java.util.UUID getId() {
        checkSpecifiedProperty("id");
        return _id;
    }

    /**
     * [set] id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]} <br>
     * shalias:{}<br>
     * 絡むコメント
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
     * [get] status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * @return The value of the column 'status'. (basically NotNull if selected: for the constraint)
     */
    public String getStatus() {
        checkSpecifiedProperty("status");
        return _status;
    }

    /**
     * [set] status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * @param status The value of the column 'status'. (basically NotNull if update: for the constraint)
     */
    protected void setStatus(String status) {
        checkClassificationCode("status", CDef.DefMeta.TodoStatus, status);
        registerModifiedProperty("status");
        _status = status;
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

    /**
     * For framework so basically DON'T use this method.
     * @param status The value of the column 'status'. (basically NotNull if update: for the constraint)
     */
    public void mynativeMappingStatus(String status) {
        setStatus(status);
    }
}
