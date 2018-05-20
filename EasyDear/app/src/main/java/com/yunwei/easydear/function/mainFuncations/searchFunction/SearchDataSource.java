package com.yunwei.easydear.function.mainFuncations.searchFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */
public interface SearchDataSource {

    void reqHotSearch(SearchCallBack callBack);

    void reqKeyMatch(SearchCallBack callBack);

    interface SearchCallBack {
        void getHotSearchSuccess(List<SearchEntity> data);

        void getHotSearchFailure(String message);

        void getMatchedKeySuccess(List<SearchEntity> data);

        void getMatchedKeyFailure(String message);

        String getUserNo();

        String getKey();
    }
}
