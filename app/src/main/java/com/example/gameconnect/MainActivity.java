package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    // 0:yellow, 1:red, 2: empty
    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        String winner = " ";
        int tappedCounter = Integer.valueOf(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().rotation(3600).translationYBy(1500).setDuration(300);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().rotation(3600).translationYBy(1500).setDuration(300);
                activePlayer = 0;
            }
            for (int i = 0; i < winningPositions.length; i++) {
                if (gameState[winningPositions[i][0]] == gameState[winningPositions[i][1]] && gameState[winningPositions[i][1]] == gameState[winningPositions[i][2]] && gameState[winningPositions[i][0]] != 2) {
                    // Someone has won
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView gameWinner = (TextView) findViewById(R.id.winnerTextView);
                    gameWinner.setText(winner + " has won!");
                    playAgain.setVisibility(View.VISIBLE);
                    gameWinner.setVisibility(View.VISIBLE);

                }
            }
        }




    }
    public void playAgain(View view) {
        Log.i("Info","Play Again pressed");
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);


        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        Log.i("Info","Play Again button invisible");

        winnerTextView.setVisibility(View.INVISIBLE);
        Log.i("Info","Text Invisible");

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }
        Log.i("Info", "Grid layout clear");

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
