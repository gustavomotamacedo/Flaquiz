package com.gustavomacedo.flaquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gustavomacedo.flaquiz.models.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome;
    private Usuario usuario;
    private TextView txtFlaquiz;
    private MediaPlayer mp;
    private int click;

    @SuppressLint("ResourceAsColor")
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
        txtFlaquiz = findViewById(R.id.textView);
        click = 0;
        usuario = new Usuario();
        usuario.setQtdCorretas(0);
        usuario.setQtdIncorretas(0);

        txtFlaquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click++;
                if (click == 3) {
                    mp = new MediaPlayer().create(getApplicationContext(), R.raw.para_de_mandar_audio_to_na_ucrania);
                    mp.start();
                } else if (click == 5) {
                    mp = new MediaPlayer().create(getApplicationContext(), R.raw.eu_tentei_crop);
                    mp.start();
                } else if (click == 4 || click == 6) {
                    mp.stop();
                } else if (click > 6) {
                    click = 0;
                }
            }
        });


    }    public void onBackPressed() {
        super.onBackPressed();
//        if (clickDuplo) {
//            Intent in = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(in);
//        }
//        clickDuplo = true;
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                clickDuplo = false;
//            }
//        }, 2000);
//        Toast.makeText(this, "Click novamente para voltar ao menu inicial.", Toast.LENGTH_SHORT).show();
        finish();
        Toast.makeText(this, "Finalizando app. Até logo!", Toast.LENGTH_SHORT).show();
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

            finish();
            startActivity(in);
        }
    }
}