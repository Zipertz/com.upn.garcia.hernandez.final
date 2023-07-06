package com.example.comupngarciahernandezfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupngarciahernandezfinal.DetalleDuelistaActivity;
import com.example.comupngarciahernandezfinal.R;
import com.example.comupngarciahernandezfinal.modelos.Duelistas;

import java.util.ArrayList;

public class DuelistaAdapter extends RecyclerView.Adapter<DuelistaAdapter.DuelistaViewHolder>{

    ArrayList<Duelistas> listaDuelistas;
    public DuelistaAdapter(ArrayList<Duelistas> listaDuelistas){
        this.listaDuelistas = listaDuelistas;
    }
    @NonNull
    @Override
    public DuelistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_duelista,null,false);
        return new DuelistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuelistaViewHolder holder, int position) {
        holder.viewNombre.setText(listaDuelistas.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return listaDuelistas.size();
    }

    public class DuelistaViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre;
        public DuelistaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.name_duelista);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DetalleDuelistaActivity.class);
                    intent.putExtra("ID",listaDuelistas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
