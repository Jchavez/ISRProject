package com.proyectos.isrproject.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Query {

    private String nit;
    private String name;
    private int amount;
    private int isrDetained;
    private int ivaDetained;
    private int total;

    public Query() {

    }

    public Query(String nit, String name, int amount, int isrDetained, int ivaDetained, int total) {
        this.nit = nit;
        this.name = name;
        this.amount = amount;
        this.isrDetained = isrDetained;
        this.ivaDetained = ivaDetained;
        this.total = total;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nit", nit);
        result.put("name", name);
        result.put("amount", amount);
        result.put("isrDetained", isrDetained);
        result.put("ivaDetained", ivaDetained);
        result.put("total", total);

        return result;
    }

    @Override
    public String toString() {
        return "Query{" +
            "nit='" + nit + '\'' +
            ", name='" + name + '\'' +
            ", amount=" + amount +
            ", isrDetained=" + isrDetained +
            ", ivaDetained=" + ivaDetained +
            ", total=" + total +
            '}';
    }
}
