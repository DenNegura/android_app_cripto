package com.exam.cripto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exam.cripto.algorithm.elgamal.ElGamal;
import com.exam.cripto.algorithm.rsa.RSA;

public class ElGamalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_gamal);

        Button startButton = findViewById(R.id.button_start_lg);
        EditText messageView = findViewById(R.id.input_message_lg);
        EditText numFromView = findViewById(R.id.input_from_num_lg);
        EditText numToView = findViewById(R.id.input_to_num_lg);
        TextView textView = findViewById(R.id.result_lg);

        startButton.setOnClickListener(view -> {
            int message = Integer.parseInt(String.valueOf(messageView.getText()));
            Integer from = Integer.parseInt(String.valueOf(numFromView.getText()));
            Integer to = Integer.parseInt(String.valueOf(numToView.getText()));
            try {
                ElGamal elGamal = new ElGamal(message, from, to);
                textView.setText(elGamal.getReport());
            } catch (Exception e) {
                textView.setText(e.toString());
            }
        });
    }
}