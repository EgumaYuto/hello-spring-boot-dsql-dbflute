package org.example.dbflute.bsbhv.loader;

import java.util.List;

import org.dbflute.bhv.*;
import org.example.dbflute.exbhv.*;
import org.example.dbflute.exentity.*;

/**
 * The referrer loader of users as TABLE.
 * @author DBFlute(AutoGenerator)
 */
public class LoaderOfUsers {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected List<Users> _selectedList;
    protected BehaviorSelector _selector;
    protected UsersBhv _myBhv; // lazy-loaded

    // ===================================================================================
    //                                                                   Ready for Loading
    //                                                                   =================
    public LoaderOfUsers ready(List<Users> selectedList, BehaviorSelector selector)
    { _selectedList = selectedList; _selector = selector; return this; }

    protected UsersBhv myBhv()
    { if (_myBhv != null) { return _myBhv; } else { _myBhv = _selector.select(UsersBhv.class); return _myBhv; } }

    // ===================================================================================
    //                                                                    Pull out Foreign
    //                                                                    ================
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public List<Users> getSelectedList() { return _selectedList; }
    public BehaviorSelector getSelector() { return _selector; }
}
