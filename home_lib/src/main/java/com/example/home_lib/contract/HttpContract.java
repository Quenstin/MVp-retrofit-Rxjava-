package com.example.home_lib.contract;

import com.example.home_lib.model.TestHttpBean;
import com.example.mtestlibrary.base.BasePresenter;
import com.example.mtestlibrary.base.BaseView;

/**
 * HttpContract
 * 2018/8/13 10:38
 * zhuguoqing
 * 作用:
 */
public interface HttpContract {

    interface HttpView extends BaseView<HttpPresenter>{
        void showData(TestHttpBean bean);

    }
    interface HttpPresenter extends BasePresenter{
        void loadHttpInfo();

    }

}
