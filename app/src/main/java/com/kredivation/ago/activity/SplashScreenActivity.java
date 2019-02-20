package com.kredivation.ago.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.kredivation.ago.R;
import com.kredivation.ago.utility.FontManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashScreenActivity extends AppCompatActivity {
    private Boolean CheckOrientation = false;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);// Removes action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);// Removes title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(SplashScreenActivity.this, "fonts/materialdesignicons-webfont.otf");
        Typeface roboto_regular = FontManager.getFontTypefaceMaterialDesignIcons(SplashScreenActivity.this, "fonts/roboto.regular.ttf");
        Typeface roboto_medium = FontManager.getFontTypefaceMaterialDesignIcons(SplashScreenActivity.this, "fonts/roboto.medium.ttf");
        Typeface roboto_italic = FontManager.getFontTypefaceMaterialDesignIcons(SplashScreenActivity.this, "fonts/roboto.italic.ttf");
        TextView logo = (TextView) findViewById(R.id.logo);
        logo.setTypeface(roboto_medium);
        // previous.setText(Html.fromHtml("&#xf141;"));
        TextView phone_icon = (TextView) findViewById(R.id.phone_icon);
        phone_icon.setTypeface(roboto_italic);
        waitForLogin();

    }


    //wait for 3 seconds
    private void waitForLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }, 3000);
    }

    public void getHSAKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

}
