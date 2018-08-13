package com.example.home_lib.view;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.home_lib.R;
import com.example.home_lib.contract.MainContract;
import com.example.home_lib.model.AppUpdateBean;
import com.example.home_lib.presenter.MainPresenter;
import com.example.mtestlibrary.base.BaseActivity;
import com.example.mtestlibrary.base.BasePresenter;

/**
 * MainActivity
 * 2018/8/10 14:58
 * zhuguoqing
 * 作用:
 */
public class MainActivity extends BaseActivity implements MainContract.View {
    private MainPresenter presenter;
    private Button bt_ok;
    private TextView txt_content;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        bt_ok = findViewById(R.id.bt_ok);
        txt_content=findViewById(R.id.txt_content);
        Log.d("cccc","eeeee");


    }

    @Override
    public void initData() {
        presenter = new MainPresenter(this);
        presenter.loadUserInfo(5);


    }

    @Override
    protected BasePresenter binPresenter() {
        return presenter;
    }





    @Override
    public void loadingStar() {
        Log.d("ccccc", "正在加载");

    }

    @Override
    public void loadingDismiss() {
        Log.d("ccccc", "加载完成");


    }

    @Override
    public void showErorr(String mes) {
        Log.d("ccccc", mes + "sorry");


    }


    @Override
    public void showData(AppUpdateBean bean) {
        bt_ok.setText(bean.message);
        txt_content.setText("查询时间:"+bean.data.date+"\n"+"    系统:"
                +bean.data.system+"    当前版本"+bean.data.version+"   url"+bean.data.updateUrl);
    }
}
