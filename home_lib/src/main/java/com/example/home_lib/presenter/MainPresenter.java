package com.example.home_lib.presenter;


import com.example.home_lib.api.ApiUrl;
import com.example.home_lib.contract.MainContract;
import com.example.home_lib.model.AppUpdateBean;
import com.example.mtestlibrary.net.RetrofitBegin;

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
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppUpdateBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppUpdateBean testBean) {
                        view.loadingDismiss();
                        view.showData(testBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErorr(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }




    @Override
    public void onDestroy() {
        view=null;
        System.gc();

    }
}
