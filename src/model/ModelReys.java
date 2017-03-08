/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Muzaffar
 */
public class ModelReys {

    private String nReys;
    private String stFrom;
    private String stTo;
    private String poezd;
    private String mestV;
    private String dniK;

    public ModelReys() {
    }

    public ModelReys(String nReys, String stFrom, String stTo, String poezd, String mestV, String dniK) {
        this.nReys = nReys;
        this.stFrom = stFrom;
        this.stTo = stTo;
        this.poezd = poezd;
        this.mestV = mestV;
        this.dniK = dniK;
    }

    public String getnReys() {
        return nReys;
    }

    public void setnReys(String nReys) {
        this.nReys = nReys;
    }

    public String getStFrom() {
        return stFrom;
    }

    public void setStFrom(String stFrom) {
        this.stFrom = stFrom;
    }

    public String getStTo() {
        return stTo;
    }

    public void setStTo(String stTo) {
        this.stTo = stTo;
    }

    public String getPoezd() {
        return poezd;
    }

    public void setPoezd(String poezd) {
        this.poezd = poezd;
    }

    public String getMestV() {
        return mestV;
    }

    public void setMestV(String mestV) {
        this.mestV = mestV;
    }

    public String getDniK() {
        return dniK;
    }

    public void setDniK(String dniK) {
        this.dniK = dniK;
    }

    @Override
    public String toString() {
        return "ModelReys{" + "nReys=" + nReys + ", stFrom=" + stFrom + ", stTo=" + stTo + ", poezd=" + poezd + ", mestV=" + mestV + ", dniK=" + dniK + '}';
    }

}
