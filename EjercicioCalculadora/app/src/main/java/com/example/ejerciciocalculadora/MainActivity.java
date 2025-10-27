package com.example.ejerciciocalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase principal de la aplicación, hereda de AppCompatActivity e implementa OnClickListener
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * TextView que mostrará la operación y el resultado
     */
    private TextView texto1;

    /**
     * Método que se llama al iniciar la actividad
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora);

        // Referencia al TextView que muestra los datos
        texto1 = findViewById(R.id.txt);

        // Asigna el listener a cada botón numérico y de operación
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

    /**
     * Método que se ejecuta al hacer clic en cualquier botón
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String actual = texto1.getText().toString(); // Obtiene el texto actual
        Button boton = (Button) v; // Obtiene el botón pulsado
        String textoBoton = boton.getText().toString(); // Obtiene el texto del botón


        // Este if es "si se presiona el botón igual"
        if (textoBoton.equals("=")) {
            try {
                double resultado = evalMultiple(actual); // Evalúa la expresión matemática
                // Muestra resultado sin decimales si es entero
                if (resultado == (long) resultado) {
                    texto1.setText(String.valueOf((long) resultado));
                } else {
                    texto1.setText(String.valueOf(resultado));
                }
            } catch (Exception e) {
                texto1.setText("Error"); // Muestra error si algo falla
            }

            // Si se presiona el botón "C" se borra todo
        } else if (textoBoton.equals("C")) {
            texto1.setText("0");

            // Si se presiona el botón de borrar un carácter borra solo un caracter
        } else if (textoBoton.equals("⌫")) {
            if (actual.length() > 1) {
                texto1.setText(actual.substring(0, actual.length() - 1)); // Borra último carácter
            } else {
                texto1.setText("0"); // Si queda vacio, vuelve a 0
            }

        } else {
            // Si el texto actual es "0" y se presiona un número
            if (actual.equals("0") && !textoBoton.equals(".")) {
                texto1.setText(textoBoton); // Reemplaza el 0 por el nuevo número

                // Si se presiona el botón del punto
            } else if (textoBoton.equals(".")) {
                String ultimoNumero = obtenerUltimoNumero(actual); // Obtiene el último número
                if (!ultimoNumero.contains(".")) {
                    texto1.setText(actual + "."); // Solo permite un punto por número
                }

                // Cualquier otro botón (números u operaciones)
            } else {
                texto1.setText(actual + textoBoton); // Añade el texto al final
            }
        }
    }

    /**
     * Método para obtener el último número de la operación actual (separado por operadores)
     * @param texto
     * @return
     */
    private String obtenerUltimoNumero(String texto) {
        String[] tokens = texto.split("[+\\-*/]");
        return tokens[tokens.length - 1]; // Devuelve el último número ingresado quitando los operadores
    }

    /**
     * Método para evaluar la operación matemática completa
     * @param expr
     * @return
     * @throws Exception
     */
    private double evalMultiple(String expr) throws Exception {
        expr = expr.replace(" ", ""); // Elimina espacios

        // Si empieza con operador negativo, agrega un 0 al principio
        if (expr.startsWith("-")) {
            expr = "0" + expr;
        }

        double resultado = 0;
        char operador = '+'; // Inicializa con suma
        StringBuilder numeroBuffer = new StringBuilder();

        // Recorre cada carácter de la expresión
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            // Si es un número o un punto, lo añade al número en construcción
            if ((c >= '0' && c <= '9') || c == '.') {
                numeroBuffer.append(c);

                // Si es un operador, evalúa el número anterior con el operador anterior
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                double numeroActual = Double.parseDouble(numeroBuffer.toString());
                numeroBuffer.setLength(0); // Limpia el buffer

                resultado = aplicarOperacion(resultado, numeroActual, operador); // Aplica la operación

                operador = c; // Actualiza el operador
            } else {
                throw new Exception("Carácter inválido"); // Si el carácter no es válido
            }
        }

        // Evalúa el último número después del bucle
        if (numeroBuffer.length() > 0) {
            double numeroFinal = Double.parseDouble(numeroBuffer.toString());
            resultado = aplicarOperacion(resultado, numeroFinal, operador);
        }

        return resultado;
    }

    /**
     * Método auxiliar que aplica una operación entre dos números
     * @param a
     * @param b
     * @param operador
     * @return
     * @throws Exception
     */
    private double aplicarOperacion(double a, double b, char operador) throws Exception {
        switch (operador) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new Exception("División por cero"); // Previene dividir por cero
                return a / b;
            default: throw new Exception("Operador no válido"); // Operador desconocido
        }
    }
}
