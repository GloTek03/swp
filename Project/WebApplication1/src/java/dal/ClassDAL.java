/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.StudentIClass;
import model.Subject;
import model.SystemSetting;

/**
 *
 * @author acer
 */
public class ClassDAL extends DBContext {

    public List<model.Class> getAllClassThatTraineeIn(int student_id, Date d) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ss = new SystemSettingDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT class.* FROM student_class JOIN class ON student_class.class_id = class.class_id "
                    + "AND student_class.subject_Id = class.subject_id "
                    + "WHERE student_id = ? "
                    + "AND (class.start_date <= ? AND ? <= class.end_date "
                    + ")";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, student_id);
            st.setDate(2, d);
            st.setDate(3, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class();
                c.setClassID(rs.getInt("class_id"));
                c.setActivate(rs.getInt("status"));
                c.setClassName(rs.getString("class_name"));
                c.setEndDate(rs.getDate("end_date"));
                c.setEndTime(rs.getTime("end_time"));
                c.setS(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setSemester(ss.getSettingByID(rs.getInt("semester")));
                c.setStartDate(rs.getDate("start_date"));
                c.setStartTime(rs.getTime("end_time"));
                c.setTrainer(ad.getAccountByAccID(rs.getInt("trainer")));
                list.add(c);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
        return list;
    }

    public int getNumOfStudentByClassID(int classID) {
        try {
            String sql = "SELECT COUNT(StudentID) AS T FROM class JOIN student_class ON class.class_id = student_class.class_id "
                    + "WHERE class.class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("1");
        }
        return 0;
    }

    public Account getTrainerByClassID(int classID) {

        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT trainer FROM class WHERE class_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = ad.getAccountByAccID(rs.getInt("trainer"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<model.Class> getAllClass() {
        SystemSettingDAL ssd = new SystemSettingDAL();
        List<model.Class> list = new ArrayList<>();
        try {
            String sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id";
            SubjectDAL sd = new SubjectDAL();
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public model.Class getClassByID(int id) {
        SystemSettingDAL ssd = new SystemSettingDAL();

        try {
            String sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE class.class_id = ?";
            SubjectDAL sd = new SubjectDAL();
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<model.Class> getClassByPage(int start, int end, List<model.Class> list) {
        List<model.Class> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public List<model.Class> getClassBySearch(String search) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();

        try {
            String sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE class_name LIKE '%" + search + "%'";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<model.Class> getClassBySearchAFilter(String search, String subjectID, int semester) {
        List<model.Class> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();

        try {
            String sql;
            PreparedStatement st;
            if (subjectID.equals("0") && semester != 0) {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE class_name LIKE '%" + search + "%' AND semester = ?";
                st = DBContext().prepareStatement(sql);
                st.setInt(1, semester);
            } else if (semester == 0 && !(subjectID.equals("0"))) {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE class_name LIKE '%" + search + "%' AND subject_code = ?";
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
            } else if ((semester == 0 && subjectID.equals("0"))) {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE class_name LIKE '%" + search + "%'";
                st = DBContext().prepareStatement(sql);
            } else {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE class_name LIKE '%" + search + "%' AND subject_code = ? AND semester = ?";
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
                st.setInt(2, semester);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void activateClass(int classID, String subjectID) {
        try {
            String sql = "UPDATE class SET status = 1 WHERE class_id = ? AND subject_code = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setString(2, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deactivateClass(int classID, String subjectID) {
        try {
            String sql = "UPDATE class SET status = 0 WHERE class_id = ? AND subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setString(2, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insClass(int classID, String className, String majorID, String semester, String subjectID) {
        try {
            String sql = "INSERT INTO class (class_id, class_name, major_id, subject_code, semester, status) VALUES (?, ?, ?, ?, ?, 1)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setString(2, className);
            st.setString(3, majorID);
            st.setString(4, subjectID);
            st.setString(5, semester);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void insStudentIClassByClassIDASemASj(String studentID, int classID, String sem, String subjectID, Date start, Date end, Time startT, Time endT) {
        try {
            String sql = "INSERT INTO student_class (username, class_id, semester, subject_code, start_date, end_date, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, studentID);
            st.setInt(2, classID);
            st.setString(3, sem);
            st.setString(4, subjectID);
            st.setDate(5, start);
            st.setDate(6, end);
            st.setTime(7, startT);
            st.setTime(8, endT);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<StudentIClass> getStudentIClassByClassIDASemASj(int classID, String subjectID) {
        List<StudentIClass> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student_class WHERE class_id = ? AND subject_code = ? ";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setString(2, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StudentIClass sic = new StudentIClass(rs.getString("username"), classID, rs.getString("semester"), subjectID, rs.getDate("start_date"), rs.getDate("end_date"), rs.getTime("start_time"), rs.getTime("end_time"));
                list.add(sic);
            }
        } catch (Exception e) {
            System.out.println("get" + e);
        }
        return list;
    }

    public void dltStudentIClassByClassIDASemASj(int classID, String subjectID) {
        try {
            String sql = "DELETE FROM student_class WHERE class_id = ? AND subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setString(2, subjectID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("dlt" + e);
        }
    }

    public void updClass(int classID, String className, String majorID, String subjectIDOld, String semesterOld, int activate, String subjectID, String semester) {
        try {
            List<StudentIClass> listSIC = getStudentIClassByClassIDASemASj(classID, subjectIDOld);
            dltStudentIClassByClassIDASemASj(classID, subjectIDOld);
            String sql = "UPDATE class Set class_name = ?, major_id = ?, subject_code = ?, semester = ?, status = ? WHERE class_id = ? AND subject_code = ? AND semester = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, className);
            st.setString(2, majorID);
            st.setString(3, subjectID);
            st.setString(4, semester);
            st.setInt(5, activate);
            st.setInt(6, classID);
            st.setString(7, subjectIDOld);
            st.setString(8, semesterOld);
            st.executeUpdate();
            for (int i = 0; i < listSIC.size(); i++) {
                insStudentIClassByClassIDASemASj(listSIC.get(i).getStudentID(), classID, semester, subjectID, listSIC.get(i).getStartDate(), listSIC.get(i).getEndDate(), listSIC.get(i).getStartTime(), listSIC.get(i).getEndTime());
            }
        } catch (Exception e) {
            System.out.println("DA" + e);
        }
    }

    public model.Class getClassByIDASjASem(int classID, String subjectID) {
        SubjectDAL sd = new SubjectDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();

        try {
            String sql = "SELECT * FROM class JOIN major ON class.major_id = major.major_id WHERE class_id= ? AND subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, classID);
            st.setString(2, subjectID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<model.Class> getClassByFilter(String subjectID, int semester) {
        List<model.Class> list = new ArrayList<>();
        SystemSettingDAL ssd = new SystemSettingDAL();

        SubjectDAL sd = new SubjectDAL();
        try {
            String sql;
            PreparedStatement st;
            if (subjectID.equals("0") && semester != 0) {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE semester = ?";
                st = DBContext().prepareStatement(sql);
                st.setInt(1, semester);
            } else if (semester == 0 && !(subjectID.equals("0"))) {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE subject_code = ?";
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
            } else if ((semester == 0 && (subjectID.equals("0")))) {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id";
                st = DBContext().prepareStatement(sql);
            } else {
                sql = "SELECT class.*, major_name FROM class JOIN major ON class.major_id = major.major_id WHERE subject_code = ? AND semester = ?";
                st = DBContext().prepareStatement(sql);
                st.setString(1, subjectID);
                st.setInt(2, semester);

            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                model.Class c = new model.Class(rs.getInt("class_id"), rs.getString("class_name"), rs.getString("major_id"), rs.getInt("status"), ssd.getSettingByID(rs.getInt("semester")), rs.getString("major_name"), sd.getSubjectByCode(rs.getString("subject_code")));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
