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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accountinclass;

/**
 *
 * @author quany
 */
public class AccountinclassDAO extends DBContext {

    public ArrayList<Accountinclass> getAll(int id, String code) {
        try {
            String sql = "SELECT * FROM student_class WHERE class_id = ? AND subject_code = ?";
            ArrayList<Accountinclass> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, code);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Accountinclass accountinclass = new Accountinclass();
                    accountinclass.setUsername(rs.getString("username"));
                    accountinclass.setClassid(rs.getInt("class_id"));
                    accountinclass.setSemester(rs.getString("semester"));
                    accountinclass.setSubjectcode(rs.getString("subject_code"));
                    accountinclass.setStartdate(rs.getDate("start_date"));
                    accountinclass.setEnddate(rs.getDate("end_date"));
                    accountinclass.setStarttime(rs.getTime("start_time"));
                    accountinclass.setEndtime(rs.getTime("end_time"));
                    list.add(accountinclass);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Accountinclass> getListByPage(int start, int end, ArrayList<Accountinclass> list) {
        ArrayList<Accountinclass> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public ArrayList<Accountinclass> getByPageSize(int page, int size) {
        try {
                page -= 1;                      
            String sql = "SELECT *  FROM swp_prj.student_class WHERE class_id = ? limit ? offset ? ";
            ArrayList<Accountinclass> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ps.setInt(1, 3);
                ps.setInt(2, size);
                ps.setInt(3, page * size);
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Accountinclass accountinclass = new Accountinclass();
                    accountinclass.setUsername(rs.getString("username"));
                    accountinclass.setClassid(rs.getInt("class_id"));
                    accountinclass.setSemester(rs.getString("semester"));
                    accountinclass.setSubjectcode(rs.getString("subject_code"));
                    accountinclass.setStartdate(rs.getDate("start_date"));
                    accountinclass.setEnddate(rs.getDate("end_date"));
                    accountinclass.setStarttime(rs.getTime("start_time"));
                    accountinclass.setEndtime(rs.getTime("end_time"));
                    list.add(accountinclass);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public void delete(String username) {
        try {
            String sql = "DELETE FROM student_class WHERE username = ?";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Accountinclass> get(String username) {
        try {
            String sql = "SELECT * FROM swp_prj.student_class WHERE swp_prj.student_class.username = ?";
            ArrayList<Accountinclass> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                System.out.println("username is : " + username);
                System.out.println("0");

                ps.setString(1, username);
                System.out.println("0.1");
                ResultSet rs = ps.executeQuery();
                System.out.println("1");

                list = new ArrayList<>();
                System.out.println("2");
                while (rs.next()) {
                    Accountinclass accountinclass = new Accountinclass();
                    accountinclass.setUsername(rs.getString("username"));
                    accountinclass.setClassid(rs.getInt("class_id"));
                    accountinclass.setSemester(rs.getString("semester"));
                    accountinclass.setSubjectcode(rs.getString("subject_code"));
                    accountinclass.setStartdate(rs.getDate("start_date"));
                    accountinclass.setEnddate(rs.getDate("end_date"));
                    accountinclass.setStarttime(rs.getTime("start_time"));
                    accountinclass.setEndtime(rs.getTime("end_time"));
                    list.add(accountinclass);
                }
                rs.close();
            }
            System.out.println("List has length : " + list.size());
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public void update(Accountinclass accountinclass) {
//        try {
//            String sql = "UPDATE student_class SET class_id = ? WHERE username = ?";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, accountinclass.getClassid());
//            ps.setString(2, accountinclass.getUsername());
//            ps.execute();
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
