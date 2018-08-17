package com.example.home_lib.view.fragment;

import android.view.View;

import com.example.home_lib.R;
import com.example.mtestlibrary.base.BaseFragment;
import com.example.mtestlibrary.base.BasePresenter;

/**
 * HomeFragment
 * 2018/8/14 14:14
 * zhuguoqing
 * 作用:
 */
public class HomeFragment extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
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
