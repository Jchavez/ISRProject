package com.proyectos.isrproject.service;

import com.google.firebase.database.FirebaseDatabase;

public class QueryService {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public void initDatabase() {
        database = FirebaseDatabase.getInstance();
    }

    public void writeNewQuery(String nit, String name, int amount, int isrCalculated, int ivaCalculated, int totalCalculated) {

    }
}
