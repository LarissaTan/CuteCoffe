package com.example.cutecoffee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cutecoffee.R;
import com.example.cutecoffee.bean.StoreBean;

/**
 * 商家信息fragment
 */
public class StoreIntroFragment extends Fragment {

    private StoreBean storeBean;
    private ImageView iv_store_pic ;
    private TextView tv_storeName ;
    private TextView tv_storeScore ;
    private TextView tv_storeSell ;
    private TextView tv_storeSign;

    public StoreIntroFragment(StoreBean storeBean){
        this.storeBean = storeBean;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_intro,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initView();
    }

    private void initData() {
    }

    private void initView() {
        iv_store_pic = getActivity().findViewById(R.id.iv_store_frag);
        tv_storeName = getActivity().findViewById(R.id.tv_storeName_frag);
        tv_storeScore = getActivity().findViewById(R.id.tv_store_score_frag);
        tv_storeSell = getActivity().findViewById(R.id.tv_store_sell_frag);
        tv_storeSign = getActivity().findViewById(R.id.tv_store_sign_frag);

        iv_store_pic.setImageResource(R.mipmap.store_1);

        tv_storeName.setText(storeBean.getStoreName());
        tv_storeScore.setText(storeBean.getStoreScore());
        tv_storeSell.setText(storeBean.getStoreSell());

    }
}
