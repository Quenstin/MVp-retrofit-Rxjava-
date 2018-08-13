package com.example.home_lib.view;

import android.widget.TextView;

import com.example.home_lib.R;
import com.example.home_lib.contract.HttpContract;
import com.example.home_lib.model.PrenBean;
import com.example.home_lib.presenter.HttpPresenterIm;
import com.example.mtestlibrary.base.BaseActivity;
import com.example.mtestlibrary.base.BasePresenter;

/**
 * HttpActivity
 * 2018/8/13 10:35
 * zhuguoqing
 * 作用:
 */
public class HttpActivity extends BaseActivity implements HttpContract.HttpView {
    private TextView txt_http;
    private HttpPresenterIm httpPresenterIm;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_http;
    }

    @Override
    public void initView() {
        txt_http=findViewById(R.id.txt_http);


    }

    @Override
    public void initData() {
        httpPresenterIm=new HttpPresenterIm(this);
        httpPresenterIm.loadHttpInfo();

    }

    @Override
    protected BasePresenter binPresenter() {
        return httpPresenterIm;
    }

    @Override
    public void showData(PrenBean bean) {
        txt_http.setText(bean.vlog+"ccc");


    }

    @Override
    public void loadingStar() {

    }

    @Override
    public void loadingDismiss() {

    }

    @Override
    public void showErorr(String mes) {

    }
}
