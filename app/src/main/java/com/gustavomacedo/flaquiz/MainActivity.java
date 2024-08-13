package com.gustavomacedo.flaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gustavomacedo.flaquiz.models.Usuario;

public class MainActivity extends AppCompatActivity {

    private TextView edtNome;
    private Usuario usuario;

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

        edtNome = findViewById(R.id.edtNome);
        usuario = new Usuario();
        usuario.setQtdCorretas(0);
        usuario.setQtdIncorretas(0);
    }

    public void iniciar(View v) {
        if (edtNome.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, digite um nome de usuário", Toast.LENGTH_SHORT).show();
        } else if (edtNome.getText().toString().length() < 3) {
            Toast.makeText(getApplicationContext(), "Digite um nome de usuário com pelo menos 3 letras", Toast.LENGTH_LONG).show();
        }else {
            usuario.setNome(edtNome.getText().toString());
            Intent in = new Intent(getApplicationContext(), Brasil.class);
            Bundle bd = new Bundle();

            bd.putString("nome", usuario.getNome());
            bd.putInt("corretas", usuario.getQtdCorretas());
            bd.putInt("incorretas", usuario.getQtdIncorretas());

            in.putExtras(bd);

            startActivity(in);
        }
    }
}