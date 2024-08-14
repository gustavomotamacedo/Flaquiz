package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gustavomacedo.flaquiz.models.Usuario;

public class SouthAfrica extends AppCompatActivity {

    Usuario usuario;
    TextView edtResposta;
    private MediaPlayer mp;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_south_africa);
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
        edtResposta = findViewById(R.id.edtResposta);

        edtResposta.setText(usuario.getNome() + ", selecione uma opção!\n" + usuario.getQtdCorretas() + "\t" + usuario.getQtdIncorretas());

        rgRespostas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    edtResposta.setText(R.string.semOpcaoSelecionada);
                } else {
                    if (checkedId == R.id.radSouthAfrica) {
                        mp = new MediaPlayer().create(getApplicationContext(), R.raw.correta);
                        mp.start();
                        edtResposta.setText(R.string.correta);
                        if (bd != null)
                            bd.putInt("corretas", usuario.incrementaCorreta());
                        proximaPagina(bd);
                    } else {
                        mp = new MediaPlayer().create(getApplicationContext(), R.raw.incorreta);
                        mp.start();
                        edtResposta.setText(R.string.incorreta);
                        if (bd != null)
                            bd.putInt("incorretas", usuario.incrementaIncorreta());
                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(300);
                        proximaPagina(bd);
                    }
                }
            }
        });
    }

    public void proximaPagina(Bundle parametros) {
        Intent in = new Intent(getApplicationContext(), TelaFinal.class);

        if (parametros != null) {
            in.putExtras(parametros);
        }

        startActivity(in);
    }
}