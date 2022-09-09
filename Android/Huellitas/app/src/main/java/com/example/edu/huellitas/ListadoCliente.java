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
import com.example.edu.huellitas.Adapters.ListadoClienteAdapter;
import com.example.edu.huellitas.Modelos.CRUD_Cliente;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListadoCliente extends AppCompatActivity {

    private RecyclerView rvDatosClientes;
    private ListadoClienteAdapter adapter;
    ArrayList<CRUD_Cliente> listaClientes;
    private FloatingActionButton agregarCliente;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_cliente);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        agregarCliente = findViewById(R.id.agregarCliente);

        rvDatosClientes = findViewById(R.id.rvDatosClientes);
        rvDatosClientes.setLayoutManager(new LinearLayoutManager(ListadoCliente.this));
        adapter = new ListadoClienteAdapter(ListadoCliente.this);
        rvDatosClientes.setAdapter(adapter);
        listaClientes = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        ConsumirWS();

        agregarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistrarClienteCRUD.class));
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
        String url = con.ConecBase() + con.ListarCliente();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i = 0; i < response.length(); i++){
                        JSONObject objCli = response.getJSONObject(i);
                        listaClientes.add(new CRUD_Cliente(objCli.getInt("codigo"),
                                objCli.getString("nombres"),
                                objCli.getString("apellidos"),
                                objCli.getString("usuario"),
                                objCli.getInt("celular"),
                                objCli.getString("contrasena"),
                                objCli.getString("direccion"),
                                objCli.getString("tipo"),
                                objCli.getString("correo"),
                                objCli.getString("fecha_nacimiento")));
                    }
                    adapter.agregarElementos(listaClientes);
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
