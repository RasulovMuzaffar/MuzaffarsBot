/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.ConnDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ModelReys;
import model.ModelVokzal;

/**
 *
 * @author Muzaffar
 */
public class ModelDAO implements ModelInterface {

    Connection conn = null;
    PreparedStatement ps = null;
    Statement st = null;

    String sqlV = "insert into vokzal(vokzal, ssylka) values(?, ?);";

    public void insertToVokzal(String vokzal, String ssylka) throws ClassNotFoundException, SQLException {

        ConnDB con = new ConnDB();
        try {
            conn = con.getConn();
            ps = conn.prepareStatement(sqlV);
            ps.setString(1, vokzal);
            ps.setString(2, ssylka);

            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ClassNotFoundException ex ---->>> " + ex);
        } catch (SQLException ex) {
            System.out.println("SQLException ex ---->>> " + ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ModelDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.closeConn(conn);
        }
    }

    public void insertToReys() {

    }

    public void selectFromVokzal() {

    }

    public void selectFromReys() {

    }

    public void updateFromVokzal() {

    }

    public void updateFromReys() {

    }
}
