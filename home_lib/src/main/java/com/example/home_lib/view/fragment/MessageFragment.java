package com.example.home_lib.view.fragment;

import android.view.View;

import com.example.home_lib.R;
import com.example.mtestlibrary.base.BaseFragment;
import com.example.mtestlibrary.base.BasePresenter;

/**
 * MessageFragment
 * 2018/8/14 14:15
 * zhuguoqing
 * 作用:
 */
public class MessageFragment extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_mes;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
