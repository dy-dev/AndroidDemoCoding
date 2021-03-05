package com.arcreane.democoding;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessANumber extends AppCompatActivity {
    private Difficulty m_eGameDifficulty = Difficulty.EASY;
    private int m_iNumberToGuess;
    private List<String> m_historicArray;
    private ArrayAdapter<String> m_historicAdapter;
    private int m_iScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_a_number);

        m_historicArray = new ArrayList<>();
        m_historicAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, m_historicArray);
        ListView historicListView = findViewById(R.id.historicListView);
        historicListView.setAdapter(m_historicAdapter);
        Button validateDifficulty = findViewById(R.id.validateDifficulty);
        validateDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
                Random rand = new Random();
                switch (difficultyRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.easyRadioButton:
                        m_eGameDifficulty = Difficulty.EASY;
                        m_iNumberToGuess = rand.nextInt(10);
                        break;
                    case R.id.mediumRadioButton:
                        m_eGameDifficulty = Difficulty.MEDIUM;
                        m_iNumberToGuess = rand.nextInt(50);
                        break;
                    case R.id.hardRadioButton:
                        m_eGameDifficulty = Difficulty.HARD;
                        m_iNumberToGuess = rand.nextInt(100);
                        break;
                }

                ViewGroup layout = findViewById(R.id.difficultyLayout);
                layout.setVisibility(View.INVISIBLE);
                layout = findViewById(R.id.gameLayout);
                layout.setVisibility(View.VISIBLE);
                TextView scoreTextView = findViewById(R.id.scoreTextView);
                switch (m_eGameDifficulty) {
                    case EASY:
                        scoreTextView.setTextColor(Color.GREEN);
                        break;
                    case MEDIUM:
                        scoreTextView.setTextColor(Color.BLUE);
                        break;
                    case HARD:
                        scoreTextView.setTextColor(Color.RED);
                        break;
                }
            }
        });
    }

    public void checkGuessUser(View view) {
        EditText userGuessEditText = findViewById(R.id.userGuessEditText);
        String guess = userGuessEditText.getText().toString();
        try {
            int guessAsInt = Integer.parseInt(guess);
            validateGuess(guessAsInt);
            userGuessEditText.setText("");
        } catch (NumberFormatException ex) {
            Log.e("Convert", "Error conversion " + ex.getMessage());
            Toast.makeText(this, "Uniquement des chiffres pas de text", Toast.LENGTH_LONG).show();
        }

    }

    private void validateGuess(int guessAsInt) {
        ProgressBar progressBar = findViewById(R.id.nbTriesProgressBar);
        TextView hintInfoTextView = findViewById(R.id.hintInfoTextView);
        if (guessAsInt == m_iNumberToGuess) {
            hintInfoTextView.setText("CORRECT !!!!!!");
            setScore(m_iScore + 10 - progressBar.getProgress());
            progressBar.setProgress(0);
            Log.d("Check", String.valueOf(m_iScore));
        } else {
            progressBar.incrementProgressBy(1);
            TextView nbTriesTextView = findViewById(R.id.nbTriesTextView);
            nbTriesTextView.setText(String.valueOf(progressBar.getProgress()));
            String feedback = guessAsInt > m_iNumberToGuess ? "Too big" : "Too small";
            hintInfoTextView.setText(feedback);

            m_historicArray.add(hintInfoTextView.getText() + " : " + guessAsInt);
            m_historicAdapter.notifyDataSetChanged();

            if (progressBar.getProgress() == progressBar.getMax()) {
                Toast.makeText(this, "Sorry you lost, please try again", Toast.LENGTH_LONG).show();
                setScore(0);
            }

            Log.d("Check", "Number proposed by user : "
                    + guessAsInt + ", number to guess  " + m_iNumberToGuess);
        }
    }


    void setScore(int scoreVal) {
        m_iScore = scoreVal;
        TextView scoreView = findViewById(R.id.scoreView);
        scoreView.setText(String.valueOf(m_iScore));
    }
}