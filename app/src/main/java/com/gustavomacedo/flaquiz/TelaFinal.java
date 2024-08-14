package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gustavomacedo.flaquiz.models.Usuario;

public class TelaFinal extends AppCompatActivity {

    private Usuario usuario;
    private MediaPlayer mp;

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

        TextView txtParabens = findViewById(R.id.txtParabens);
        TextView txtCorretas = findViewById(R.id.txtCorretas);
        TextView txtIncorretas = findViewById(R.id.txtIncorretas);


        txtCorretas.setText("Acertos\n" + usuario.getQtdCorretas());
        txtIncorretas.setText("Erros\n" + usuario.getQtdIncorretas());
        if (usuario.diferencaAcertosErros() > 0) {
                txtParabens.setText(getString(R.string.parabens) + ", " + usuario.getNome().toUpperCase());
            if (usuario.getQtdIncorretas() == 0) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.sexy_sax);
                mp.start();
            } else {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.anime_wow_sound_effect);
                mp.start();
            }
        } else {
            txtParabens.setText(getString(R.string.tente_novamente) + usuario.getNome() + "!");
            if (usuario.getQtdCorretas() == 0) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.emotional_damage);
                mp.start();
            } else {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.english_or_spanish);
                mp.start();
            }
        }
    }

    public void reiniciar(View v) {
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        mp.stop();
        finish();
        startActivity(in);
        overridePendingTransition(0, 0);
    }
}