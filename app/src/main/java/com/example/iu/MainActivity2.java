package com.example.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Localizar los controles
        TextView lblSaludo = (TextView)findViewById(R.id.lblSaludo);
        TextView lblGenero = (TextView)findViewById(R.id.lblGenero2);
        TextView lblFecha = (TextView)findViewById(R.id.lblFecha2);
        TextView lbltelefono = (TextView)findViewById(R.id.lblTelefono2);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        lblSaludo.setText(bundle.getString("NOMBRE"));
        lblGenero.setText(bundle.getString("GENERO"));
        lblFecha.setText(bundle.getString("FECHA"));
        lbltelefono.setText(bundle.getString("TELEFONO"));
    }
}