package com.example.eatup.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatup.R;
import com.example.eatup.ui.model.ListResto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListRestoAdapter extends RecyclerView.Adapter<ListRestoAdapter.ListRestoViewHolder> {

    ArrayList<ListResto> ListData = new ArrayList<>();

    public class ListRestoViewHolder extends RecyclerView.ViewHolder{

        TextView nameResto, nameJalan, textStar, textEye;
        ImageView image;
        ConstraintLayout itemResto;
        public ListRestoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameResto = itemView.findViewById(R.id.nameResto);
            nameJalan = itemView.findViewById(R.id.nameJalan);
            textStar = itemView.findViewById(R.id.textStar);
            textEye = itemView.findViewById(R.id.textEye);
            image = itemView.findViewById(R.id.imageView2);
            itemResto = itemView.findViewById(R.id.itemResto);
        }
    }

    OnRestaurantHolderClickListener listener = null;

    public interface OnRestaurantHolderClickListener{
        void onClick(View view, ListResto resto);
        void onItemClick(int position, View v);
    }

    public void setListener(OnRestaurantHolderClickListener listener){
        this.listener = listener;
        notifyDataSetChanged();
    }

    public void setListData(ArrayList<ListResto> listData) {
        ListData = listData;
        notifyDataSetChanged();
    }


    @NonNull
    @NotNull
    @Override
    public ListRestoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_resto , parent , false);
        Log.d("V12345", "sammmmm");
        return new ListRestoViewHolder(view);
    }

    public void setResources(int resources){

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListRestoViewHolder holder, int position){
        ListResto ListResto = ListData.get(position);
        Log.d("Z12345", String.valueOf(ListResto.namaResto));
        Log.d("A12345", "sam");
        holder.nameResto.setText(ListResto.namaResto);
        holder.nameJalan.setText(ListResto.jalanResto);
        holder.textStar.setText(ListResto.bintangResto);
        holder.textEye.setText(ListResto.seeResto);
        holder.image.setImageDrawable(ListResto.image);

        // Set onclicklistener pada view tvTitle (TextView)
        holder.itemResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Z12345", String.valueOf(ListResto.restaurantId));

            }
        });
    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }


}
