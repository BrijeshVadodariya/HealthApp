package com.example.healthapp;

public class hospital {
    String h_name;
    String h_pgflag;
    public hospital(){

    }

    public hospital(String h_name, String h_pgflag) {
        this.h_name = h_name;
        this.h_pgflag = h_pgflag;
    }

    public String getH_pgflag() {
        return h_pgflag;
    }

    public void setH_pgflag(String h_pgflag) {
        this.h_pgflag = h_pgflag;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }
}
