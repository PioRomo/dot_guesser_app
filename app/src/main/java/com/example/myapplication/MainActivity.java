package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothClass;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import java.text.DecimalFormat;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fetch textviews
        TextView screen = findViewById(R.id.screen);
        TextView output = findViewById(R.id.output);

        //initialize display metrics to fetch height and width of user screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        screen.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                int x = (int)event.getX();
                int y = (int)event.getY();
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Random random = new Random();
                    int randomHeight = random.nextInt(height);
                    int randomWidth = random.nextInt(width);


                    if(x == randomWidth && y == randomHeight){
                        output.setText("100%! You win!");
                    }
                    else{
                        ImageView actualPos = findViewById(R.id.actualPosition);
                        actualPos.setX(randomWidth);
                        actualPos.setY(randomHeight);

                        ImageView clickPos = findViewById(R.id.clickPosition);
                        clickPos.setX(x);
                        clickPos.setY(y);

                        DecimalFormat df = new DecimalFormat("0.00");
                        double distance = Math.sqrt(Math.pow((randomWidth -x), 2)+ Math.pow((randomHeight - y),2));

                        double xAcc = (x - distance)/ randomWidth;
                        double yAcc = (x - distance) / randomHeight;
                        double avgAcc = ((xAcc + yAcc) / 2) * 100;
                        if(avgAcc <= 0 ){
                            avgAcc = 0;
                        }

                        output.setText("Click Position: " + x + ", " + y );
                        output.append("\nActual Position: " + randomWidth + ", " + randomHeight);
                        output.append("\nDistance: " + df.format(distance));
                        output.append("\nAccuracy: " + df.format(avgAcc) + "%");
                    }
                }
                return true;
            }
        });
    }




}