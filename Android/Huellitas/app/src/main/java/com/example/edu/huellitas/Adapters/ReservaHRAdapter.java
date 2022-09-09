package com.example.edu.huellitas.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edu.huellitas.HistorialReservasFinalizadas_detalle;
import com.example.edu.huellitas.Modelos.ReservaP;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class ReservaHRAdapter extends RecyclerView.Adapter<ReservaHRAdapter.ReservaHViewHolder> {

    private Context context;
    private ArrayList<ReservaP> lista;

    public ReservaHRAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReservaHRAdapter.ReservaHViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_reservasfinalizadas,viewGroup,false);
        return new ReservaHViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaHRAdapter.ReservaHViewHolder reservaHViewHolder, int i) {
        final ReservaP objRes = lista.get(i);
        reservaHViewHolder.tvEstadoFI2.setText("Estado: "+objRes.getEstado_r());
        reservaHViewHolder.tvTicket2.setText("Ticket: "+objRes.getNro_ticket());
        reservaHViewHolder.tvFechaHoraFI2.setText("F/H Registro: "+objRes.getFh_res_gen());
        reservaHViewHolder.contenedor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, HistorialReservasFinalizadas_detalle.class);
                intentHRD.putExtra("reserva",objRes);
                context.startActivity(intentHRD);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void agregarElementos(ArrayList<ReservaP> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    public class ReservaHViewHolder extends RecyclerView.ViewHolder {
        TextView tvEstadoFI2,tvTicket2,tvFechaHoraFI2;
        CardView contenedor4;
        public ReservaHViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEstadoFI2 = itemView.findViewById(R.id.tvEstadoFI2);
            tvTicket2 = itemView.findViewById(R.id.tvTicket2);
            tvFechaHoraFI2 = itemView.findViewById(R.id.tvFechaHoraFI2);
            contenedor4 = itemView.findViewById(R.id.contenedor4);

        }
    }
}
