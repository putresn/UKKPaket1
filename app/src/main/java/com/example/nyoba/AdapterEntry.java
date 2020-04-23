package com.example.nyoba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterEntry extends RecyclerView.Adapter <com.example.nyoba.AdapterEntry.EntryViewHolder> {
    Context mcontext;
    List<ModelEntry> mentry;
    DatabaseReference dRef;

    public AdapterEntry(Context context, List<ModelEntry> mentry) {
        this.mcontext = context;
        this.mentry = mentry;
    }

    @NonNull
    @Override
    public AdapterEntry.EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowen, parent, false);
        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEntry.EntryViewHolder holder, int position) {
        final ModelEntry modelentry = mentry.get(position);
        holder.namae.setText(modelentry.getNama());
        holder.jumlahe.setText(modelentry.getJumlah());
    }

    @Override
    public int getItemCount() {
        return mentry.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        TextView namae, jumlahe;
        View mView;

        public EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            namae = (TextView) itemView.findViewById(R.id.namae);
            jumlahe = (TextView) itemView.findViewById(R.id.nominale);
            dRef = FirebaseDatabase.getInstance().getReference("Pembayaran");

        }
    }
}

