package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.regex.Pattern;

public class RegistrarPetwalkerCRUD extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +       //at least 1 digit
                    //"(?=.*[a-z])" +       //at least 1 lower case letter
                    "(?=.*[A-Z])" +       //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=*])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$");


    private EditText etnombrepetw2,etapellidopetw2,etdireccionpetw,etdnipetw,etcorreopetw,etfnpetw,etcelularpetw,etusuariopetw2,etcontrasenapetw;
    private Button btnRegistrarPetw;
    private LinearLayout cargandoPETW;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_petwalker_crud);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        etnombrepetw2 = findViewById(R.id.etnombrepetw2);
        etapellidopetw2 = findViewById(R.id.etapellidopetw2);
        etdireccionpetw = findViewById(R.id.etdireccionpetw);
        etdnipetw = findViewById(R.id.etdnipetw);
        etcorreopetw = findViewById(R.id.etcorreopetw);
        etfnpetw = findViewById(R.id.etfnpetw);
        etcelularpetw = findViewById(R.id.etcelularpetw);
        etusuariopetw2 = findViewById(R.id.etusuariopetw2);
        etcontrasenapetw = findViewById(R.id.etcontrasenapetw);
        btnRegistrarPetw = findViewById(R.id.btnRegistrarPetw);
        cargandoPETW = findViewById(R.id.cargandoPETW);

        mQueue = Volley.newRequestQueue(this);

        cargandoPETW.setVisibility(View.GONE);

        btnRegistrarPetw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnombrepetw2.getText().toString().equals("")) {
                    etnombrepetw2.setError("Campo Obligatorio");
                }else if(etapellidopetw2.getText().toString().equals("")){
                    etapellidopetw2.setError("Campo Obligatorio");
                }else if(etdireccionpetw.getText().toString().equals("")){
                    etdireccionpetw.setError("Campo Obligatorio");
                }else if(etdnipetw.getText().toString().equals("")){
                    etdnipetw.setError("Campo Obligatorio");
                }else if(etcorreopetw.getText().toString().equals("")){
                    etcorreopetw.setError("Campo Obligatorio");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(etcorreopetw.getText().toString().trim()).matches()){
                    etcorreopetw.setError("Ingrese un email válido");
                }else if(etfnpetw.getText().toString().equals("")){
                    etfnpetw.setError("Campo Obligatorio");
                }else if(etcelularpetw.getText().toString().equals("")){
                    etcelularpetw.setError("Campo Obligatorio");
                }else if(etusuariopetw2.getText().toString().equals("")){
                    etusuariopetw2.setError("Campo Obligatorio");
                }else if(etcontrasenapetw.getText().toString().equals("")){
                    etcontrasenapetw.setError("Campo Obligatorio");
                }else if(!PASSWORD_PATTERN.matcher(etcontrasenapetw.getText().toString().trim()).matches()){
                    etcontrasenapetw.setError("La contraseña debe contener 1 número, 1 mayúscula y 1 caracter (@,#,$,%,^,&,+,=,*)");
                }else{
                    etnombrepetw2.setEnabled(false);
                    etapellidopetw2.setEnabled(false);
                    etdireccionpetw.setEnabled(false);
                    etdnipetw.setEnabled(false);
                    etcorreopetw.setEnabled(false);
                    etfnpetw.setEnabled(false);
                    etcelularpetw.setEnabled(false);
                    etusuariopetw2.setEnabled(false);
                    etcontrasenapetw.setEnabled(false);
                    btnRegistrarPetw.setEnabled(false);

                    cargandoPETW.setVisibility(View.VISIBLE);
                    ConsumirSWRegistrar();
                }
            }
        });



        etfnpetw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfnpetw:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        etfnpetw.setFocusable(false);
        etfnpetw.setEnabled(true);
        etfnpetw.setCursorVisible(false);
        etfnpetw.setKeyListener(null);
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

    private void ConsumirSWRegistrar() {
        Conexion con = new Conexion();
        String url = con.ConecBase()+con.RegistrarPetwalker();
        Map<String, String> params = new HashMap<>();
        params.put("usuario", etusuariopetw2.getText().toString());
        params.put("contraseña", etcontrasenapetw.getText().toString());
        params.put("nombres", etnombrepetw2.getText().toString());
        params.put("apellidos", etapellidopetw2.getText().toString());
        params.put("direccion", etdireccionpetw.getText().toString());
        params.put("dni", etdnipetw.getText().toString());
        params.put("celular", etcelularpetw.getText().toString());
        params.put("fecha_nacimiento",etfnpetw.getText().toString());
        params.put("correo", etcorreopetw.getText().toString());
        params.put("tipo", "2");
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        cargandoPETW.setVisibility(View.GONE);
                        etnombrepetw2.setEnabled(true);
                        etapellidopetw2.setEnabled(true);
                        etdireccionpetw.setEnabled(true);
                        etdnipetw.setEnabled(true);
                        etcorreopetw.setEnabled(true);
                        etfnpetw.setEnabled(true);
                        etcelularpetw.setEnabled(true);
                        etusuariopetw2.setEnabled(true);
                        etcontrasenapetw.setEnabled(true);
                        btnRegistrarPetw.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al registrar petwalker",Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = year + "-" + (month+1) + "-" + day;
                etfnpetw.setText(selectedDate);
                etfnpetw.setError(null);
            }
        });

        newFragment.show(RegistrarPetwalkerCRUD.this.getSupportFragmentManager(),"datePicker");
    }
}
