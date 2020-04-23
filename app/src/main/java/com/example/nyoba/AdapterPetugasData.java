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

public class AdapterPetugasData extends RecyclerView.Adapter <com.example.nyoba.AdapterPetugasData.PetugasViewHolder> {
    Context mcontext;
    List<ModelUser> muser;
    DatabaseReference dRef;

    public AdapterPetugasData( Context context, List<ModelUser> muser) {
        this.mcontext = context;
        this.muser = muser;
    }

    @NonNull
    @Override
    public PetugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rowp,parent,false);
        return new PetugasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PetugasViewHolder holder, int position) {
            final ModelUser modeluser = muser.get(position);
            holder.namap.setText(modeluser.getNama_petugas());
            holder.levelp.setText(modeluser.getLevel());
            holder.ubahp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mcontext,ActivityEditPetugas.class);
                    i.putExtra("id_petugas", modeluser.getId_petugas());
                    i.putExtra("username", modeluser.getUsername());
                    i.putExtra("password", modeluser.getPassword());
                    i.putExtra("nama_petugas", modeluser.getNama_petugas());
                    i.putExtra("level", modeluser.getLevel());
                    mcontext.startActivity(i);
                }
            });
            holder.deletep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = modeluser.getId_petugas();
                    deleteInfo(id);

                }
            });

    }

    @Override
    public int getItemCount() { return muser.size(); }

    public class PetugasViewHolder extends RecyclerView.ViewHolder {
        TextView namap, levelp;
        View mView;
        ImageView deletep, ubahp;

        public PetugasViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            namap = (TextView)itemView.findViewById(R.id.namap);
            levelp = (TextView)itemView.findViewById(R.id.levelp);
            deletep = (ImageView)itemView.findViewById(R.id.buttondeletep);
            dRef = FirebaseDatabase.getInstance().getReference("User");
            ubahp = (ImageView)itemView.findViewById(R.id.buttonubahp);

        }
    }

    private void deleteInfo(String id) {
        DatabaseReference dInfo = FirebaseDatabase.getInstance().getReference("User").child(id);
        dInfo.removeValue();
        Toast.makeText(mcontext, "Delete Success", Toast.LENGTH_SHORT).show();
    }


}

