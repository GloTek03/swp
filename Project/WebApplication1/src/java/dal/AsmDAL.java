/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Assignment;
import model.Chapter;
import model.Subject;

/**
 *
 * @author acer
 */
public class AsmDAL extends DBContext {

    public Assignment getAllAsmByLesson(int lesson) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, lesson);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                a.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Assignment> getAllAsmByChapter(int chapter) {
        List<Assignment> list = new ArrayList<>();
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM assignment WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapter);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                a.setAsmDes(rs.getString("asm_des"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setCreatedAt(rs.getTimestamp("created_at"));
                a.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                a.setUpdatedAt(rs.getTimestamp("updated_at"));
                a.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                a.setDl(rs.getTimestamp("deadline"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Assignment> pagingAssignment(int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "select Row_number() over (order by asm_id asc) as no, a.asm_id,a.asm_name,a.asm_des,sj.subject_code,ct.chapter_name,a.deadline \n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                Chapter c = new Chapter();
                s.setSubjectCode(rs.getString("subject_code"));
                c.setChapterName(rs.getString("chapter_name"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setAsmDes(rs.getString("asm_des"));
                a.setSubject(s);
                a.setChapter(c);
                a.setDl(rs.getTimestamp("deadline"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAssignment() {
        int count = 0;
        String query = "select count(*)\n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public int getTotalAssignmentBySubject(String subject) {
        String query = "select count(*)\n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where sj.subject_code like ?";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Assignment> paggingAccountBySubject(String subject, int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "select Row_number() over (order by asm_id asc) as no, a.asm_id,a.asm_name,a.asm_des,sj.subject_code,ct.chapter_name \n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where sj.subject_code like ?\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                Chapter c = new Chapter();
                s.setSubjectCode(rs.getString("subject_code"));
                c.setChapterName(rs.getString("chapter_name"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setAsmDes(rs.getString("asm_des"));
                a.setSubject(s);
                a.setChapter(c);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Assignment> paggingAccountByName(String subject, int index) {
        List<Assignment> list = new ArrayList<>();
        String query = "select Row_number() over (order by asm_id asc) as no, a.asm_id,a.asm_name,a.asm_des,sj.subject_code,ct.chapter_name \n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where a.asm_name like ?\n"
                + "order by no asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            stm.setInt(2, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Assignment a = new Assignment();
                Subject s = new Subject();
                Chapter c = new Chapter();
                s.setSubjectCode(rs.getString("subject_code"));
                c.setChapterName(rs.getString("chapter_name"));
                a.setAsmID(rs.getInt("asm_id"));
                a.setAsmName(rs.getString("asm_name"));
                a.setAsmDes(rs.getString("asm_des"));
                a.setSubject(s);
                a.setChapter(c);
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public int getTotalAssignmentBySubjectName(String subject) {
        String query = "select count(*)\n"
                + "from assignment a \n"
                + "join subject sj on a.subject_id=sj.subject_id\n"
                + "join chapter ct on a.chapter_id=ct.chapter_id\n"
                + "where a.asm_name like ?";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }
    public static void main(String[] args) {
        AsmDAL dao = new AsmDAL();
        List<Assignment> asmList = dao.pagingAssignment(1);
        int count = dao.getTotalAssignment();
        System.out.println(count);
        for (Assignment a : asmList) {
            System.out.println(a);
        }
    }
}
