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

import com.example.edu.huellitas.Modelos.CRUD_Petwalker;
import com.example.edu.huellitas.PetwalkerCRUD;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class ListadoPetwalkerAdapter extends RecyclerView.Adapter<ListadoPetwalkerAdapter.ListadoPetwalkerViewHolder>  {

    private Context context;
    private ArrayList<CRUD_Petwalker> lista;

    public ListadoPetwalkerAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListadoPetwalkerAdapter.ListadoPetwalkerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_listado_petwalker,viewGroup,false);
        return new ListadoPetwalkerViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoPetwalkerAdapter.ListadoPetwalkerViewHolder holder, int i) {
        final CRUD_Petwalker objPetw = lista.get(i);
        holder.tvnombrepetw.setText(objPetw.getNombres());
        holder.tvapellidopetw.setText(objPetw.getApellidos());
        holder.tvusuariopetw.setText(objPetw.getUsuario());
        holder.contenedorPETW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, PetwalkerCRUD.class);
                intentHRD.putExtra("petwalker",objPetw);
                context.startActivity(intentHRD);
            }
        });
    }

    public void agregarElementos(ArrayList<CRUD_Petwalker> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ListadoPetwalkerViewHolder extends RecyclerView.ViewHolder {
        TextView tvnombrepetw,tvapellidopetw,tvusuariopetw;
        CardView contenedorPETW;
        public ListadoPetwalkerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnombrepetw = itemView.findViewById(R.id.tvnombrepetw);
            tvapellidopetw = itemView.findViewById(R.id.tvapellidopetw);
            tvusuariopetw = itemView.findViewById(R.id.tvusuariopetw);
            contenedorPETW = itemView.findViewById(R.id.contenedorPETW);
        }
    }
}
