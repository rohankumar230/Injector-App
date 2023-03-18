package com.plexus.lieshooter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends Activity {

    private static long back_pressed;
    static boolean splashloaded=false;

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(Color.parseColor("#FF121212"));
            activity.getWindow().setNavigationBarColor(Color.parseColor("#FF121212"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setLightStatusBar(this);
        new Thread() {
			ProgressBar progressva = findViewById(R.id.progressbar);
            public void run() {
                try {
                    progressva.setProgress(5);
                    sleep(25);
                    progressva.setProgress(10);
                    sleep(225);
                    progressva.setProgress(15);
                    sleep(25);
                    progressva.setProgress(20);
                    sleep(25);
                    progressva.setProgress(25);
                    sleep(70);
                    progressva.setProgress(30);
                    sleep(25);
                    progressva.setProgress(35);
                    sleep(100);
                    progressva.setProgress(40);
                    sleep(25);
                    progressva.setProgress(45);
                    sleep(25);
                    progressva.setProgress(50);
                    sleep(25);
                    progressva.setProgress(55);
                    sleep(25);
                    progressva.setProgress(60);
                    sleep(25);
                    progressva.setProgress(65);
                    sleep(25);
                    progressva.setProgress(70);
                    sleep(25);
                    progressva.setProgress(75);
                    sleep(25);
                    progressva.setProgress(80);
                    sleep(25);
                    progressva.setProgress(85);
                    sleep(35);
                    progressva.setProgress(90);
                    sleep(45);
                    progressva.setProgress(95);
                    sleep(50);
                    progressva.setProgress(100);
                    splashloaded=true;
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        if (back_pressed + 2000 > System.currentTimeMillis()){
            finishAffinity();
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
}
