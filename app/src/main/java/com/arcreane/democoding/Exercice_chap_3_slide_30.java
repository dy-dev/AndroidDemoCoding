package com.arcreane.democoding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class Exercice_chap_3_slide_30 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_chap_3_slide_30);

        RelativeLayout layout = findViewById(R.id.monlayout);
        layout.setBackgroundColor(getColor(R.color.maDeuxiemeColor));
    }
}