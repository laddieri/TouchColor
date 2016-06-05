package com.example.laddieri.touchcolor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int touchCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                }

                if (touchCounter>100 && touchCounter < 110) {
                    adamView.setVisibility(View.VISIBLE);
                } else {
                    adamView.setVisibility(View.INVISIBLE);
                }

            }
        });


    }

    public void reset (View view){
        touchCounter = 0;
        Toast.makeText(MainActivity.this, "Back to the beginning! " + touchCounter + " touches!",
                Toast.LENGTH_SHORT).show();
    }





}
