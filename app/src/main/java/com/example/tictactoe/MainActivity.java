package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridLayout myGridLayout;
    private Button exit, reset;
    private boolean gameOver = false;
    private GridLayout gridLayout;

    private enum Player {
        ONE, TWO, NO
    }

    Player currentPlayer = Player.ONE;
    Player playerChoices[] = new Player[9];

    int[][] winnnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 9; i++) {
            playerChoices[i] = Player.NO;
        }
        reset = findViewById(R.id.reset);
        exit = findViewById(R.id.exit);
        gridLayout = findViewById(R.id.myGridLayout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void imageViewTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;

        int Tag = Integer.parseInt(tappedImageView.getTag().toString());
        if (playerChoices[Tag] == Player.NO && gameOver == false) {
            playerChoices[Tag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.animate().rotationY(180).setDuration(500);
                tappedImageView.setImageResource(R.drawable.x);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.animate().rotation(180).setDuration(500);
                tappedImageView.setImageResource(R.drawable.o);
                currentPlayer = Player.ONE;
            }
            // Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.NO) {
                    gameOver = true;
                    reset.setVisibility(View.VISIBLE);
                    exit.setVisibility(View.VISIBLE);
                    if (currentPlayer == Player.ONE) {
                        Toast.makeText(this, "Player 2 is winner", Toast.LENGTH_SHORT).show();
                    } else if (currentPlayer == Player.TWO) {
                        Toast.makeText(this, "Player 1 is winner", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }

    }

    public void resetGame()
    {
        for(int index= 0 ; index<gridLayout.getChildCount(); index++)
        {
            ImageView imageView = (ImageView)gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);

            currentPlayer = Player.ONE;
            for (int i = 0; i < 9; i++) {
                playerChoices[i] = Player.NO;
            }
            gameOver = false;
            reset.setVisibility(View.GONE);
            exit.setVisibility(View.GONE);
        }
    }
}