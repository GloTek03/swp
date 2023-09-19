/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class DAO {
    public static List<Account> getAllAcounts(){
        List<Account> accounts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            conn = DBContext.getConnection();
            String sql = "select full_name, enrolment_date, role, status from account";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString(1),rs.getString(2),rs.getString(3),rs.getBoolean(4));
                accounts.add(a);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }
}
