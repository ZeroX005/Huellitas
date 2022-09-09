package com.example.edu.huellitas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu.huellitas.Modelos.CRUD_Reserva;
import com.example.edu.huellitas.R;
import com.example.edu.huellitas.ReservaCRUD;

import java.util.ArrayList;

public class ListadoReservaAdapter extends RecyclerView.Adapter<ListadoReservaAdapter.ListadoReservaViewHolder>  {

    private Context context;
    private ArrayList<CRUD_Reserva> lista;

    public ListadoReservaAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListadoReservaAdapter.ListadoReservaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_listado_reserva,viewGroup,false);
        return new ListadoReservaViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoReservaAdapter.ListadoReservaViewHolder holder, int i) {
        final CRUD_Reserva objRes = lista.get(i);
        holder.tvnroticket.setText(objRes.getNro_ticket());
        holder.tvestado.setText(objRes.getEstado());
        holder.tvfr.setText(objRes.getFh_res_gen());
        holder.contenedorRES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, ReservaCRUD.class);
                intentHRD.putExtra("reserva",objRes);
                context.startActivity(intentHRD);
            }
        });
    }

    public void agregarElementos(ArrayList<CRUD_Reserva> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ListadoReservaViewHolder extends RecyclerView.ViewHolder {
        TextView tvnroticket,tvestado,tvfr;
        CardView contenedorRES;
        public ListadoReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnroticket = itemView.findViewById(R.id.tvnroticket);
            tvestado = itemView.findViewById(R.id.tvestado);
            tvfr = itemView.findViewById(R.id.tvfr);
            contenedorRES = itemView.findViewById(R.id.contenedorRES);
        }
    }
}
