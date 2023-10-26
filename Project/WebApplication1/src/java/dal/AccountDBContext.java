/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;

/**
 *
 * @author Nguyen Quoc Trumg
 */
public class AccountDBContext extends DBContext {

    public Account check(String u, String p) {
        try {
            String sql = "SELECT * FROM account\n"
                    + " JOIN system_setting ON account.role = setting_id "
                    + "WHERE (email = ? OR mobile = ?) AND password = ? AND status = 1;";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, u);
            st.setString(2, u); // Sử dụng giá trị u cho cả email và SĐT
            st.setString(3, p);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString("username"), rs.getString("password"), rs.getString("setting_name"), rs.getInt("status"), rs.getString("full_name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("email"), rs.getString("mobile"));
                a.setID(rs.getInt("account_id"));
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
