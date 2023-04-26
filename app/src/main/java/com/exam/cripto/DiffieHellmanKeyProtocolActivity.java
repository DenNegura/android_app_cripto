package com.exam.cripto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.exam.cripto.algorithm.diffiehellman.DiffieHellmanKeyProtocol;
import com.exam.cripto.algorithm.elgamal.ElGamal;

public class DiffieHellmanKeyProtocolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffie_hellman_key_protocol);

        Button startButton = findViewById(R.id.button_start_dh);
        TextView textView = findViewById(R.id.result_dh);

        startButton.setOnClickListener(view -> {
            try {
                DiffieHellmanKeyProtocol protocol = new DiffieHellmanKeyProtocol();
                textView.setText(protocol.getReport());
            } catch (Exception e) {
                textView.setText(e.toString());
            }
        });
    }
}