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
import com.proyectos.isrproject.service.QueryService;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    TextView userEmail;
    String uid;

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
            String emailLogged = user.getEmail();
            uid = user.getUid();
            userEmail.setText(emailLogged);
        }
    }

    @OnClick(R.id.calculate_save_button)
    public void loginButton() {
        String currency = "Q.";

        int amount = Integer.parseInt(isrAmount.getText().toString());
        int isrCalculated = calculateISR(amount);
        int ivaCalculated = calculateIVA(amount);
        int totalCalculated = calculateTOTAL(amount);

        // Saving Data
        saveData(amount, isrCalculated, ivaCalculated, totalCalculated);
        //

        String isrCalculatedText = currency + String.valueOf(isrCalculated);
        String ivaCalculatedText = currency + String.valueOf(ivaCalculated);
        String totalCalculatedText = currency + String.valueOf(totalCalculated);

        isrDetained.setText(isrCalculatedText);
        ivaDetained.setText(ivaCalculatedText);
        isrTotal.setText(totalCalculatedText);
    }

    private int calculateISR(int amount) {
        int amountCalculate = amount * 8;
        amountCalculate += 5;
        return amountCalculate;
    }

    private int calculateIVA(int amount) {
        int amountCalculate = amount * 10;
        amountCalculate += 8;
        return amountCalculate;
    }

    private int calculateTOTAL(int amount) {
        int amountCalculate = amount * 1000;
        amountCalculate += 65;
        return amountCalculate;
    }

    private void saveData(int amount, int isrCalculated, int ivaCalculated, int totalCalculated) {
        String nit = isrNit.getText().toString();
        String name = isrName.getText().toString();

        QueryService queryService = new QueryService();
        queryService.initDatabase();
        queryService.writeNewQuery(uid, nit, name, amount, isrCalculated, ivaCalculated, totalCalculated);
    }
}
