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

import com.example.edu.huellitas.ClienteCRUD;
import com.example.edu.huellitas.Modelos.CRUD_Cliente;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class ListadoClienteAdapter extends RecyclerView.Adapter<ListadoClienteAdapter.ListadoClienteViewHolder> {

    private Context context;
    private ArrayList<CRUD_Cliente> lista;

    public ListadoClienteAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListadoClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_listado_clientes,viewGroup,false);
        return new ListadoClienteViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoClienteViewHolder holder, int i) {
        final CRUD_Cliente objCli = lista.get(i);
        holder.tvnombrecli.setText(objCli.getNombres());
        holder.tvapellidocli.setText(objCli.getApellidos());
        holder.tvusuariocli.setText(objCli.getUsuario());
        holder.contenedorCLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, ClienteCRUD.class);
                intentHRD.putExtra("cliente",objCli);
                context.startActivity(intentHRD);
            }
        });

    }

    public void agregarElementos(ArrayList<CRUD_Cliente> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ListadoClienteViewHolder extends RecyclerView.ViewHolder {
        TextView tvnombrecli,tvapellidocli,tvusuariocli;
        CardView contenedorCLI;
        public ListadoClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnombrecli = itemView.findViewById(R.id.tvnombrecli);
            tvapellidocli = itemView.findViewById(R.id.tvapellidocli);
            tvusuariocli = itemView.findViewById(R.id.tvusuariocli);
            contenedorCLI = itemView.findViewById(R.id.contenedorCLI);
        }
    }
}
