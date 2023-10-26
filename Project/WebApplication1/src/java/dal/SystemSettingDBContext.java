/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SystemSetting;

/**
 *
 * @author Nguyen Quoc Trumg
 */
public class SystemSettingDBContext extends DBContext {

    public List<SystemSetting> getAllSysSetting() {
        List<SystemSetting> ls = new ArrayList<>();
        try {
            String sql = "SELECT * from system_setting";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SystemSetting ss = new SystemSetting(rs.getString("setting_name"), rs.getString("setting_type"), rs.getBoolean("setting_status"));
                ls.add(ss);
            }
        } catch (Exception e) {

        }
        return ls;
    }

    public void insertSysSetting(SystemSetting ss) {
        try {
            String sql = "INSERT INTO system_setting\n"
                    + "VALUES\n"
                    + "(?,\n"
                    + "?,"
                    + "?);";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, ss.getSetting_name());
            st.setString(2, ss.getSetting_type());
            st.setBoolean(3, ss.isSetting_status());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateSysSetting(String name, String type, boolean status, String choose) {
        try {
            String sql = "UPDATE system_setting \n"
                    + "                    SET setting_name = ?, setting_type = ?, setting_status = ?\n"
                    + "                    WHERE setting_name = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, type);
            st.setBoolean(3, status);
            st.setString(4, choose);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public SystemSetting getSysSetByName(String s) {
        try {
            String sql = "Select * from system_setting \n"
                    + "WHERE setting_name = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, s);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                SystemSetting ss = new SystemSetting();
                ss.setSetting_name(rs.getString("setting_name"));
                ss.setSetting_type(rs.getString("setting_type"));
                ss.setSetting_status(rs.getBoolean("setting_status"));
                return ss;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        SystemSettingDBContext sd = new SystemSettingDBContext();
        sd.updateSysSetting("Admin", "UserRole", true, "Admin");
    }
}
