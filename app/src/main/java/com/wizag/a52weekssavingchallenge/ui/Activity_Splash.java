package com.wizag.a52weekssavingchallenge.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wizag.a52weekssavingchallenge.R;

public class Activity_Splash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        /* New Handler to start the main-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Activity_Splash.this,MainActivity.class);
                Activity_Splash.this.startActivity(mainIntent);
                Activity_Splash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}