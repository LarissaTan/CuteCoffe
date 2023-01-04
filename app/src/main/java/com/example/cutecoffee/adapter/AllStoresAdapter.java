package com.example.cutecoffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cutecoffee.R;
import com.example.cutecoffee.bean.StoreBean;

import java.util.List;


public class AllStoresAdapter extends RecyclerView.Adapter<AllStoresAdapter.ViewHolder>{

    //设置数据源
    private LayoutInflater inflater ;
    private List<StoreBean> storeBeans;


    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }


    //适配器的构造方法
    public AllStoresAdapter(List<StoreBean> storeBeans){
        this.storeBeans = storeBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.store_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.iv_store_pic.setImageResource(R.mipmap.store_1);
        holder.tv_storeName.setText(storeBeans.get(position).getStoreName());
        holder.tv_storeScore.setText(storeBeans.get(position).getStoreScore());
        holder.tv_storeSell.setText(storeBeans.get(position).getStoreSell());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return storeBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

            private ImageView iv_store_pic ;
            private TextView tv_storeName ;
            private TextView tv_storeScore ;
            private TextView tv_storeSell ;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);


                iv_store_pic = itemView.findViewById(R.id.iv_store_pic);
                tv_storeName = itemView.findViewById(R.id.tv_storeName);
                tv_storeScore = itemView.findViewById(R.id.tv_store_score);
                tv_storeSell = itemView.findViewById(R.id.tv_store_sell);

                //实现item点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener != null){
                            onItemClickListener.onClick(view,getLayoutPosition(),storeBeans.get(getLayoutPosition()));
                        }
                    }
                });
            }
        }


    /**
     * RecycleView 的 点击事件 的 接口
     */
    public interface  OnItemClickListener  {
        void onClick(View v, int position, StoreBean storeBean);
    }

}
