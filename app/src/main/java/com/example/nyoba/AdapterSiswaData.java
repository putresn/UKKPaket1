package com.example.nyoba;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.media.Image;
import android.os.AsyncTask;
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

public class AdapterSiswaData extends RecyclerView.Adapter <AdapterSiswaData.SiswaViewHolder> {
    Context mcontext;
    List <ModelSiswa> msiswa;
    DatabaseReference dRef;

    public AdapterSiswaData( Context context, List<ModelSiswa> msiswa) {
        this.mcontext = context;
        this.msiswa = msiswa;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        final ModelSiswa modelsiswa = msiswa.get(position);
        holder.nama.setText(modelsiswa.getNama());
        holder.nisn.setText(modelsiswa.getNisn());
        holder.ubahsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext,ActivityEditSiswa.class);
                i.putExtra("nisn", modelsiswa.getNisn());
                i.putExtra("nama", modelsiswa.getNama());
                i.putExtra("kelas", modelsiswa.getKelas());
                i.putExtra("notelp", modelsiswa.getNotelp());
                i.putExtra("alamat", modelsiswa.getAlamat());
                i.putExtra("id_siswa", modelsiswa.getIdsiswa());
                mcontext.startActivity(i);
            }
        });
        holder.deletesiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = modelsiswa.getIdsiswa();
                deleteInfo(id);

            }
        });

    }

    @Override
    public int getItemCount() {
        return msiswa.size();
    }

    public class SiswaViewHolder extends RecyclerView.ViewHolder {
        TextView nama, nisn;
        View mView;
        ImageView deletesiswa, ubahsiswa;

        public SiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            nama = (TextView)itemView.findViewById(R.id.textnama);
            nisn = (TextView)itemView.findViewById(R.id.textnisn);
            deletesiswa = (ImageView)itemView.findViewById(R.id.buttondeletesiswa);
            dRef = FirebaseDatabase.getInstance().getReference("Siswa");
            ubahsiswa = (ImageView)itemView.findViewById(R.id.buttonubahsiswa);

        }
    }
    private void deleteInfo(String id) {
        DatabaseReference dInfo = FirebaseDatabase.getInstance().getReference("Siswa").child(id);
        dInfo.removeValue();
        Toast.makeText(mcontext, "Delete Success", Toast.LENGTH_SHORT).show();
    }
}
