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
    
    public void insertToVokzal() throws ClassNotFoundException, SQLException {
        
        
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bot", "test", "test");
        System.out.println("conn --->> "+conn.getMetaData().getDatabaseProductName());
        
//        ModelVokzal mr = new ModelVokzal();
//        ConnDB con = new ConnDB();
//        try {
//            conn = con.getConn();
//            ps = conn.prepareStatement(sqlV);
//            ps.setString(1, mr.getVokzal());
//            ps.setString(2, mr.getSsylka());
//            
//            ps.executeQuery();
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(ModelDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                ps.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(ModelDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            con.closeConn(conn);
//        }
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
