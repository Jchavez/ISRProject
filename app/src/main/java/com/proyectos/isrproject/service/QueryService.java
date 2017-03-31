package com.proyectos.isrproject.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectos.isrproject.model.Query;

import java.util.HashMap;
import java.util.Map;

public class QueryService {

    DatabaseReference database;

    public void initDatabase() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewQuery(String uid, String nit, String name, int amount, int isrCalculated, int ivaCalculated, int totalCalculated) {
        String key = database.child("queries").push().getKey();
        Query query = new Query(nit, name, amount, isrCalculated, ivaCalculated, totalCalculated);
        Map<String, Object> queryValues = query.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/queries/" + key, queryValues);
        childUpdates.put("/user-queries/" + uid + "/" + key, queryValues);

        database.updateChildren(childUpdates);
    }
}
