package com.example.rvasm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ItemAdaptor>{

    private ArrayList<Item> itemArrayList = new ArrayList<>();

    public void setItemData(ArrayList<Item> itemArrayList){
        this.itemArrayList = itemArrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemAdaptor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);


        return new ItemAdaptor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdaptor holder, int position) {
        Item item = itemArrayList.get(position);
        if(item==null){
            return;
        }
        holder.name.setText(item.getName() + " " +(position + 1));
        holder.des.setText(item.getDes() + " " +(position + 1));

    }

    @Override
    public int getItemCount() {
        if (itemArrayList != 
                null){
            return itemArrayList.size();
        }
        return 0;
    }

    public class ItemAdaptor extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView des;

        public ItemAdaptor(@NonNull View itemView) {
            super(itemView);

             name = itemView.findViewById(R.id.itemName);
            des = itemView.findViewById(R.id.itemDes);

       }
    }


}
