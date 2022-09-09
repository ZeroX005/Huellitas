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
import com.example.edu.huellitas.Adapters.ListadoReclamoAdapter;
import com.example.edu.huellitas.Modelos.CRUD_Reclamo;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListadoReclamo extends AppCompatActivity {

    private RecyclerView rvDatosReclamoListado;
    private ListadoReclamoAdapter adapter;
    ArrayList<CRUD_Reclamo> listaReclamos;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_reclamo);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvDatosReclamoListado = findViewById(R.id.rvDatosReclamoListado);
        rvDatosReclamoListado.setLayoutManager(new LinearLayoutManager(ListadoReclamo.this));
        adapter = new ListadoReclamoAdapter(ListadoReclamo.this);
        rvDatosReclamoListado.setAdapter(adapter);
        listaReclamos = new ArrayList<>();
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
        String url = con.ConecBase() + con.ListarReclamos();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        JSONObject objRcl = response.getJSONObject(i);
                        listaReclamos.add(new CRUD_Reclamo(objRcl.getString("nro_ticketReclamo"),
                                objRcl.getString("asunto_rec"),
                                objRcl.getString("detalle_rec"),
                                objRcl.getString("fh_recl_gen"),
                                objRcl.getString("nro_ticket"),
                                objRcl.getString("estado_rec"),
                                objRcl.getString("responsable"),
                                objRcl.getString("afectado"),
                                objRcl.getInt("cod_responsable"),
                                objRcl.getInt("cod_afectado")));
                    }
                    adapter.agregarElementos(listaReclamos);
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
