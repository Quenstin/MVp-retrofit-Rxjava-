package com.example.home_lib.presenter;



import com.example.home_lib.api.ApiUrl;
import com.example.home_lib.contract.HttpContract;
import com.example.home_lib.model.PrenBean;
import com.example.mtestlibrary.base.BaseBean;
import com.example.mtestlibrary.net.RetrofitBegin;
import com.example.mtestlibrary.rx.BaseObserver;
import com.example.mtestlibrary.rx.RxSchedulers;


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
                .compose(RxSchedulers.<BaseBean<PrenBean>>compose())
                .subscribe(new BaseObserver<PrenBean>() {
                    @Override
                    protected void onSuccees(BaseBean<PrenBean> t) throws Exception {
                       view.showData(t.returnInformation);

                    }

                    @Override
                    protected void onFailure(Throwable e) throws Exception {
                    view.showErorr(e.getMessage());
                    }
                });
        view.loadingDismiss();


    }

    @Override
    public void onDestroy() {

    }
}
