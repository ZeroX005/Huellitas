package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Adapters.ReservaPPAdapter;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistorialPaseosPendientes extends AppCompatActivity {

    private RecyclerView rvDatosReservas;
    private ReservaPPAdapter adapter;
    ArrayList<ReservaP> listaReserva;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_paseospendientes);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvDatosReservas = findViewById(R.id.rvDatosReservas);
        rvDatosReservas.setLayoutManager(new LinearLayoutManager(HistorialPaseosPendientes.this));
        adapter = new ReservaPPAdapter(HistorialPaseosPendientes.this);
        rvDatosReservas.setAdapter(adapter);
        listaReserva = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWSListarR();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,MenuCliente.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWSListarR() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.PaseosPendientes();
        SharedPreferences preferences =getSharedPreferences("appLogin",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("cod_usuario_cli", preferences.getString("idusuario",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("objeto");
                    for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject objReserva = jsonArray.getJSONObject(i);
                        listaReserva.add(new ReservaP(objReserva.getString("nro_ticket"),
                                objReserva.getString("fecha_r"),
                                objReserva.getString("hora_r"),
                                objReserva.getString("direccion_r"),
                                objReserva.getInt("tiempo_paseo_r"),
                                objReserva.getInt("pago_r"),
                                objReserva.getInt("precio_r"),
                                objReserva.getString("fh_res_gen"),
                                objReserva.getString("estado_r"),
                                objReserva.getString("metodopago"),
                                objReserva.getInt("cod_usuario_cli"),
                                objReserva.getInt("cod_usuario_petw"),
                                objReserva.getString("cliente"),
                                objReserva.getString("petwalker")));
                    }
                    adapter.agregarElementos(listaReserva);
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
