package com.example.mtestlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BaseFragment
 * 2018/8/10 13:50
 * zhuguoqing
 * 作用: 所有fragment的基类
 */
public abstract class BaseFragment extends Fragment {
    private BasePresenter presenter = null;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = View.inflate(mContext, getLayoutResId(), null);
        presenter = bindPresenter();
        initView(view);
        initData();
        return view;
    }


    public abstract int getLayoutResId();


    public abstract void initView(View view);


    public abstract void initData();

    //绑定presenter，主要用于销毁工作
    protected abstract BasePresenter bindPresenter();


    //如果重写了此方法，一定要调用super.onDestroy();
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }
}
