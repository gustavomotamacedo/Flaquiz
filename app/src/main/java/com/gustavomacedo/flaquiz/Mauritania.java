package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gustavomacedo.flaquiz.models.Usuario;

public class Mauritania extends AppCompatActivity {

    private Usuario usuario;
    int checkedRadButton;
    private MediaPlayer mp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mauritania);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent in = getIntent();
        Bundle bd = in.getExtras();

        if (bd != null)
            usuario = new Usuario(bd.getString("nome"), bd.getInt("corretas"), bd.getInt("incorretas"));

        RadioGroup rgRespostas = findViewById(R.id.rgRespostas);
        rgRespostas.setOnCheckedChangeListener((group, checkedId) -> checkedRadButton = checkedId);

        Button btnResponder = findViewById(R.id.btnResponder);
        checkedRadButton = -1;
        btnResponder.setOnClickListener(v -> {
            if (checkedRadButton == -1) {
                Toast.makeText(getApplicationContext(), R.string.semOpcaoSelecionada, Toast.LENGTH_SHORT).show();
            } else {
                if (checkedRadButton == R.id.radMauritania) {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.correta);
                    mp.start();
                    if (bd != null)
                        bd.putInt("corretas", usuario.incrementaCorreta());
                    proximaPagina(bd);
                } else {
                    if (bd != null)
                        bd.putInt("incorretas", usuario.incrementaIncorreta());
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.incorreta);
                    mp.start();
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(300);
                    proximaPagina(bd);
                }
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Redirecionado para o menu inicial.", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(in);
            }
        });
    }

    public void proximaPagina(Bundle parametros) {
        Intent in = new Intent(getApplicationContext(), Congo.class);

        if (parametros != null) {
            in.putExtras(parametros);
        }

        finish();
        startActivity(in);
    }
}