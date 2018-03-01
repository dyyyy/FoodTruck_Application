package com.example.user.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.foodtruck.R;
import com.example.user.vo.FoodTruckVO;

import java.util.List;

/**
 * Created by bit-user on 2018-03-01.
 */

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.ViewHolder> {

    private List<FoodTruckVO> listItems;
    private Context context;

    public TabAdapter(List<FoodTruckVO> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);


        return new ViewHolder(view);
        */
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodTruckVO vo = listItems.get(position);
        holder.head.setText(vo.getFtruckName());
        holder.desc.setText(vo.getFtruckTel());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView head;
        public TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            /*
            head = itemView.findViewById(R.id.menuHead);
            desc = itemView.findViewById(R.id.textDescription);
            */
        }
    }
}
