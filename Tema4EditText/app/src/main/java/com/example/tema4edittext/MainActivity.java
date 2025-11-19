package com.example.tema4edittext;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.editall);


        String[] opciones = {"Opcion 1", "Opcion 2", "Opcion 3", "Opcion 4"};
        AutoCompleteTextView textoLeido = findViewById(R.id.miTexto);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        textoLeido.setAdapter(adaptador);

        Spinner miSpinner = findViewById(R.id.miSpinner);
        String[] valores = {"Blancanieves", "Frozen", "Jurasic Park"};
        miSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valores));

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Has seleccionado el valor: " + parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("No has seleccionado nada");
            }
        });
    }
}
