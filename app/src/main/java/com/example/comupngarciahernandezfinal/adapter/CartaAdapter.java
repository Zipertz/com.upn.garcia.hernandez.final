package com.example.comupngarciahernandezfinal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupngarciahernandezfinal.R;
import com.example.comupngarciahernandezfinal.modelos.Carta;

import java.util.ArrayList;

public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartaViewHolder> {
private ArrayList<Carta> listaCartas;

public CartaAdapter(ArrayList<Carta> listaCartas) {
        this.listaCartas = listaCartas;
        }

@NonNull
@Override
public CartaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta, parent, false);
        return new CartaViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull CartaViewHolder holder, int position) {
        Carta carta = listaCartas.get(position);
        holder.bind(carta);
        }

@Override
public int getItemCount() {
        return listaCartas.size();
        }

public class CartaViewHolder extends RecyclerView.ViewHolder {
    private TextView tvCarta;


    public CartaViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCarta = itemView.findViewById(R.id.tvCarta);

    }

    public void bind(Carta carta) {
        tvCarta.setText(carta.getMounstro());

    }
}
}
