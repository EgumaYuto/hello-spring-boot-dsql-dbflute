package org.example.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import org.example.dbflute.exbhv.*;
import org.example.dbflute.exentity.*;

/**
 * The referrer loader of todos as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfTodos {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<Todos> _selectedList;
    protected BehaviorSelector _selector;
    protected TodosBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfTodos ready(List<Todos> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected TodosBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(TodosBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    protected LoaderOfClsTodoStatus _foreignClsTodoStatusLoader;
    public LoaderOfClsTodoStatus pulloutClsTodoStatus() {
        if (_foreignClsTodoStatusLoader == null)
        { _foreignClsTodoStatusLoader = new LoaderOfClsTodoStatus().ready(myBhv().pulloutClsTodoStatus(_selectedList), _selector); }
        return _foreignClsTodoStatusLoader;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<Todos> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
