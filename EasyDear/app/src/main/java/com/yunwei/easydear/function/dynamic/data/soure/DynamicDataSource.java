package com.yunwei.easydear.function.dynamic.data.soure;

import com.yunwei.easydear.function.dynamic.data.DynamicDetailEntity;
import com.yunwei.easydear.function.dynamic.data.DynamicEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */

public interface DynamicDataSource {

    interface QueryDynamicListCallBack{
        void queryDynamicListSuccess(List<DynamicEntity> list);

        void queryDynamicListFailure(int code,String msg);

        void queryDynamicListEnd();
    }

     void queryDynamicList(int pageSize,int pageCount,String keywords,String type,String province,String city,String area,QueryDynamicListCallBack callBack);


    interface QueryDynamicDetailsCallBack{
        void queryDynamicDetailsStart();

        void queryDynamicDetailsSuccess(DynamicDetailEntity entity);

        void queryDynamicDetailsFailure(int code,String msg);

        void queryDynamicDetailsEnd();
    }

    void queryDynamicDetails(int articleId,QueryDynamicDetailsCallBack callBack);
}
