package com.yunwei.easydear.function.mainFuncations.findFuncation.data.source;

import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.common.Constant;
import com.yunwei.easydear.common.retrofit.RetrofitManager;
import com.yunwei.easydear.entity.ResponseModel;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/21.
 */
public class ChildTabRemoteRepo implements ChildTabDataSource {

    private final String TAG = getClass().getSimpleName();

    private Call<ResponseModel<ArrayList<ArticleItemEntity>>> call;

    private static ChildTabRemoteRepo instance;

    public static ChildTabRemoteRepo getInstance() {
        if (instance == null) {
            instance = new ChildTabRemoteRepo();
        }
        return instance;
    }

    @Override
    public void requestRecyclerArticles(final ChildTabCallBack callBack) {
        call = RetrofitManager.getInstance().getService().reqHomeArticleList(callBack.getPageSize(), callBack.getPageCount(), callBack.getKey(), callBack.getType(), callBack.getProvince(), callBack.getCity(), callBack.getArea(), DataApplication.getInstance().getUserInfoEntity().getUserNo());
//        call = RetrofitManager.getInstance().getService().reqHomeArticleList(1, 5, "", "", "", "", "");
        call.enqueue(new Callback<ResponseModel<ArrayList<ArticleItemEntity>>>() {

            @Override
            public void onResponse(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Response<ResponseModel<ArrayList<ArticleItemEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onGetArticleListSuccess(response.body().getData());
                } else {
                    callBack.onGetArticleListFailure(response.body().getCode(), "获取文章列表失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Throwable t) {
                callBack.onGetArticleListFailure(201, "获取文章列表失败");
            }
        });
    }

    @Override
    public void cancelRequest() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }
}
