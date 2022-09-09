package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PerfilP extends AppCompatActivity {

    private EditText etNombPS,etApePS,etEmailPS,etFechaNaciPS,etDireccionPS,etCelularPS;
    private Button btnRegreso2;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_p);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        etNombPS = findViewById(R.id.etNombPS);
        etApePS = findViewById(R.id.etApePS);
        etEmailPS = findViewById(R.id.etEmailPS);
        etFechaNaciPS = findViewById(R.id.etFechaNaciPS);
        etDireccionPS = findViewById(R.id.etDireccionPS);
        etCelularPS = findViewById(R.id.etCelularPS);
        btnRegreso2 = findViewById(R.id.btnRegreso2);

        mQueue = Volley.newRequestQueue(this);

        ConsumirWS();

        btnRegreso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuPaseador.class);
                finish();
                startActivity(intent);
            }
        });

        etNombPS.setFocusable(false);
        etNombPS.setEnabled(true);
        etNombPS.setCursorVisible(false);
        etNombPS.setKeyListener(null);

        etApePS.setFocusable(false);
        etApePS.setEnabled(true);
        etApePS.setCursorVisible(false);
        etApePS.setKeyListener(null);

        etEmailPS.setFocusable(false);
        etEmailPS.setEnabled(true);
        etEmailPS.setCursorVisible(false);
        etEmailPS.setKeyListener(null);

        etFechaNaciPS.setFocusable(false);
        etFechaNaciPS.setEnabled(true);
        etFechaNaciPS.setCursorVisible(false);
        etFechaNaciPS.setKeyListener(null);

        etDireccionPS.setFocusable(false);
        etDireccionPS.setEnabled(true);
        etDireccionPS.setCursorVisible(false);
        etDireccionPS.setKeyListener(null);

        etCelularPS.setFocusable(false);
        etCelularPS.setEnabled(true);
        etCelularPS.setCursorVisible(false);
        etCelularPS.setKeyListener(null);
    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.PerfilP();
        SharedPreferences preferences =getSharedPreferences("appLoginP",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("codigo", preferences.getString("idusuarioP",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    etNombPS.setText(response.getString("nombres"));
                    etApePS.setText(response.getString("apellidos"));
                    etEmailPS.setText(response.getString("correo"));
                    etFechaNaciPS.setText(response.getString("fecha_nacimiento"));
                    etDireccionPS.setText(response.getString("direccion"));
                    etCelularPS.setText(response.getString("celular"));
                }catch(Exception ex){
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
