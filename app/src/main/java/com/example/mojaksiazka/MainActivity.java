package com.example.mojaksiazka;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    Button przypomnij;

    private static final String CHANNEL_ID = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        zobaczOpis = findViewById(R.id.zobaczOpis);
        ukrytyTekst = findViewById(R.id.ukrytyTekst);
        dodajDo = findViewById(R.id.dodaj);
        addedToListTextView = findViewById(R.id.addedToListTextView);
        przypomnij = findViewById(R.id.przypomnij);

        dodajDo.setOnClickListener(v -> {
            if (ukrytyTekst.getVisibility() == View.INVISIBLE) {
                ukrytyTekst.setVisibility(View.VISIBLE);
                dodajDo.setText("USUŃ Z CHCE PRZECZYTAĆ");
                addedToListTextView.setVisibility(View.VISIBLE);
            } else {
                ukrytyTekst.setVisibility(View.INVISIBLE);
                dodajDo.setText("DODAJ DO CHCE PRZECZYTAĆ");
                addedToListTextView.setVisibility(View.GONE);
            }
        });

        createNotificationChannel(this);

        zobaczOpis.setOnClickListener(v -> {
            sendNotification(1,CHANNEL_ID,this,this,"Moja książka", "Krótki opis: Ekscytująca historia pełna zwrotów akcji");
        });

        przypomnij.setOnClickListener(v -> {
            sendNotification(2,CHANNEL_ID,this,this,"Moja książka", "Pamiętaj, aby znaleźć czas na lekturę!");
        });
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channeldefault = new NotificationChannel(CHANNEL_ID, "Kanal Powiadomien", NotificationManager.IMPORTANCE_DEFAULT);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channeldefault);
            }
        }
    }

    public static void sendNotification(int NOTIFICATION_ID, String CHANNEL_ID, AppCompatActivity activity, Context context, String tytul, String opis) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        }
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(tytul)
                .setContentText(opis)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}



