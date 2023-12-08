package com.example.shotactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {

    // Initialize media player
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerTwo = findViewById(R.id.playerTwo);
        Button startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                intent.putExtra("playerOne", getPlayerOneName);
                intent.putExtra("playerTwo", getPlayerTwoName);

                // Play sound effect when start game button is clicked
                final MediaPlayer startGameButtonMediaPlayer = MediaPlayer.create(AddPlayers.this, R.raw.notif);
                startGameButtonMediaPlayer.start();
                startGameButtonMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        startGameButtonMediaPlayer.release();
                    }
                });

                startActivity(intent);
            }
        });
    }

    public void playBackgroundMusic(View v) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
            // loop the song
            mediaPlayer.setLooping(true);
        } else if (!mediaPlayer.isPlaying()) {
            // Reset and start the MediaPlayer if it's not playing
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
            mediaPlayer.setLooping(true);
        }

        mediaPlayer.start();
    }

    public void stopBackgroundMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
    }

    // When the activity is resumed, if the background music is not playing, start playing the background music
    @Override
    protected void onResume() {
        super.onResume();
        playBackgroundMusic(null);
    }

    // When the activity is paused, stop playing the background music
    @Override
    protected void onStop() {
        super.onStop();
        stopBackgroundMusic();

    }
}