package com.example.cutecoffee.activityAndNav;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cutecoffee.R;
import com.example.cutecoffee.adapter.MyTabAdapter;
import com.example.cutecoffee.bean.StoreBean;
import com.example.cutecoffee.fragment.StoreCommentFragment;
import com.example.cutecoffee.fragment.StoreGoodsFragment;
import com.example.cutecoffee.fragment.StoreIntroFragment;
import com.example.cutecoffee.util.MySQLiteHelper;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;


public class StoreGoodsActivity extends AppCompatActivity {

    private TabLayout tabLayout = null;
    private MyTabAdapter myTabAdapter;
    private ViewPager home_news_viewPager;
    private StoreGoodsFragment storeGoodsFragment;
    private StoreCommentFragment storeCommentFragment;
    private StoreIntroFragment storeIntroFragment;
    private List<Fragment> fragments = new ArrayList<>();

    private TextView tv_bar_title;
    private Toolbar toolbar;
    private String storeID;
    private StoreBean storeBean;
    private ImageView iv_pic;
    private TextView tv_storeName;
    private TextView tv_storeSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_goods);
        storeID = "0";
        initData();
        initView();
        setActionBar();
    }

    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    //toolbar set return button
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initData() {
        storeBean = new StoreBean();
        storeBean = MySQLiteHelper.getInstance(getApplicationContext()).queryStoreBeanFromStoreID(storeID);
        storeGoodsFragment = new StoreGoodsFragment(storeID);
        storeCommentFragment = new StoreCommentFragment();
        storeIntroFragment = new StoreIntroFragment(storeBean);
        fragments.add(storeGoodsFragment);
        fragments.add(storeCommentFragment);
        fragments.add(storeIntroFragment);
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tv_bar_title = findViewById(R.id.tv_bar_title);
        tv_bar_title.setText("Details");
        tabLayout = findViewById(R.id.home_newsTab);
        home_news_viewPager = findViewById(R.id.home_news_vp);
        iv_pic = findViewById(R.id.iv_store_act);
        tv_storeName = findViewById(R.id.tv_storeName_act);
        tv_storeSell = findViewById(R.id.tv_store_sell_act);
        iv_pic.setImageResource(R.mipmap.store_1);


        tv_storeName.setText(storeBean.getStoreName());
        tv_storeSell.setText("Monthly sales："+storeBean.getStoreSell());

        //初始化tab的Adapter
        myTabAdapter = new MyTabAdapter(getSupportFragmentManager(),fragments);
        home_news_viewPager.setAdapter(myTabAdapter);
        tabLayout.setupWithViewPager(home_news_viewPager);

    }



}