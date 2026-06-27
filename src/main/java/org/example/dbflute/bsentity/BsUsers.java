package org.example.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.example.dbflute.allcommon.DBMetaInstanceHandler;
import org.example.dbflute.exentity.*;

/**
 * The entity of users as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsUsers extends AbstractEntity implements DomainEntity {

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

    /** name: {NotNull, varchar(100)} */
    protected String _name;

    /** email: {NotNull, varchar(255)} */
    protected String _email;

    /** created_at: {timestamptz(35, 6), default=[now()]} */
    protected java.time.LocalDateTime _createdAt;

    /** password_hash: {varchar(255)} */
    protected String _passwordHash;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "users";
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
        if (obj instanceof BsUsers) {
            BsUsers other = (BsUsers)obj;
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
        sb.append(dm).append(xfND(_name));
        sb.append(dm).append(xfND(_email));
        sb.append(dm).append(xfND(_createdAt));
        sb.append(dm).append(xfND(_passwordHash));
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
    public Users clone() {
        return (Users)super.clone();
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
     * [get] name: {NotNull, varchar(100)} <br>
     * @return The value of the column 'name'. (basically NotNull if selected: for the constraint)
     */
    public String getName() {
        checkSpecifiedProperty("name");
        return _name;
    }

    /**
     * [set] name: {NotNull, varchar(100)} <br>
     * @param name The value of the column 'name'. (basically NotNull if update: for the constraint)
     */
    public void setName(String name) {
        registerModifiedProperty("name");
        _name = name;
    }

    /**
     * [get] email: {NotNull, varchar(255)} <br>
     * @return The value of the column 'email'. (basically NotNull if selected: for the constraint)
     */
    public String getEmail() {
        checkSpecifiedProperty("email");
        return _email;
    }

    /**
     * [set] email: {NotNull, varchar(255)} <br>
     * @param email The value of the column 'email'. (basically NotNull if update: for the constraint)
     */
    public void setEmail(String email) {
        registerModifiedProperty("email");
        _email = email;
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
     * [get] password_hash: {varchar(255)} <br>
     * @return The value of the column 'password_hash'. (NullAllowed even if selected: for no constraint)
     */
    public String getPasswordHash() {
        checkSpecifiedProperty("passwordHash");
        return _passwordHash;
    }

    /**
     * [set] password_hash: {varchar(255)} <br>
     * @param passwordHash The value of the column 'password_hash'. (NullAllowed: null update allowed for no constraint)
     */
    public void setPasswordHash(String passwordHash) {
        registerModifiedProperty("passwordHash");
        _passwordHash = passwordHash;
    }
}
