package com.proyectos.isrproject.model;

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
