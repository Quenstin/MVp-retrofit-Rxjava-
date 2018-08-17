package com.example.home_lib.presenter;


import com.example.home_lib.api.ApiUrl;
import com.example.home_lib.contract.MainContract;
import com.example.home_lib.model.AppUpdateBean;
import com.example.mtestlibrary.base.BaseBean;
import com.example.mtestlibrary.net.RetrofitBegin;
import com.example.mtestlibrary.rx.BaseObserver;
import com.example.mtestlibrary.rx.RxSchedulers;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * MainPresenter
 * 2018/8/9 14:56
 * zhuguoqing
 * 作用:
 */
public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadUserInfo(int code) {
        view.loadingStar();
        RetrofitBegin.getInstence().create(ApiUrl.class).getUpLoadApp(code)
                .compose(RxSchedulers.<BaseBean<AppUpdateBean>>compose())
                .subscribe(new BaseObserver<AppUpdateBean>() {
                    @Override
                    protected void onSuccees(BaseBean<AppUpdateBean> t) throws Exception {

                    }

                    @Override
                    protected void onFailure(Throwable e) throws Exception {

                    }
                });


    }




    @Override
    public void onDestroy() {
        view=null;
        System.gc();

    }
}
