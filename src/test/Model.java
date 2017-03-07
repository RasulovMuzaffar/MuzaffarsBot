/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Muzaffar
 */
public class Model {

    private String nReys;
    private String stFromTo;
    private String mestV;
    private String dniK;

    public Model() {
    }

    public Model(String nReys, String stFromTo, String mestV, String dniK) {
        this.nReys = nReys;
        this.stFromTo = stFromTo;
        this.mestV = mestV;
        this.dniK = dniK;
    }

    public String getnReys() {
        return nReys;
    }

    public void setnReys(String nReys) {
        this.nReys = nReys;
    }

    public String getStFromTo() {
        return stFromTo;
    }

    public void setStFromTo(String stFromTo) {
        this.stFromTo = stFromTo;
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
        return "Model{" + "nReys=" + nReys + ", stFromTo=" + stFromTo + ", mestV=" + mestV + ", dniK=" + dniK + '}';
    }

}
