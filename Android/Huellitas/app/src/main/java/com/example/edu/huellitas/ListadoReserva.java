package com.example.edu.huellitas;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Adapters.ListadoReservaAdapter;
import com.example.edu.huellitas.Modelos.CRUD_Reserva;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListadoReserva extends AppCompatActivity {

    private RecyclerView rvDatosReservaListado;
    private ListadoReservaAdapter adapter;
    ArrayList<CRUD_Reserva> listaReservas;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_reserva);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvDatosReservaListado = findViewById(R.id.rvDatosReservaListado);
        rvDatosReservaListado.setLayoutManager(new LinearLayoutManager(ListadoReserva.this));
        adapter = new ListadoReservaAdapter(ListadoReserva.this);
        rvDatosReservaListado.setAdapter(adapter);
        listaReservas = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWS();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.ListarReserva();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        JSONObject objRes = response.getJSONObject(i);
                        listaReservas.add(new CRUD_Reserva(objRes.getString("nro_ticket"),
                                objRes.getString("fecha_r"),
                                objRes.getString("hora_r"),
                                objRes.getString("direccion_r"),
                                objRes.getInt("tiempo_paseo_r"),
                                objRes.getInt("pago_r"),
                                objRes.getInt("precio_r"),
                                objRes.getString("fh_res_gen"),
                                objRes.getString("estado_r"),
                                objRes.getString("metodopago"),
                                objRes.getInt("cod_usuario_cli"),
                                objRes.getInt("cod_usuario_petw"),
                                objRes.getString("cliente"),
                                objRes.getString("petwalker")));
                    }
                    adapter.agregarElementos(listaReservas);
                }catch(JSONException ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
