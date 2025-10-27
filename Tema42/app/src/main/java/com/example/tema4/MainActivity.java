package com.example.tema4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txtEstado;
    private ToggleButton toggleBtn;
    private ImageButton imgBtn;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.togglebutonnraro2);

        txtEstado=findViewById(R.id.txt);
        toggleBtn=findViewById(R.id.miToggleButton);
        imgBtn=findViewById(R.id.miImageButton);

    toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                txtEstado.setText("Estado: Pulsado");
            }else{
                txtEstado.setText("Estado: No pulsado");
            }
        }
    });
    imgBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this,"Has pulsado el ImageButton", Toast.LENGTH_SHORT).show();
            String texto= (String) txtEstado.getText();
            /**if(texto){
            imgBtn.setImageResource(R.drawable.pause);
             }else{
                 imgBtn.setImageResource(R.drawable.play);
             }*/
        }
    });
    }

}