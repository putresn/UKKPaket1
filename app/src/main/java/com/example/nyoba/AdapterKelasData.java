package com.example.nyoba;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterKelasData extends RecyclerView.Adapter <com.example.nyoba.AdapterKelasData.KelasViewHolder> {
    Context mcontext;
    List<ModelKelas> mkelas;
    DatabaseReference dRef;

    public AdapterKelasData( Context context, List<ModelKelas> mkelas) {
        this.mcontext = context;
        this.mkelas = mkelas;
    }
    @NonNull
    @Override
    public AdapterKelasData.KelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowk,parent,false);
        return new KelasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKelasData.KelasViewHolder holder, int position) {
        final ModelKelas modelkelas = mkelas.get(position);
        holder.namak.setText(modelkelas.getKelas());
        holder.ubahk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext,ActivityEditKelas.class);
                i.putExtra("id_kelas", modelkelas.getIdkelas());
                i.putExtra("kelas", modelkelas.getKelas());
                mcontext.startActivity(i);
            }
        });
        holder.deletek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = modelkelas.getIdkelas();
                deleteInfo(id);

            }
        });

    }
    @Override
    public int getItemCount() { return mkelas.size(); }

    public class KelasViewHolder extends RecyclerView.ViewHolder {
        TextView namak;
        View mView;
        ImageView deletek, ubahk;

        public KelasViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            namak = (TextView) itemView.findViewById(R.id.namakelas);
            deletek = (ImageView) itemView.findViewById(R.id.buttondeletek);
            dRef = FirebaseDatabase.getInstance().getReference("Kelas");
            ubahk = (ImageView) itemView.findViewById(R.id.buttonubahk);
        }

        }
        private void deleteInfo(String id) {
            DatabaseReference dInfo = FirebaseDatabase.getInstance().getReference("Kelas").child(id);
            dInfo.removeValue();
            Toast.makeText(mcontext, "Delete Success", Toast.LENGTH_SHORT).show();
        }
}
