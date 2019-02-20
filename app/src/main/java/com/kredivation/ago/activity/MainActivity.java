package com.kredivation.ago.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kredivation.ago.R;
import com.kredivation.ago.fragment.AddVichileDetailFragment;
import com.kredivation.ago.fragment.HomeFragment;
import com.kredivation.ago.fragment.SupportFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    LinearLayout homelayout, addVichilelayout, profilelayout, orderlayout, registrationlayout, currentWorklayout, recordlayout, Offerlayout, companylayout, Supportlayout, loginlayout;
    DrawerLayout drawer;
    TextView phoneno, name, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        homelayout = findViewById(R.id.homelayout);
        homelayout.setOnClickListener(this);
        addVichilelayout = findViewById(R.id.addVichilelayout);
        addVichilelayout.setOnClickListener(this);
        profilelayout = findViewById(R.id.profilelayout);
        profilelayout.setOnClickListener(this);
        orderlayout = findViewById(R.id.orderlayout);
        orderlayout.setOnClickListener(this);
        registrationlayout = findViewById(R.id.registrationlayout);
        registrationlayout.setOnClickListener(this);
        currentWorklayout = findViewById(R.id.currentWorklayout);
        currentWorklayout.setOnClickListener(this);
        recordlayout = findViewById(R.id.recordlayout);
        recordlayout.setOnClickListener(this);
        Offerlayout = findViewById(R.id.Offerlayout);
        Offerlayout.setOnClickListener(this);
        companylayout = findViewById(R.id.companylayout);
        companylayout.setOnClickListener(this);
        Supportlayout = findViewById(R.id.Supportlayout);
        Supportlayout.setOnClickListener(this);
        loginlayout = findViewById(R.id.loginlayout);
        loginlayout.setOnClickListener(this);
        phoneno = findViewById(R.id.phoneno);
        name = findViewById(R.id.name);
        login = findViewById(R.id.login);
        getProfileInfo();
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.mainView, homeFragment);
        fragmentTransaction.commit();

    }

    private long customerId;
    private String role, mobileNumber, nameStr;

    private void getProfileInfo() {
        SharedPreferences UserInfo = getSharedPreferences("UserInfoSharedPref", MODE_PRIVATE);
        customerId = UserInfo.getLong("customerId", 0);
        role = UserInfo.getString("role", "");
        mobileNumber = UserInfo.getString("mobileNumber", "");
        nameStr = UserInfo.getString("name", "");
        if (role != null && !role.equals("")) {
            login.setText("Logout");
            phoneno.setText(mobileNumber + "");
            name.setText(nameStr + "");
            /*if (role.equals("Admin")) {
                addVichilelayout.setVisibility(View.VISIBLE);
                profilelayout.setVisibility(View.VISIBLE);
                orderlayout.setVisibility(View.VISIBLE);

                currentWorklayout.setVisibility(View.VISIBLE);
                recordlayout.setVisibility(View.VISIBLE);
            } else*/
            if (role.equals("Customer")) {
                addVichilelayout.setVisibility(View.VISIBLE);
                profilelayout.setVisibility(View.VISIBLE);
                orderlayout.setVisibility(View.VISIBLE);
                currentWorklayout.setVisibility(View.GONE);
                recordlayout.setVisibility(View.GONE);
                registrationlayout.setVisibility(View.GONE);
            } else if (role.equals("Employee")) {
                addVichilelayout.setVisibility(View.GONE);
                profilelayout.setVisibility(View.VISIBLE);
                orderlayout.setVisibility(View.GONE);
                currentWorklayout.setVisibility(View.VISIBLE);
                recordlayout.setVisibility(View.VISIBLE);
                registrationlayout.setVisibility(View.GONE);
            } else if (role.equals("Supervisor")) {
                addVichilelayout.setVisibility(View.GONE);
                profilelayout.setVisibility(View.VISIBLE);
                orderlayout.setVisibility(View.GONE);
                currentWorklayout.setVisibility(View.GONE);
                recordlayout.setVisibility(View.GONE);
                registrationlayout.setVisibility(View.VISIBLE);
            }
        } else {
            login.setText("Login");
            addVichilelayout.setVisibility(View.GONE);
            profilelayout.setVisibility(View.GONE);
            orderlayout.setVisibility(View.GONE);
            currentWorklayout.setVisibility(View.GONE);
            recordlayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProfileInfo();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_service) {

        } else if (id == R.id.nav_support) {

        } else if (id == R.id.nav_login) {

        } else if (id == R.id.nav_addvichile) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateFragment(Fragment pageFragment, Bundle bundle) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
        pageFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.mainView, pageFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homelayout:
                HomeFragment addTeamFragment = new HomeFragment();
                updateFragment(addTeamFragment, null);
                closeDrawer();
                break;
            case R.id.addVichilelayout:
                AddVichileDetailFragment addfrg = new AddVichileDetailFragment();
                updateFragment(addfrg, null);
                closeDrawer();
                break;
            case R.id.profilelayout:
                closeDrawer();
                break;
            case R.id.orderlayout:
                closeDrawer();
                break;
            case R.id.registrationlayout:
                Intent regintent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(regintent);
                closeDrawer();
                break;
            case R.id.currentWorklayout:
                closeDrawer();
                break;
            case R.id.recordlayout:
                closeDrawer();
                break;
            case R.id.Offerlayout:
                closeDrawer();
                break;
            case R.id.companylayout:
                closeDrawer();
                break;
            case R.id.Supportlayout:
                SupportFragment supportfrg = new SupportFragment();
                updateFragment(supportfrg, null);
                closeDrawer();
                break;
            case R.id.loginlayout:
               /* if (role != null && !role.equals("")) {

                }else{

                }*/
                SharedPreferences UserInfo = getSharedPreferences("UserInfoSharedPref", MODE_PRIVATE);
                UserInfo.edit().clear().commit();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                closeDrawer();
                break;

        }
    }

    //close drawer after item select
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
        //return true;
    }
}
