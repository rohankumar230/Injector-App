package com.plexus.lieshooter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoaderActivity extends Activity {

    static {
        System.loadLibrary("native-lib");
    }

    private native String titleLie();
    private native String titleShooter();
    private static long back_pressed;
    static boolean rooted;
    static int gameType;
    static boolean is32 = true;
    static boolean loaderLoaded = false;

    AlertDialog.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void CheckFloatViewPermission() {
        if (!Settings.canDrawOverlays(this)) {
            new AlertDialog.Builder(this)
                    .setMessage("This application requires overlay permission please give permission first")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, 0);
                        }
                    }).show();
        }
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(Color.parseColor("#FF121212"));
            activity.getWindow().setNavigationBarColor(Color.parseColor("#FF121212"));
        }
    }

    public static boolean isRootAvailable() {
        for (String pathDir : System.getenv("PATH").split(":")) {
            if (new File(pathDir, "su").exists()) {
                return true;
            }
        }
        return false;
    }


    public static boolean isRootGiven() {
        if (isRootAvailable()) {
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(new String[]{"su", "-c", "id"});
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = in.readLine();
                if (output != null && output.toLowerCase().contains("uid=0"))
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null)
                    process.destroy();
            }
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loader);
        setLightStatusBar(this);
        if (!SplashActivity.splashloaded) {
            finish();
        }
        
        TextView txtTitleLie = findViewById(R.id.txtTitleLie);
        if (!txtTitleLie.getText().toString().equals(new  String(titleLie()))) {
            finish();
        }
        txtTitleLie.setText(titleLie());

        TextView txtTitleShooter = findViewById(R.id.txtTitleShooter);
        if (!txtTitleShooter.getText().toString().equals(new  String(titleShooter()))) {
            finish();
        }
        txtTitleShooter.setText(titleShooter());

        loaderLoaded = false;
        rooted = isRootGiven();
        CheckFloatViewPermission();

        boolean isAppInstalledGL = appInstalledOrNot("com.tencent.ig");
        if (isAppInstalledGL) {
            TextView installGL = findViewById(R.id.installGL);
            installGL.setText("Installed");
            installGL.setTextColor(Color.parseColor("#00FF00"));
        } else {
            TextView installGL = findViewById(R.id.installGL);
            installGL.setText("Not Installed");
            installGL.setTextColor(Color.parseColor("#70FF0000"));
            CardView bgGL = findViewById(R.id.bgGL);
            bgGL.setCardBackgroundColor(Color.parseColor("#70212121"));
            TextView txtGL = findViewById(R.id.txtGL);
            txtGL.setTextColor(Color.parseColor("#70FFFFFF"));
            CardView runbgGL = findViewById(R.id.runbgGL);
            runbgGL.setCardBackgroundColor(Color.parseColor("#70E49542"));
            TextView txtrunmenuGL = findViewById(R.id.txtrunmenuGL);
            txtrunmenuGL.setTextColor(Color.parseColor("#70FFFFFF"));
            LinearLayout runGL = findViewById(R.id.runGL);
            runGL.setEnabled(false);
        }

        boolean isAppInstalledVN = appInstalledOrNot("com.vng.pubgmobile");
        if (isAppInstalledVN) {
            TextView installGL = findViewById(R.id.installVN);
            installGL.setText("Installed");
            installGL.setTextColor(Color.parseColor("#00FF00"));
        } else {
            TextView installGL = findViewById(R.id.installVN);
            installGL.setText("Not Installed");
            installGL.setTextColor(Color.parseColor("#70FF0000"));
            CardView bgGL = findViewById(R.id.bgVN);
            bgGL.setCardBackgroundColor(Color.parseColor("#70212121"));
            TextView txtGL = findViewById(R.id.txtVN);
            txtGL.setTextColor(Color.parseColor("#70FFFFFF"));
            CardView runbgGL = findViewById(R.id.runbgVN);
            runbgGL.setCardBackgroundColor(Color.parseColor("#70E49542"));
            TextView txtrunmenuGL = findViewById(R.id.txtrunmenuVN);
            txtrunmenuGL.setTextColor(Color.parseColor("#70FFFFFF"));
            LinearLayout runGL = findViewById(R.id.runVN);
            runGL.setEnabled(false);
        }

        boolean isAppInstalledKR = appInstalledOrNot("com.pubg.krmobile");
        if (isAppInstalledKR) {
            TextView installGL = findViewById(R.id.installKR);
            installGL.setText("Installed");
            installGL.setTextColor(Color.parseColor("#00FF00"));
        } else {
            TextView installGL = findViewById(R.id.installKR);
            installGL.setText("Not Installed");
            installGL.setTextColor(Color.parseColor("#70FF0000"));
            CardView bgGL = findViewById(R.id.bgKR);
            bgGL.setCardBackgroundColor(Color.parseColor("#70212121"));
            TextView txtGL = findViewById(R.id.txtKR);
            txtGL.setTextColor(Color.parseColor("#70FFFFFF"));
            CardView runbgGL = findViewById(R.id.runbgKR);
            runbgGL.setCardBackgroundColor(Color.parseColor("#70E49542"));
            TextView txtrunmenuGL = findViewById(R.id.txtrunmenuKR);
            txtrunmenuGL.setTextColor(Color.parseColor("#70FFFFFF"));
            LinearLayout runGL = findViewById(R.id.runKR);
            runGL.setEnabled(false);
        }

        boolean isAppInstalledTW = appInstalledOrNot("com.rekoo.pubgm");
        if (isAppInstalledTW) {
            TextView installGL = findViewById(R.id.installTW);
            installGL.setText("Installed");
            installGL.setTextColor(Color.parseColor("#00FF00"));
        } else {
            TextView installGL = findViewById(R.id.installTW);
            installGL.setText("Not Installed");
            installGL.setTextColor(Color.parseColor("#70FF0000"));
            CardView bgGL = findViewById(R.id.bgTW);
            bgGL.setCardBackgroundColor(Color.parseColor("#70212121"));
            TextView txtGL = findViewById(R.id.txtTW);
            txtGL.setTextColor(Color.parseColor("#70FFFFFF"));
            CardView runbgGL = findViewById(R.id.runbgTW);
            runbgGL.setCardBackgroundColor(Color.parseColor("#70E49542"));
            TextView txtrunmenuGL = findViewById(R.id.txtrunmenuTW);
            txtrunmenuGL.setTextColor(Color.parseColor("#70FFFFFF"));
            LinearLayout runGL = findViewById(R.id.runTW);
            runGL.setEnabled(false);
        }

        boolean isAppInstalledIN = appInstalledOrNot("com.pubg.imobile");
        if (isAppInstalledIN) {
            TextView installGL = findViewById(R.id.installIN);
            installGL.setText("Installed");
            installGL.setTextColor(Color.parseColor("#00FF00"));
        } else {
            TextView installGL = findViewById(R.id.installIN);
            installGL.setText("Not Installed");
            installGL.setTextColor(Color.parseColor("#70FF0000"));
            CardView bgGL = findViewById(R.id.bgIN);
            bgGL.setCardBackgroundColor(Color.parseColor("#70212121"));
            TextView txtGL = findViewById(R.id.txtIN);
            txtGL.setTextColor(Color.parseColor("#70FFFFFF"));
            CardView runbgGL = findViewById(R.id.runbgIN);
            runbgGL.setCardBackgroundColor(Color.parseColor("#70E49542"));
            TextView txtrunmenuGL = findViewById(R.id.txtrunmenuIN);
            txtrunmenuGL.setTextColor(Color.parseColor("#70FFFFFF"));
            LinearLayout runGL = findViewById(R.id.runBGMI);
            runGL.setEnabled(false);
        }

        LinearLayout runGL = findViewById(R.id.runGL);
        builder = new AlertDialog.Builder(this);
        runGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Global")
                        .setMessage("Please select execution method")
                        .setPositiveButton("Root", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 1;
                                rooted = true;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Virtual", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 1;
                                rooted = false;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        LinearLayout runVN = findViewById(R.id.runVN);
        runVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Vietnam")
                        .setMessage("Please select execution method")
                        .setPositiveButton("Root", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 3;
                                rooted = true;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Virtual", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 3;
                                rooted = false;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        LinearLayout runKR = findViewById(R.id.runKR);
        runKR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Korean")
                        .setMessage("Please select execution method")
                        .setPositiveButton("Root", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 2;
                                rooted = true;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Virtual", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 2;
                                rooted = false;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        LinearLayout runTW = findViewById(R.id.runTW);
        runTW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Taiwan")
                        .setMessage("Please select execution method")
                        .setPositiveButton("Root", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 4;
                                rooted = true;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Virtual", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 4;
                                rooted = false;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        LinearLayout runBGMI = findViewById(R.id.runBGMI);
        runBGMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("India")
                        .setMessage("Please select execution method")
                        .setPositiveButton("Root", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 5;
                                rooted = true;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Virtual", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameType = 5;
                                rooted = false;
                                loaderLoaded = true;
                                startActivity(new Intent(LoaderActivity.this, MainActivity.class));
                                finish();
                            }
                        })
                ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private static final ArrayList<Activity> activities = new ArrayList<Activity>();

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            finishAffinity();
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}
