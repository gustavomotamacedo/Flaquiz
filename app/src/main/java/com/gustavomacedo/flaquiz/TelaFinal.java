package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gustavomacedo.flaquiz.models.Usuario;

public class TelaFinal extends AppCompatActivity {

    private Usuario usuario;
    private TextView txtParabens;
    private TextView txtCorretas;
    private TextView txtIncorretas;
    private Button btnRecomecar;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_final);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent in = getIntent();
        Bundle bd = in.getExtras();

        if (bd != null)
            usuario = new Usuario(bd.getString("nome"), bd.getInt("corretas"), bd.getInt("incorretas"));

        txtParabens = findViewById(R.id.txtParabens);
        txtCorretas = findViewById(R.id.txtCorretas);
        txtIncorretas = findViewById(R.id.txtIncorretas);
        btnRecomecar = findViewById(R.id.btnRecomecar);



        txtCorretas.setText("Acertos\n" + usuario.getQtdCorretas());
        txtIncorretas.setText("Erros\n" + usuario.getQtdIncorretas());
        if (usuario.temAcertosMaioresQueErros()) {
            txtParabens.setText(getString(R.string.parabens) + ", " + usuario.getNome().toUpperCase());
        } else {
            txtParabens.setText(getString(R.string.tente_novamente) + usuario.getNome() + "!");
        }
    }

    public void reiniciar(View v) {
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }
}