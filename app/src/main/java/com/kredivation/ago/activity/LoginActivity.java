package com.kredivation.ago.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kredivation.ago.R;
import com.kredivation.ago.framework.IAsyncWorkCompletedCallback;
import com.kredivation.ago.framework.ServiceCaller;
import com.kredivation.ago.utility.ASTProgressBar;
import com.kredivation.ago.utility.Contants;
import com.kredivation.ago.utility.FontManager;
import com.kredivation.ago.utility.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(LoginActivity.this, "fonts/materialdesignicons-webfont.otf");
        Typeface roboto_regular = FontManager.getFontTypefaceMaterialDesignIcons(LoginActivity.this, "fonts/roboto.regular.ttf");
        Typeface roboto_medium = FontManager.getFontTypefaceMaterialDesignIcons(LoginActivity.this, "fonts/roboto.medium.ttf");
        Typeface roboto_italic = FontManager.getFontTypefaceMaterialDesignIcons(LoginActivity.this, "fonts/roboto.italic.ttf");
        TextView logo = (TextView) findViewById(R.id.logo);
        logo.setTypeface(roboto_medium);
        TextView forgotpass = (TextView) findViewById(R.id.forgotpass);
        forgotpass.setTypeface(roboto_italic);
        forgotpass.setPaintFlags(forgotpass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView phoicon = (TextView) findViewById(R.id.phoicon);
        phoicon.setTypeface(materialdesignicons_font);
        phoicon.setText(Html.fromHtml("&#xf3f2;"));
        TextView passicon = (TextView) findViewById(R.id.passicon);
        passicon.setTypeface(materialdesignicons_font);
        passicon.setText(Html.fromHtml("&#xf33e;"));
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        login.setTypeface(roboto_regular);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isvalidateSignup()) {
                    loginService();
                }
            }
        });


        TextView signup = findViewById(R.id.signup);
        signup.setTypeface(roboto_regular);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    String phoneStr, passwordStr;

    public boolean isvalidateSignup() {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        phoneStr = phone.getText().toString();
        passwordStr = password.getText().toString();

        if (phoneStr.equals("")) {
            Toast.makeText(LoginActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordStr.equals("")) {
            Toast.makeText(LoginActivity.this, "Please Enter password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loginService() {
        if (Utility.isOnline(LoginActivity.this)) {
            final ASTProgressBar signupDialog = new ASTProgressBar(LoginActivity.this);
            signupDialog.show();
            String serviceURL = Contants.BASE_URL + Contants.GetLoginResult + phoneStr + "/" + passwordStr;
            JSONObject jsonObject = new JSONObject();
            ServiceCaller serviceCaller = new ServiceCaller(LoginActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, jsonObject, "loginService", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete && result != null) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            int status = resultObj.optInt("status");
                            JSONArray jsonArray = resultObj.optJSONArray("loginDetailList");
                            if (jsonArray != null && jsonArray.length() > 0) {
                                Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.optJSONObject(i);
                                    String role = object.optString("role");
                                    long customerId = object.optLong("customerId");
                                    String mobileNumber = object.optString("mobileNumber");
                                    String name = object.optString("name");
                                    SharedPreferences UserInfo = getSharedPreferences("UserInfoSharedPref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = UserInfo.edit();
                                    editor.putLong("customerId", customerId);
                                    editor.putString("role", role);
                                    editor.putString("mobileNumber", mobileNumber);
                                    editor.putString("name", name);
                                    editor.commit();
                                    break;
                                }
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Login not success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                        }

                    } else {

                        Utility.alertForErrorMessage(Contants.Error, LoginActivity.this);
                    }
                    if (signupDialog.isShowing()) {
                        signupDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, LoginActivity.this);//off line msg....
        }
    }
}
