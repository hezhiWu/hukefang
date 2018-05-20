package com.yunwei.easydear.function.dynamic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;
import com.yunwei.easydear.base.BaseRecyclerViewAdapter;
import com.yunwei.easydear.common.dialog.ToastUtil;
import com.yunwei.easydear.function.dynamic.data.DynamicEntity;
import com.yunwei.easydear.function.dynamic.data.soure.DynamicDataSource;
import com.yunwei.easydear.function.dynamic.data.soure.DynamicRemoteRepo;
import com.yunwei.easydear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easydear.utils.ISkipActivityUtil;
import com.yunwei.easydear.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:动态子选项卡
 *
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 12:0
 * Version:1.0
 */

public class DynamicChildFragment extends BaseFragment implements DynamicDataSource.QueryDynamicListCallBack, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private final String TAG = getClass().getSimpleName();

    private int pageSize = 1;

    @BindView(R.id.tab_child_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;
    @BindView((R.id.tab_child_empty_textView))
    TextView emptyTextView;

    private DynamicListAdapter adapter;

    private String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
        adapter = new DynamicListAdapter(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_child_layout, null);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setMode(PullToRefreshRecyclerView.Mode.BOTH);
        mRecyclerView.setPullToRefreshListener(this);
        adapter.setOnItemClickListener(this);
        mRecyclerView.startUpRefresh();
    }


    @Override
    public void onDownRefresh() {
        pageSize = 1;
        DynamicRemoteRepo.getRemoteRepo().queryDynamicList(pageSize,20,"",type,"","","",this);
    }

    @Override
    public void onPullRefresh() {
        pageSize++;
        DynamicRemoteRepo.getRemoteRepo().queryDynamicList(pageSize,20,"",type,"","","",this);
    }

    @Override
    public void queryDynamicListSuccess(List<DynamicEntity> list) {
        if (pageSize ==1){
            adapter.clearList();

            if (list.size()<=0){
                mRecyclerView.setEmptyTextView();
            }else {
                adapter.addItems(list);
            }
        }else {
            adapter.addItems(list, adapter.getItemCount() - 1);
        }
    }

    @Override
    public void queryDynamicListFailure(int code, String msg) {
        ToastUtil.showToast(getContext(),msg);
    }

    @Override
    public void queryDynamicListEnd() {
        if (pageSize ==1){
            mRecyclerView.closeDownRefresh();
        }else {
            mRecyclerView.onLoadMoreFinish();
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("articleId",adapter.getList().get(position).getArticleId());

        ISkipActivityUtil.startIntent(getActivity(), DynamicDetailsActivity.class,bundle);
    }
}
