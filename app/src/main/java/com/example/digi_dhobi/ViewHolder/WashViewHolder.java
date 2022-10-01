package com.example.digi_dhobi.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digi_dhobi.R;

public class WashViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView items, dropDate, pickDate, token;

    public WashViewHolder(@NonNull View itemView) {
        super(itemView);

        items = itemView.findViewById(R.id.wash_quantity);
        dropDate = itemView.findViewById(R.id.wash_drop_off_date);
        pickDate = itemView.findViewById(R.id.wash_pick_up_date);
        token = itemView.findViewById(R.id.wash_token);
    }

    @Override
    public void onClick(View view)
    {}
}
