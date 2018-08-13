package com.example.home_lib.presenter;

import com.example.home_lib.api.ApiUrl;
import com.example.home_lib.contract.HttpContract;
import com.example.home_lib.model.TestHttpBean;
import com.example.mtestlibrary.net.RetrofitBegin;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * HttpPresenterIm
 * 2018/8/13 10:42
 * zhuguoqing
 * 作用:
 */
public class HttpPresenterIm implements HttpContract.HttpPresenter {
    private HttpContract.HttpView view;

    public HttpPresenterIm(HttpContract.HttpView view) {
        this.view = view;
    }

    @Override
    public void loadHttpInfo() {
        view.loadingStar();
        RetrofitBegin.getInstence().create(ApiUrl.class).getTest()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestHttpBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestHttpBean bean) {
                        view.showData(bean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErorr(e.getMessage()+e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        view.loadingDismiss();


    }

    @Override
    public void onDestroy() {

    }
}
