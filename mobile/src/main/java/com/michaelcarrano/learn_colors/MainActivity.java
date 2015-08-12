package com.michaelcarrano.learn_colors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String mAnswer;
    private String[] mColorName;
    private List<ColorsModel> mColors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the resources so we can create MyColor objects
        mColorName = getResources().getStringArray(R.array.color_names);
        String[] colorHex = getResources().getStringArray(R.array.color_hex);

        // List of MyColors
        if (mColors.size() == 0) {
            for (int i = 0; i < mColorName.length; i++) {
                mColors.add(new ColorsModel(mColorName[i], colorHex[i]));
            }
            // Shuffle list
            Collections.shuffle(mColors);
        }

        // Setup the GridView
        GridView gridView = (GridView) findViewById(R.id.color_grid);
        gridView.setAdapter(new GridViewAdapter(this, mColors));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selected = mColors.get(position).getName();
                if (selected.equals(mAnswer)) {
                    Toast.makeText(getBaseContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                    generateQuestion();
                } else {
                    Toast.makeText(getBaseContext(), R.string.wrong, Toast.LENGTH_SHORT).show();
                }
            }
        });

        generateQuestion();
    }

    /**
     * Function to return a random number within a range. Used to randomly select the color
     * question.
     */
    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates the random color question to be asked. ie: Select the color... Blue
     */
    private void generateQuestion() {
        mAnswer = mColorName[randInt(0, mColors.size() - 1)];
        TextView textView = (TextView) findViewById(R.id.question_text);
        textView.setText(getString(R.string.select_color) + " " + mAnswer);
    }

}
