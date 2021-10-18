package com.example.bbhlcandystore;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList candy_id, candy_name, candy_place, candy_address, candy_price, candy_date;
    Activity activity;
    Animation translate_anim;

    CustomAdapter(Activity activity, Context context, ArrayList candy_id, ArrayList candy_name, ArrayList candy_place, ArrayList candy_address, ArrayList candy_price, ArrayList candy_date)
    {
        this.activity = activity;
        this.context = context;
        this.candy_id = candy_id;
        this.candy_name = candy_name;
        this.candy_place = candy_place;
        this.candy_address = candy_address;
        this.candy_price = candy_price;
        this.candy_date = candy_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.candyid.setText(String.valueOf(candy_id.get(position)));
        holder.candyname.setText(String.valueOf(candy_name.get(position)));
        holder.candyplace.setText(String.valueOf(candy_place.get(position)));
        holder.candyaddress.setText(String.valueOf(candy_address.get(position)));
        holder.candyprice.setText(String.valueOf(candy_price.get(position)));
        holder.candydate.setText(String.valueOf(candy_date.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(candy_id.get(position)));
                intent.putExtra("name", String.valueOf(candy_name.get(position)));
                intent.putExtra("place", String.valueOf(candy_place.get(position)));
                intent.putExtra("address", String.valueOf(candy_address.get(position)));
                intent.putExtra("price", String.valueOf(candy_price.get(position)));
                intent.putExtra("date", String.valueOf(candy_date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return candy_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView candyid, candyname, candyplace, candyaddress, candyprice, candydate;
        protected LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            candyid = itemView.findViewById(R.id.candyid);
            candyname = itemView.findViewById(R.id.candyname);
            candyplace = itemView.findViewById(R.id.candymadein);
            candyaddress = itemView.findViewById(R.id.candyaddress);
            candyprice = itemView.findViewById(R.id.candyprice);
            candydate = itemView.findViewById(R.id.candydate);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
