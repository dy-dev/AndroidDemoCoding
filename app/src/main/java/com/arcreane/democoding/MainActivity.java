package com.arcreane.democoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView thirdTV = findViewById(R.id.myThirdTextView);
        thirdTV.setText(R.string.encore_un_autre_text);

        Resources res = getResources();
        String tmp = res.getString(R.string.un_texte_au_hasard);
        tmp = tmp.replace("hasard", "toto");
        TextView fourthTV = findViewById(R.id.myFourthTextView);
        fourthTV.setText(tmp);

    }
}