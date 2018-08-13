package com.example.mtestlibrary.base;

/**
 * BaseView
 * 2018/8/9 14:23
 * zhuguoqing
 * 作用: 所有的view层的基类
 */
public interface BaseView<P> {
    void loadingStar();//加载开始loading
    void loadingDismiss();//加载完毕关闭loading
    void showErorr(String mes);//错误信息
}
