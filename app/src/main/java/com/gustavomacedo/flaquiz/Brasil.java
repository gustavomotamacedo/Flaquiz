package com.gustavomacedo.flaquiz;

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

        txtResposta = findViewById(R.id.resposta);
        RadioGroup rgRespostas = findViewById(R.id.rgRespostas);

        rgRespostas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radBrasil) {
                txtResposta.setText("CORRETA");
            } else {
                txtResposta.setText("INCORRETA");
            }
        });
    }
}