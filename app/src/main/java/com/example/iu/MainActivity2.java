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
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        lblSaludo.setText("Hola!, Bienvenido \n " + bundle.getString("NOMBRE"));
    }
}