package org.example.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import org.dbflute.bhv.referrer.*;
import org.example.dbflute.exbhv.*;
import org.example.dbflute.exentity.*;
import org.example.dbflute.cbean.*;

/**
 * The referrer loader of cls_todo_status as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfClsTodoStatus {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<ClsTodoStatus> _selectedList;
    protected BehaviorSelector _selector;
    protected ClsTodoStatusBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfClsTodoStatus ready(List<ClsTodoStatus> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected ClsTodoStatusBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(ClsTodoStatusBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                       Load Referrer
    //                                                                       =============
    protected List<Todos> _referrerTodos;

    /**
     * Load referrer of todosList by the set-upper of referrer. <br>
     * todos by status, named 'todosList'.
     * <pre>
     * <span style="color: #0000C0">clsTodoStatusBhv</span>.<span style="color: #994747">load</span>(<span style="color: #553000">clsTodoStatusList</span>, <span style="color: #553000">statusLoader</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *     <span style="color: #553000">statusLoader</span>.<span style="color: #CC4747">loadTodos</span>(<span style="color: #553000">todosCB</span> <span style="color: #90226C; font-weight: bold"><span style="font-size: 120%">-</span>&gt;</span> {
     *         <span style="color: #553000">todosCB</span>.setupSelect...
     *         <span style="color: #553000">todosCB</span>.query().set...
     *         <span style="color: #553000">todosCB</span>.query().addOrderBy...
     *     }); <span style="color: #3F7E5E">// you can load nested referrer from here</span>
     *     <span style="color: #3F7E5E">//}).withNestedReferrer(<span style="color: #553000">todosLoader</span> -&gt; {</span>
     *     <span style="color: #3F7E5E">//    todosLoader.load...</span>
     *     <span style="color: #3F7E5E">//});</span>
     * });
     * for (ClsTodoStatus clsTodoStatus : <span style="color: #553000">clsTodoStatusList</span>) {
     *     ... = clsTodoStatus.<span style="color: #CC4747">getTodosList()</span>;
     * }
     * </pre>
     * About internal policy, the value of primary key (and others too) is treated as case-insensitive. <br>
     * The condition-bean, which the set-upper provides, has settings before callback as follows:
     * <pre>
     * cb.query().setStatus_InScope(pkList);
     * cb.query().addOrderBy_Status_Asc();
     * </pre>
     * @param refCBLambda The callback to set up referrer condition-bean for loading referrer. (NotNull)
     * @return The callback interface which you can load nested referrer by calling withNestedReferrer(). (NotNull)
     */
    public NestedReferrerLoaderGateway<LoaderOfTodos> loadTodos(ReferrerConditionSetupper<TodosCB> refCBLambda) {
        myBhv().loadTodos(_selectedList, refCBLambda).withNestedReferrer(refLs -> _referrerTodos = refLs);
        return hd -> hd.handle(new LoaderOfTodos().ready(_referrerTodos, _selector));
    }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<ClsTodoStatus> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
