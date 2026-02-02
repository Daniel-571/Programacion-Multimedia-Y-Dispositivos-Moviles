package com.example.materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.materialdesign.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentoUno extends Fragment {

    private TextInputLayout textInputLayout;
    private TextInputEditText editTextEmail;
    private Button btnValidate;
    private TextView tvResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento_uno, container, false);

        textInputLayout = view.findViewById(R.id.textInputLayout);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        btnValidate = view.findViewById(R.id.btnValidate);
        tvResult = view.findViewById(R.id.tvResult);

        btnValidate.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            if (isValidEmail(email)) {
                textInputLayout.setError(null);
                tvResult.setText("Email válido: " + email);
            } else {
                textInputLayout.setError("Email no válido");
                tvResult.setText("");
            }
        });

        return view;
    }
    public void limpiarCorreo() {
        tvResult.setText("");
        textInputLayout.setError(null);
        textInputLayout.setHelperText(null);
    }

    private boolean isValidEmail(String email) {
        return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}