package com.exam.cripto;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.exam.cripto.algorithm.rsa.RSA;

public class RsaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);

        Button startButton = findViewById(R.id.button_start_rsa);
        EditText messageView = findViewById(R.id.input_message_rsa);
        EditText numFromView = findViewById(R.id.input_from_num_rsa);
        EditText numToView = findViewById(R.id.input_to_num_rsa);
        TextView textView = findViewById(R.id.result_rsa);

        startButton.setOnClickListener(view -> {
            long message = Long.parseLong(String.valueOf(messageView.getText()));
            Integer from = Integer.parseInt(String.valueOf(numFromView.getText()));
            Integer to = Integer.parseInt(String.valueOf(numToView.getText()));
            try {
                RSA rsa = new RSA(message, from, to);
                textView.setText(rsa.report);
            } catch (Exception e) {
                textView.setText(e.toString());
            }
        });
    }

}