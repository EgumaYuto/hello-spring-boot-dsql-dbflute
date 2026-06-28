package org.example.dbflute.bsentity;

import java.util.List;
import java.util.ArrayList;

import org.dbflute.dbmeta.DBMeta;
import org.dbflute.dbmeta.AbstractEntity;
import org.dbflute.dbmeta.accessory.DomainEntity;
import org.example.dbflute.allcommon.DBMetaInstanceHandler;
import org.example.dbflute.allcommon.CDef;
import org.example.dbflute.exentity.*;

/**
 * The entity of cls_todo_status as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public abstract class BsClsTodoStatus extends AbstractEntity implements DomainEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    /** The serial version UID for object serialization. (Default) */
    private static final long serialVersionUID = 1L;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** code: {PK, NotNull, varchar(20), classification=TodoStatus} */
    protected String _code;

    /** name: {NotNull, varchar(50)} */
    protected String _name;

    /** disp_order: {NotNull, int4(10)} */
    protected Integer _dispOrder;

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    /** {@inheritDoc} */
    public DBMeta asDBMeta() {
        return DBMetaInstanceHandler.findDBMeta(asTableDbName());
    }

    /** {@inheritDoc} */
    public String asTableDbName() {
        return "cls_todo_status";
    }

    // ===================================================================================
    //                                                                        Key Handling
    //                                                                        ============
    /** {@inheritDoc} */
    public boolean hasPrimaryKeyValue() {
        if (_code == null) { return false; }
        return true;
    }

    // ===================================================================================
    //                                                             Classification Property
    //                                                             =======================
    /**
     * Get the value of code as the classification of TodoStatus. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * TODO status
     * <p>It's treated as case insensitive and if the code value is null, it returns null.</p>
     * @return The instance of classification definition (as ENUM type). (NullAllowed: when the column value is null)
     */
    public CDef.TodoStatus getCodeAsTodoStatus() {
        return CDef.TodoStatus.of(getCode()).orElse(null);
    }

    /**
     * Set the value of code as the classification of TodoStatus. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * TODO status
     * @param cdef The instance of classification definition (as ENUM type). (NullAllowed: if null, null value is set to the column)
     */
    public void setCodeAsTodoStatus(CDef.TodoStatus cdef) {
        setCode(cdef != null ? cdef.code() : null);
    }

    // ===================================================================================
    //                                                              Classification Setting
    //                                                              ======================
    /**
     * Set the value of code as ToDo (TODO). <br>
     * To Do
     */
    public void setCode_ToDo() {
        setCodeAsTodoStatus(CDef.TodoStatus.ToDo);
    }

    /**
     * Set the value of code as Doing (DOING). <br>
     * Doing
     */
    public void setCode_Doing() {
        setCodeAsTodoStatus(CDef.TodoStatus.Doing);
    }

    /**
     * Set the value of code as Done (DONE). <br>
     * Done
     */
    public void setCode_Done() {
        setCodeAsTodoStatus(CDef.TodoStatus.Done);
    }

    // ===================================================================================
    //                                                        Classification Determination
    //                                                        ============================
    /**
     * Is the value of code ToDo? <br>
     * To Do
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCodeToDo() {
        CDef.TodoStatus cdef = getCodeAsTodoStatus();
        return cdef != null ? cdef.equals(CDef.TodoStatus.ToDo) : false;
    }

    /**
     * Is the value of code Doing? <br>
     * Doing
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCodeDoing() {
        CDef.TodoStatus cdef = getCodeAsTodoStatus();
        return cdef != null ? cdef.equals(CDef.TodoStatus.Doing) : false;
    }

    /**
     * Is the value of code Done? <br>
     * Done
     * <p>It's treated as case insensitive and if the code value is null, it returns false.</p>
     * @return The determination, true or false.
     */
    public boolean isCodeDone() {
        CDef.TodoStatus cdef = getCodeAsTodoStatus();
        return cdef != null ? cdef.equals(CDef.TodoStatus.Done) : false;
    }

    // ===================================================================================
    //                                                                    Foreign Property
    //                                                                    ================
    // ===================================================================================
    //                                                                   Referrer Property
    //                                                                   =================
    /** todos by status, named 'todosList'. */
    protected List<Todos> _todosList;

    /**
     * [get] todos by status, named 'todosList'.
     * @return The entity list of referrer property 'todosList'. (NotNull: even if no loading, returns empty list)
     */
    public List<Todos> getTodosList() {
        if (_todosList == null) { _todosList = newReferrerList(); }
        return _todosList;
    }

    /**
     * [set] todos by status, named 'todosList'.
     * @param todosList The entity list of referrer property 'todosList'. (NullAllowed)
     */
    public void setTodosList(List<Todos> todosList) {
        _todosList = todosList;
    }

    protected <ELEMENT> List<ELEMENT> newReferrerList() { // overriding to import
        return new ArrayList<ELEMENT>();
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected boolean doEquals(Object obj) {
        if (obj instanceof BsClsTodoStatus) {
            BsClsTodoStatus other = (BsClsTodoStatus)obj;
            if (!xSV(_code, other._code)) { return false; }
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected int doHashCode(int initial) {
        int hs = initial;
        hs = xCH(hs, asTableDbName());
        hs = xCH(hs, _code);
        return hs;
    }

    @Override
    protected String doBuildStringWithRelation(String li) {
        StringBuilder sb = new StringBuilder();
        if (_todosList != null) { for (Todos et : _todosList)
        { if (et != null) { sb.append(li).append(xbRDS(et, "todosList")); } } }
        return sb.toString();
    }

    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(xfND(_code));
        sb.append(dm).append(xfND(_name));
        sb.append(dm).append(xfND(_dispOrder));
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    @Override
    protected String doBuildRelationString(String dm) {
        StringBuilder sb = new StringBuilder();
        if (_todosList != null && !_todosList.isEmpty())
        { sb.append(dm).append("todosList"); }
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length()).insert(0, "(").append(")");
        }
        return sb.toString();
    }

    @Override
    public ClsTodoStatus clone() {
        return (ClsTodoStatus)super.clone();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * [get] code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * @return The value of the column 'code'. (basically NotNull if selected: for the constraint)
     */
    public String getCode() {
        checkSpecifiedProperty("code");
        return _code;
    }

    /**
     * [set] code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * @param code The value of the column 'code'. (basically NotNull if update: for the constraint)
     */
    protected void setCode(String code) {
        checkClassificationCode("code", CDef.DefMeta.TodoStatus, code);
        registerModifiedProperty("code");
        _code = code;
    }

    /**
     * [get] name: {NotNull, varchar(50)} <br>
     * @return The value of the column 'name'. (basically NotNull if selected: for the constraint)
     */
    public String getName() {
        checkSpecifiedProperty("name");
        return _name;
    }

    /**
     * [set] name: {NotNull, varchar(50)} <br>
     * @param name The value of the column 'name'. (basically NotNull if update: for the constraint)
     */
    public void setName(String name) {
        registerModifiedProperty("name");
        _name = name;
    }

    /**
     * [get] disp_order: {NotNull, int4(10)} <br>
     * @return The value of the column 'disp_order'. (basically NotNull if selected: for the constraint)
     */
    public Integer getDispOrder() {
        checkSpecifiedProperty("dispOrder");
        return _dispOrder;
    }

    /**
     * [set] disp_order: {NotNull, int4(10)} <br>
     * @param dispOrder The value of the column 'disp_order'. (basically NotNull if update: for the constraint)
     */
    public void setDispOrder(Integer dispOrder) {
        registerModifiedProperty("dispOrder");
        _dispOrder = dispOrder;
    }

    /**
     * For framework so basically DON'T use this method.
     * @param code The value of the column 'code'. (basically NotNull if update: for the constraint)
     */
    public void mynativeMappingCode(String code) {
        setCode(code);
    }
}
