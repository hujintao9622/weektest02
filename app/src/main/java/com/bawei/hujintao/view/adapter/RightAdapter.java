package com.bawei.hujintao.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.hujintao.R;
import com.bawei.hujintao.model.bean.RightBean;
import com.bawei.hujintao.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2020/1/6 0006 上午 9:19
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {
    private List<RightBean.DataBean> list;

    public RightAdapter(List<RightBean.DataBean> data) {

        list = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.rightitem, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //获取对应数据
        RightBean.DataBean dataBean = list.get(position);
        holder.itName.setText(dataBean.getGoods_name());
        holder.itPrice.setText(dataBean.getCurrency_price()+"");
        NetUtil.getInstance().getPhoto(dataBean.getGoods_thumb(),holder.itImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.it_img)
        ImageView itImg;
        @BindView(R.id.it_name)
        TextView itName;
        @BindView(R.id.it_price)
        TextView itPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
