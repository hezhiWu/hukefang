package com.yunwei.easydear.function.mainFuncations.mineFuncation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yunwei.easydear.R;
import com.yunwei.easydear.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-02-18
 * Time: 11:45
 * Version:1.0
 */

public class ModiflyNickFragment extends BaseFragment {
    public static final String MODIFLY_NICK_FLAG = "modifly_nick";

    private static ModiflyNickFragment fragment;
    @BindView(R.id.FragmentModifyNick_editView)
    EditText FragmentModifyNickEditView;

    public static ModiflyNickFragment newInstance() {
        if (fragment == null) {
            fragment = new ModiflyNickFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_modify_nick, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public String getEditViewText(){
        return FragmentModifyNickEditView.getText().toString();
    }
}
