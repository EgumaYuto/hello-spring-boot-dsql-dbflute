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
 * The abstract condition-query of cls_todo_status.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsClsTodoStatusCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsClsTodoStatusCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
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
        return "cls_todo_status";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @param code The value of code as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setCode_Equal(String code) {
        doSetCode_Equal(fRES(code));
    }

    /**
     * Equal(=). As TodoStatus. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * TODO status
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setCode_Equal_AsTodoStatus(CDef.TodoStatus cdef) {
        doSetCode_Equal(cdef != null ? cdef.code() : null);
    }

    /**
     * Equal(=). As ToDo (TODO). And OnlyOnceRegistered. <br>
     * To Do
     */
    public void setCode_Equal_ToDo() {
        setCode_Equal_AsTodoStatus(CDef.TodoStatus.ToDo);
    }

    /**
     * Equal(=). As Doing (DOING). And OnlyOnceRegistered. <br>
     * Doing
     */
    public void setCode_Equal_Doing() {
        setCode_Equal_AsTodoStatus(CDef.TodoStatus.Doing);
    }

    /**
     * Equal(=). As Done (DONE). And OnlyOnceRegistered. <br>
     * Done
     */
    public void setCode_Equal_Done() {
        setCode_Equal_AsTodoStatus(CDef.TodoStatus.Done);
    }

    protected void doSetCode_Equal(String code) {
        regCode(CK_EQ, code);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @param code The value of code as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setCode_NotEqual(String code) {
        doSetCode_NotEqual(fRES(code));
    }

    /**
     * NotEqual(&lt;&gt;). As TodoStatus. And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * TODO status
     * @param cdef The instance of classification definition (as ENUM type). (basically NotNull: error as default, or no condition as option)
     */
    public void setCode_NotEqual_AsTodoStatus(CDef.TodoStatus cdef) {
        doSetCode_NotEqual(cdef != null ? cdef.code() : null);
    }

    /**
     * NotEqual(&lt;&gt;). As ToDo (TODO). And OnlyOnceRegistered. <br>
     * To Do
     */
    public void setCode_NotEqual_ToDo() {
        setCode_NotEqual_AsTodoStatus(CDef.TodoStatus.ToDo);
    }

    /**
     * NotEqual(&lt;&gt;). As Doing (DOING). And OnlyOnceRegistered. <br>
     * Doing
     */
    public void setCode_NotEqual_Doing() {
        setCode_NotEqual_AsTodoStatus(CDef.TodoStatus.Doing);
    }

    /**
     * NotEqual(&lt;&gt;). As Done (DONE). And OnlyOnceRegistered. <br>
     * Done
     */
    public void setCode_NotEqual_Done() {
        setCode_NotEqual_AsTodoStatus(CDef.TodoStatus.Done);
    }

    protected void doSetCode_NotEqual(String code) {
        regCode(CK_NES, code);
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @param codeList The collection of code as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setCode_InScope(Collection<String> codeList) {
        doSetCode_InScope(codeList);
    }

    /**
     * InScope {in ('a', 'b')}. As TodoStatus. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * TODO status
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCode_InScope_AsTodoStatus(Collection<CDef.TodoStatus> cdefList) {
        doSetCode_InScope(cTStrL(cdefList));
    }

    protected void doSetCode_InScope(Collection<String> codeList) {
        regINS(CK_INS, cTL(codeList), xgetCValueCode(), "code");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @param codeList The collection of code as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    protected void setCode_NotInScope(Collection<String> codeList) {
        doSetCode_NotInScope(codeList);
    }

    /**
     * NotInScope {not in ('a', 'b')}. As TodoStatus. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus} <br>
     * TODO status
     * @param cdefList The list of classification definition (as ENUM type). (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setCode_NotInScope_AsTodoStatus(Collection<CDef.TodoStatus> cdefList) {
        doSetCode_NotInScope(cTStrL(cdefList));
    }

    protected void doSetCode_NotInScope(Collection<String> codeList) {
        regINS(CK_NINS, cTL(codeList), xgetCValueCode(), "code");
    }

    /**
     * Set up ExistsReferrer (correlated sub-query). <br>
     * {exists (select status from todos where ...)} <br>
     * todos by status, named 'todosAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">existsTodos</span>(todosCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     todosCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of TodosList for 'exists'. (NotNull)
     */
    public void existsTodos(SubQuery<TodosCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        TodosCB cb = new TodosCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepCode_ExistsReferrer_TodosList(cb.query());
        registerExistsReferrer(cb.query(), "code", "status", pp, "todosList");
    }
    public abstract String keepCode_ExistsReferrer_TodosList(TodosCQ sq);

    /**
     * Set up NotExistsReferrer (correlated sub-query). <br>
     * {not exists (select status from todos where ...)} <br>
     * todos by status, named 'todosAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">notExistsTodos</span>(todosCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     todosCB.query().set...
     * });
     * </pre>
     * @param subCBLambda The callback for sub-query of Code_NotExistsReferrer_TodosList for 'not exists'. (NotNull)
     */
    public void notExistsTodos(SubQuery<TodosCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        TodosCB cb = new TodosCB(); cb.xsetupForExistsReferrer(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepCode_NotExistsReferrer_TodosList(cb.query());
        registerNotExistsReferrer(cb.query(), "code", "status", pp, "todosList");
    }
    public abstract String keepCode_NotExistsReferrer_TodosList(TodosCQ sq);

    public void xsderiveTodosList(String fn, SubQuery<TodosCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        TodosCB cb = new TodosCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepCode_SpecifyDerivedReferrer_TodosList(cb.query());
        registerSpecifyDerivedReferrer(fn, cb.query(), "code", "status", pp, "todosList", al, op);
    }
    public abstract String keepCode_SpecifyDerivedReferrer_TodosList(TodosCQ sq);

    /**
     * Prepare for (Query)DerivedReferrer (correlated sub-query). <br>
     * {FOO &lt;= (select max(BAR) from todos where ...)} <br>
     * todos by status, named 'todosAsOne'.
     * <pre>
     * cb.query().<span style="color: #CC4747">derivedTodos()</span>.<span style="color: #CC4747">max</span>(todosCB <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     todosCB.specify().<span style="color: #CC4747">columnFoo...</span> <span style="color: #3F7E5E">// derived column by function</span>
     *     todosCB.query().setBar... <span style="color: #3F7E5E">// referrer condition</span>
     * }).<span style="color: #CC4747">greaterEqual</span>(123); <span style="color: #3F7E5E">// condition to derived column</span>
     * </pre>
     * @return The object to set up a function for referrer table. (NotNull)
     */
    public HpQDRFunction<TodosCB> derivedTodos() {
        return xcreateQDRFunctionTodosList();
    }
    protected HpQDRFunction<TodosCB> xcreateQDRFunctionTodosList() {
        return xcQDRFunc((fn, sq, rd, vl, op) -> xqderiveTodosList(fn, sq, rd, vl, op));
    }
    public void xqderiveTodosList(String fn, SubQuery<TodosCB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        TodosCB cb = new TodosCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String sqpp = keepCode_QueryDerivedReferrer_TodosList(cb.query()); String prpp = keepCode_QueryDerivedReferrer_TodosListParameter(vl);
        registerQueryDerivedReferrer(fn, cb.query(), "code", "status", sqpp, "todosList", rd, vl, prpp, op);
    }
    public abstract String keepCode_QueryDerivedReferrer_TodosList(TodosCQ sq);
    public abstract String keepCode_QueryDerivedReferrer_TodosListParameter(Object vl);

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     */
    public void setCode_IsNull() { regCode(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     */
    public void setCode_IsNotNull() { regCode(CK_ISNN, DOBJ); }

    protected void regCode(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueCode(), "code"); }
    protected abstract ConditionValue xgetCValueCode();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as equal. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_Equal(String name) {
        doSetName_Equal(fRES(name));
    }

    protected void doSetName_Equal(String name) {
        regName(CK_EQ, name);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as notEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_NotEqual(String name) {
        doSetName_NotEqual(fRES(name));
    }

    protected void doSetName_NotEqual(String name) {
        regName(CK_NES, name);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as greaterThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_GreaterThan(String name) {
        regName(CK_GT, fRES(name));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as lessThan. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_LessThan(String name) {
        regName(CK_LT, fRES(name));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as greaterEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_GreaterEqual(String name) {
        regName(CK_GE, fRES(name));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as lessEqual. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_LessEqual(String name) {
        regName(CK_LE, fRES(name));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param nameList The collection of name as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_InScope(Collection<String> nameList) {
        doSetName_InScope(nameList);
    }

    protected void doSetName_InScope(Collection<String> nameList) {
        regINS(CK_INS, cTL(nameList), xgetCValueName(), "name");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param nameList The collection of name as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setName_NotInScope(Collection<String> nameList) {
        doSetName_NotInScope(nameList);
    }

    protected void doSetName_NotInScope(Collection<String> nameList) {
        regINS(CK_NINS, cTL(nameList), xgetCValueName(), "name");
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * name: {NotNull, varchar(50)} <br>
     * <pre>e.g. setName_LikeSearch("xxx", op <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> op.<span style="color: #CC4747">likeContain()</span>);</pre>
     * @param name The value of name as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setName_LikeSearch(String name, ConditionOptionCall<LikeSearchOption> opLambda) {
        setName_LikeSearch(name, xcLSOP(opLambda));
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br>
     * name: {NotNull, varchar(50)} <br>
     * <pre>e.g. setName_LikeSearch("xxx", new <span style="color: #CC4747">LikeSearchOption</span>().likeContain());</pre>
     * @param name The value of name as likeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    protected void setName_LikeSearch(String name, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(name), xgetCValueName(), "name", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param opLambda The callback for option of like-search. (NotNull)
     */
    public void setName_NotLikeSearch(String name, ConditionOptionCall<LikeSearchOption> opLambda) {
        setName_NotLikeSearch(name, xcLSOP(opLambda));
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br>
     * And NullOrEmptyIgnored, SeveralRegistered. <br>
     * name: {NotNull, varchar(50)}
     * @param name The value of name as notLikeSearch. (basically NotNull, NotEmpty: error as default, or no condition as option)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    protected void setName_NotLikeSearch(String name, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(name), xgetCValueName(), "name", likeSearchOption);
    }

    protected void regName(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueName(), "name"); }
    protected abstract ConditionValue xgetCValueName();

    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrder The value of dispOrder as equal. (basically NotNull: error as default, or no condition as option)
     */
    public void setDispOrder_Equal(Integer dispOrder) {
        doSetDispOrder_Equal(dispOrder);
    }

    protected void doSetDispOrder_Equal(Integer dispOrder) {
        regDispOrder(CK_EQ, dispOrder);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrder The value of dispOrder as notEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setDispOrder_NotEqual(Integer dispOrder) {
        doSetDispOrder_NotEqual(dispOrder);
    }

    protected void doSetDispOrder_NotEqual(Integer dispOrder) {
        regDispOrder(CK_NES, dispOrder);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrder The value of dispOrder as greaterThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setDispOrder_GreaterThan(Integer dispOrder) {
        regDispOrder(CK_GT, dispOrder);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrder The value of dispOrder as lessThan. (basically NotNull: error as default, or no condition as option)
     */
    public void setDispOrder_LessThan(Integer dispOrder) {
        regDispOrder(CK_LT, dispOrder);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrder The value of dispOrder as greaterEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setDispOrder_GreaterEqual(Integer dispOrder) {
        regDispOrder(CK_GE, dispOrder);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrder The value of dispOrder as lessEqual. (basically NotNull: error as default, or no condition as option)
     */
    public void setDispOrder_LessEqual(Integer dispOrder) {
        regDispOrder(CK_LE, dispOrder);
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param minNumber The min number of dispOrder. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of dispOrder. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param opLambda The callback for option of range-of. (NotNull)
     */
    public void setDispOrder_RangeOf(Integer minNumber, Integer maxNumber, ConditionOptionCall<RangeOfOption> opLambda) {
        setDispOrder_RangeOf(minNumber, maxNumber, xcROOP(opLambda));
    }

    /**
     * RangeOf with various options. (versatile) <br>
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br>
     * And NullIgnored, OnlyOnceRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param minNumber The min number of dispOrder. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param maxNumber The max number of dispOrder. (basically NotNull: if op.allowOneSide(), null allowed)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    protected void setDispOrder_RangeOf(Integer minNumber, Integer maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, xgetCValueDispOrder(), "disp_order", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrderList The collection of dispOrder as inScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setDispOrder_InScope(Collection<Integer> dispOrderList) {
        doSetDispOrder_InScope(dispOrderList);
    }

    protected void doSetDispOrder_InScope(Collection<Integer> dispOrderList) {
        regINS(CK_INS, cTL(dispOrderList), xgetCValueDispOrder(), "disp_order");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br>
     * disp_order: {NotNull, int4(10)}
     * @param dispOrderList The collection of dispOrder as notInScope. (basically NotNull, NotEmpty: error as default, or no condition as option)
     */
    public void setDispOrder_NotInScope(Collection<Integer> dispOrderList) {
        doSetDispOrder_NotInScope(dispOrderList);
    }

    protected void doSetDispOrder_NotInScope(Collection<Integer> dispOrderList) {
        regINS(CK_NINS, cTL(dispOrderList), xgetCValueDispOrder(), "disp_order");
    }

    protected void regDispOrder(ConditionKey ky, Object vl) { regQ(ky, vl, xgetCValueDispOrder(), "disp_order"); }
    protected abstract ConditionValue xgetCValueDispOrder();

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
    public HpSLCFunction<ClsTodoStatusCB> scalar_Equal() {
        return xcreateSLCFunction(CK_EQ, ClsTodoStatusCB.class);
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
    public HpSLCFunction<ClsTodoStatusCB> scalar_NotEqual() {
        return xcreateSLCFunction(CK_NES, ClsTodoStatusCB.class);
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
    public HpSLCFunction<ClsTodoStatusCB> scalar_GreaterThan() {
        return xcreateSLCFunction(CK_GT, ClsTodoStatusCB.class);
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
    public HpSLCFunction<ClsTodoStatusCB> scalar_LessThan() {
        return xcreateSLCFunction(CK_LT, ClsTodoStatusCB.class);
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
    public HpSLCFunction<ClsTodoStatusCB> scalar_GreaterEqual() {
        return xcreateSLCFunction(CK_GE, ClsTodoStatusCB.class);
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br>
     * {where FOO &lt;= (select max(BAR) from ...)}
     * <pre>
     * cb.query().<span style="color: #CC4747">scalar_LessEqual()</span>.max(new SubQuery&lt;ClsTodoStatusCB&gt;() {
     *     public void query(ClsTodoStatusCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSLCFunction<ClsTodoStatusCB> scalar_LessEqual() {
        return xcreateSLCFunction(CK_LE, ClsTodoStatusCB.class);
    }

    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xscalarCondition(String fn, SubQuery<CB> sq, String rd, HpSLCCustomized<CB> cs, ScalarConditionOption op) {
        assertObjectNotNull("subQuery", sq);
        ClsTodoStatusCB cb = xcreateScalarConditionCB(); sq.query((CB)cb);
        String pp = keepScalarCondition(cb.query()); // for saving query-value
        cs.setPartitionByCBean((CB)xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(fn, cb.query(), pp, rd, cs, op);
    }
    public abstract String keepScalarCondition(ClsTodoStatusCQ sq);

    protected ClsTodoStatusCB xcreateScalarConditionCB() {
        ClsTodoStatusCB cb = newMyCB(); cb.xsetupForScalarCondition(this); return cb;
    }

    protected ClsTodoStatusCB xcreateScalarConditionPartitionByCB() {
        ClsTodoStatusCB cb = newMyCB(); cb.xsetupForScalarConditionPartitionBy(this); return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String fn, SubQuery<ClsTodoStatusCB> sq, String al, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        ClsTodoStatusCB cb = new ClsTodoStatusCB(); cb.xsetupForDerivedReferrer(this);
        lockCall(() -> sq.query(cb)); String pp = keepSpecifyMyselfDerived(cb.query()); String pk = "code";
        registerSpecifyMyselfDerived(fn, cb.query(), pk, pk, pp, "myselfDerived", al, op);
    }
    public abstract String keepSpecifyMyselfDerived(ClsTodoStatusCQ sq);

    /**
     * Prepare for (Query)MyselfDerived (correlated sub-query).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<ClsTodoStatusCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived(ClsTodoStatusCB.class);
    }
    @SuppressWarnings("unchecked")
    protected <CB extends ConditionBean> void xqderiveMyselfDerived(String fn, SubQuery<CB> sq, String rd, Object vl, DerivedReferrerOption op) {
        assertObjectNotNull("subQuery", sq);
        ClsTodoStatusCB cb = new ClsTodoStatusCB(); cb.xsetupForDerivedReferrer(this); sq.query((CB)cb);
        String pk = "code";
        String sqpp = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String prpp = keepQueryMyselfDerivedParameter(vl);
        registerQueryMyselfDerived(fn, cb.query(), pk, pk, sqpp, "myselfDerived", rd, vl, prpp, op);
    }
    public abstract String keepQueryMyselfDerived(ClsTodoStatusCQ sq);
    public abstract String keepQueryMyselfDerivedParameter(Object vl);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (correlated sub-query).
     * @param subCBLambda The implementation of sub-query. (NotNull)
     */
    public void myselfExists(SubQuery<ClsTodoStatusCB> subCBLambda) {
        assertObjectNotNull("subCBLambda", subCBLambda);
        ClsTodoStatusCB cb = new ClsTodoStatusCB(); cb.xsetupForMyselfExists(this);
        lockCall(() -> subCBLambda.query(cb)); String pp = keepMyselfExists(cb.query());
        registerMyselfExists(cb.query(), pp);
    }
    public abstract String keepMyselfExists(ClsTodoStatusCQ sq);

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
    protected ClsTodoStatusCB newMyCB() {
        return new ClsTodoStatusCB();
    }
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabUDT() { return Date.class.getName(); }
    protected String xabCQ() { return ClsTodoStatusCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSLCS() { return HpSLCSetupper.class.getName(); }
    protected String xabSCP() { return SubQuery.class.getName(); }
}
