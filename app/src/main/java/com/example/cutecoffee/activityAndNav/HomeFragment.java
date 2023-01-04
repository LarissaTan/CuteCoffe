package com.example.cutecoffee.activityAndNav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cutecoffee.MainActivity;
import com.example.cutecoffee.R;
import com.example.cutecoffee.util.MySQLiteHelper;

import java.text.DecimalFormat;


public class HomeFragment extends Fragment {
    private View view;

    //startActivityForResult(new Intent(AccountFragment.this,RechargeActivity.class),0);

//    private void refreshMoney(){
//        newMoney = MySQLiteHelper.getInstance(getApplicationContext()).getUserMoneyFromUserName(MainActivity.username);
//        //不使用科学计数法显示double类型数据，解决显示错误问题
//        df = new DecimalFormat("0.00");
//        result = df.format(newMoney);
//        tv_money.setText(result +"$");
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

}
