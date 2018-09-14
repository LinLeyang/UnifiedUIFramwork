package com.penta.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class UUBaseViewHolder extends RecyclerView.ViewHolder{
    public UUBaseViewHolder(View itemView) {
        super(itemView);
        if (itemView == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
    }
}