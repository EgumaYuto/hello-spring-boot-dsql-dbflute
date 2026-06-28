package org.example.dbflute.cbean.cq.bs;

import java.util.*;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.ckey.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.ordering.*;
import org.dbflute.cbean.scoping.*;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.dbmeta.DBMetaProvider;
import org.example.dbflute.allcommon.*;
import org.example.dbflute.cbean.*;
import org.example.dbflute.cbean.cq.*;

/**
 * The abstract condition-query of todos.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsTodosCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsTodosCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    public String asTableDbName() {
        return "todos";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]}
     * @param id The value of id as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setId_Equal(java.util.UUID id) {
        regId(CK_EQ, id);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]}
     * @param idList The collection of id as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setId_InScope(Collection<java.util.UUID> idList) {
        regINS(CK_INS, cTL(idList), xgetCValueId(), "id");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]}
     */
    public void setId_IsNull() { regId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * id: {PK, NotNull, uuid(2147483647), default=[gen_random_uuid()]}
     */
    public void setId_IsNotNull() { regId(CK_ISNN, DOBJ); }

    protected void regId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueId(), "id"); }
    protected abstract ConditionValue xgetCValueId();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * user_id: {NotNull, uuid(2147483647)}
     * @param userId The value of userId as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setUserId_Equal(java.util.UUID userId) {
        regUserId(CK_EQ, userId);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * user_id: {NotNull, uuid(2147483647)}
     * @param userIdList The collection of userId as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setUserId_InScope(Collection<java.util.UUID> userIdList) {
        regINS(CK_INS, cTL(userIdList), xgetCValueUserId(), "user_id");
    }

    protected void regUserId(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueUserId(), "user_id"); }
    protected abstract ConditionValue xgetCValueUserId();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_Equal(String title) {
        doSetTitle_Equal(fRES(title));
    }

    protected void doSetTitle_Equal(String title) {
        regTitle(CK_EQ, title);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_NotEqual(String title) {
        doSetTitle_NotEqual(fRES(title));
    }

    protected void doSetTitle_NotEqual(String title) {
        regTitle(CK_NES, title);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_GreaterThan(String title) {
        regTitle(CK_GT, fRES(title));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_LessThan(String title) {
        regTitle(CK_LT, fRES(title));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_GreaterEqual(String title) {
        regTitle(CK_GE, fRES(title));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_LessEqual(String title) {
        regTitle(CK_LE, fRES(title));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param titleList The collection of title as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_InScope(Collection<String> titleList) {
        doSetTitle_InScope(titleList);
    }

    protected void doSetTitle_InScope(Collection<String> titleList) {
        regINS(CK_INS, cTL(titleList), xgetCValueTitle(), "title");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param titleList The collection of title as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setTitle_NotInScope(Collection<String> titleList) {
        doSetTitle_NotInScope(titleList);
    }

    protected void doSetTitle_NotInScope(Collection<String> titleList) {
        regINS(CK_NINS, cTL(titleList), xgetCValueTitle(), "title");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * title: {NotNull, varchar(500)} <br>
     * <pre>e.g. setTitle_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param title The value of title as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setTitle_LikeSearch(String title, ConditionOptionCall<LikeSearchOption> opLambda) {
        setTitle_LikeSearch(title, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * title: {NotNull, varchar(500)} <br>
     * <pre>e.g. setTitle_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param title The value of title as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setTitle_LikeSearch(String title, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(title), xgetCValueTitle(), "title", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setTitle_NotLikeSearch(String title, ConditionOptionCall<LikeSearchOption> opLambda) {
        setTitle_NotLikeSearch(title, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * title: {NotNull, varchar(500)}
     * @param title The value of title as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setTitle_NotLikeSearch(String title, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(title), xgetCValueTitle(), "title", likeSearchOption);
    }

    protected void regTitle(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueTitle(), "title"); }
    protected abstract ConditionValue xgetCValueTitle();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus}
     * @param status The value of status as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setStatus_Equal(String status) {
        doSetStatus_Equal(fRES(status));
    }

    /**
     * Equal(=). As TodoStatus. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * TODO status
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setStatus_Equal_AsTodoStatus(CDef.TodoStatus cdef) {
        doSetStatus_Equal(cdef != null ? cdef.code() : null);
    }

    /**
     * Equal(=). As ToDo (TODO). And OnlyOnceRegistered. <br>
     * To Do
     */
    public void setStatus_Equal_ToDo() {
        setStatus_Equal_AsTodoStatus(CDef.TodoStatus.ToDo);
    }

    /**
     * Equal(=). As Doing (DOING). And OnlyOnceRegistered. <br>
     * Doing
     */
    public void setStatus_Equal_Doing() {
        setStatus_Equal_AsTodoStatus(CDef.TodoStatus.Doing);
    }

    /**
     * Equal(=). As Done (DONE). And OnlyOnceRegistered. <br>
     * Done
     */
    public void setStatus_Equal_Done() {
        setStatus_Equal_AsTodoStatus(CDef.TodoStatus.Done);
    }

    protected void doSetStatus_Equal(String status) {
        regStatus(CK_EQ, status);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus}
     * @param status The value of status as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setStatus_NotEqual(String status) {
        doSetStatus_NotEqual(fRES(status));
    }

    /**
     * NotEqual(&lt;&gt;). As TodoStatus. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * TODO status
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setStatus_NotEqual_AsTodoStatus(CDef.TodoStatus cdef) {
        doSetStatus_NotEqual(cdef != null ? cdef.code() : null);
    }

    /**
     * NotEqual(&lt;&gt;). As ToDo (TODO). And OnlyOnceRegistered. <br>
     * To Do
     */
    public void setStatus_NotEqual_ToDo() {
        setStatus_NotEqual_AsTodoStatus(CDef.TodoStatus.ToDo);
    }

    /**
     * NotEqual(&lt;&gt;). As Doing (DOING). And OnlyOnceRegistered. <br>
     * Doing
     */
    public void setStatus_NotEqual_Doing() {
        setStatus_NotEqual_AsTodoStatus(CDef.TodoStatus.Doing);
    }

    /**
     * NotEqual(&lt;&gt;). As Done (DONE). And OnlyOnceRegistered. <br>
     * Done
     */
    public void setStatus_NotEqual_Done() {
        setStatus_NotEqual_AsTodoStatus(CDef.TodoStatus.Done);
    }

    protected void doSetStatus_NotEqual(String status) {
        regStatus(CK_NES, status);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus}
     * @param statusList The collection of status as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setStatus_InScope(Collection<String> statusList) {
        doSetStatus_InScope(statusList);
    }

    /**
     * InScope {in ('a', 'b')}. As TodoStatus. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * TODO status
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStatus_InScope_AsTodoStatus(Collection<CDef.TodoStatus> cdefList) {
        doSetStatus_InScope(cTStrL(cdefList));
    }

    protected void doSetStatus_InScope(Collection<String> statusList) {
        regINS(CK_INS, cTL(statusList), xgetCValueStatus(), "status");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus}
     * @param statusList The collection of status as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setStatus_NotInScope(Collection<String> statusList) {
        doSetStatus_NotInScope(statusList);
    }

    /**
     * NotInScope {not in ('a', 'b')}. As TodoStatus. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * status: {NotNull, varchar(20), default=['TODO'::character varying], FK to cls_todo_status, classification=TodoStatus} <br>
     * TODO status
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setStatus_NotInScope_AsTodoStatus(Collection<CDef.TodoStatus> cdefList) {
        doSetStatus_NotInScope(cTStrL(cdefList));
    }

    protected void doSetStatus_NotInScope(Collection<String> statusList) {
        regINS(CK_NINS, cTL(statusList), xgetCValueStatus(), "status");
    }

    protected void regStatus(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueStatus(), "status"); }
    protected abstract ConditionValue xgetCValueStatus();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAt The value of createdAt as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setCreatedAt_Equal(java.time.LocalDateTime createdAt) {
        regCreatedAt(CK_EQ,  createdAt);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAt The value of createdAt as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setCreatedAt_NotEqual(java.time.LocalDateTime createdAt) {
        regCreatedAt(CK_NES,  createdAt);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAt The value of createdAt as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setCreatedAt_GreaterThan(java.time.LocalDateTime createdAt) {
        regCreatedAt(CK_GT,  createdAt);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAt The value of createdAt as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setCreatedAt_LessThan(java.time.LocalDateTime createdAt) {
        regCreatedAt(CK_LT,  createdAt);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAt The value of createdAt as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setCreatedAt_GreaterEqual(java.time.LocalDateTime createdAt) {
        regCreatedAt(CK_GE,  createdAt);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAt The value of createdAt as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setCreatedAt_LessEqual(java.time.LocalDateTime createdAt) {
        regCreatedAt(CK_LE, createdAt);
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * <pre>e.g. setCreatedAt_FromTo(fromDate, toDate, op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">compareAsDate()</span>);</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of createdAt. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of createdAt. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of from-to. (NotNull)
     */
    public void setCreatedAt_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, ConditionOptionCall<FromToOption> opLambda) {
        setCreatedAt_FromTo(fromDatetime, toDatetime, xcFTOP(opLambda));
    }

    /**
     * FromTo with various options. (versatile) {(default) fromDatetime &lt;= column &lt;= toDatetime} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * <pre>e.g. setCreatedAt_FromTo(fromDate, toDate, new <span style="color: #CC4747">FromToOption</span>().compareAsDate());</pre>
     * @param fromDatetime The from-datetime(yyyy/MM/dd HH:mm:ss.SSS) of createdAt. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param toDatetime The to-datetime(yyyy/MM/dd HH:mm:ss.SSS) of createdAt. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param fromToOption The option of from-to. (NotNull)
     */
    protected void setCreatedAt_FromTo(java.time.LocalDateTime fromDatetime, java.time.LocalDateTime toDatetime, FromToOption fromToOption) {
        String nm = "created_at"; FromToOption op = fromToOption;
        regFTQ(xfFTHD(fromDatetime, nm, op), xfFTHD(toDatetime, nm, op), xgetCValueCreatedAt(), nm, op);
    }

    /**
     * InScope {in ('1965-03-03', '1966-09-15')}. And NullOrEmptyIgnored, NullElementIgnored, SeveralRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAtList The collection of createdAt as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCreatedAt_InScope(Collection<java.time.LocalDateTime> createdAtList) {
        doSetCreatedAt_InScope(createdAtList);
    }

    protected void doSetCreatedAt_InScope(Collection<java.time.LocalDateTime> createdAtList) {
        regINS(CK_INS, cTL(createdAtList), xgetCValueCreatedAt(), "created_at");
    }

    /**
     * NotInScope {not in ('1965-03-03', '1966-09-15')}. And NullOrEmptyIgnored, NullElementIgnored, SeveralRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     * @param createdAtList The collection of createdAt as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCreatedAt_NotInScope(Collection<java.time.LocalDateTime> createdAtList) {
        doSetCreatedAt_NotInScope(createdAtList);
    }

    protected void doSetCreatedAt_NotInScope(Collection<java.time.LocalDateTime> createdAtList) {
        regINS(CK_NINS, cTL(createdAtList), xgetCValueCreatedAt(), "created_at");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     */
    public void setCreatedAt_IsNull() { regCreatedAt(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * created_at: {timestamptz(35, 6), default=[now()]}
     */
    public void setCreatedAt_IsNotNull() { regCreatedAt(CK_ISNN, DOBJ); }

    protected void regCreatedAt(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueCreatedAt(), "created_at"); }
    protected abstract ConditionValue xgetCValueCreatedAt();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO = (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<TodosCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, TodosCB.class);
    }

    /**
     * Prepare ScalarCondition as equal. <br>
     * {where FOO &lt;&gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<TodosCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, TodosCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br>
     * {where FOO &gt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<TodosCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, TodosCB.class);
    }

    /**
     * Prepare ScalarCondition as lessThan. <br>
     * {where FOO &lt; (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<TodosCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, TodosCB.class);
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br>
     * {where FOO &gt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().scalar_Equal().<span style="color: #CC4747">avg</span>(<span style="color: #553000">purchaseCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">purchaseCB</span>.specify().<span style="color: #CC4747">columnPurchasePrice</span>(); <span style="color: #3F7E5E">// *Point!</span>
     *     <span style="color: #553000">purchaseCB</span>.query().setPaymentCompleteFlg_Equal_True();
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<TodosCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, TodosCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;TodosCB&gt;() {
     *     public void query(TodosCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<TodosCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, TodosCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        TodosCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(TodosCQ sq);

    protected TodosCB xcreateScalarConditionCB() {
        TodosCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected TodosCB xcreateScalarConditionPartitionByCB() {
        TodosCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<TodosCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        TodosCB cb = new TodosCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "id";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(TodosCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<TodosCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(TodosCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        TodosCB cb = new TodosCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "id";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(TodosCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<TodosCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        TodosCB cb = new TodosCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(TodosCQ sq);

    // ===================================================================================
    //                                                                        Manual Order
    //                                                                        ============
    /**
     * Order along manual ordering information.
     * <pre>
     * cb.query().addOrderBy_Birthdate_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_GreaterEqual</span>(priorityDate); <span style="color: #3F7E5E">// e.g. 2000/01/01</span>
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when BIRTHDATE &gt;= '2000/01/01' then 0</span>
     * <span style="color: #3F7E5E">//     else 1</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     *
     * cb.query().addOrderBy_MemberStatusCode_Asc().<span style="color: #CC4747">withManualOrder</span>(<span style="color: #553000">op</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Withdrawal);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Formalized);
     *     <span style="color: #553000">op</span>.<span style="color: #CC4747">when_Equal</span>(CDef.MemberStatus.Provisional);
     * });
     * <span style="color: #3F7E5E">// order by </span>
     * <span style="color: #3F7E5E">//   case</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'WDL' then 0</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'FML' then 1</span>
     * <span style="color: #3F7E5E">//     when MEMBER_STATUS_CODE = 'PRV' then 2</span>
     * <span style="color: #3F7E5E">//     else 3</span>
     * <span style="color: #3F7E5E">//   end asc, ...</span>
     * </pre>
     * <p>This function with Union is unsupported!</p>
     * <p>The order values are bound (treated as bind parameter).</p>
     * @param opLambda The callback for option of manual-order containing order values. (NotNull)
     */
    public void withManualOrder(ManualOrderOptionCall opLambda) { // is user public!
        xdoWithManualOrder(cMOO(opLambda));
    }

    // ===================================================================================
    //                                                                    Small Adjustment
    //                                                                    ================
    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    protected TodosCB newMyCB() {
        return new TodosCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return TodosCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
