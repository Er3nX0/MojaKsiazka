package com.example.mojaksiazka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    Button zobaczOpis;

    TextView ukrytyTekst;
    Button dodajDo;
    TextView addedToListTextView;


    private static final String CHANNEL_ID = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        zobaczOpis = findViewById(R.id.zobaczOpis);
        ukrytyTekst = findViewById(R.id.ukrytyTekst);
        dodajDo = findViewById(R.id.dodaj);


        dodajDo.setOnClickListener(v ->{
            if (ukrytyTekst.getVisibility() == View.INVISIBLE) {
                ukrytyTekst.setVisibility(View.VISIBLE);
            } else {
                ukrytyTekst.setVisibility(View.INVISIBLE);
            }
        });
    }
    }
