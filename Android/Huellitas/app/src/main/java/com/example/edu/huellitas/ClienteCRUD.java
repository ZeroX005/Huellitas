package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.CRUD_Cliente;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ClienteCRUD extends AppCompatActivity {

    private TextView tvcodcli,tvusuariocli2,tvcontrasenacli,tvtipocli,tvnombrecli2,tvapellidocli2,tvdireccioncli,tvfncli,tvcorreocli,tvcelularcli;
    private Button btnactualizarcli,btneliminarcli;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvcodcli=findViewById(R.id.tvcodcli);
        tvusuariocli2=findViewById(R.id.tvusuariocli2);
        tvcontrasenacli=findViewById(R.id.tvcontrasenacli);
        tvtipocli=findViewById(R.id.tvtipocli);
        tvnombrecli2=findViewById(R.id.tvnombrecli2);
        tvapellidocli2=findViewById(R.id.tvapellidocli2);
        tvdireccioncli=findViewById(R.id.tvdireccioncli);
        tvfncli=findViewById(R.id.tvfncli);
        tvcorreocli=findViewById(R.id.tvcorreocli);
        tvcelularcli=findViewById(R.id.tvcelularcli);
        btnactualizarcli=findViewById(R.id.btnactualizarcli);
        btneliminarcli=findViewById(R.id.btneliminarcli);

        mQueue = Volley.newRequestQueue(this);

        if(getIntent().hasExtra("cliente")){
            CRUD_Cliente objCli = getIntent().getParcelableExtra("cliente");
            tvcodcli.setText(String.valueOf(objCli.getCodigo()));
            tvusuariocli2.setText(objCli.getUsuario());
            tvcontrasenacli.setText(objCli.getContrasena());
            tvtipocli.setText(objCli.getTipo());
            tvnombrecli2.setText(objCli.getNombres());
            tvapellidocli2.setText(objCli.getApellidos());
            tvdireccioncli.setText(objCli.getDireccion());
            tvfncli.setText(objCli.getFecha_nacimiento());
            tvcorreocli.setText(objCli.getCorreo());
            tvcelularcli.setText(String.valueOf(objCli.getCelular()));
        }

        btnactualizarcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("codigo",tvcodcli.getText().toString());
                bundle.putString("usuario",tvusuariocli2.getText().toString());
                bundle.putString("contrasena",tvcontrasenacli.getText().toString());
                bundle.putString("tipo",tvtipocli.getText().toString());
                bundle.putString("nombre",tvnombrecli2.getText().toString());
                bundle.putString("apellido",tvapellidocli2.getText().toString());
                bundle.putString("direccion",tvdireccioncli.getText().toString());
                bundle.putString("fecha_naci",tvfncli.getText().toString());
                bundle.putString("correo",tvcorreocli.getText().toString());
                bundle.putString("celular",tvcelularcli.getText().toString());

                Intent intent = new Intent(getApplicationContext(),ActualizarClienteCRUD.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btneliminarcli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsumirWS();
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

    public void ConsumirWS(){
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.EliminarCliente();
        Map<String, String> params = new HashMap<>();
        params.put("codigo", tvcodcli.getText().toString());
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("rpta")) {
                        startActivity(new Intent(ClienteCRUD.this,MenuAdmin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{

                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
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
