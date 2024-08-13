package com.gustavomacedo.flaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nome = findViewById(R.id.edtNome);
    }

    public void iniciar(View v) {
        if (nome.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Por favor, digite um nome de usuário", Toast.LENGTH_SHORT).show();
        } else if (nome.getText().length() < 3) {
            Toast.makeText(getApplicationContext(), "Por favor, digite um nome de usuário com pelo menos 3 letras", Toast.LENGTH_LONG).show();
        }else {
            Intent in = new Intent(getApplicationContext(), Brasil.class);
            Bundle parametros = new Bundle();

            parametros.putString("nome", nome.getText().toString());

            in.putExtras(parametros);

            startActivity(in);
        }
    }
}