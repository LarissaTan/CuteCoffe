package com.example.cutecoffee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cutecoffee.MainActivity;
import com.example.cutecoffee.R;
import com.example.cutecoffee.adapter.AllStoresAdapter;
import com.example.cutecoffee.bean.StoreBean;
import com.example.cutecoffee.util.MySQLiteHelper;
import com.example.cutecoffee.util.ShareUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;

public class HomeAllStoresActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarDrawerToggle toggle ;
    private DrawerLayout drawerLayout;
    private BottomNavigationView navigationView;
    private Toolbar toolbar;
    private TextView tv_bar_title;

    private AllStoresAdapter allStoresAdapter;
    private RecyclerView rv_stores;
    private List<StoreBean> storeBeans;
    private LinearLayout headerView;
    private int UserID ;

    private void initData(){
        storeBeans = new ArrayList<>();
        storeBeans = MySQLiteHelper.getInstance(getApplicationContext()).queryAllStores();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_all_stores);

        initData();
        ininView();
        hideScrollBar();
        setActionBar();
        setListener();
    }


    private void ininView() {
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        tv_bar_title = findViewById(R.id.tv_bar_title);
        tv_bar_title.setText("Cute Coffee Branches");

        UserID = MySQLiteHelper.getInstance(getApplicationContext()).GetUserId(MainActivity.username);


        rv_stores = findViewById(R.id.rv_stores);
        allStoresAdapter = new AllStoresAdapter(storeBeans);
        rv_stores.setItemAnimator(new DefaultItemAnimator());
        rv_stores.setLayoutManager(new LinearLayoutManager(this));
        rv_stores.setAdapter(allStoresAdapter);

        allStoresAdapter.setOnItemClickListener(new AllStoresAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, StoreBean storeBean) {
                startActivity(new Intent(HomeAllStoresActivity.this,StoreGoodsActivity.class).putExtra("storeID",storeBean.getID()));
            }
        });

    }

    private void hideScrollBar() {
        navigationView.getChildAt(0).setVerticalScrollBarEnabled(false);
    }

    /*set Action Bar*/
    private void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }




    private void setListener() {
        navigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.single_1:
                    startActivity(new Intent(HomeAllStoresActivity.this,OrderActivity.class));
                    break;
                case R.id.single_2:
                    startActivity(new Intent(HomeAllStoresActivity.this, MyAccountActivity.class));
                    break;
                case R.id.single_4:
                    startActivity(new Intent(HomeAllStoresActivity.this, MainActivity.class));
                    ShareUtils.putAuto_Login("0");
                    HomeAllStoresActivity.this.finish();
                    break;

            }
            return true;
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }


    /**
     * 设置返回两次退出程序的方法
     */
    protected long exitTime ; //记录第一次点击的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() - exitTime) > 2000 ){
                Toast.makeText(HomeAllStoresActivity.this,"再按一次退出商城",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                HomeAllStoresActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}