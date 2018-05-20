package com.yunwei.easydear.function.mainFuncations.searchFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */

public interface SearchContact {

    interface SearchView {
        void onGetHotSearchSuccess(List<SearchEntity> list);

        void onGetHotSearchFailure(String msg);

        void onGetMatchedKeySuccess(List<SearchEntity> list);

        void onGetMatchedKeyFailure(String msg);

        String getUserNo();

        String getKey();
    }

    interface Presenter {
        void requestHotSearch();

        void requestKeyMatch();
    }
}
