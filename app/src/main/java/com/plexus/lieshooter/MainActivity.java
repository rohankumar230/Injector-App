package com.plexus.lieshooter;

import android.app.*;
import android.content.*;
import android.graphics.Color;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.io.IOException;
import java.util.Calendar;

import android.util.Log;
import android.view.View.OnClickListener;

import androidx.cardview.widget.CardView;

public class MainActivity extends Activity implements OnClickListener 
{

	static {
        System.loadLibrary("native-lib");
    }

	private final String EXPIRED = "EXPIRED";
	private Prefs prefs;
	private TextView exp;

	private native String TITLECHECK();
	private native String TG();
	private native String WEBSITE();

    public static String socket;
    private boolean isDisplay = false;
    private boolean isMenuDis = false;
	public String daemonPath;
	public String Username = "USER";

	private void setLightStatusBar(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			activity.getWindow().setStatusBarColor(Color.parseColor("#FF121212"));
			activity.getWindow().setNavigationBarColor(Color.parseColor("#FF121212"));
		}
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		prefs = Prefs.with(this);
		countDownStart();

        setLightStatusBar(this);
		if (!SplashActivity.splashloaded) {
			finish();
		}
		
		TextView txtTitle = findViewById(R.id.txtTitle);
		if (!txtTitle.getText().toString().equals(new  String(TITLECHECK()))) {
			finish();
		}
		txtTitle.setText(TITLECHECK());

		TextView txtName = findViewById(R.id.txtName);
		txtName.setText(prefs.read(Username, "JxL CHEATS"));
        /*if (!LoaderActivity.rooted) {
            TextView txtMode = findViewById(R.id.txtMode);
            txtMode.setText("Mode          : Virtual");
        }else{
            TextView txtMode = findViewById(R.id.txtMode);
            txtMode.setText("Mode          : Root");
        }
        if (LoaderActivity.gameType == 1) {
            TextView txtVersion = findViewById(R.id.txtVersion);
            txtVersion.setText("Version      : Global");
        }else if (LoaderActivity.gameType == 2) {
            TextView txtVersion = findViewById(R.id.txtVersion);
            txtVersion.setText("Version      : Korea");
        }else if (LoaderActivity.gameType == 3) {
            TextView txtVersion = findViewById(R.id.txtVersion);
            txtVersion.setText("Version      : Vietnam");
        }else if (LoaderActivity.gameType == 4) {
            TextView txtVersion = findViewById(R.id.txtVersion);
            txtVersion.setText("Version      : Taiwan");
        }else if (LoaderActivity.gameType == 5) {
            TextView txtVersion = findViewById(R.id.txtVersion);
            txtVersion.setText("Version      : India");
        }*/

		initialization();
		CardView startBtnView = findViewById(R.id.startBtnView);
		startBtnView.setOnClickListener(this);
        CardView stopBtnView = findViewById(R.id.stopBtnView);
		stopBtnView.setOnClickListener(this);

