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

public class AdapterSPPData extends RecyclerView.Adapter <com.example.nyoba.AdapterSPPData.SPPViewHolder> {
    Context mcontext;
    List<ModelSPP> mspp;
    DatabaseReference dRef;

    public AdapterSPPData( Context context, List<ModelSPP> mspp) {
        this.mcontext = context;
        this.mspp = mspp;
    }

    @NonNull
    @Override
    public SPPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowspp,parent,false);
        return new SPPViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull AdapterSPPData.SPPViewHolder holder, int position) {
        final ModelSPP modelspp = mspp.get(position);
        holder.tahunp.setText(modelspp.getTahun());
        holder.nominalp.setText(modelspp.getNominal());
        holder.ubahspp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext,ActivityEditSPP.class);
                i.putExtra("idspp", modelspp.getIdspp());
                i.putExtra("tahun", modelspp.getTahun());
                i.putExtra("nominal", modelspp.getNominal());
                mcontext.startActivity(i);
            }
        });
        holder.deletespp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = modelspp.getIdspp();
                deleteInfo(id);

            }
        });

    }

    @Override
    public int getItemCount() { return mspp.size(); }

    public class SPPViewHolder extends RecyclerView.ViewHolder {
        TextView tahunp, nominalp;
        View mView;
        ImageView deletespp, ubahspp;

        public SPPViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tahunp = (TextView)itemView.findViewById(R.id.tahunspp);
            nominalp = (TextView)itemView.findViewById(R.id.nominalspp);
            deletespp = (ImageView)itemView.findViewById(R.id.buttondeletespp);
            dRef = FirebaseDatabase.getInstance().getReference("SPP");
            ubahspp = (ImageView)itemView.findViewById(R.id.buttonubahspp);

        }
    }

    private void deleteInfo(String id) {
        DatabaseReference dInfo = FirebaseDatabase.getInstance().getReference("SPP").child(id);
        dInfo.removeValue();
        Toast.makeText(mcontext, "Delete Success", Toast.LENGTH_SHORT).show();
    }


}
