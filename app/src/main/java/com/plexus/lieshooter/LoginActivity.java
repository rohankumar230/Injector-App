package com.plexus.lieshooter;

import android.app.*;
import android.content.Intent;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.DialogInterface;

public class LoginActivity extends Activity {

    static {
        System.loadLibrary("native-lib");
    }

    private native String getkey();
	public native String MainAct();
    private native String titleLie();
    private native String titleShooter();
    private final String USER = "USER";
    private final String PASS = "PASS";
    private Prefs prefs;
    
    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(Color.parseColor("#FF121212"));
            activity.getWindow().setNavigationBarColor(Color.parseColor("#FF121212"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setLightStatusBar(this);
        setContentView(R.layout.activity_login);
        if (!SplashActivity.splashloaded) {
            finish();
        }
        prefs = Prefs.with(this);
final Context m_Context = (Context) this;
        TextView textUsername = findViewById(R.id.textUsername);
    //    TextView textPassword = findViewById(R.id.textPassword);
        textUsername.setText(prefs.read(USER, ""));
  //      textPassword.setText(prefs.read(PASS, ""));

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
		
        CardView btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textUsername = findViewById(R.id.textUsername);
				//	TextView textPassword = findViewById(R.id.textPassword);

String userKey = textUsername.getText().toString();
				Login(m_Context, userKey);
                    }
                
            });

        TextView getKey = findViewById(R.id.getKey);
        getKey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getkey()));
                startActivity(intent);
            }
        });

    }

private static void Login(final Context m_Context, final String userKey) {
        final ProgressDialog progressDialog = new ProgressDialog(m_Context, 5);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final Handler loginHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {

                    Intent i = new Intent(m_Context.getApplicationContext(), LoaderActivity.class);
                    m_Context.startActivity(i);
                } else if (msg.what == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(m_Context, 5);
                    builder.setTitle("PRO - Error");
                    builder.setMessage(msg.obj.toString());
                    builder.setCancelable(false);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								System.exit(0);
							}
						});
                    builder.show();
                }
                progressDialog.dismiss();
            }
        };

        new Thread(new Runnable() {
				@Override
				public void run() {
					String result = Check(m_Context, userKey);
					if (result.equals("OK")) {
						loginHandler.sendEmptyMessage(0);
					} else {
						Message msg = new Message();
						msg.what = 1;
						msg.obj = result;
						loginHandler.sendMessage(msg);
					}
				}
			}).start();
    }

private static native String Check(Context mContext, String userKey);
}

