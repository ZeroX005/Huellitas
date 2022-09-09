package com.example.edu.huellitas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.edu.huellitas.Modelos.Tarjeta;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReservarPaseo extends AppCompatActivity {

    private EditText etFechaE,etHoraE,etDireccionR,etTiempoP,etPrecio,etPagarCon;
    private Button btnReservar;
    private TextView etMetodoPago1,etMetodoPago2,nro_tarjeta,nomb_tarjeta;
    private LinearLayout LLPagarCon,LLtarjeta,cargando2;

    private String mp;
    private String totalpagar;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_paseo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        etFechaE = findViewById(R.id.etFechaE);
        etHoraE = findViewById(R.id.etHoraE);
        etDireccionR = findViewById(R.id.etDireccionR);
        etTiempoP = findViewById(R.id.etTiempoEstimadoP);
        etPrecio = findViewById(R.id.etPrecio);
        etPagarCon = findViewById(R.id.etPagarCon);
        btnReservar = findViewById(R.id.btnReservar);
        LLPagarCon = findViewById(R.id.LLPagoCon);
        LLtarjeta = findViewById(R.id.LLtarjeta);
        cargando2 = findViewById(R.id.cargando2);
        nro_tarjeta = findViewById(R.id.nro_tarjeta);
        nomb_tarjeta = findViewById(R.id.nomb_tarjeta);
        etMetodoPago1 = findViewById(R.id.etMetodoPago1);
        etMetodoPago2 = findViewById(R.id.etMetodoPago2);

        mQueue = Volley.newRequestQueue(this);

        cargando2.setVisibility(View.GONE);

        Bundle regreso = this.getIntent().getExtras();

        if(regreso !=null) {
            etFechaE.setText(regreso.getString("fecha_re"));
            etHoraE.setText(regreso.getString("hora_re"));
            etDireccionR.setText(regreso.getString("direccion_re"));
            etTiempoP.setText(regreso.getString("tiempo_paseo_re"));
            int total1;
            total1 = Integer.parseInt(regreso.getString("tiempo_paseo_re")) * 15;
            etPrecio.setText("S/."+total1);
            totalpagar = String.valueOf(total1);

        }

        /*------------METODO DE PAGO-------------*/

        etMetodoPago1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fecha",etFechaE.getText().toString());
                bundle.putString("hora",etHoraE.getText().toString());
                bundle.putString("direccion",etDireccionR.getText().toString());
                bundle.putString("tiempo_paseo",etTiempoP.getText().toString());


                Intent intent = new Intent(getApplicationContext(),MetodoPago.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        etMetodoPago2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("fecha",etFechaE.getText().toString());
                bundle.putString("hora",etHoraE.getText().toString());
                bundle.putString("direccion",etDireccionR.getText().toString());
                bundle.putString("tiempo_paseo",etTiempoP.getText().toString());

                Intent intent = new Intent(getApplicationContext(),MetodoPago.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        LLtarjeta.setVisibility(View.GONE);
        mp="1";

        if(getIntent().hasExtra("metodopago")) {
            Tarjeta tarjeta = getIntent().getParcelableExtra("metodopago");
            nro_tarjeta.setText(tarjeta.getNro_tarjeta());
            nomb_tarjeta.setText(tarjeta.getNombre_propietario() + " " + tarjeta.getApellido_propietario());
            LLtarjeta.setVisibility(View.VISIBLE);
            LLPagarCon.setVisibility(View.GONE);
            etPagarCon.setText("0");
            mp="2";
        }

        if(getIntent().hasExtra("efectivo")) {
            LLtarjeta.setVisibility(View.GONE);
            LLPagarCon.setVisibility(View.VISIBLE);
            mp="1";

        }

        if(etTiempoP.getText().toString().equals("")){
            etTiempoP.setText("0");
        }

        /*------------RESTRICCIONES-------------*/

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFechaE.getText().toString().equals("")){
                    etFechaE.setError("Campo Obligatorio");
                }else if(etHoraE.getText().toString().equals("")){
                    etHoraE.setError("Campo Obligatorio");
                }else if(etDireccionR.getText().toString().equals("")){
                    etDireccionR.setError("Campo Obligatorio");
                }else if(etTiempoP.getText().toString().equals("")){
                    etTiempoP.setError("Campo Obligatorio");
                }else if(Integer.parseInt(etTiempoP.getText().toString()) > 4){
                    etTiempoP.setError("El máximo de horas es 4");
                }else if(Integer.parseInt(etTiempoP.getText().toString()) == 0 ){
                    etTiempoP.setError("Ingrese tiempo estimado");
                }else if(etPagarCon.getText().toString().equals("")){
                    etPagarCon.setError("Campo Obligatorio");
                }else if(Integer.parseInt(etPagarCon.getText().toString()) < Integer.parseInt(totalpagar) & mp.equals("1")){
                    etPagarCon.setError("El monto debe ser mayor al precio");
                }else if(Integer.parseInt(etPagarCon.getText().toString()) > 100){
                    etPagarCon.setError("El máximo a pagar es 100");
                }else {
                    cargando2.setVisibility(View.VISIBLE);
                    etFechaE.setEnabled(false);
                    etHoraE.setEnabled(false);
                    etDireccionR.setEnabled(false);
                    etTiempoP.setEnabled(false);
                    etPagarCon.setEnabled(false);
                    btnReservar.setEnabled(false);
                    ConsumirWSReserva();
                }

            }
        });

        /*------------FECHA-------------*/

        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        etFechaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReservarPaseo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String selectedDate = year + "-" + month + "-" + day;
                        etFechaE.setText(selectedDate);
                        etFechaE.setError(null);
                    }
                },year,month,day
                );
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();
            }
        });



        /*------------HORA-------------*/

        etFechaE.setFocusable(false);
        etFechaE.setEnabled(true);
        etFechaE.setCursorVisible(false);
        etFechaE.setKeyListener(null);

        etHoraE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.etHoraE:
                        showTimePickerDialog();
                        break;
                }
            }
        });

        etHoraE.setFocusable(false);
        etHoraE.setEnabled(true);
        etHoraE.setCursorVisible(false);
        etHoraE.setKeyListener(null);


        /*------------TIEMPO DE PASEO-------------*/

        etTiempoP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(etTiempoP.getText().toString().equals("")){
                    etPrecio.setText("0");
                }else if(Integer.parseInt(etTiempoP.getText().toString()) > 4){
                    etTiempoP.setError("El máximo de horas es 4");
                    etTiempoP.setText("0");
                    etPrecio.setText("0");
                }else if(Integer.parseInt(etTiempoP.getText().toString()) == 0){
                    etTiempoP.setError("El máximo de horas es 4");
                }else {
                    int tiempoEstimado = Integer.parseInt(etTiempoP.getText().toString());
                    int precio = tiempoEstimado * 15;
                    etPrecio.setText("S/."+precio);
                    totalpagar = String.valueOf(precio);
                }
            }
        });

        etTiempoP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                } else if(etTiempoP.getText().toString().equals("")){
                    etTiempoP.setText("0");
                }

            }
        });

        etPrecio.setEnabled(false);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,MenuCliente.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWSReserva() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.ReservarPaseo();
        SharedPreferences preferences =getSharedPreferences("appLogin",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("fecha_r",etFechaE.getText().toString());
        params.put("hora_r",etHoraE.getText().toString());
        params.put("direccion_r",etDireccionR.getText().toString());
        params.put("tiempo_paseo_r",etTiempoP.getText().toString());
        params.put("pago_r",etPagarCon.getText().toString());
        params.put("precio_r",totalpagar);
        params.put("estado_r","1");
        params.put("metodopago",mp);
        params.put("cod_usuario_cli",preferences.getString("idusuario",""));

        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        Intent intent = new Intent(getApplicationContext(), MenuCliente.class);
                        finish();
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        cargando2.setVisibility(View.GONE);
                        etFechaE.setEnabled(true);
                        etHoraE.setEnabled(true);
                        etDireccionR.setEnabled(true);
                        etTiempoP.setEnabled(true);
                        etPagarCon.setEnabled(true);
                        btnReservar.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al reservar paseo",Toast.LENGTH_LONG).show();
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
                etHoraE.setText(String.format("%02d:%02d",hora,minutos));
                etHoraE.setError(null);
            }
        });
        newFragment1.show(ReservarPaseo.this.getSupportFragmentManager(),"timePicker");
    }

}
