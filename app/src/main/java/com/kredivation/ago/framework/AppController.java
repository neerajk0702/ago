package com.kredivation.ago.framework;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by Neeraj on 7/25/2017.
 */
public class AppController extends MultiDexApplication {
    public static final String TAG = AppController.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppController mInstance;

    private Typeface _sTypeface;
    public volatile MenuItem lastMenuItem;
    private SharedPreferences sharedPref;
    private String packageName;
    private Typeface _fontRegular;
    private Typeface _fontBold;
    private Typeface _fontItalic;
    private Typeface _fontBoldItalic;
    private Typeface _fontSemiBold;
    private Typeface _fontSemiBoldItalic;
    private Typeface _fontLightItalic;
    private Typeface _fontExtraLightItalic;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public String packageName() {
        if (this.packageName == null) {
            PackageInfo info = this.packageInfo();
            this.packageName = info != null ? info.packageName : "com.kredivation.aakhale";
        }
        return this.packageName;
    }

    public PackageInfo packageInfo() {
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
           // FNExceptionUtil.logException(e2, getApplicationContext());
        }
        return info;
    }

}
