package com.example.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnEnviar(View view){
        //Creamos la información a pasar entre actividades - Pares Key-Value
        EditText txtNombreNuevo = (EditText)findViewById(R.id.txtNombre);
        EditText txtFechaNuevo = (EditText)findViewById(R.id.txtFecha);
        EditText txtTelefonoNuevo = (EditText)findViewById(R.id.txtTelefono);
        Boolean bandera=true;

        //resetear códigos de error
        txtFechaNuevo.setError(null);
        txtNombreNuevo.setError(null);
        txtTelefonoNuevo.setError(null);

        //Obtener informacion de Radio Button
        RadioGroup radioGroup = findViewById(R.id.rdgGenero);
        int idRadioButton=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=findViewById(idRadioButton);
        //Toast.makeText(this, idRadioButton+"", Toast.LENGTH_SHORT ).show();

        //validar datos ingresados
        if(txtNombreNuevo.getText().toString().trim().length()==0)
        {
            bandera=false;
            txtNombreNuevo.setError("Ingrese su nombre");
        }
        if(idRadioButton<0)
        {
            bandera=false;
            //.setError("Seleccione su género");
        }
        if(!validarFecha(txtFechaNuevo.getText().toString().trim())) {
            bandera=false;
            txtFechaNuevo.setError("Ingresa una fecha correcta (dd/mm/yyyy)");
        }
        if(txtTelefonoNuevo.getText().length()<10) {
            bandera=false;
            txtTelefonoNuevo.setError("Ingrese un # de teléfono válido");
        }


        //enviar información
        if (bandera) {
            //Creamos el Intent
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            Bundle b = new Bundle();

            b.putString("NOMBRE", txtNombreNuevo.getText().toString());
            b.putString("GENERO", radioButton.getText().toString());
            b.putString("FECHA", txtFechaNuevo.getText().toString());
            b.putString("TELEFONO", txtTelefonoNuevo.getText().toString());

            //Añadimos la información al intent
            intent.putExtras(b);
            // Iniciamos la nueva actividad
            startActivity(intent);
        } else {
            Toast.makeText(this, "Ingrese todos los datos", Toast.LENGTH_SHORT ).show();
        }
    }

    public void OnClickSeletFecha(View view)
    {
        int dia, mes, anio;
        final Calendar Calendario = Calendar.getInstance();
        dia=Calendario.get(Calendar.DAY_OF_MONTH);
        mes=Calendario.get(Calendar.MONTH);
        anio=Calendario.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this/*, android.R.style.Theme_Holo_Light_Dialog */, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText txtFechaNuevo=(EditText)findViewById(R.id.txtFecha);
                txtFechaNuevo.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },anio,mes,dia);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+(long)3600);
        Calendario.add(Calendar.YEAR,-100);
        datePickerDialog.getDatePicker().setMinDate(Calendario.getTimeInMillis());
        datePickerDialog.show();
    }

    public boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}