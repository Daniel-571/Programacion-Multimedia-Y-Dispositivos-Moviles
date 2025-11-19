package com.example.cuentabar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    EditText editTotal;
    CheckBox checkPropina;
    SeekBar seekPropina;
    TextView textPorcentaje, textResultado;
    RadioGroup radioGroupPago;
    RatingBar ratingServicio;
    Button btnCalcular;
    AutoCompleteTextView autoCamarero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);

        editTotal = findViewById(R.id.editTotal);
        checkPropina = findViewById(R.id.checkPropina);
        seekPropina = findViewById(R.id.seekPropina);
        textPorcentaje = findViewById(R.id.textPorcentaje);
        textResultado = findViewById(R.id.textResultado);
        radioGroupPago = findViewById(R.id.radioGroupPago);
        ratingServicio = findViewById(R.id.ratingServicio);
        btnCalcular = findViewById(R.id.btnCalcular);
        autoCamarero = findViewById(R.id.autoCamarero);


        String[] camareros = {"Carlos", "María", "Lucía", "José", "Ana"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, camareros);
        autoCamarero.setAdapter(adapter);


        seekPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPorcentaje.setText("Porcentaje de propina: " + progress + "%");
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotal();
            }
        });
    }

    private void calcularTotal() {
        String totalStr = editTotal.getText().toString().trim();

        if (totalStr.isEmpty()) {
            mostrarError("Falta meter el valor de la cuenta.");
            return;
        }

        double total;
        try {
            total = Double.parseDouble(totalStr);
        } catch (NumberFormatException e) {
            mostrarError("Formato numérico inválido.");
            return;
        }

        if (total <= 0) {
            mostrarError("El total debe ser mayor que 0.");
            return;
        }

        double propina = 0;
        if (checkPropina.isChecked()) {
            int porcentaje = seekPropina.getProgress();
            propina = total * porcentaje / 100.0;
        }

        double totalFinal = total + propina;

        int idSeleccionado = radioGroupPago.getCheckedRadioButtonId();
        RadioButton radioSeleccionado = findViewById(idSeleccionado);
        String metodoPago = radioSeleccionado.getText().toString();

        float rating = ratingServicio.getRating();

        String camarero = autoCamarero.getText().toString();
        if (camarero.isEmpty()) camarero = "No especificado";

        String resultado = String.format(
                " Total: %.2f €\nMétodo de pago: %s\nPropina: %.2f €\nCamarero: %s\n Calificación: %.1f estrellas",
                totalFinal, metodoPago, propina, camarero, rating);

        textResultado.setTextColor(Color.BLACK);
        textResultado.setText(resultado);
    }

    private void mostrarError(String mensaje) {
        textResultado.setTextColor(Color.RED);
        textResultado.setText(mensaje);
    }
}
