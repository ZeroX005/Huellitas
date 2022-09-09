package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Adapters.MetodoPagoAdapter;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.Tarjeta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MetodoPago extends AppCompatActivity {

    private CardView cvEfectivo,cvAddTarjeta;
    private RecyclerView rvMetodosPagos;
    private MetodoPagoAdapter adapter;
    ArrayList<Tarjeta> listaTarjeta;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvEfectivo = findViewById(R.id.cvEfectivo);
        cvAddTarjeta = findViewById(R.id.cvAddTarjeta);
        rvMetodosPagos = findViewById(R.id.rvMetodosPagos);

        rvMetodosPagos.setLayoutManager(new LinearLayoutManager(MetodoPago.this));
        adapter = new MetodoPagoAdapter(MetodoPago.this);
        rvMetodosPagos.setAdapter(adapter);
        listaTarjeta = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWS();

        Bundle parametros = this.getIntent().getExtras();

        String fecha_r = "";
        String hora_r = "";
        String direccion_r = "";
        String tiempo_paseo_r = "";

        if(parametros !=null){
             fecha_r = parametros.getString("fecha");
             hora_r = parametros.getString("hora");
             direccion_r = parametros.getString("direccion");
             tiempo_paseo_r = parametros.getString("tiempo_paseo");
        }

        final Bundle retorno = new Bundle();
        retorno.putString("fecha_re",fecha_r);
        retorno.putString("hora_re",hora_r);
        retorno.putString("direccion_re",direccion_r);
        retorno.putString("tiempo_paseo_re",tiempo_paseo_r);


        cvEfectivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReservarPaseo.class);
                intent.putExtra("efectivo",1);
                intent.putExtras(retorno);
                startActivity(intent);
                finish();
            }
        });

        cvAddTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AgregarTarjeta.class);
                intent.putExtras(retorno);
                startActivity(intent);
            }
        });


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
        String url = con.ConecBase() + con.ListarTarjeta();
        SharedPreferences preferencesP = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("cod_cliente", preferencesP.getString("idusuario",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("objeto");
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objTarj = jsonArray.getJSONObject(i);
                        listaTarjeta.add(new Tarjeta(objTarj.getInt("cod_tarjeta"),
                                                      objTarj.getString("nro_tarjeta"),
                                                      objTarj.getString("nombre_propietario"),
                                                      objTarj.getString("apellido_propietario"),
                                                      objTarj.getInt("cod_cliente")));
                    }
                    adapter.agregarElementos(listaTarjeta);
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
