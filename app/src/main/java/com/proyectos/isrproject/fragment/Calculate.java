package com.proyectos.isrproject.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectos.isrproject.R;
import com.proyectos.isrproject.service.QueryService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Calculate extends Fragment {

    String uid;

    @BindView(R.id.isr_nit) EditText isrNit;
    @BindView(R.id.isr_name) EditText isrName;
    @BindView(R.id.isr_amount) EditText isrAmount;

    @BindView(R.id.isr_detained_calculated) TextView isrDetained;
    @BindView(R.id.iva_detained_calculated) TextView ivaDetained;
    @BindView(R.id.isr_total_calculate) TextView isrTotal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);

        ButterKnife.bind(this, view);

        loadUserProfile();
        return view;

    }

    private void loadUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            uid = user.getUid();
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
