package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.Menu;
import com.example.edu.huellitas.Adapters.MenuAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuCliente extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvMenu;
    private MenuAdapter adapter;
    private TextView tvUsuarioNav,tvCorreoNav;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cliente);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        rvMenu = findViewById(R.id.rvMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(MenuCliente.this));
        adapter = new MenuAdapter(MenuCliente.this);
        rvMenu.setAdapter(adapter);

        mQueue = Volley.newRequestQueue(this);

        ArrayList<Menu> lista = new ArrayList<>();
        lista.add(new Menu(lista.size()+1,"Reservar Paseo",R.drawable.imagen3));
        lista.add(new Menu(lista.size()+1,"Paseos Pendientes",R.drawable.imagen4));
        lista.add(new Menu(lista.size()+1,"Historial de Reservas",R.drawable.perro11));

        adapter.agregarElementos(lista);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);
        tvUsuarioNav = hView.findViewById(R.id.tvUsuarioNav);
        tvCorreoNav = hView.findViewById(R.id.tvCorreoNav);
        ConsumirWSInformacion();

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
        if (id == R.id.nav_camera) {
            startActivity(new Intent(getApplicationContext(), Perfil.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(getApplicationContext(), HistorialReclamos.class));
        } else if (id == R.id.nav_cerrar) {
            SharedPreferences preferences =getSharedPreferences("appLogin",Context.MODE_PRIVATE);
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


    private void ConsumirWSInformacion() {
        Conexion con = new Conexion();
        String url = con.ConecBase()+con.PerfilU();
        SharedPreferences preferences =getSharedPreferences("appLogin",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("codigo", preferences.getString("idusuario",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    tvUsuarioNav.setText(response.getString("nombres") +" "+response.getString("apellidos"));
                    tvCorreoNav.setText(response.getString("correo"));
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
