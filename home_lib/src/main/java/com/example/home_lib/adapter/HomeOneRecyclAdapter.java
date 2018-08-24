package com.example.home_lib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.home_lib.R;

import java.util.List;

import ezy.ui.view.BadgeButton;

/**
 * HomeOneRecyclAdapter
 * 2018/8/20 13:49
 * zhuguoqing
 * 作用:
 */
public class HomeOneRecyclAdapter extends BaseQuickAdapter<String,BaseViewHolder>{



    public HomeOneRecyclAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
