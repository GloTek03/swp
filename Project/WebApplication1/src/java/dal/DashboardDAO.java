/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Dashboard;
import util.FormatDate;
import util.FormatTime;

/**
 *
 * @author quany
 */
public class DashboardDAO extends DBContext {

    public ArrayList<Dashboard> getAll(Account a) {
        FormatDate fd = new FormatDate();
        FormatTime ft = new FormatTime();
        try {
            String sql = "SELECT class.*, subject.subject_code,setting_name, student_class.start_date, student_class.end_date, student_class.start_time, student_class.end_time, subject.subject_name "
                    + "FROM class "
                    
                    + "JOIN subject ON class.subject_id = subject.subject_id"
                    + " JOIN system_setting ON class.semester = system_setting.setting_id"
                    + " JOIN student_class ON class.class_id = student_class.class_id AND class.subject_id = student_class.subject_id"
                    + " WHERE student_class.student_id = ?" ;
            ArrayList<Dashboard> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setInt(1, a.getID());
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Dashboard dashboard = new Dashboard();
                    dashboard.setSubject(rs.getInt("subject_id"));
                    dashboard.setClassid(rs.getInt("class_id"));
                    dashboard.setClassname(rs.getString("class_name"));
                    dashboard.setSubjectcode(rs.getString("subject_code"));
                    dashboard.setSemester(rs.getString("setting_name"));
                    dashboard.setStatus(rs.getInt("status"));
                    dashboard.setStartdate(rs.getDate("start_date"));
                    dashboard.setEnddate(rs.getDate("end_date"));
                    dashboard.setStarttime(rs.getTime("start_time"));
                    dashboard.setEndtime(rs.getTime("end_time"));
                    dashboard.setSubjectname(rs.getString("subject_name"));
                    dashboard.setStartS(fd.formatDateSQL(rs.getDate("start_date")));
                    dashboard.setEndS(fd.formatDateSQL(rs.getDate("end_date")));
                    dashboard.setStartT(ft.formatTimeSQL(rs.getTime("start_time")));
                    dashboard.setEndT(ft.formatTimeSQL(rs.getTime("end_time")));
                    list.add(dashboard);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
