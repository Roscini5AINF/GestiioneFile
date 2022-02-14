package com.example.gestionefile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestiionefile.R;


public class MainActivity extends AppCompatActivity {

    TextView testo;
    Button leggere, scrivere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testo = findViewById(R.id.testo);
        leggere = findViewById(R.id.btnLeggi);
        scrivere = findViewById(R.id.btnScrivi);
        GestioneFile gf = new GestioneFile();
        Context context = getApplicationContext();

        leggere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gf.leggiFile("Test.txt", getApplicationContext());
            }
        });

        scrivere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = gf.scriviFile("Test.txt", getApplicationContext());
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            }
        });

    }
}

