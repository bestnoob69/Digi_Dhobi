package com.example.digi_dhobi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digi_dhobi.R;
import com.example.digi_dhobi.ViewHolder.WashViewHolder;
import com.example.digi_dhobi.model.Wash;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WashViewAdapter extends RecyclerView.Adapter<WashViewHolder>{

    private Context context;
    private List<Wash> washes;

    public WashViewAdapter(Context context, List<Wash> washes) {
        this.context = context;
        this.washes = washes;
    }

    @NonNull
    @Override
    public WashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.washes_layout, parent, false);
        WashViewHolder holder = new WashViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WashViewHolder holder, int position) {
        final Wash wash = washes.get(position);
        holder.items.setText("Number Of Clothes : " + wash.getUnits());

        Date d = new Date(wash.getSubmitTime()*1000);
        DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        holder.dropDate.setText("Drop Off Date : " + f.format(d));

        Date d1 = new Date(wash.getPickupTime()*1000);
        DateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
        holder.pickDate.setText("Pick Up Date : " + f1.format(d1));

        holder.token.setText("Token : " + wash.getToken());


    }

    @Override
    public int getItemCount() {
        return washes == null ? 0 : washes.size();
    }
}
