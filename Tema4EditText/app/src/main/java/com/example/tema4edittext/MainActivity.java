package com.example.tema4edittext;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editall );
        String[]opciones={"Opcion 1","Opcion 2","Opcion 3","Opcion 4"};
        AutoCompleteTextView textoLeido=findViewById(R.id.miTexto);
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,opciones);
        textoLeido.setAdapter(adaptador);
    }
}