package com.example.hardcarry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hardcarry.common.Cosntantes;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
TextView tvTimer, tnNick,counterDucks;
ImageView ivDuck;
int counter=0;
int anchoPantalla;
int altoPantalla;
Random aleatorio;
boolean gameover=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    InitViewComponent();
    eventos();
    InitPantalla();
    InitCuentaAtras();
    }

    private void InitCuentaAtras() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                Long segundosRestantes=millisUntilFinished / 1000;

                tvTimer.setText(segundosRestantes+"s");
            }

            public void onFinish() {

                tvTimer.setText("0s");
                gameover=true;
                mostrarDialogoGameover();
                gameover=false;
                InitCuentaAtras();



            }
        }.start();
    }

    private void mostrarDialogoGameover() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setMessage("Felicidades haz conseguido matar "+counter+" terminators")
                .setTitle("No te desconcentres");

        builder.setPositiveButton("Reiniciar el juego", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                counter=0;
                counterDucks.setText("0");
                gameover=false;
                InitCuentaAtras();


            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               dialog.dismiss();
               finish();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void InitPantalla() {
        Display display=getWindowManager().getDefaultDisplay();
        Point size=new Point();
        display.getSize(size);
        anchoPantalla=size.x;
        altoPantalla=size.y;
        aleatorio=new Random();

    }


    private void InitViewComponent() {
        counterDucks=findViewById(R.id.txt_contador);
        tvTimer=findViewById(R.id.textView4);
        tnNick=findViewById(R.id.txt_nick);
        ivDuck=findViewById(R.id.img_duck);

        Bundle extras=getIntent().getExtras();
        String nick=extras.getString("nick");
        tnNick.setText(nick);

        Typeface typeface=Typeface.createFromAsset(getAssets(),"pixel.ttf");
        tnNick.setTypeface(typeface);

    }
    private void eventos() {
        ivDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameover) {


                    counter++;
                    counterDucks.setText(String.valueOf(counter));
                    ivDuck.setImageResource(R.drawable.times);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ivDuck.setImageResource(R.drawable.recorte);
                            moveduck();

                        }
                    }, 300);
                }


            }

            private void moveduck() {
                int min=0;
                int maximox=anchoPantalla-ivDuck.getWidth();
                int maximoy=altoPantalla-ivDuck.getHeight();
                int randomx=aleatorio.nextInt(((maximox-min)+1)+min);
                int randomy=aleatorio.nextInt(((maximoy-min)+1)+min);
                ivDuck.setX(randomx);
                ivDuck.setY(randomy);

            }
        });
    }
    }
