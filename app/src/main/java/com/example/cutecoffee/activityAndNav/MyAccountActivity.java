package com.example.cutecoffee.activityAndNav;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.cutecoffee.MainActivity;
import com.example.cutecoffee.R;
import com.example.cutecoffee.bean.Userinfo;
import com.example.cutecoffee.util.MySQLiteHelper;


import java.text.DecimalFormat;


public class MyAccountActivity extends AppCompatActivity {

    private TextView tv_bar_title;
    private Toolbar toolbar;
    private TextView tv_recharge;
    private TextView tv_userName;
    private TextView tv_money;
    private TextView tv_phoneNumb;
    private Userinfo userinfo;
    private double newMoney;
    private int myUserID;
    private DecimalFormat df;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
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
    //set return button for tool bar
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        //从数据库获取userId和user信息
        userinfo = MySQLiteHelper.getInstance(getApplicationContext()).getUserInfoFromUserName(MainActivity.username);
        myUserID = MySQLiteHelper.getInstance(getApplicationContext()).GetUserId(MainActivity.username);
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tv_bar_title = findViewById(R.id.tv_bar_title);
        tv_bar_title.setText("My Wallet");
        tv_recharge = findViewById(R.id.tv_bar_function);
        tv_recharge.setVisibility(View.VISIBLE);
        tv_userName = findViewById(R.id.tv_username_acc);
        tv_money = findViewById(R.id.tv_money);
        tv_phoneNumb = findViewById(R.id.tv_phoneNumb_acc);


        tv_userName.setText(userinfo.getUserName());
        df = new DecimalFormat("0.00");
        result = df.format(userinfo.getMoney());
        tv_money.setText(result+"$");
        tv_phoneNumb.setText(userinfo.getPhoneNumb());


        tv_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivityForResult(new Intent(MyAccountActivity.this,RechargeActivity.class),0);
            }
        });


    }



    private void refreshMoney(){
        newMoney = MySQLiteHelper.getInstance(getApplicationContext()).getUserMoneyFromUserName(MainActivity.username);
        //不使用科学计数法显示double类型数据，解决显示错误问题
        df = new DecimalFormat("0.00");
        result = df.format(newMoney);
        tv_money.setText(result +"$");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            refreshMoney();
        }else {
            return;
        }
    }
}