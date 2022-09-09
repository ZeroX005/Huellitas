package com.example.edu.huellitas;

import android.content.Context;
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
import com.example.edu.huellitas.Adapters.ReclamoAdapter;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.Reclamo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistorialReclamos extends AppCompatActivity {

    private RecyclerView rvDatosReclamos;
    private ReclamoAdapter adapter;
    ArrayList<Reclamo> listaReclamos;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reclamos);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvDatosReclamos = findViewById(R.id.rvDatosReclamos);
        rvDatosReclamos.setLayoutManager(new LinearLayoutManager(HistorialReclamos.this));
        adapter = new ReclamoAdapter(HistorialReclamos.this);
        rvDatosReclamos.setAdapter(adapter);
        listaReclamos = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWSListarRcl();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void ConsumirWSListarRcl() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.ListarReclamoCli();
        SharedPreferences preferences = getSharedPreferences("appLogin",Context.MODE_PRIVATE);
        if(preferences.contains("idusuario")) {
            Map<String, String> params = new HashMap<>();
            params.put("cod_afectado", preferences.getString("idusuario", ""));
            JSONObject paramJSON = new JSONObject(params);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("objeto");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objReserva = jsonArray.getJSONObject(i);
                            listaReclamos.add(new Reclamo(objReserva.getString("nro_ticketReclamo"),
                                    objReserva.getString("asunto_rec"),
                                    objReserva.getString("detalle_rec"),
                                    objReserva.getString("fh_recl_gen"),
                                    objReserva.getString("nro_ticket"),
                                    objReserva.getString("estado_rec"),
                                    objReserva.getString("responsable"),
                                    objReserva.getInt("cod_responsable"),
                                    objReserva.getInt("cod_afectado")));
                        }
                        adapter.agregarElementos(listaReclamos);
                    } catch (JSONException ex) {
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
}
