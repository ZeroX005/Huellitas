package com.example.edu.huellitas;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

public class GenerarBackup extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_backup);

        mQueue = Volley.newRequestQueue(this);

        ConsumirWS();
        startActivity(new Intent(GenerarBackup.this,MenuAdmin.class));
        Toast.makeText(getApplicationContext(),"Backup generado",Toast.LENGTH_LONG).show();

    }


    public void ConsumirWS(){
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.BackupBD();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

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