		CardView communityServer = findViewById(R.id.communityServer);
		communityServer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(TG()));
				startActivity(intent);
			}
		});

		CardView officialWebsite = findViewById(R.id.officialWebsite);
		officialWebsite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(WEBSITE()));
				startActivity(intent);
			}
		});



		loadAssets();
    }
	
	public void onClick(View v) {
		switch (v.getId()) { 
			case R.id.startBtnView:
				if (SplashActivity.splashloaded && LoaderActivity.loaderLoaded) {
					if (isDisplay == false && isMenuDis == false) {
						if (!LoaderActivity.rooted) {
							socket = daemonPath;
						}
						if (LoaderActivity.rooted) {
							socket = "su -c " + daemonPath;
						}
						
									Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.pubg.imobile");
									if (launchIntent != null) {
										startActivity(launchIntent);
										startPatcher();
										//	startService(new Intent(this, Overlay.class));
										isDisplay = true;
									}
								
				
						
					} else {
						Toast.makeText(MainActivity.this, "Already Started !!", Toast.LENGTH_LONG).show();
					}
				}
				break;
			case R.id.stopBtnView:
				if (SplashActivity.splashloaded && LoaderActivity.loaderLoaded) {
					if (isDisplay == true) {
						isDisplay = false;
						isMenuDis = false;
						stopService(new Intent(this, FloatLogo.class));
						stopService(new Intent(this, Overlay.class));
					}
					if (FloatLogo.floatAimbot) {
						setValue("Aimbot FOV", false);
						stopFloatAimbot();
					}
				}
				break;
		}
	}
	Handler handler = new Handler();
	private void countDownStart() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					handler.postDelayed(this, 1000);
					long duration = Long.parseLong(prefs.read(EXPIRED, ""));
					long now = Calendar.getInstance().getTimeInMillis();
					long distance = duration - now;
					long days = distance / (24 * 60 * 60 * 1000);
					long hours = distance / (60 * 60 * 1000) % 24;
					long minutes = distance / (60 * 1000) % 60;
					long seconds = distance / 1000 % 60;
					if (distance < 0) {
					} else{
						exp = findViewById(R.id.txtExpired);
						if (days == 0 && hours == 0 && minutes == 0) {
							exp.setText(String.format("%02d", seconds) + "s");
						}else if (days == 0 && hours == 0) {
							exp.setText(String.format("%02d", minutes) + "m " + String.format("%02d", seconds) + "s");
						}else if (days == 0) {
							exp.setText(String.format("%02d", hours) + "h " + String.format("%02d", minutes) + "m " + String.format("%02d", seconds) + "s");
						}else{
							exp.setText(String.format("%02d", days) + "d " + String.format("%02d", hours) + "h " + String.format("%02d", minutes) + "m " + String.format("%02d", seconds) + "s");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		handler.postDelayed(runnable, 0);
	}
    private boolean MvAssets(String outPath, String fileName) {
        File file = new File(outPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try {
            InputStream inputStream = getAssets().open(fileName);
            File outFile = new File(file, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void initialization() {
		if (!LoaderActivity.loaderLoaded) { //Jump Login Protection
        	finish();
        } else {
			MvAssets(getFilesDir() + "/", "bypass");
			MvAssets(getFilesDir() + "/", "island");
			MvAssets(getFilesDir() + "/", "trinity");
			Handler Handler = new Handler();
			Handler.postDelayed(new Runnable() {
					@Override
					public void run() {}}, 6000);
		}
    }

	private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
				if (FloatLogo.class.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

	void startPatcher() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 123);
        } else {
            startFloater();
        }
    }

    private void startFloater() {
        if (!isServiceRunning()) {
			startService(new Intent(this, FloatLogo.class));
        } else {
            Toast.makeText(this, "Service Already Running!", Toast.LENGTH_SHORT).show();
        }
    }
	

	private void  setValue(String key,boolean b) {
        SharedPreferences sp=this.getSharedPreferences("espValue",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed= sp.edit();
        ed.putBoolean(key,b);
        ed.apply();
    }
	
	private void stopFloatAimbot(){
		stopService(new Intent(this, FloatAimbot.class));
	}

	private void ExecuteElf(String shell) {
		String s=shell;

		try {
			Runtime.getRuntime().exec(s, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void RunCPP(String path) {
		try {
			ExecuteElf("chmod 777 " + getFilesDir() + path);
			ExecuteElf(getFilesDir() + path);
			ExecuteElf("su -c chmod 777 " + getFilesDir() + path);
			ExecuteElf("su -c " + getFilesDir() + path);
		} catch (Exception e) {
		}
	}

	public void loadAssets() {
		String filepath =Environment.getExternalStorageDirectory() + "/Android/data/.tyb";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			byte[] buffer = "DO NOT DELETE".getBytes();
			fos.write(buffer, 0, buffer.length);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String pathf = getFilesDir().toString() + "/sock";
		try {	
			OutputStream myOutput = new FileOutputStream(pathf);
			byte[] buffer = new byte[1024];
			int length;
			InputStream myInput = getAssets().open("sock");
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myInput.close();
			myOutput.flush();
			myOutput.close();
		} catch (IOException e) { 
		}
		daemonPath = getFilesDir().toString() + "/sock";
		try {
			Runtime.getRuntime().exec("chmod 777 " + daemonPath);
		} catch (IOException e) { 
		}
	}

	

	private static long back_pressed;
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

