package com.example.home_lib.contract;

import com.example.home_lib.model.AppUpdateBean;
import com.example.mtestlibrary.base.BasePresenter;
import com.example.mtestlibrary.base.BaseView;

/**
 * MainContract
 * 2018/8/9 14:44
 * zhuguoqing
 * 作用:
 */
public interface MainContract {
    interface View extends BaseView<Presenter> {
        //ui处理的数据 loading
        void showData(AppUpdateBean bean);

    }

    interface Presenter extends BasePresenter {
        //业务逻辑处理
        void loadUserInfo(int code);

    }
}
