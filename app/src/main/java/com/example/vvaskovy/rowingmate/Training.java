package com.example.vvaskovy.rowingmate;

import java.util.ArrayList;

/**
 * Created by VVASKOVY on 29.10.2017.
 */

public class Training {

    private int idTreningu;
    private String dataTreningu;
    private String sposobTreningu;
    private ArrayList<Interwal> interwals;

    public int getIdTreningu() {
        return idTreningu;
    }

    public void setIdTreningu(int idTreningu) {
        this.idTreningu = idTreningu;
    }

    public String getDataTreningu() {
        return dataTreningu;
    }

    public void setDataTreningu(String dataTreningu) {
        this.dataTreningu = dataTreningu;
    }

    public String getSposobTreningu() {
        return sposobTreningu;
    }

    public void setSposobTreningu(String sposobTreningu) {
        this.sposobTreningu = sposobTreningu;
    }

    public void addInterwal(Interwal interwal){
        interwals.add(interwal);
    }

    public ArrayList<Interwal> getInterwals() {
        return interwals;
    }

    public Training(int idTreningu, String dataTreningu, String sposobTreningu) {
        this.idTreningu = idTreningu;
        this.dataTreningu = dataTreningu;
        this.sposobTreningu = sposobTreningu;
        interwals = new ArrayList<Interwal>();
    }
}
