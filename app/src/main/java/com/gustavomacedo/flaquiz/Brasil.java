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

import com.gustavomacedo.flaquiz.models.Usuario;

public class Brasil extends AppCompatActivity {

    private TextView txtResposta;
    private Usuario usuario;

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
            usuario.setNome(parametrosRecebidos.getString("nome"));
            usuario.setQtdCorretas(parametrosRecebidos.getInt("corretas"));
            usuario.setQtdIncorretas(parametrosRecebidos.getInt("incorretas"));
            txtResposta.setText(usuario.getNome() + ", selecione uma opção!\n" + usuario.getQtdCorretas() + "\t" + usuario.getQtdIncorretas());
        }

        rgRespostas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == -1) {
                txtResposta.setText(R.string.semOpcaoSelecionada);
            } else {
                if (checkedId == R.id.radBrasil) {
                    txtResposta.setText(R.string.correta);
                    if (parametrosRecebidos != null)
                        parametrosRecebidos.putInt("corretas", usuario.incrementaCorreta());
                    proximaPagina(parametrosRecebidos);
                } else {
                    txtResposta.setText(R.string.incorreta);
                    if (parametrosRecebidos != null)
                        parametrosRecebidos.putInt("incorretas", usuario.incrementaIncorreta());
                    proximaPagina(parametrosRecebidos);
                }
            }
        });
    }

    public void proximaPagina(Bundle parametros) {
        Intent in = new Intent(getApplicationContext(), Brasil.class);

        if (parametros != null) {
            in.putExtras(parametros);
        }

        startActivity(in);
    }
}