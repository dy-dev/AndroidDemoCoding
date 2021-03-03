package com.arcreane.democoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GuessANumber extends AppCompatActivity {
    private Difficulty m_eGameDifficulty = Difficulty.EASY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_a_number);
    }
}