package com.example.hardcarry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText etNick;
    Button btnStart;
    String nick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNick=findViewById(R.id.txt_usuario);
        btnStart=findViewById(R.id.btn_login);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"pixel.ttf");
        etNick.setTypeface(typeface);
        btnStart.setTypeface(typeface);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nick=etNick.getText().toString();
                if (nick.isEmpty()){
                    etNick.setError("El nombre de usuario es obligatorio");

                }else {
                    etNick.setText("");
                    Intent intent=new Intent(LoginActivity.this,GameActivity.class);
                    intent.putExtra("nick",nick);
                    startActivity(intent);
                }

            }
        });
    }
}
