package com.kredivation.ago.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kredivation.ago.R;
import com.kredivation.ago.activity.LoginActivity;
import com.kredivation.ago.activity.SignUpActivity;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddVichileDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddVichileDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AddVichileDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddVichileDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddVichileDetailFragment newInstance(String param1, String param2) {
        AddVichileDetailFragment fragment = new AddVichileDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View view;
    Spinner ProductServicSpinner, TimeSlotSpinner, colorSpinner, companySpinner;
    private EditText regNo, model;

    ArrayList<String> productList;
    ArrayList<Integer> productIdList;
    ArrayList<String> timeSlotList;
    ArrayList<Integer> timeSlotIdList;
    ArrayList<String> colorList;
    ArrayList<Integer> colorIdList;
    ArrayList<String> companyList;
    ArrayList<Integer> companyIdList;
    int selectcompanyId, selectcolorId, selecttimeId, selectproductId;
    Button SubmitVehicleDetails;
    long customerId;
    ArrayAdapter<String> productadapter;
    ArrayAdapter<String> companyadapter;
    ArrayAdapter<String> referadapter;
    ArrayAdapter<String> timeSlotadapter;
    ArrayAdapter<String> coloradapter;
    ASTProgressBar dotDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_vichile_detail, container, false);
        getActivity().setTitle("Vehicle Details");
        init();
        return view;
    }

    private void init() {
        dotDialog = new ASTProgressBar(getContext());
        dotDialog.show();
        Typeface roboto_regular = FontManager.getFontTypefaceMaterialDesignIcons(getContext(), "fonts/roboto.regular.ttf");
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(getContext(), "fonts/materialdesignicons-webfont.otf");
        TextView modelIcon = view.findViewById(R.id.modelIcon);
        modelIcon.setTypeface(materialdesignicons_font);
        modelIcon.setText(Html.fromHtml("&#xf10b;"));
        TextView regIcon = view.findViewById(R.id.regIcon);
        regIcon.setTypeface(materialdesignicons_font);
        regIcon.setText(Html.fromHtml("&#xf219;"));
        TextView companyIcon = (TextView) view.findViewById(R.id.companyIcon);
        companyIcon.setTypeface(materialdesignicons_font);
        companyIcon.setText(Html.fromHtml("&#xf2dd;"));
        TextView colorIcon = (TextView)  view.findViewById(R.id.colorIcon);
        colorIcon.setTypeface(materialdesignicons_font);
        colorIcon.setText(Html.fromHtml("&#xf266;"));
        TextView timeIcon = (TextView)  view.findViewById(R.id.timeIcon);
        timeIcon.setTypeface(materialdesignicons_font);
        timeIcon.setText(Html.fromHtml("&#xf51b;"));
        TextView productIcon = (TextView)  view.findViewById(R.id.productIcon);
        productIcon.setTypeface(materialdesignicons_font);
        productIcon.setText(Html.fromHtml("&#xf219;"));

        ProductServicSpinner = view.findViewById(R.id.ProductServicSpinner);
        TimeSlotSpinner = view.findViewById(R.id.TimeSlotSpinner);
        colorSpinner = view.findViewById(R.id.colorSpinner);
        companySpinner = view.findViewById(R.id.companySpinner);
        regNo = view.findViewById(R.id.regNo);
        model = view.findViewById(R.id.model);

        companyList = new ArrayList<>();
        companyIdList = new ArrayList<>();
        companyList.add("--Select Company Name--");
        companyIdList.add(0);
        companyadapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, companyList);
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
        productadapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, productList);
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
        timeSlotadapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, timeSlotList);
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
        coloradapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_row, colorList);
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

        SubmitVehicleDetails = view.findViewById(R.id.SubmitVehicleDetails);
        SubmitVehicleDetails.setTypeface(roboto_regular);
        SubmitVehicleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarService();
            }
        });
        GetAllCompany();
    }

    private void CarService() {
        if (Utility.isOnline(getActivity())) {
            String resStr = regNo.getText().toString();
            String modelStr = model.getText().toString();
            SharedPreferences UserInfo = getActivity().getSharedPreferences("UserInfoSharedPref", getActivity().MODE_PRIVATE);
            customerId = UserInfo.getLong("customerId", 0);
            if (modelStr.length() == 0) {
                Toast.makeText(getActivity(), "Please Enter Model Number", Toast.LENGTH_SHORT).show();
                return;
            } else if (resStr.length() == 0) {
                Toast.makeText(getActivity(), "Please Enter Registration No", Toast.LENGTH_SHORT).show();
                return;
            } else if (selectcompanyId == 0) {
                Toast.makeText(getActivity(), "Please Select Company Name ", Toast.LENGTH_SHORT).show();
                return;
            } else if (selectcolorId == 0) {
                Toast.makeText(getActivity(), "Please Select Color ", Toast.LENGTH_SHORT).show();
                return;
            } else if (selecttimeId == 0) {
                Toast.makeText(getActivity(), "Please Select Time Slot ", Toast.LENGTH_SHORT).show();
                return;
            } else if (selectproductId == 0) {
                Toast.makeText(getActivity(), "Please Select Product/Service ", Toast.LENGTH_SHORT).show();
                return;
            } else if (customerId == 0) {
                Toast.makeText(getActivity(), "Please Login First!", Toast.LENGTH_SHORT).show();
                return;
            }
            final ASTProgressBar signupDialog = new ASTProgressBar(getActivity());
            signupDialog.show();
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
            ServiceCaller serviceCaller = new ServiceCaller(getActivity());
            serviceCaller.CallCommanServiceMethod(serviceURL, object, "CarService", new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete && result != null) {
                        try {
                            JSONObject resultObj = new JSONObject(result);
                            int status = resultObj.optInt("status");
                            if (status == 1) {
                                Toast.makeText(getActivity(), "Vehicle Details Added Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Vehicle Details not submit!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                        }

                    } else {
                        Utility.alertForErrorMessage(Contants.Error, getActivity());
                    }
                    if (signupDialog.isShowing()) {
                        signupDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, getActivity());//off line msg....
        }
    }

    private void GetAllCompany() {
        if (Utility.isOnline(getActivity())) {

            String serviceURL = Contants.BASE_URL + Contants.GetVehicleCompany;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(getActivity());
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
                        if (dotDialog.isShowing()) {
                            dotDialog.dismiss();
                        }
                        Utility.alertForErrorMessage(Contants.Error, getActivity());
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, getActivity());//off line msg....
        }
    }

    private void GetAllColor() {
        if (Utility.isOnline(getActivity())) {
            String serviceURL = Contants.BASE_URL + Contants.GetVehicleColor;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(getActivity());
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
                        if (dotDialog.isShowing()) {
                            dotDialog.dismiss();
                        }
                        Utility.alertForErrorMessage(Contants.Error, getActivity());
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, getActivity());//off line msg....
        }
    }

    private void GetproductList() {
        if (Utility.isOnline(getActivity())) {
            String serviceURL = Contants.BASE_URL + Contants.GetProduct;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(getActivity());
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
                        if (dotDialog.isShowing()) {
                            dotDialog.dismiss();
                        }
                        Utility.alertForErrorMessage(Contants.Error, getActivity());
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, getActivity());//off line msg....
        }
    }

    private void GettimeSlotList() {
        if (Utility.isOnline(getActivity())) {
            String serviceURL = Contants.BASE_URL + Contants.GetTimeSlot;
            JSONObject object = new JSONObject();

            ServiceCaller serviceCaller = new ServiceCaller(getActivity());
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
                        Utility.alertForErrorMessage(Contants.Error, getActivity());
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, getActivity());//off line msg....
        }
    }
}
