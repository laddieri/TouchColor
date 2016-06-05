package com.example.laddieri.touchcolor;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int soundID;
    boolean plays = false, loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    int loop=0;

    int touchCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Touch the screen!",
                Toast.LENGTH_SHORT).show();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        soundID = soundPool.load(this, R.raw.clap, 1);




        final Random rand = new Random();
        final ImageView adamView = (ImageView)findViewById(R.id.adamImage);


        final View view = findViewById(R.id.bigSquare);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                int randomColor = Color.rgb(r,g,b);

                view.setBackgroundColor(randomColor);

                touchCounter++;

                if (touchCounter%10==0){
                    Toast.makeText(MainActivity.this, "OMG " + touchCounter + " touches!",
                            Toast.LENGTH_SHORT).show();
                    playSound();
                }

                if (touchCounter>100 && touchCounter < 110) {
                    adamView.setVisibility(View.VISIBLE);
                } else {
                    adamView.setVisibility(View.INVISIBLE);
                }

            }
        });


    }

    public void playSound() {
        // Is the sound loaded does it already play?
        if (loaded) {
            soundPool.play(soundID, volume, volume, 1, 0, 1f);
            plays = true;
            loop=1;
        }
    }

    public void playLoop() {
        // Is the sound loaded does it already play?
        if (loaded && !plays) {
            // the sound will play for ever if we put the loop parameter -1
            soundPool.play(soundID, volume, volume, 1, -1, 1f);
            plays = true;
        }
    }

    public void pauseSound() {
        if (plays) {
            soundPool.pause(soundID);
            soundID = soundPool.load(this, R.raw.clap,1);
            plays = false;
        }
    }

    public void stopSound(View v) {
        if (plays) {
            soundPool.stop(soundID);
            soundID = soundPool.load(this, R.raw.clap, 1);
            plays = false;
        }
    }




    public void reset (View view){
        touchCounter = 0;
        Toast.makeText(MainActivity.this, "Back to the beginning! " + touchCounter + " touches!",
                Toast.LENGTH_SHORT).show();
    }





}
