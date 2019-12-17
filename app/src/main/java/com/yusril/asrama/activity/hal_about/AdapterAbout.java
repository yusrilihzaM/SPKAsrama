package com.yusril.asrama.activity.hal_about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yusril.asrama.R;

import java.util.ArrayList;

public class AdapterAbout extends RecyclerView.Adapter<AdapterAbout.MyViewHolder >{
    private Context context;
    private ArrayList<ModelProfil> listprofil;
    private ArrayList<ModelProfil> getListprofil() {
        return listprofil;
    }
    public void setListHero(ArrayList<ModelProfil> listprofil) {
        this.listprofil = listprofil;
    }
    public AdapterAbout(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profil, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelProfil item=getListprofil().get(position);
        Glide.with(context)
                .load(item.getPhoto())
                .apply(new RequestOptions().override(350,550))
                .into(holder.img);
        holder.tvPeran.setText(item.getPeran());
        holder.tvNama.setText(item.getNama());
//        holder.tvemail.setText(item.getEmail());
        holder.tvNim.setText(item.getNim());
    }

    @Override
    public int getItemCount() {
        return getListprofil().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvNama,tvNim,tvPeran,tvemail;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.foto);
            tvNama=itemView.findViewById(R.id.tv_nama);
            tvNim=itemView.findViewById(R.id.nim);
            tvemail=itemView.findViewById(R.id.email);
            tvPeran=itemView.findViewById(R.id.tv_peran);
        }

    }
}
