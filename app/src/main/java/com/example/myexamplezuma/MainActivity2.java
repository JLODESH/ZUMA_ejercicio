package com.example.myexamplezuma;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString("name");
        String apellido = parametros.getString("apellido");

        //String imagen = parametros.getString("photo");


        TextView itemNombre = (TextView) findViewById(R.id.txt3);
        TextView itemApe = (TextView) findViewById(R.id.txt4);






        itemNombre.setText(nombre);
        itemApe.setText(apellido);






    }



}