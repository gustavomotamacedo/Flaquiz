package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Brasil extends AppCompatActivity {

    private TextView txtResposta;
    private int corretas;
    private int incorretas;

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

        Intent recebida = getIntent();
        Bundle parametrosRecebidos = recebida.getExtras();

        txtResposta = findViewById(R.id.resposta);
        RadioGroup rgRespostas = findViewById(R.id.rgRespostas);

        if (parametrosRecebidos != null) {
            txtResposta.setText(parametrosRecebidos.getString("nome") + ", selecione uma opção!");
            corretas = parametrosRecebidos.getInt("corretas");
            corretas = parametrosRecebidos.getInt("incorretas");
        }

        rgRespostas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == -1) {
                txtResposta.setText(R.string.semOpcaoSelecionada);
            } else {
                if (checkedId == R.id.radBrasil) {
                    txtResposta.setText(R.string.correta);

                } else {
                    txtResposta.setText(R.string.incorreta);
                }
            }
        });
    }
}