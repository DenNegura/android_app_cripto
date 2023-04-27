package com.exam.cripto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.exam.cripto.algorithm.definition.Definition;

public class DefinitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        TextView tv1 = findViewById(R.id.symmetric);
        TextView tv2 = findViewById(R.id.asymmetric);
        TextView tv3 = findViewById(R.id.block);
        TextView tv4 = findViewById(R.id.fluid);
        TextView tv5 = findViewById(R.id.electronic_sign);
        TextView tv6 = findViewById(R.id.hash);
        Definition definition = new Definition();
        tv1.setText(definition.getSymmetricCripting());
        tv2.setText(definition.getAsymmetricCripting());
        tv3.setText(definition.getBlockCripting());
        tv4.setText(definition.getFluidCripting());
        tv5.setText(definition.getElectronicSign());
        tv6.setText(definition.getHashCripting());
    }
}