package com.proyectos.isrproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    TextView userEmail;

    @BindView(R.id.isr_nit) EditText isrNit;
    @BindView(R.id.isr_name) EditText isrName;
    @BindView(R.id.isr_amount) EditText isrAmount;

    @BindView(R.id.isr_detained_calculated) TextView isrDetained;
    @BindView(R.id.iva_detained_calculated) TextView ivaDetained;
    @BindView(R.id.isr_total_calculate) TextView isrTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        userEmail = (TextView) header.findViewById(R.id.user_email);
        navigationView.setNavigationItemSelectedListener(this);

        loadUserProfile();
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
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            signOut();
            startMainActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calculate) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private void startMainActivity() {
        Intent mainActivity = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    private void loadUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String email = user.getEmail();
            userEmail.setText(email);
        }
    }

    @OnClick(R.id.calculate_save_button)
    public void loginButton() {
        String currency = "Q.";

        int amount = Integer.parseInt(isrAmount.getText().toString());

        String isrCalculated = currency + calculateISR(amount);
        String ivaCalculated = currency + calculateIVA(amount);
        String totalCalculated = currency + calculateTOTAL(amount);

        isrDetained.setText(isrCalculated);
        ivaDetained.setText(ivaCalculated);
        isrTotal.setText(totalCalculated);
    }

    private String calculateISR(int amount) {
        int amountCalculate = amount * 8;
        return String.valueOf(amountCalculate);
    }

    private String calculateIVA(int amount) {
        int amountCalculate = amount * 10;
        return String.valueOf(amountCalculate);
    }

    private String calculateTOTAL(int amount) {
        int amountCalculate = amount * 1000;
        return String.valueOf(amountCalculate);
    }
}
