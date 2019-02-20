package com.kredivation.ago.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    Spinner stateSpinner, referSpinner, ProductServicSpinner, TimeSlotSpinner, colorSpinner, companySpinner;
    ArrayList<String> StateList;
    ArrayList<Integer> stateIdList;
    ArrayList<Integer> referIdList;
    ArrayList<String> referList;
    int selectReferId, selectStateId;
    ArrayAdapter<String> roleadapter;
    ArrayAdapter<String> productadapter;
    ArrayAdapter<String> companyadapter;
    ArrayAdapter<String> referadapter;
    ArrayAdapter<String> timeSlotadapter;
    ArrayAdapter<String> coloradapter;
    ASTProgressBar dotDialog;
    private EditText Name, pincode, address, phone, password, regNo, model;
    String NameStr, pincodeStr, addressStr, phoneStr, passwordStr;

    ArrayList<String> productList;
    ArrayList<Integer> productIdList;
    ArrayList<String> timeSlotList;
    ArrayList<Integer> timeSlotIdList;
    ArrayList<String> colorList;
    ArrayList<Integer> colorIdList;
    ArrayList<String> companyList;
    ArrayList<Integer> companyIdList;
    int selectcompanyId, selectcolorId, selecttimeId, selectproductId;
    Button singup, SubmitVehicleDetails;
    long customerId;
    boolean submitFlag = true;
    CardView vichileLayout;
    LinearLayout signupLayout;
    ASTProgressBar signupDialog;
    String resStr, modelStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(SignUpActivity.this, "fonts/materialdesignicons-webfont.otf");
        Typeface roboto_regular = FontManager.getFontTypefaceMaterialDesignIcons(SignUpActivity.this, "fonts/roboto.regular.ttf");
        Typeface roboto_medium = FontManager.getFontTypefaceMaterialDesignIcons(SignUpActivity.this, "fonts/roboto.medium.ttf");
        Typeface roboto_italic = FontManager.getFontTypefaceMaterialDesignIcons(SignUpActivity.this, "fonts/roboto.italic.ttf");
        TextView logo = findViewById(R.id.logo);
        logo.setTypeface(roboto_medium);
        TextView loginDetail = findViewById(R.id.loginDetail);
        loginDetail.setTypeface(roboto_medium);
        TextView vehicledetail = findViewById(R.id.vehicledetail);
        vehicledetail.setTypeface(roboto_medium);

        TextView personal = findViewById(R.id.personal);
        personal.setTypeface(roboto_medium);
        TextView phoicon = (TextView) findViewById(R.id.phoicon);
        phoicon.setTypeface(materialdesignicons_font);
        phoicon.setText(Html.fromHtml("&#xf3f2;"));
        TextView passicon = (TextView) findViewById(R.id.passicon);
        passicon.setTypeface(materialdesignicons_font);
        passicon.setText(Html.fromHtml("&#xf33e;"));

        TextView nameIcon = (TextView) findViewById(R.id.nameIcon);
        nameIcon.setTypeface(materialdesignicons_font);
        nameIcon.setText(Html.fromHtml("&#xf004;"));

        TextView modelIcon = findViewById(R.id.modelIcon);
        modelIcon.setTypeface(materialdesignicons_font);
        modelIcon.setText(Html.fromHtml("&#xf10b;"));

        TextView regIcon = findViewById(R.id.regIcon);
        regIcon.setTypeface(materialdesignicons_font);
        regIcon.setText(Html.fromHtml("&#xf219;"));

        TextView fnameIcon = (TextView) findViewById(R.id.fnameIcon);
        fnameIcon.setTypeface(materialdesignicons_font);
        fnameIcon.setText(Html.fromHtml("&#xf004;"));
        TextView emailIcon = (TextView) findViewById(R.id.emailIcon);
        emailIcon.setTypeface(materialdesignicons_font);
        emailIcon.setText(Html.fromHtml("&#xf1ee;"));
        TextView addIcon = (TextView) findViewById(R.id.addIcon);
        addIcon.setTypeface(materialdesignicons_font);
        addIcon.setText(Html.fromHtml("&#xf2dc;"));
        TextView pinIcon = (TextView) findViewById(R.id.pinIcon);
        pinIcon.setTypeface(materialdesignicons_font);
        pinIcon.setText(Html.fromHtml("&#xf34e;"));
        TextView stateIcon = (TextView) findViewById(R.id.stateIcon);
        stateIcon.setTypeface(materialdesignicons_font);
        stateIcon.setText(Html.fromHtml("&#xf5f8;"));
        TextView referIcon = (TextView) findViewById(R.id.referIcon);
        referIcon.setTypeface(materialdesignicons_font);
        referIcon.setText(Html.fromHtml("&#xf00a;"));
        TextView companyIcon = (TextView) findViewById(R.id.companyIcon);
        companyIcon.setTypeface(materialdesignicons_font);
        companyIcon.setText(Html.fromHtml("&#xf2dd;"));
        TextView colorIcon = (TextView) findViewById(R.id.colorIcon);
        colorIcon.setTypeface(materialdesignicons_font);
        colorIcon.setText(Html.fromHtml("&#xf266;"));
        TextView timeIcon = (TextView) findViewById(R.id.timeIcon);
        timeIcon.setTypeface(materialdesignicons_font);
        timeIcon.setText(Html.fromHtml("&#xf51b;"));
        TextView productIcon = (TextView) findViewById(R.id.productIcon);
        productIcon.setTypeface(materialdesignicons_font);
        productIcon.setText(Html.fromHtml("&#xf219;"));

        Name = findViewById(R.id.Name);
        pincode = findViewById(R.id.pincode);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        ProductServicSpinner = findViewById(R.id.ProductServicSpinner);
        TimeSlotSpinner = findViewById(R.id.TimeSlotSpinner);
        colorSpinner = findViewById(R.id.colorSpinner);
        companySpinner = findViewById(R.id.companySpinner);
        regNo = findViewById(R.id.regNo);
        model = findViewById(R.id.model);

        vichileLayout = findViewById(R.id.vichileLayout);
        signupLayout = findViewById(R.id.signupLayout);

        dotDialog = new ASTProgressBar(SignUpActivity.this);
        dotDialog.show();
        singup = findViewById(R.id.singup);
        singup.setTypeface(roboto_regular);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SubmitVehicleDetails = findViewById(R.id.SubmitVehicleDetails);
        SubmitVehicleDetails.setTypeface(roboto_regular);
        SubmitVehicleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isvalidateSignup()) {
                    signUpService();
                }
            }
        });
        TextView loginvehile = findViewById(R.id.loginvehile);
        loginvehile.setTypeface(roboto_regular);
        loginvehile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        stateSpinner = findViewById(R.id.stateSpinner);
        StateList = new ArrayList<>();
        stateIdList = new ArrayList<>();
        StateList.add("--Select State--");
        stateIdList.add(0);
        roleadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, StateList);
        stateSpinner.setAdapter(roleadapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectStateId = stateIdList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        referSpinner = findViewById(R.id.referSpinner);
        referList = new ArrayList<>();
        referIdList = new ArrayList<>();
        referList.add("--Select RefereBy--");
        referIdList.add(0);
        referadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, referList);
        referSpinner.setAdapter(referadapter);

        referSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectReferId = referIdList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        companyList = new ArrayList<>();
        companyIdList = new ArrayList<>();
        companyList.add("--Select Company Name--");
        companyIdList.add(0);
        companyadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, companyList);
        companySpinner.setAdapter(companyadapter);

        companySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectcompanyId = companyIdList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        productList = new ArrayList<>();
        productIdList = new ArrayList<>();
        productList.add("--Select Product/Service--");
        productIdList.add(0);
        productadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, productList);
        ProductServicSpinner.setAdapter(productadapter);

        ProductServicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectproductId = productIdList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        timeSlotList = new ArrayList<>();
        timeSlotIdList = new ArrayList<>();
        timeSlotList.add("--Select Time Slot--");
        timeSlotIdList.add(0);
        timeSlotadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, timeSlotList);
        TimeSlotSpinner.setAdapter(timeSlotadapter);

        TimeSlotSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selecttimeId = timeSlotIdList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        colorList = new ArrayList<>();
        colorIdList = new ArrayList<>();
        colorList.add("--Select Color--");
        colorIdList.add(0);
        coloradapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, colorList);
        colorSpinner.setAdapter(coloradapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                selectcolorId = colorIdList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        GetAllState();

    }

    private void GetAllState() {
        if (Utility.isOnline(SignUpActivity.this)) {
            String serviceURL = Contants.BASE_URL + Contants.GetAllState;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, object, "GetAllState", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        parseStatedata(result);
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void parseStatedata(String result) {
        try {
            JSONObject resultObj = new JSONObject(result);
            JSONArray jsonArray = resultObj.optJSONArray("stateList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                int id = jsonObject.optInt("iD");
                String stateName = jsonObject.optString("name");
                StateList.add(stateName);
                stateIdList.add(id);
            }
            roleadapter.notifyDataSetChanged();
            GetReferedBy();
        } catch (JSONException e) {
        }
    }

    private void GetReferedBy() {
        if (Utility.isOnline(SignUpActivity.this)) {
            String serviceURL = Contants.BASE_URL + Contants.GetReferedBy;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, object, "GetReferedBy", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        parsereferBy(result);
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void parsereferBy(String result) {
        try {
            JSONObject resultObj = new JSONObject(result);
            JSONArray jsonArray = resultObj.optJSONArray("referenceEmployeeList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                int id = jsonObject.optInt("iD");
                String name = jsonObject.optString("name");
                referList.add(name);
                referIdList.add(id);
            }
            referadapter.notifyDataSetChanged();
            GetAllCompany();
        } catch (JSONException e) {
        }
    }

    public boolean isvalidateSignup() {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        NameStr = Name.getText().toString();
        pincodeStr = pincode.getText().toString();
        addressStr = address.getText().toString();
        phoneStr = phone.getText().toString();
        passwordStr = password.getText().toString();
        resStr = regNo.getText().toString();
        modelStr = model.getText().toString();
        if (NameStr.equals("")) {
            Toast.makeText(SignUpActivity.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (pincodeStr.equals("")) {
            Toast.makeText(SignUpActivity.this, "Please Enter Pincode", Toast.LENGTH_SHORT).show();
            return false;
        } else if (addressStr.equals("")) {
            Toast.makeText(SignUpActivity.this, "Please Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneStr.equals("")) {
            Toast.makeText(SignUpActivity.this, "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordStr.equals("")) {
            Toast.makeText(SignUpActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectReferId == 0) {
            Toast.makeText(SignUpActivity.this, "Please Select Employee ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectStateId == 0) {
            Toast.makeText(SignUpActivity.this, "Please Select State", Toast.LENGTH_SHORT).show();
            return false;
        } else if (modelStr.length() == 0) {
            Toast.makeText(SignUpActivity.this, "Please Enter Model Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (resStr.length() == 0) {
            Toast.makeText(SignUpActivity.this, "Please Enter Registration No", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectcompanyId == 0) {
            Toast.makeText(SignUpActivity.this, "Please Select Company Name ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectcolorId == 0) {
            Toast.makeText(SignUpActivity.this, "Please Select Color ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selecttimeId == 0) {
            Toast.makeText(SignUpActivity.this, "Please Select Time Slot ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (selectproductId == 0) {
            Toast.makeText(SignUpActivity.this, "Please Select Product/Service ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signUpService() {
        if (Utility.isOnline(SignUpActivity.this)) {
            signupDialog = new ASTProgressBar(SignUpActivity.this);
            signupDialog.show();
            String serviceURL = Contants.BASE_URL + Contants.UserRegistration;
            JSONObject object = new JSONObject();
            try {
                object.put("Name", NameStr);
                object.put("LoginPassword", passwordStr);
                object.put("MobileNumber", phoneStr);
                object.put("tblMasterStateId", String.valueOf(selectStateId));
                object.put("Pincode", pincodeStr);
                object.put("Adress", addressStr);
                object.put("ReferredBy", String.valueOf(selectReferId));
            } catch (JSONException e) {
                // e.printStackTrace();
            }
            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanServiceMethod(serviceURL, object, "signUpService", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete && result != null) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            int status = resultObj.optInt("status");
                            if (status == 0) {
                                Toast.makeText(SignUpActivity.this, "SignUp not success!", Toast.LENGTH_SHORT).show();
                                if (signupDialog.isShowing()) {
                                    signupDialog.dismiss();
                                }
                            } else {
                                vichileLayout.setVisibility(View.VISIBLE);
                                signupLayout.setVisibility(View.GONE);
                                customerId = resultObj.optLong("customerId");
                                SharedPreferences UserInfo = getSharedPreferences("UserInfoSharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor editor = UserInfo.edit();
                                editor.putLong("customerId", customerId);
                                editor.commit();
                                CarService();
                            }
                        } catch (JSONException e) {
                        }

                    } else {
                        if (signupDialog.isShowing()) {
                            signupDialog.dismiss();
                        }
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void CarService() {
        if (Utility.isOnline(SignUpActivity.this)) {
            String serviceURL = Contants.BASE_URL + Contants.PostVehicleDetail;
            JSONObject object = new JSONObject();
            try {
                object.put("CustomerId", String.valueOf(customerId));
                object.put("CompanyName", String.valueOf(selectcompanyId));
                object.put("Model", modelStr);
                object.put("RegistrationNo", resStr);
                object.put("Color", String.valueOf(selectcolorId));
                object.put("Product", String.valueOf(selectproductId));
                object.put("TimeSlot", String.valueOf(selecttimeId));
            } catch (JSONException e) {
                // e.printStackTrace();
            }
            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanServiceMethod(serviceURL, object, "CarService", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete && result != null) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            int status = resultObj.optInt("status");
                            if (status == 1) {
                                Toast.makeText(SignUpActivity.this, "Thanks for Registration", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration not submit!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                        }

                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                    if (signupDialog.isShowing()) {
                        signupDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void GetAllCompany() {
        if (Utility.isOnline(SignUpActivity.this)) {

            String serviceURL = Contants.BASE_URL + Contants.GetVehicleCompany;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, object, "GetAllCompany", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            JSONArray jsonArray = resultObj.optJSONArray("vehicleCompanyList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.optJSONObject(i);
                                int id = jsonObject.optInt("iD");
                                String Name = jsonObject.optString("companyName");
                                companyList.add(Name);
                                companyIdList.add(id);
                            }
                            companyadapter.notifyDataSetChanged();
                            GetAllColor();
                        } catch (JSONException e) {
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void GetAllColor() {
        if (Utility.isOnline(SignUpActivity.this)) {
            String serviceURL = Contants.BASE_URL + Contants.GetVehicleColor;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, object, "GetAllColor", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            JSONArray jsonArray = resultObj.optJSONArray("vehicleColorList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.optJSONObject(i);
                                int id = jsonObject.optInt("iD");
                                String Name = jsonObject.optString("colorName");
                                colorList.add(Name);
                                colorIdList.add(id);
                            }
                            coloradapter.notifyDataSetChanged();
                            GetproductList();
                        } catch (JSONException e) {
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void GetproductList() {
        if (Utility.isOnline(SignUpActivity.this)) {
            String serviceURL = Contants.BASE_URL + Contants.GetProduct;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, object, "GetproductList", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            JSONArray jsonArray = resultObj.optJSONArray("productList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.optJSONObject(i);
                                int id = jsonObject.optInt("iD");
                                String Name = jsonObject.optString("productName");
                                productList.add(Name);
                                productIdList.add(id);
                            }
                            productadapter.notifyDataSetChanged();
                            GettimeSlotList();
                        } catch (JSONException e) {
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    private void GettimeSlotList() {
        if (Utility.isOnline(SignUpActivity.this)) {
            String serviceURL = Contants.BASE_URL + Contants.GetTimeSlot;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(SignUpActivity.this);
            serviceCaller.CallCommanGetServiceMethod(serviceURL, object, "GettimeSlotList", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            JSONArray jsonArray = resultObj.optJSONArray("timeSlotList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.optJSONObject(i);
                                int id = jsonObject.optInt("iD");
                                String Name = jsonObject.optString("timeSlotName");
                                timeSlotList.add(Name);
                                timeSlotIdList.add(id);
                            }
                            timeSlotadapter.notifyDataSetChanged();
                            if (dotDialog.isShowing()) {
                                dotDialog.dismiss();
                            }
                        } catch (JSONException e) {
                        }
                    } else {
                        if (dotDialog.isShowing()) {
                            dotDialog.dismiss();
                        }
                        Utility.alertForErrorMessage(Contants.Error, SignUpActivity.this);
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, SignUpActivity.this);//off line msg....
        }
    }

    boolean stateflage, referflag, companyflag, colorflag, productflag, timeflag;

    private void allDatadone() {
        if (stateflage && referflag && companyflag && colorflag && productflag && timeflag) {
            if (dotDialog.isShowing()) {
                dotDialog.dismiss();
            }
        }
    }
}
