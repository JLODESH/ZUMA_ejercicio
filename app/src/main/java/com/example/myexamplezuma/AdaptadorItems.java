package com.example.myexamplezuma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorItems extends RecyclerView.Adapter<AdaptadorItems.ViewHolderItems> {


    ArrayList<ItemsPojo> listaItems;

    public AdaptadorItems(ArrayList<ItemsPojo> listaItems) {
        this.listaItems = listaItems;
    }

    @NonNull
    @Override
    public ViewHolderItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car,null,false);
        return new ViewHolderItems(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItems holder, int position) {

        holder.txt1.setText(listaItems.get(position).getNombre());
        holder.txt2.setText(listaItems.get(position).getApellido());

    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolderItems extends RecyclerView.ViewHolder {

        TextView txt1,txt2;

        public ViewHolderItems(@NonNull View itemView) {
            super(itemView);
            txt1 = (TextView) itemView.findViewById(R.id.txt1);
            txt2 = (TextView) itemView.findViewById(R.id.txt2);
        }
    }
}
