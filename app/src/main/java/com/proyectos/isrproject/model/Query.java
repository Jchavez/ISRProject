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
    private String date;

    public Query() {

    }

    public Query(String nit, String name, int amount, int isrDetained, int ivaDetained, int total, String date) {
        this.nit = nit;
        this.name = name;
        this.amount = amount;
        this.isrDetained = isrDetained;
        this.ivaDetained = ivaDetained;
        this.total = total;
        this.date = date;
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
        result.put("date", date);

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
            ", date=" + date +
            '}';
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIsrDetained() {
        return isrDetained;
    }

    public void setIsrDetained(int isrDetained) {
        this.isrDetained = isrDetained;
    }

    public int getIvaDetained() {
        return ivaDetained;
    }

    public void setIvaDetained(int ivaDetained) {
        this.ivaDetained = ivaDetained;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
