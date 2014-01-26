package com.michaelcarrano.learn_colors;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends ActionBarActivity {

    public GridView mGridView;

    public TextView mTextView;

    public ArrayList<ColorsModel> mColors;

    public String[] mColorName;

    public String[] mColorHex;

    public String mAnswer;

    /**
     * Function to return a random number within a range. Used to randomly select the color
     * question.
     */
    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the resources so we can create MyColor objects
        mColorName = getResources().getStringArray(R.array.color_names);
        mColorHex = getResources().getStringArray(R.array.color_hex);

        // List of MyColors
        mColors = new ArrayList();
        if (mColors.size() == 0) {
            for (int i = 0; i < mColorName.length; i++) {
                mColors.add(new ColorsModel(mColorName[i], mColorHex[i]));
            }
            // Shuffle list
            Collections.shuffle(mColors);
        }

        // Setup the GridView
        mGridView = (GridView) findViewById(R.id.color_grid);
        mGridView.setAdapter(new GridViewAdapter(this, mColors));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selected = mColors.get(position).name;
                if (selected == mAnswer) {
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
     * Generates the random color question to be asked. ie: Select the color... Blue
     */
    private void generateQuestion() {
        mAnswer = mColorName[randInt(0, mColors.size() - 1)];
        mTextView = (TextView) findViewById(R.id.question_text);
        mTextView.setText(getString(R.string.select_color) + " " + mAnswer);
    }

}
