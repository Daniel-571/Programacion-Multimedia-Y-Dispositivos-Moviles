package com.example.ejerciciocalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView texto1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        texto1 = findViewById(R.id.txt);


        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);

        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnSub).setOnClickListener(this);
        findViewById(R.id.btnMul).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);

        findViewById(R.id.btnDot).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);

        findViewById(R.id.btnClearAll).setOnClickListener(this);
        findViewById(R.id.btnClearOne).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String actual = texto1.getText().toString();
        Button boton = (Button) v;
        String textoBoton = boton.getText().toString();

        if (textoBoton.equals("=")) {
            try {
                double resultado = evalMultiple(actual);
                if (resultado == (long) resultado) {
                    texto1.setText(String.valueOf((long) resultado));
                } else {
                    texto1.setText(String.valueOf(resultado));
                }
            } catch (Exception e) {
                texto1.setText("Error");
            }
        } else if (textoBoton.equals("C")) {
            texto1.setText("0");
        } else if (textoBoton.equals("⌫")) {
            if (actual.length() > 1) {
                texto1.setText(actual.substring(0, actual.length() - 1));
            } else {
                texto1.setText("0");
            }
        } else {
            if (actual.equals("0") && !textoBoton.equals(".")) {
                texto1.setText(textoBoton);
            } else {
                texto1.setText(actual + textoBoton);
            }
        }
    }


    private double evalMultiple(String expr) throws Exception {
        expr = expr.replace(" ", "");


        if (expr.startsWith("-")) {
            expr = "0" + expr;
        }


        double resultado = 0;
        char operador = '+';
        StringBuilder numeroBuffer = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if ((c >= '0' && c <= '9') || c == '.') {
                numeroBuffer.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {

                double numeroActual = Double.parseDouble(numeroBuffer.toString());
                numeroBuffer.setLength(0);


                resultado = aplicarOperacion(resultado, numeroActual, operador);


                operador = c;
            } else {
                throw new Exception("Carácter inválido");
            }
        }


        if (numeroBuffer.length() > 0) {
            double numeroFinal = Double.parseDouble(numeroBuffer.toString());
            resultado = aplicarOperacion(resultado, numeroFinal, operador);
        }

        return resultado;
    }

    private double aplicarOperacion(double a, double b, char operador) throws Exception {
        switch (operador) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new Exception("División por cero");
                return a / b;
            default: throw new Exception("Operador no válido");
        }
    }
}
