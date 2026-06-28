package org.example.dbflute.cbean.cq.bs;

import java.util.Map;

import org.dbflute.cbean.*;
import org.dbflute.cbean.chelper.*;
import org.dbflute.cbean.coption.*;
import org.dbflute.cbean.cvalue.ConditionValue;
import org.dbflute.cbean.sqlclause.SqlClause;
import org.dbflute.exception.IllegalConditionBeanOperationException;
import org.example.dbflute.cbean.cq.ciq.*;
import org.example.dbflute.cbean.*;
import org.example.dbflute.cbean.cq.*;

/**
 * The base condition-query of cls_todo_status.
 * @author DBFlute(AutoGenerator)
 */
public class BsClsTodoStatusCQ extends AbstractBsClsTodoStatusCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected ClsTodoStatusCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsClsTodoStatusCQ(ConditionQuery referrerQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(referrerQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br>
     * {select ... from ... left outer join (select * from cls_todo_status) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public ClsTodoStatusCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected ClsTodoStatusCIQ xcreateCIQ() {
        ClsTodoStatusCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected ClsTodoStatusCIQ xnewCIQ() {
        return new ClsTodoStatusCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br>
     * {select ... from ... left outer join cls_todo_status on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #CC4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public ClsTodoStatusCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        ClsTodoStatusCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    protected ConditionValue _code;
    public ConditionValue xdfgetCode()
    { if (_code == null) { _code = nCV(); }
      return _code; }
    protected ConditionValue xgetCValueCode() { return xdfgetCode(); }

    public Map<String, TodosCQ> xdfgetCode_ExistsReferrer_TodosList() { return xgetSQueMap("code_ExistsReferrer_TodosList"); }
    public String keepCode_ExistsReferrer_TodosList(TodosCQ sq) { return xkeepSQue("code_ExistsReferrer_TodosList", sq); }

    public Map<String, TodosCQ> xdfgetCode_NotExistsReferrer_TodosList() { return xgetSQueMap("code_NotExistsReferrer_TodosList"); }
    public String keepCode_NotExistsReferrer_TodosList(TodosCQ sq) { return xkeepSQue("code_NotExistsReferrer_TodosList", sq); }

    public Map<String, TodosCQ> xdfgetCode_SpecifyDerivedReferrer_TodosList() { return xgetSQueMap("code_SpecifyDerivedReferrer_TodosList"); }
    public String keepCode_SpecifyDerivedReferrer_TodosList(TodosCQ sq) { return xkeepSQue("code_SpecifyDerivedReferrer_TodosList", sq); }

    public Map<String, TodosCQ> xdfgetCode_QueryDerivedReferrer_TodosList() { return xgetSQueMap("code_QueryDerivedReferrer_TodosList"); }
    public String keepCode_QueryDerivedReferrer_TodosList(TodosCQ sq) { return xkeepSQue("code_QueryDerivedReferrer_TodosList", sq); }
    public Map<String, Object> xdfgetCode_QueryDerivedReferrer_TodosListParameter() { return xgetSQuePmMap("code_QueryDerivedReferrer_TodosList"); }
    public String keepCode_QueryDerivedReferrer_TodosListParameter(Object pm) { return xkeepSQuePm("code_QueryDerivedReferrer_TodosList", pm); }

    /**
     * Add order-by as ascend. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addOrderBy_Code_Asc() { regOBA("code"); return this; }

    /**
     * Add order-by as descend. <br>
     * code: {PK, NotNull, varchar(20), classification=TodoStatus}
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addOrderBy_Code_Desc() { regOBD("code"); return this; }

    protected ConditionValue _name;
    public ConditionValue xdfgetName()
    { if (_name == null) { _name = nCV(); }
      return _name; }
    protected ConditionValue xgetCValueName() { return xdfgetName(); }

    /**
     * Add order-by as ascend. <br>
     * name: {NotNull, varchar(50)}
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addOrderBy_Name_Asc() { regOBA("name"); return this; }

    /**
     * Add order-by as descend. <br>
     * name: {NotNull, varchar(50)}
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addOrderBy_Name_Desc() { regOBD("name"); return this; }

    protected ConditionValue _dispOrder;
    public ConditionValue xdfgetDispOrder()
    { if (_dispOrder == null) { _dispOrder = nCV(); }
      return _dispOrder; }
    protected ConditionValue xgetCValueDispOrder() { return xdfgetDispOrder(); }

    /**
     * Add order-by as ascend. <br>
     * disp_order: {NotNull, int4(10)}
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addOrderBy_DispOrder_Asc() { regOBA("disp_order"); return this; }

    /**
     * Add order-by as descend. <br>
     * disp_order: {NotNull, int4(10)}
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addOrderBy_DispOrder_Desc() { regOBD("disp_order"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addSpecifiedDerivedOrderBy_Asc(String aliasName) { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #CC4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #CC4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #CC4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsClsTodoStatusCQ addSpecifiedDerivedOrderBy_Desc(String aliasName) { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    public void reflectRelationOnUnionQuery(ConditionQuery bqs, ConditionQuery uqs) {
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    public Map<String, ClsTodoStatusCQ> xdfgetScalarCondition() { return xgetSQueMap("scalarCondition"); }
    public String keepScalarCondition(ClsTodoStatusCQ sq) { return xkeepSQue("scalarCondition", sq); }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public Map<String, ClsTodoStatusCQ> xdfgetSpecifyMyselfDerived() { return xgetSQueMap("specifyMyselfDerived"); }
    public String keepSpecifyMyselfDerived(ClsTodoStatusCQ sq) { return xkeepSQue("specifyMyselfDerived", sq); }

    public Map<String, ClsTodoStatusCQ> xdfgetQueryMyselfDerived() { return xgetSQueMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerived(ClsTodoStatusCQ sq) { return xkeepSQue("queryMyselfDerived", sq); }
    public Map<String, Object> xdfgetQueryMyselfDerivedParameter() { return xgetSQuePmMap("queryMyselfDerived"); }
    public String keepQueryMyselfDerivedParameter(Object pm) { return xkeepSQuePm("queryMyselfDerived", pm); }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, ClsTodoStatusCQ> _myselfExistsMap;
    public Map<String, ClsTodoStatusCQ> xdfgetMyselfExists() { return xgetSQueMap("myselfExists"); }
    public String keepMyselfExists(ClsTodoStatusCQ sq) { return xkeepSQue("myselfExists", sq); }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    public Map<String, ClsTodoStatusCQ> xdfgetMyselfInScope() { return xgetSQueMap("myselfInScope"); }
    public String keepMyselfInScope(ClsTodoStatusCQ sq) { return xkeepSQue("myselfInScope", sq); }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return ClsTodoStatusCB.class.getName(); }
    protected String xCQ() { return ClsTodoStatusCQ.class.getName(); }
    protected String xCHp() { return HpQDRFunction.class.getName(); }
    protected String xCOp() { return ConditionOption.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
