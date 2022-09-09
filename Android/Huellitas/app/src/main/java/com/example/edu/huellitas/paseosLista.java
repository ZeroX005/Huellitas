package com.example.edu.huellitas;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Adapters.PaseoPAdapter;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class paseosLista extends AppCompatActivity {

    private RecyclerView rvDatosPaseo;
    private PaseoPAdapter adapter;
    ArrayList<ReservaP> listaPaseos;

    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paseos_lista);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        rvDatosPaseo = findViewById(R.id.rvDatosPaseo);
        rvDatosPaseo.setLayoutManager(new LinearLayoutManager(paseosLista.this));
        adapter = new PaseoPAdapter(paseosLista.this);
        rvDatosPaseo.setAdapter(adapter);
        listaPaseos = new ArrayList<>();
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
        String url = con.ConecBase() + con.ReservasPendientes();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        JSONObject objPaseo = response.getJSONObject(i);
                        listaPaseos.add(new ReservaP(objPaseo.getString("nro_ticket"),
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
                                objPaseo.getString("cliente")));
                    }
                    adapter.agregarElementos(listaPaseos);
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
