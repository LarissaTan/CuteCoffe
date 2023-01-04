package com.example.cutecoffee.activityAndNav;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cutecoffee.MainActivity;
import com.example.cutecoffee.R;
import com.example.cutecoffee.adapter.OrderAdapter;
import com.example.cutecoffee.bean.OrderBean;
import com.example.cutecoffee.util.MySQLiteHelper;

import java.util.List;


public class OrderFragment extends Fragment {
    private View view;
    private RecyclerView tv_order;
    private OrderAdapter orderAdapter ;
    private List<OrderBean>  orderBeans;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragement_order, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        orderBeans = MySQLiteHelper.getInstance(getContext()).queryOrderBeanFromUserName(MainActivity.username);
    }

    private void initView() {

        tv_order = view.findViewById(R.id.rv_order);
        orderAdapter = new OrderAdapter(orderBeans);
        tv_order.setItemAnimator(new DefaultItemAnimator());
        tv_order.setLayoutManager(new LinearLayoutManager(getContext()));
        tv_order.setAdapter(orderAdapter);
    }
}