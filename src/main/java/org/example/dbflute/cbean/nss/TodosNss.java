package org.example.dbflute.cbean.nss;

import org.example.dbflute.cbean.cq.TodosCQ;

/**
 * The nest select set-upper of todos.
 * @author DBFlute(AutoGenerator)
 */
public class TodosNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final TodosCQ _query;
    public TodosNss(TodosCQ query) { _query = query; }
    public boolean hasConditionQuery() { return _query != null; }

    // ===================================================================================
    //                                                                     Nested Relation
    //                                                                     ===============
    /**
     * With nested relation columns to select clause. <br>
     * cls_todo_status by my status, named 'clsTodoStatus'.
     */
    public void withClsTodoStatus() {
        _query.xdoNss(() -> _query.queryClsTodoStatus());
    }
}
