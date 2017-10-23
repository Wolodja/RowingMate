package com.example.vvaskovy.rowingmate;

import java.util.Date;

/**
 * Created by VVASKOVY on 23.10.2017.
 */

public class Interwal {

    int idInterwalu;
    Date czasInterwalu;
    String mocInterwalu;
    String tempoInterwalu;
    String dystansInterwalu;

    public Interwal(int idInterwalu, Date czasInterwalu, String mocInterwalu, String tempoInterwalu, String dystansInterwalu){

        this.idInterwalu=idInterwalu;
        this.czasInterwalu=czasInterwalu;
        this.mocInterwalu=mocInterwalu;
        this.tempoInterwalu=tempoInterwalu;
        this.dystansInterwalu=dystansInterwalu;
    }

    public int getIdInterwalu() {
        return idInterwalu;
    }

    public void setIdInterwalu(int idInterwalu) {
        this.idInterwalu = idInterwalu;
    }

    public Date getCzasInterwalu() {
        return czasInterwalu;
    }

    public void setCzasInterwalu(Date czasInterwalu) {
        this.czasInterwalu = czasInterwalu;
    }

    public String getMocInterwalu() {
        return mocInterwalu;
    }

    public void setMocInterwalu(String mocInterwalu) {
        this.mocInterwalu = mocInterwalu;
    }

    public String getTempoInterwalu() {
        return tempoInterwalu;
    }

    public void setTempoInterwalu(String tempoInterwalu) {
        this.tempoInterwalu = tempoInterwalu;
    }

    public String getDystansInterwalu() {
        return dystansInterwalu;
    }

    public void setDystansInterwalu(String dystansInterwalu) {
        this.dystansInterwalu = dystansInterwalu;
    }
}
