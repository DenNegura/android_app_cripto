package com.exam.cripto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.exam.cripto.algorithm.chaum.Chaum;

public class ChaumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaum);

        TextView textView = findViewById(R.id.chaum_content);
        Chaum chaum = new Chaum();
        textView.setText(chaum.getReport());
    }
}