package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

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

public class MenuAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private TextView tvAdminNav,tvCorreoNavA;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        mQueue = Volley.newRequestQueue(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);
        tvAdminNav = hView.findViewById(R.id.tvAdminNav);
        tvCorreoNavA = hView.findViewById(R.id.tvCorreoNavA);
        ConsumirWSInformacionP();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfilA) {
            startActivity(new Intent(getApplicationContext(), PerfilA.class));
        } else if (id == R.id.nav_dbUSU) {
            startActivity(new Intent(getApplicationContext(), DashboardUsuario.class));
        } else if (id == R.id.nav_dbRECL) {
            startActivity(new Intent(getApplicationContext(), DashboardReclamo.class));
        } else if (id == R.id.nav_dbRES) {
            startActivity(new Intent(getApplicationContext(), DashboardReserva.class));
        } else if (id == R.id.nav_reservaA) {
            startActivity(new Intent(getApplicationContext(), ListadoReserva.class));
        } else if (id == R.id.nav_clienteA) {
            startActivity(new Intent(getApplicationContext(), ListadoCliente.class));
        } else if (id == R.id.nav_petwalkerA) {
            startActivity(new Intent(getApplicationContext(), ListadoPetwalker.class));
        } else if (id == R.id.nav_reclamoA) {
            startActivity(new Intent(getApplicationContext(), ListadoReclamo.class));
        } else if (id == R.id.nav_backup) {
            startActivity(new Intent(getApplicationContext(), GenerarBackup.class));
        } else if (id == R.id.nav_cerrarA) {
            SharedPreferences preferences =getSharedPreferences("appLoginA", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), Loguin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Sesión Cerrada",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void ConsumirWSInformacionP() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.PerfilA();
        SharedPreferences preferences =getSharedPreferences("appLoginA",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("codigo", preferences.getString("idusuarioA",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    tvAdminNav.setText(response.getString("nombres") +" "+response.getString("apellidos"));
                    tvCorreoNavA.setText(response.getString("correo"));
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
