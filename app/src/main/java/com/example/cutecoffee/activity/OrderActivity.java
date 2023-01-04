package com.example.cutecoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cutecoffee.MainActivity;
import com.example.cutecoffee.R;
import com.example.cutecoffee.adapter.OrderAdapter;
import com.example.cutecoffee.bean.OrderBean;
import com.example.cutecoffee.util.MySQLiteHelper;

import java.util.List;


public class OrderActivity extends AppCompatActivity {


    private TextView tv_bar_title;
    private Toolbar toolbar;
    private RecyclerView tv_order;
    private OrderAdapter orderAdapter ;
    private List<OrderBean>  orderBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initData();
        initView();
        setActionBar();
    }

    /*设置ActionBar*/
    private void setActionBar() {
        setSupportActionBar(toolbar);
        /*显示Home图标*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置不显示项目名称
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    //为toolbar设置返回按钮
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        orderBeans = MySQLiteHelper.getInstance(OrderActivity.this).queryOrderBeanFromUserName(MainActivity.username);
    }



    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tv_bar_title = findViewById(R.id.tv_bar_title);
        tv_bar_title.setText("订单信息");

        tv_order = findViewById(R.id.rv_order);
        orderAdapter = new OrderAdapter(orderBeans);
        tv_order.setItemAnimator(new DefaultItemAnimator());
        tv_order.setLayoutManager(new LinearLayoutManager(this));
        tv_order.setAdapter(orderAdapter);
    }
}