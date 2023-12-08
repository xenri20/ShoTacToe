package com.example.shotactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultDialog extends Dialog {

    private final String message;
    private final MainActivity mainActivity;

    public ResultDialog(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);

        TextView messageText = findViewById(R.id.messageText);
        Button startAgainButton = findViewById(R.id.startAgainButton);

        messageText.setText(message);

        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch();
                dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        playSound(this.message);
    }

    // Play winner and draw sound effect
    public void playSound(String msg) {
        final MediaPlayer sound;
        if (msg.contains("is a Winner!")) {
            sound = MediaPlayer.create(this.mainActivity, R.raw.fanfare);
            sound.start();
        } else if (msg.contains("Match Draw")) {
            sound = MediaPlayer.create(this.mainActivity, R.raw.draw);
            sound.start();
        }
    }
}