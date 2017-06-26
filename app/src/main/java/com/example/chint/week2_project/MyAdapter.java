package com.example.chint.week2_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chint on 6/26/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    String[] title;
    String[] description;
    int[] images;
    Context context;

    public static final int zero = 1;
    public static final int one = 2;

    public MyAdapter(Context context, String[] title, String[] description, int[] images) {
        this.title = title;
        this.description = description;
        this.images = images;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case zero: return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false));
            case one: return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list2, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {


        if(holder.getItemViewType() == zero) {
            holder.img.setImageResource(images[position]);
            holder.desc.setText(description[position]);
            holder.title.setText(title[position]);
        }
        else if (holder.getItemViewType() == one){
            holder.img.setImageResource(images[position]);
            holder.desc.setText(description[position]);
            holder.title.setText(title[position]);


        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //Toast.makeText(context, title[position]+": "+description[position], Toast.LENGTH_LONG).show();
                holder.img.buildDrawingCache();
                Bitmap bitmap = holder.img.getDrawingCache();


                Intent i = new Intent(context, DetailedActivity.class);

                i.putExtra("image", bitmap);
                i.putExtra("title", title[position]);
                i.putExtra("desc", description[position]);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 ==  0 ? zero : one);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}

