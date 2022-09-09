package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActualizarReservaCRUD extends AppCompatActivity {

    private EditText etfrA,erhrA,etdireccionresA,ettiempopaseoA,etPagarConA;
    private TextView tvprecioA;
    private LinearLayout cargandoRESA;
    private Button btnActualizarResA;
    private RequestQueue mQueue;
    Spinner sp02,sp03;
    String ticket,totalpagar,codestare,codmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_reserva_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etfrA = findViewById(R.id.etfrA);
        erhrA = findViewById(R.id.erhrA);
        etdireccionresA = findViewById(R.id.etdireccionresA);
        ettiempopaseoA = findViewById(R.id.ettiempopaseoA);
        etPagarConA = findViewById(R.id.etPagarConA);
        tvprecioA = findViewById(R.id.tvprecioA);
        cargandoRESA = findViewById(R.id.cargandoRESA);
        btnActualizarResA = findViewById(R.id.btnActualizarResA);
        sp02 = findViewById(R.id.sp02);
        sp03 = findViewById(R.id.sp03);

        mQueue = Volley.newRequestQueue(this);
        cargandoRESA.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.opciones2_pendiente, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.opciones2_encurso, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.opciones2_finalizado, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.opciones3_efectivo, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.opciones3_tarjeta, android.R.layout.simple_spinner_item);

        Bundle parametros4 = this.getIntent().getExtras();
        if(parametros4 !=null){
            etfrA.setText(parametros4.getString("fecha_r"));
            erhrA.setText(parametros4.getString("hora_r"));
            etdireccionresA.setText(parametros4.getString("direccion_r"));
            ettiempopaseoA.setText(parametros4.getString("tiempo_paseo"));
            etPagarConA.setText(parametros4.getString("pagar_con"));
            tvprecioA.setText("S/."+parametros4.getString("precio"));
            ticket = parametros4.getString("ticket");
            totalpagar =parametros4.getString("precio");

            if(parametros4.getString("estado").equals("Pendiente")) {
                sp02.setAdapter(adapter1);
            } else if (parametros4.getString("estado").equals("En curso")){
                sp02.setAdapter(adapter2);
            } else if(parametros4.getString("estado").equals("Finalizado")){
                sp02.setAdapter(adapter3);
            }

            if(parametros4.getString("metodo_pago").equals("Efectivo")) {
                sp03.setAdapter(adapter4);
            } else if (parametros4.getString("metodo_pago").equals("Tarjeta")){
                sp03.setAdapter(adapter5);
            }
        }

        btnActualizarResA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etfrA.getText().toString().equals("")){
                    etfrA.setError("Campo Obligatorio");
                }else if(erhrA.getText().toString().equals("")){
                    erhrA.setError("Campo Obligatorio");
                }else if(etdireccionresA.getText().toString().equals("")){
                    etdireccionresA.setError("Campo Obligatorio");
                }else if(ettiempopaseoA.getText().toString().equals("")){
                    ettiempopaseoA.setError("Campo Obligatorio");
                }else if(Integer.parseInt(ettiempopaseoA.getText().toString()) > 4){
                    ettiempopaseoA.setError("El máximo de horas es 4");
                }else if(Integer.parseInt(ettiempopaseoA.getText().toString()) == 0 ){
                    ettiempopaseoA.setError("Ingrese tiempo estimado");
                }else if(etPagarConA.getText().toString().equals("")){
                    etPagarConA.setError("Campo Obligatorio");
                }else {
                    cargandoRESA.setVisibility(View.VISIBLE);
                    etfrA.setEnabled(false);
                    erhrA.setEnabled(false);
                    etdireccionresA.setEnabled(false);
                    ettiempopaseoA.setEnabled(false);
                    etPagarConA.setEnabled(false);
                    btnActualizarResA.setEnabled(false);
                    ConsumirWSReserva();
                }

            }
        });

        /*------------FECHA-------------*/

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        etfrA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActualizarReservaCRUD.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = year + "-" + month + "-" + day;
                        etfrA.setText(selectedDate);
                        etfrA.setError(null);
                    }
                },year,month,day
                );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();
            }
        });



        /*------------HORA-------------*/

        etfrA.setFocusable(false);
        etfrA.setEnabled(true);
        etfrA.setCursorVisible(false);
        etfrA.setKeyListener(null);

        erhrA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.erhrA:
                        showTimePickerDialog();
                        break;
                }
            }
        });

        erhrA.setFocusable(false);
        erhrA.setEnabled(true);
        erhrA.setCursorVisible(false);
        erhrA.setKeyListener(null);


        /*------------TIEMPO DE PASEO-------------*/

        ettiempopaseoA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(ettiempopaseoA.getText().toString().equals("")){
                    tvprecioA.setText("0");
                }else if(Integer.parseInt(ettiempopaseoA.getText().toString()) > 4){
                    ettiempopaseoA.setError("El máximo de horas es 4");
                    ettiempopaseoA.setText("0");
                    tvprecioA.setText("0");
                }else if(Integer.parseInt(ettiempopaseoA.getText().toString()) == 0){
                    ettiempopaseoA.setError("El máximo de horas es 4");
                }else {
                    int tiempoEstimado = Integer.parseInt(ettiempopaseoA.getText().toString());
                    int precio = tiempoEstimado * 15;
                    tvprecioA.setText("S/."+precio);
                    totalpagar = String.valueOf(precio);
                }
            }
        });

        ettiempopaseoA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                } else if(ettiempopaseoA.getText().toString().equals("")){
                    ettiempopaseoA.setText("0");
                }

            }
        });

        tvprecioA.setEnabled(false);
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

    private void ConsumirWSReserva() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.ActualizarReserva();
        Map<String, String> params = new HashMap<>();
        params.put("nro_ticket",ticket);
        params.put("fecha_r",etfrA.getText().toString());
        params.put("hora_r",erhrA.getText().toString());
        params.put("direccion_r",etdireccionresA.getText().toString());
        params.put("tiempo_paseo_r",ettiempopaseoA.getText().toString());
        params.put("pago_r",etPagarConA.getText().toString());
        params.put("precio_r",totalpagar);
        if(sp02.getSelectedItem().toString().equals("Pendiente")){
            codestare = "1";
        }else if(sp02.getSelectedItem().toString().equals("En curso")){
            codestare = "2";
        }else if(sp02.getSelectedItem().toString().equals("Finalizado")){
            codestare = "3";
        }
        params.put("estado_r",codestare);
        if(sp03.getSelectedItem().toString().equals("Efectivo")){
            codmp = "1";
        }else if(sp03.getSelectedItem().toString().equals("Tarjeta")){
            codmp = "2";
        }
        params.put("metodopago",codmp);
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        Intent intent = new Intent(getApplicationContext(), MenuAdmin.class);
                        finish();
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        cargandoRESA.setVisibility(View.GONE);
                        etfrA.setEnabled(true);
                        erhrA.setEnabled(true);
                        etdireccionresA.setEnabled(true);
                        ettiempopaseoA.setEnabled(true);
                        etPagarConA.setEnabled(true);
                        btnActualizarResA.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al actualizar paseo",Toast.LENGTH_LONG).show();
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


    private void showTimePickerDialog() {
        TimePickerFragment newFragment1 = TimePickerFragment.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hora, int minutos) {
                erhrA.setText(String.format("%02d:%02d",hora,minutos));
                erhrA.setError(null);
            }
        });
        newFragment1.show(ActualizarReservaCRUD.this.getSupportFragmentManager(),"timePicker");
    }
}
