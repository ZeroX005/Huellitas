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
import com.example.edu.huellitas.Adapters.FinalizadoAdapter;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaseosRealizados extends AppCompatActivity {

    private RecyclerView rvDatosPaseoRealizado;
    private FinalizadoAdapter adapter;
    ArrayList<ReservaP> listaPaseosF;

    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paseos_realizados);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvDatosPaseoRealizado = findViewById(R.id.rvDatosPaseoRealizado);
        rvDatosPaseoRealizado.setLayoutManager(new LinearLayoutManager(PaseosRealizados.this));
        adapter = new FinalizadoAdapter(PaseosRealizados.this);
        rvDatosPaseoRealizado.setAdapter(adapter);
        listaPaseosF = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWS();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,MenuPaseador.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.ReservaRealizadas();
        SharedPreferences preferencesP = getSharedPreferences("appLoginP",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("cod_usuario_petw", preferencesP.getString("idusuarioP",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("objeto");
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objPaseo = jsonArray.getJSONObject(i);
                        listaPaseosF.add(new ReservaP(objPaseo.getString("nro_ticket"),
                                objPaseo.getString("fecha_r"),
                                objPaseo.getString("hora_r"),
                                objPaseo.getString("direccion_r"),
                                objPaseo.getInt("tiempo_paseo_r"),
                                objPaseo.getInt("pago_r"),
                                objPaseo.getInt("precio_r"),
                                objPaseo.getString("fh_res_gen"),
                                objPaseo.getString("estado_r"),
                                objPaseo.getString("metodopago"),
                                objPaseo.getInt("cod_usuario_cli"),
                                objPaseo.getInt("cod_usuario_petw"),
                                objPaseo.getString("cliente"),
                                objPaseo.getString("petwalker")));
                    }
                    adapter.agregarElementos(listaPaseosF);
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
