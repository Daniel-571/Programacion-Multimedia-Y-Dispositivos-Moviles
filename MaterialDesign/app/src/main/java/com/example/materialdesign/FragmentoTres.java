package com.example.materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentoTres extends Fragment implements View.OnClickListener {

    private TextView texto1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragmento_tres, container, false);

        texto1 = v.findViewById(R.id.txt);

        // Asignar listeners a todos los botones
        int[] botones = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
                R.id.btnDot, R.id.btnEqual, R.id.btnClearAll, R.id.btnClearOne
        };

        for (int id : botones) {
            v.findViewById(id).setOnClickListener(this);
        }

        return v;
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

            } else if (textoBoton.equals(".")) {
                String ultimoNumero = obtenerUltimoNumero(actual);
                if (!ultimoNumero.contains(".")) {
                    texto1.setText(actual + ".");
                }

            } else {
                texto1.setText(actual + textoBoton);
            }
        }
    }

    private String obtenerUltimoNumero(String texto) {
        String[] tokens = texto.split("[+\\-*/]");
        return tokens[tokens.length - 1];
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
