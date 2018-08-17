package com.example.home_lib.view.activity;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.example.home_lib.R;
import com.example.home_lib.contract.MainContract;
import com.example.home_lib.model.AppUpdateBean;
import com.example.home_lib.presenter.MainPresenter;
import com.example.home_lib.view.fragment.CartFragment;
import com.example.home_lib.view.fragment.HomeFragment;
import com.example.home_lib.view.fragment.MessageFragment;
import com.example.home_lib.view.fragment.UserFragment;
import com.example.mtestlibrary.base.BaseActivity;
import com.example.mtestlibrary.base.BasePresenter;
import com.next.easynavigition.view.EasyNavigitionBar;

import java.util.ArrayList;
import java.util.List;

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
    private EasyNavigitionBar enb_bar;
    private String[] tabText = {"首页", "购物车", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.btn_nav_home_normal, R.mipmap.btn_nav_cart_normal, R.mipmap.btn_nav_msg_normal, R.mipmap.btn_nav_user_normal};
    //选中时icon
    private int[] selectIcon = {R.mipmap.btn_nav_home_press, R.mipmap.btn_nav_cart_press, R.mipmap.btn_nav_msg_press, R.mipmap.btn_nav_user_press};

    private List<Fragment> fragments = new ArrayList<>();


    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        enb_bar = findViewById(R.id.enb_bar);
        bt_ok = findViewById(R.id.bt_ok);
        txt_content = findViewById(R.id.txt_content);
        fragments.add(new HomeFragment());
        fragments.add(new CartFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserFragment());
        enb_bar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .mode(EasyNavigitionBar.MODE_ADD)
                .addIcon(R.mipmap.icon_main_publish)
                .fragmentManager(getSupportFragmentManager())
                .build();
        //数字消息大于99显示99+ 小于等于0不显示，取消显示则可以navigitionBar.setMsgPointCount(2, 0)
        enb_bar.setMsgPointCount(2, 109);
        enb_bar.setMsgPointCount(0, 5);
        //红点提示 第二个参数控制是否显示
        enb_bar.setHintPoint(3, true);


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

    }

    @Override
    public void loadingDismiss() {


    }

    @Override
    public void showErorr(String mes) {


    }


    @Override
    public void showData(AppUpdateBean bean) {
//        bt_ok.setText(bean.message);
        txt_content.setText("查询时间:" + bean.data.date + "\n" + "    系统:"
                + bean.data.system + "    当前版本" + bean.data.version + "   url" + bean.data.updateUrl);
    }
}
