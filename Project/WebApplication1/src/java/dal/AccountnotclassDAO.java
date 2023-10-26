/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accountnotclass;
import model.Class;

/**
 *
 * @author quany
 */
public class AccountnotclassDAO extends DBContext {

    public ArrayList<Accountnotclass> getAl(int classSJ, String subjectID) {
        try {
            String sql = "SELECT * FROM account WHERE username NOT IN (\n"
                    + "SELECT DISTINCT username FROM student_class WHERE class_id = ? AND subject_code = ?\n"
                    + ") AND role = 'User'";
            ArrayList<Accountnotclass> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setInt(1, classSJ);
                ps.setString(2, subjectID);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Accountnotclass accountnotclass = new Accountnotclass();
                    accountnotclass.setUsername(rs.getString("username"));
                    accountnotclass.setFullname(rs.getString("full_name"));
                    accountnotclass.setEmail(rs.getString("email"));
                    list.add(accountnotclass);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Class> getClassBySubjectCode(String subjectCode) {
        try {
            SystemSettingDAL ssd = new SystemSettingDAL();

            SubjectDAL sd = new SubjectDAL();
            String sql = "SELECT * FROM swp_prj.class where subject_code = ?;";
            ArrayList<Class> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setString(1, subjectCode);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Class myClass = new Class();
                    myClass.setClassID(rs.getInt("class_id"));
                    myClass.setClassName(rs.getString("class_name"));
                    myClass.setMajorID(rs.getString("major_id"));
                    myClass.setS(sd.getSubjectByCode(rs.getString("subject_code")));
                    myClass.setSemester(ssd.getSettingByID(rs.getInt("semester")));
                    myClass.setActivate(rs.getInt("status"));
                    myClass.setStartDate(rs.getDate("start_date"));
                    myClass.setEndDate(rs.getDate("end_date"));
                    myClass.setStartTime(rs.getTime("start_time"));
                    myClass.setEndTime(rs.getTime("end_time"));
                    list.add(myClass);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AccountnotclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void add(String username, int classid, String semester, String subjectcode, Date startdate, Date enddate, Time starttime, Time endtime) {
        try {
            String sql = "INSERT INTO student_class (username, class_id, semester, subject_code, start_date, end_date, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, classid);
            ps.setString(3, semester);
            ps.setString(4, subjectcode);
            ps.setDate(5, startdate);
            ps.setDate(6, enddate);
            ps.setTime(7, starttime);
            ps.setTime(8, endtime);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add(String username, int classid, int semester, String subjectcode, LocalDate startdate, LocalDate enddate, LocalTime starttime, LocalTime endtime) {
        try {
            String sql = "INSERT INTO student_class (username, class_id, semester, subject_code, start_date, end_date, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, classid);
            ps.setInt(3, semester);
            ps.setString(4, subjectcode);
            ps.setDate(5, Date.valueOf(startdate));
            ps.setDate(6, Date.valueOf(enddate));
            ps.setTime(7, Time.valueOf(starttime));
            ps.setTime(8, Time.valueOf(endtime));
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
