package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

public class Brasil extends AppCompatActivity {

    private TextView txtResposta;
    private Usuario usuario;
    RadioGroup rgRespostas;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_brasil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent in = getIntent();
        Bundle bd = in.getExtras();

        txtResposta = findViewById(R.id.edtResposta);
        rgRespostas = findViewById(R.id.rgRespostas);
        usuario = new Usuario();

        if (bd != null) {
            usuario.setNome(bd.getString("nome"));
            usuario.setQtdCorretas(bd.getInt("corretas"));
            usuario.setQtdIncorretas(bd.getInt("incorretas"));
            txtResposta.setText(usuario.getNome() + ", selecione uma opção!\n" + usuario.getQtdCorretas() + "\t\t\t" + usuario.getQtdIncorretas());
        }

        rgRespostas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == -1) {
                txtResposta.setText(R.string.semOpcaoSelecionada);
            } else {
                if (checkedId == R.id.radBrasil) {
                    txtResposta.setText(R.string.correta);
                    if (bd != null)
                        bd.putInt("corretas", usuario.incrementaCorreta());
                    proximaPagina(bd);
                } else {
                    txtResposta.setText(R.string.incorreta);
                    if (bd != null)
                        bd.putInt("incorretas", usuario.incrementaIncorreta());
                    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(300);
                    proximaPagina(bd);
                }
            }
        });
    }

    public void proximaPagina(Bundle parametros) {
        Intent in = new Intent(getApplicationContext(), Russia.class);

        if (parametros != null) {
            in.putExtras(parametros);
        }

        startActivity(in);
    }
}