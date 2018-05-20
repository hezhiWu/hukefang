package com.yunwei.easydear.function.mainFuncations.searchFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseActivity;
import com.yunwei.easydear.base.DataApplication;
import com.yunwei.easydear.utils.ILog;
import com.yunwei.easydear.utils.ISpfUtil;
import com.yunwei.easydear.view.MeasuredGridView;
import com.yunwei.easydear.view.MeasuredListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/3/4.
 */
public class SearchActivity extends BaseActivity implements SearchContact.SearchView {

    final String TAG = getClass().getSimpleName();

    @BindView(R.id.search_root_layout)
    RelativeLayout mSearchRootLayout;
    @BindView(R.id.search_editText)
    EditText mSearchEditText;
    @BindView(R.id.search_hot_gridView)
    MeasuredGridView mSearchHotGridView;
    @BindView(R.id.search_history_textView)
    TextView mSearchHistoryTextView;
    @BindView(R.id.search_history_listView)
    MeasuredListView mSearchHistoryListView;

    private View mSearchMatchedView;
    private MeasuredListView mSearchMatchedListView;

    private SearchPresenter mSearchPresenter;
    private SearchHotAdapter mSearchHotAdapter;
    private String mSearchText;
    private String mMatchText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setToolbarTitle("搜索");
        setToolbarVisibility(View.GONE);
        ButterKnife.bind(this);
        initPresenter();
        setEditTextInputChangeListener();
        initSearchHotGridView();
        initSearchHistoryListView();
        initSearchKeyMatchedView();
    }

    private void initPresenter() {
        mSearchPresenter = new SearchPresenter(SearchRemoteRepo.getInstance(), this);
    }

    private void initSearchHotGridView() {
        mSearchHotAdapter = new SearchHotAdapter(this);
        mSearchHotGridView.setAdapter(mSearchHotAdapter);
        requestHotSearch();
    }

    private void initSearchHistoryListView() {
        String savedHistory = (String) ISpfUtil.getValue("search_history", "");
        ILog.v(TAG, "Saved search history = " + savedHistory);
        if (!savedHistory.isEmpty()) {
            final String[] savedHisArr = savedHistory.split(";");
            int len = savedHisArr.length;
            if (len > 0) {
                mSearchHistoryTextView.setVisibility(View.VISIBLE);
                mSearchHistoryListView.setVisibility(View.VISIBLE);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.item_search_list_layout, savedHisArr);
                mSearchHistoryListView.setAdapter(arrayAdapter);
                mSearchHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        saveAndSearch(savedHisArr[i]);
                    }
                });
            }
        }
    }

    private void initSearchKeyMatchedView() {
        mSearchMatchedView = LayoutInflater.from(this).inflate(R.layout.dialog_search_match_layout, null);
        mSearchMatchedListView = (MeasuredListView) mSearchMatchedView.findViewById(R.id.search_matched_listView);
    }

    private void requestHotSearch() {
        mSearchPresenter.requestHotSearch();
    }

    @Override
    public void onGetHotSearchSuccess(final List<SearchEntity> entities) {
        if (entities == null) {
            return;
        }
        mSearchHotAdapter.setSearchHotList((ArrayList<SearchEntity>) entities);
        mSearchHotAdapter.notifyDataSetChanged();
        mSearchHotGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String newSearch = entities.get(position).getMsg();
                saveAndSearch(newSearch);
            }
        });
    }

    @Override
    public void onGetHotSearchFailure(String msg) {

    }

    @Override
    public void onGetMatchedKeySuccess(List<SearchEntity> entities) {
        if (entities == null || entities.size() <= 0) {
            ILog.v(TAG, "onGetMatchedKeySuccess: entities empty!");
            return;
        }

        int size = entities.size();
        ILog.v(TAG, "onGetMatchedKeySuccess: entities size = " + size);
        final String[] searchMatchedArr = new String[size];
        for (int i = 0; i < size; i++) {
            searchMatchedArr[i] = entities.get(i).getMsg();
        }
        int len = searchMatchedArr.length;
        if (len > 0) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.item_search_list_layout, searchMatchedArr);
            mSearchMatchedListView.setAdapter(arrayAdapter);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mSearchRootLayout.removeView(mSearchMatchedView);
            mSearchRootLayout.addView(mSearchMatchedView, params);
            mSearchMatchedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ILog.v(TAG, "On Searched Key Clicked: " + searchMatchedArr[i]);
                    saveAndSearch(searchMatchedArr[i]);
                }
            });
        }
    }

    @Override
    public void onGetMatchedKeyFailure(String msg) {
        ILog.v(TAG, "onGetMatchedKeyFailure:: msg = " + msg);
        mSearchRootLayout.removeView(mSearchMatchedView);
    }

    private void saveAndSearch(String search) {
        saveSearchHistory(search);
        startToSearch(search);
    }

    private void saveSearchHistory(String searchText) {
        String savedHistory = (String) ISpfUtil.getValue("search_history", "");
        String newHistory = searchText + ";";
        if (!savedHistory.isEmpty()) {
            String[] savedHisArr = savedHistory.split(";");
            int len = savedHisArr.length;
            for (int i = 0; i < 4 && i < len; i++) {
                if (!savedHisArr[i].equals(searchText)) {
                    newHistory += savedHisArr[i] + ";";
                }
            }
        }
        ISpfUtil.setValue("search_history", newHistory);
        ILog.v(TAG, "Save new history = " + newHistory);
    }

    private void startToSearch(String searchText) {
        Intent intent = new Intent();
        intent.putExtra("search_key", searchText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick({R.id.search_cancel_textView, R.id.search_start_textView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_cancel_textView:
                finish();
                break;
            case R.id.search_start_textView:
                mSearchText = mSearchEditText.getText().toString();
                saveAndSearch(mSearchText);
                break;
            default:
                break;
        }
    }

    private void setEditTextInputChangeListener() {
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ILog.v(TAG, "onTextChanged:  charSequence = " + charSequence);
                mMatchText = charSequence.toString();
                if (mMatchText == null || mMatchText.isEmpty()) {
                    mSearchRootLayout.removeView(mSearchMatchedView);
                    return;
                }
                startToMatch(mMatchText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void startToMatch(String matchKey) {
        mSearchPresenter.requestKeyMatch();
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public String getKey() {
        return mMatchText;
    }
}
