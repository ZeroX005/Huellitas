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

import com.example.edu.huellitas.HistorialPaseosPendientes_detalle;
import com.example.edu.huellitas.Modelos.ReservaP;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class ReservaPPAdapter extends RecyclerView.Adapter<ReservaPPAdapter.ReservaPViewHolder> {

    private Context context;
    private ArrayList<ReservaP> lista;

    public ReservaPPAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReservaPPAdapter.ReservaPViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_reservaspendientes,viewGroup,false);
        return new ReservaPViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaPPAdapter.ReservaPViewHolder reservaPViewHolder, int i) {
        final ReservaP objRes = lista.get(i);
        reservaPViewHolder.tvTicket1.setText("Ticket: "+objRes.getNro_ticket());
        reservaPViewHolder.tvFechaHoraR.setText("F/H Registro: "+objRes.getFh_res_gen());
        reservaPViewHolder.tvEstadoR.setText("Estado: "+objRes.getEstado_r());
        reservaPViewHolder.contenedor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, HistorialPaseosPendientes_detalle.class);
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

    public class ReservaPViewHolder extends RecyclerView.ViewHolder {
        TextView tvFechaHoraR,tvTicket1,tvEstadoR;
        CardView contenedor3;
        public ReservaPViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFechaHoraR = itemView.findViewById(R.id.tvFechaHoraR);
            tvTicket1 = itemView.findViewById(R.id.tvTicket1);
            tvEstadoR = itemView.findViewById(R.id.tvEstadoR);
            contenedor3 = itemView.findViewById(R.id.contenedor3);

        }
    }
}
