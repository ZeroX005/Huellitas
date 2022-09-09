package com.example.edu.huellitas;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Adapters.ListadoPetwalkerAdapter;
import com.example.edu.huellitas.Modelos.CRUD_Petwalker;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListadoPetwalker extends AppCompatActivity {

    private RecyclerView rvDatosPetwalker;
    private ListadoPetwalkerAdapter adapter;
    ArrayList<CRUD_Petwalker> listaPetwalkers;
    private FloatingActionButton agregarPetwalker;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_petwalker);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        agregarPetwalker = findViewById(R.id.agregarPetwalker);

        rvDatosPetwalker = findViewById(R.id.rvDatosPetwalker);
        rvDatosPetwalker.setLayoutManager(new LinearLayoutManager(ListadoPetwalker.this));
        adapter = new ListadoPetwalkerAdapter(ListadoPetwalker.this);
        rvDatosPetwalker.setAdapter(adapter);
        listaPetwalkers = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWS();

        agregarPetwalker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrarPetwalkerCRUD.class));
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
        String url = con.ConecBase() + con.ListarPetwalker();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        JSONObject objPetw = response.getJSONObject(i);
                        listaPetwalkers.add(new CRUD_Petwalker(objPetw.getInt("codigo"),
                                objPetw.getString("nombres"),
                                objPetw.getString("apellidos"),
                                objPetw.getString("usuario"),
                                objPetw.getString("dni"),
                                objPetw.getInt("celular"),
                                objPetw.getString("contrasena"),
                                objPetw.getString("direccion"),
                                objPetw.getString("tipo"),
                                objPetw.getString("correo"),
                                objPetw.getString("fecha_nacimiento")));
                    }
                    adapter.agregarElementos(listaPetwalkers);
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
