package com.example.chint.week2_project;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chint on 6/26/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView title, desc, btitle, bdesc;
    public ImageView img, bimg;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.text1);
        desc = (TextView) itemView.findViewById(R.id.text2);

        img = (ImageView) itemView.findViewById(R.id.image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
