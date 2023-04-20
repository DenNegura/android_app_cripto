package com.exam.cripto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exam.cripto.algorithm.rc4.RC4;
import com.exam.cripto.algorithm.rsa.RSA;

public class Rc4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc4);

        Button startButton = findViewById(R.id.button_start_rc4);
        EditText messageView = findViewById(R.id.input_message_rc4);
        EditText keyView = findViewById(R.id.input_key_rc4);
        TextView textView = findViewById(R.id.result_rc4);

        startButton.setOnClickListener(view -> {
            String message = String.valueOf(messageView.getText());
            String key = String.valueOf(keyView.getText());
            try {
                RC4 rc4 = new RC4(key, message);
                textView.setText(rc4.getReport());
            } catch (Exception e) {
                textView.setText(e.toString());
            }
        });
    }
}