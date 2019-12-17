package com.yusril.asrama.activity.hal_tambah_mhs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yusril.asrama.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListMahasiswa extends RecyclerView.Adapter<AdapterListMahasiswa.MyViewHolder> {
    private Context mContext;
    private ArrayList<ModelMahasiswa> mahasiswaList=new ArrayList<>();
    int x;
    public AdapterListMahasiswa(Context mContext,ArrayList<ModelMahasiswa>mahasiswaList) {
        this.mContext = mContext;
        this.mahasiswaList=mahasiswaList;
    }
    public AdapterListMahasiswa(Context mContext) {
        this.mContext = mContext;
    }
    public void setdata(ArrayList<ModelMahasiswa> items) {
        mahasiswaList.clear();
        mahasiswaList.addAll(items);
        x=mahasiswaList.size();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mhs_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ModelMahasiswa mhsItems = mahasiswaList.get(position);
        int no=mahasiswaList.indexOf(mahasiswaList.get(position))+1;
        holder.no.setText(no+"");
        holder.nim.setText(mhsItems.getNim());
        holder.nama.setText(mhsItems.getNama());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,mhsItems.getNama(),Toast.LENGTH_SHORT).show();
                Intent moveWithDataIntent = new Intent(mContext, DetailMahasiswaActivity.class);
                moveWithDataIntent.putExtra(DetailMahasiswaActivity.EXTRA_DATA, mahasiswaList.get(position));
                mContext.startActivity(moveWithDataIntent);
                ((Activity)view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView no,nama,nim;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.no_mhs);
            nim=itemView.findViewById(R.id.nim_mhs);
            nama=itemView.findViewById(R.id.nama_mhs);
            linearLayout=itemView.findViewById(R.id.linearLayoutItem);

        }
    }
}
