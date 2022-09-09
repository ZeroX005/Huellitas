package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.CRUD_Petwalker;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PetwalkerCRUD extends AppCompatActivity {

    private TextView tvcodpetw,tvusuariopetw2,tvcontrasenapetw,tvdnipetw,tvtipopetw,tvnombrepetw2,tvapellidopetw2,tvdireccionpetw,tvfnpetw,tvcorreopetw,tvcelularpetw;
    private Button btnactualizarpetw,btneliminarpetw;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petwalker_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvcodpetw=findViewById(R.id.tvcodpetw);
        tvusuariopetw2=findViewById(R.id.tvusuariopetw2);
        tvcontrasenapetw=findViewById(R.id.tvcontrasenapetw);
        tvdnipetw=findViewById(R.id.tvdnipetw);
        tvtipopetw=findViewById(R.id.tvtipopetw);
        tvnombrepetw2=findViewById(R.id.tvnombrepetw2);
        tvapellidopetw2=findViewById(R.id.tvapellidopetw2);
        tvdireccionpetw=findViewById(R.id.tvdireccionpetw);
        tvfnpetw=findViewById(R.id.tvfnpetw);
        tvcorreopetw=findViewById(R.id.tvcorreopetw);
        tvcelularpetw=findViewById(R.id.tvcelularpetw);
        btnactualizarpetw=findViewById(R.id.btnactualizarpetw);
        btneliminarpetw=findViewById(R.id.btneliminarpetw);

        mQueue = Volley.newRequestQueue(this);

        if(getIntent().hasExtra("petwalker")){
            CRUD_Petwalker objPetw = getIntent().getParcelableExtra("petwalker");
            tvcodpetw.setText(String.valueOf(objPetw.getCodigo()));
            tvusuariopetw2.setText(objPetw.getUsuario());
            tvcontrasenapetw.setText(objPetw.getContrasena());
            tvdnipetw.setText(objPetw.getDni());
            tvtipopetw.setText(objPetw.getTipo());
            tvnombrepetw2.setText(objPetw.getNombres());
            tvapellidopetw2.setText(objPetw.getApellidos());
            tvdireccionpetw.setText(objPetw.getDireccion());
            tvfnpetw.setText(objPetw.getFecha_nacimiento());
            tvcorreopetw.setText(objPetw.getCorreo());
            tvcelularpetw.setText(String.valueOf(objPetw.getCelular()));
        }

        btnactualizarpetw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("codigo",tvcodpetw.getText().toString());
                bundle.putString("usuario",tvusuariopetw2.getText().toString());
                bundle.putString("contrasena",tvcontrasenapetw.getText().toString());
                bundle.putString("dni",tvdnipetw.getText().toString());
                bundle.putString("tipo",tvtipopetw.getText().toString());
                bundle.putString("nombre",tvnombrepetw2.getText().toString());
                bundle.putString("apellido",tvapellidopetw2.getText().toString());
                bundle.putString("direccion",tvdireccionpetw.getText().toString());
                bundle.putString("fecha_naci",tvfnpetw.getText().toString());
                bundle.putString("correo",tvcorreopetw.getText().toString());
                bundle.putString("celular",tvcelularpetw.getText().toString());

                Intent intent = new Intent(getApplicationContext(),ActualizarPetwalkerCRUD.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btneliminarpetw.setOnClickListener(new View.OnClickListener() {
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
        String url = con.ConecBase() + con.EliminarPetwalker();
        Map<String, String> params = new HashMap<>();
        params.put("codigo", tvcodpetw.getText().toString());
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("rpta")) {
                        startActivity(new Intent(PetwalkerCRUD.this,MenuAdmin.class));
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
