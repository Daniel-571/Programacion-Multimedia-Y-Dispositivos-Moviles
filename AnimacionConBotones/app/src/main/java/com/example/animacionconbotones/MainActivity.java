package com.example.animacionconbotones;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

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
        setContentView(R.layout.linearara);

    }
    /*public void onClick(View view){
        TextView texto= findViewById(R.id.texto);
        int id = view.getId();
        if(id==R.id.btnTranslate){
            texto.setText("He presionado el boton divertido");
        }else if(id==R.id.btnRotate){
            texto.setText("He presionado el boton chachi");
        }else if(id==R.id.btnDetener){
            texto.setText("He presionado el boton malo");
        }
    }*/
    protected void onStart(){
        super.onStart();
        TextView texto= findViewById(R.id.texto);
        Button botonVoltereta = findViewById(R.id.btnRotate);
        Button botonTrans = findViewById(R.id.btnTranslate);
        Button botonDetener = findViewById(R.id.btnDetener);
        botonVoltereta.setOnClickListener(v-> {texto.setText("He presionado el boton divertido");});
        botonTrans.setOnClickListener(v-> {texto.setText("He presionado el boton chachi");});
        botonDetener.setOnClickListener(v-> {texto.setText("He presionado el boton malo");});
        botonTrans.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translacion);
            texto.startAnimation(anim);

        });
        botonVoltereta.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.voltereta);
            texto.startAnimation(anim);});

    }


}
