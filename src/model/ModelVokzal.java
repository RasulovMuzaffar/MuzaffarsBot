/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Muzaffar
 */
public class ModelVokzal {

    private int id;
    private String vokzal;
    private Timestamp curDate;
    private String ssylka;

    public ModelVokzal() {
    }

    public ModelVokzal(int id, String vokzal, Timestamp curDate, String ssylka) {
        this.id = id;
        this.vokzal = vokzal;
        this.curDate = curDate;
        this.ssylka = ssylka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVokzal() {
        return vokzal;
    }

    public void setVokzal(String vokzal) {
        this.vokzal = vokzal;
    }

    public Timestamp getCurDate() {
        return curDate;
    }

    public void setCurDate(Timestamp curDate) {
        this.curDate = curDate;
    }

    public String getSsylka() {
        return ssylka;
    }

    public void setSsylka(String ssylka) {
        this.ssylka = ssylka;
    }

    @Override
    public String toString() {
        return "ModelVokzal{" + "id=" + id + ", vokzal=" + vokzal + ", curDate=" + curDate + ", ssylka=" + ssylka + '}';
    }
}
