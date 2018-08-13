package com.example.home_lib.contract;

import com.example.home_lib.model.PrenBean;
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
        void showData(PrenBean bean);

    }
    interface HttpPresenter extends BasePresenter{
        void loadHttpInfo();

    }

}
