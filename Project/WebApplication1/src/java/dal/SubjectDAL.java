/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ClassSubject;
import model.Subject;
import model.SubjectProgress;

/**
 *
 * @author acer
 */
public class SubjectDAL extends DBContext {
    
    public Subject getSubjectByID(int id) {
        try {
            AccountDAL ad = new AccountDAL();
            String sql = "SELECT * FROM subject WHERE subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setStatus(rs.getInt("status"));
                s.setSubjectID(rs.getInt("subject_id"));
                s.setSubjectCode(rs.getString("subject_code"));
                s.setSubjectName(rs.getString("subject_name"));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public int GetSubjectID(Subject s) {
        try {
            AccountDAL ad = new AccountDAL();
            String sql = "SELECT subject_id FROM subject WHERE subject_name = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, s.getSubjectName());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("subject_id");
                return id;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public Subject getSubjectByCode(String id) {
        try {
            AccountDAL ad = new AccountDAL();
            String sql = "SELECT * FROM subject WHERE subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"),ad.getAccountByAccID(rs.getInt("manager")));
                s.setSubjectCode(rs.getString("subject_code"));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Subject> getAllSubject() {
        List<Subject> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"),ad.getAccountByAccID(rs.getInt("manager")));
                
                s.setSubjectCode(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Subject> getAllSubjectByMng(String mng) {
        List<Subject> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        try {
            String sql;
            PreparedStatement st;
            ResultSet rs;
            if (mng.equals("0")) {
                sql = "SELECT * FROM subject";
                st = DBContext().prepareStatement(sql);
                rs = st.executeQuery();
            } else {
                sql = "SELECT * FROM subject WHERE manager = ?";
                st = DBContext().prepareStatement(sql);
                st.setString(1, mng);
                rs = st.executeQuery();
            }
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"),ad.getAccountByAccID(rs.getInt("manager")));
                s.setSubjectCode(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Subject> getSubjectByPage(int start, int end, List<Subject> list) {
        List<Subject> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }
    
    public List<Subject> getSubjectBySearchAMng(String search, String mng) {
        List<Subject> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        try {
            PreparedStatement st;
            ResultSet rs;
            if (mng.equals("0")) {
                String sql = "SELECT * FROM subject WHERE (subject_name LIKE '%" + search + "%' OR subject_code LIKE '%" + search + "%')";
                st = DBContext().prepareStatement(sql);
                rs = st.executeQuery();
            } else {
                String sql = "SELECT * FROM subject WHERE (subject_name LIKE '%" + search + "%' OR subject_code LIKE '%" + search + "%') AND manager = ?";
                st = DBContext().prepareStatement(sql);
                st.setString(1, mng);
                rs = st.executeQuery();
            }
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"),ad.getAccountByAccID(rs.getInt("manager")));
                s.setSubjectCode(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Subject> getSubjectBySearch(String search) {
        List<Subject> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM subject WHERE subject_name LIKE '%" + search + "%' OR subject_code LIKE '%" + search + "%'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject s = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getString("subject_des"), rs.getInt("status"),ad.getAccountByAccID(rs.getInt("manager")));
                s.setSubjectCode(rs.getString("subject_code"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void insertSubject(String subjectID, String subjectName, String subjectDes,  int manager) {
        try {
            String sql = "INSERT INTO subject (subject_code, subject_name, subject_des, status, manager) VALUES (?, ?, ?, 1, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, subjectID);
            st.setString(2, subjectName);
            st.setString(3, subjectDes);
            st.setInt(4, manager);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updSubjectDetail(int subjectID, String subjectCode, String subjectName, String subjectDescription, int manager) {
        try {
            String sql = "UPDATE subject SET subject_name = ?, subject_des = ?, manager = ?, subject_code = ? WHERE subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, subjectName);
            st.setString(2, subjectDescription);
            st.setInt(3, manager);
            st.setString(4, subjectCode);
            st.setInt(5, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void activateSubject(int subjectID) {
        try {
            String sql = "UPDATE subject SET status = 1 WHERE subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            st.executeUpdate();
            sql = "UPDATE class SET status = 1 WHERE subject_id = ?";
            st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deactivateSubject(int subjectID) {
        try {
            String sql = "UPDATE subject SET status = 0 WHERE subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            st.executeUpdate();
            sql = "UPDATE class SET status = 0 WHERE subject_id = ?";
            st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<model.Class> getAllClassBySubjectID(int subjectID) {
        SystemSettingDAL ssd = new SystemSettingDAL();
        
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "SELECT * FROM class WHERE subject_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
