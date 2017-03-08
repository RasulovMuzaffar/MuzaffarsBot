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

    public ModelVokzal() {
    }

    public ModelVokzal(int id, String vokzal, Timestamp curDate) {
        this.id = id;
        this.vokzal = vokzal;
        this.curDate = curDate;
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

    @Override
    public String toString() {
        return "ModelVokzal{" + "id=" + id + ", vokzal=" + vokzal + ", curDate=" + curDate + '}';
    }

}
