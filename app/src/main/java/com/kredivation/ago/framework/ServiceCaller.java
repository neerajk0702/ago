package com.kredivation.ago.framework;

import android.content.Context;
import android.util.Log;


import com.kredivation.ago.utility.Contants;

import org.json.JSONObject;

/**
 * Created by Neeraj on 7/25/2017.
 */
public class ServiceCaller {
    Context context;

    public ServiceCaller(Context context) {
        this.context = context;
    }

    //call Commen Method ForCall Servier Data
    public void CallCommanServiceMethod(final String loginUrl, final String methodNmae, final IAsyncWorkCompletedCallback workCompletedCallback) {
        new ServiceHelper().callService(loginUrl, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                    Log.d(Contants.LOG_TAG, methodNmae + "********" + result);
                } else {
                    workCompletedCallback.onDone(methodNmae, false);
                }
            }
        });
    }

    //call Commen Method ForCall Servier Data with json Object
    public void CallCommanServiceMethod(final String loginUrl, JSONObject jsonObject, final String methodNmae, final IAsyncWorkCompletedCallback workCompletedCallback) {
        Log.d(Contants.LOG_TAG, methodNmae + "Payload********" + jsonObject.toString());
        new ServiceHelper().callService(loginUrl, jsonObject, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                    Log.d(Contants.LOG_TAG, methodNmae + "********" + result);
                } else {
                    workCompletedCallback.onDone(methodNmae, false);
                }
            }
        });
    }

    //call Commen Method ForCall Servier Data with json Object
    public void CallCommanGetServiceMethod(final String loginUrl, JSONObject jsonObject, final String methodNmae, final IAsyncWorkCompletedCallback workCompletedCallback) {
        Log.d(Contants.LOG_TAG, methodNmae + "Payload********" + jsonObject.toString());
        new ServiceHelper().callGetService(loginUrl, jsonObject, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                    Log.d(Contants.LOG_TAG, methodNmae + "********" + result);
                } else {
                    workCompletedCallback.onDone(methodNmae, false);
                }
            }
        });
    }


    //call Commen Method ForCall Servier Data
    public void CallCommanGetServiceMethod(final String loginUrl, final String methodNmae, final IAsyncWorkCompletedCallback workCompletedCallback) {
        new ServiceHelper().callGetService(loginUrl, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                    Log.d(Contants.LOG_TAG, methodNmae + "********" + result);
                } else {
                    workCompletedCallback.onDone(methodNmae, false);
                }
            }
        });
    }

}
