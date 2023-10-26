/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Chapter;
import model.Dimension;
import model.Lesson;
import model.SubjectSetting;
import model.SystemSetting;

/**
 *
 * @author acer
 */
public class SubjectSettingDAL extends DBContext {

    public List<SubjectSetting> getAllSettingBySubject(String code) {
        List<SubjectSetting> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE subject_code = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectSetting ss = new SubjectSetting(rs.getInt("setting_id"), rs.getString("subject_code"), rs.getString("setting_group"), rs.getString("setting_name"), rs.getString("setting_value"), rs.getInt("display_order"), rs.getString("description"), sd.getSubjectByCode(rs.getString("subject_code")), rs.getInt("status"));
                list.add(ss);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public SubjectSetting getAllSettingBySetting(int id) {
        List<SubjectSetting> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        try {
            String sql = "SELECT * FROM subject_setting WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SubjectSetting ss = new SubjectSetting(rs.getInt("setting_id"), rs.getString("subject_code"), rs.getString("setting_group"), rs.getString("setting_name"), rs.getString("setting_value"), rs.getInt("display_order"), rs.getString("description"), sd.getSubjectByCode(rs.getString("subject_code")), rs.getInt("status"));
                return ss;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SubjectSetting> getListByPage(int start, int end, List<SubjectSetting> list) {
        List<SubjectSetting> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public List<Lesson> getLessonByPage(int start, int end, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public void activateSST(int id) {
        try {
            String sql = "UPDATE subject_setting SET status = 1 WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public void deactivateSST(int id) {
        try {
            String sql = "UPDATE subject_setting SET status = 0 WHERE setting_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("da" + e);
        }
    }

    public List<Chapter> getAllChapterFromClassAndSubject(int subjectID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        List<Chapter> list = new ArrayList<>();
        AsmDAL asd = new AsmDAL();
        try {
            String sql = "SELECT * FROM chapter LEFT JOIN\n"
                    + "(SELECT chapter.chapter_id, COUNT(chapter.chapter_id) AS cnt FROM chapter\n"
                    + " LEFT JOIN lesson ON chapter.chapter_id = lesson.chapter_id\n"
                    + "LEFT JOIN video_tracking ON lesson.lesson_id = video_tracking.lesson_id\n"
                    + "LEFT JOIN quiz ON lesson.lesson_id = quiz.lesson_id\n"
                    + "LEFT JOIN quiz_grade ON quiz.quiz_id = quiz_grade.quiz_id\n"
                    + "WHERE chapter.subject_id = ? AND (quiz_grade.student_id > 0 OR video_tracking.status = 1)\n"
                    + "GROUP BY chapter.chapter_id) AS T\n"
                    + "ON chapter.chapter_id = T.chapter_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, subjectID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("chapter_id"));
                c.setChapterName(rs.getString("chapter_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                c.setAsm(asd.getAllAsmByChapter(rs.getInt("chapter_id")));
                c.setNumAchieve(rs.getInt("cnt"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Chapter> getAllChapter() {
        List<Chapter> list = new ArrayList<>();
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM chapter";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("chapter_id"));
                c.setChapterName(rs.getString("chapter_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Chapter getChapterByID(int id) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM chapter WHERE chapter_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Chapter c = new Chapter();
                c.setChapterID(rs.getInt("chapter_id"));
                c.setChapterName(rs.getString("chapter_name"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                c.setUpdatedAt(rs.getTimestamp("updated_at"));
                c.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                c.setDescription(rs.getString("description"));
                c.setDisplayOrder(rs.getInt("display_order"));
                c.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                c.setStatus(rs.getInt("status"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Lesson> getAllLessonByChapter(int chapterID) {
        AccountDAL ad = new AccountDAL();
        List<Lesson> list = new ArrayList<>();
        QuizDAL qd = new QuizDAL();
        AsmDAL asd = new AsmDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT lesson.*, quiz_grade.student_id, quiz.quiz_id, asm_id, grade, video_tracking.status AS vist FROM lesson LEFT JOIN quiz ON lesson.lesson_id = quiz.lesson_id "
                    + "LEFT JOIN video_tracking ON lesson.lesson_id = video_tracking.lesson_id"
                    + " LEFT JOIN assignment ON lesson.lesson_id = assignment.lesson_id"
                    + " LEFT JOIN quiz_grade ON quiz.quiz_id = quiz_grade.quiz_id WHERE lesson.chapter_id = ?\n";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, chapterID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setLessonID(rs.getInt("lesson_id"));
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                l.setQuiz(qd.getQuizByLessonID(rs.getInt("lesson_id")));
                l.setAsm(asd.getAllAsmByLesson(rs.getInt("asm_id")));
                l.setVideoLink(rs.getString("video_link"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setDescription(rs.getString("description"));
                if (rs.getInt("student_id") > 0 || rs.getInt("vist") == 1) {
                    System.out.println(rs.getInt("student_id") + " " + rs.getInt("vist"));
                    l.setAchieve(true);
                } else {
                    l.setAchieve(false);
                }
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println("2" + e);
        }
        return list;
    }

    public Dimension getDimensionByID(int dimensionID) {
        SubjectDAL sd = new SubjectDAL();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM dimension WHERE dimension_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, dimensionID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Dimension d = new Dimension();
                d.setCreatedAt(rs.getTimestamp("created_at"));
                d.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                d.setUpdatedAt(rs.getTimestamp("updated_at"));
                d.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                d.setDescription(rs.getString("description"));
                d.setDimensionID(rs.getInt("dimension_id"));
                d.setDimensionName(rs.getString("dimension_name"));
                d.setDisplayOrder(rs.getInt("display_order"));
                d.setSubject(sd.getSubjectByID(rs.getInt("subject_id")));
                d.setStatus(rs.getInt("status"));
                d.setType(ssd.getSettingByID(rs.getInt("dimension_type")));
                return d;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Lesson getLessonByID(int ID) {
        AccountDAL ad = new AccountDAL();
        QuizDAL qd = new QuizDAL();
        AsmDAL asd = new AsmDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT lesson.*, quiz_id, asm_id FROM lesson LEFT JOIN quiz ON lesson.lesson_id = quiz.lesson_id "
                    + "LEFT JOIN assignment ON lesson.lesson_id = assignment.lesson_id WHERE lesson.lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setStatus(rs.getInt("status"));
                l.setVideoLink(rs.getString("video_link"));
                l.setQuiz(qd.getQuizByLessonID(rs.getInt("lesson_id")));
                l.setAsm(asd.getAllAsmByLesson(rs.getInt("asm_id")));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setDescription(rs.getString("description"));
                return l;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Lesson> getAllLesson() {
        List<Lesson> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Lesson> getLessonBySearch(String more, String order) {
        List<Lesson> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson " + more
                    + order;
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            SystemSettingDAL ssd = new SystemSettingDAL();
            AccountDAL ad = new AccountDAL();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list.add(l);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Lesson> getLessonByKey(String key, List<Lesson> list, String more) {
        List<Lesson> list1 = new ArrayList<>();
        List<Lesson> list3 = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE lesson_name LIKE '%" + key + "%'"
                    + more
                    + " ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list1.add(l);
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list1.get(i).getChapter().getChapterID() == list.get(j).getChapter().getChapterID()) {
                        list3.add(list1.get(i));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list3;
    }

    public List<Lesson> getLessonByType(int id, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        List<Lesson> list3 = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE lesson_type = ? "
                    + " ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list1.add(l);
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list1.get(i).getChapter().getChapterID() == list.get(j).getChapter().getChapterID()) {
                        list3.add(list1.get(i));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list3;
    }

    public List<Lesson> getLessonByStatus(int status, List<Lesson> list) {
        List<Lesson> list1 = new ArrayList<>();
        List<Lesson> list3 = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        SystemSettingDAL ssd = new SystemSettingDAL();
        try {
            String sql = "SELECT * FROM lesson WHERE status = ? "
                    + " ORDER BY display_order, lesson_id";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Lesson l = new Lesson();
                l.setChapter(getChapterByID(rs.getInt("chapter_id")));
                l.setCreatedAt(rs.getTimestamp("created_at"));
                l.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                l.setDescription(rs.getString("description"));
                l.setDisplayOrder(rs.getInt("display_order"));
                l.setLessonID(rs.getInt("lesson_id"));
                l.setLessonName(rs.getString("lesson_name"));
                l.setLessonType(ssd.getSettingByID(rs.getInt("lesson_type")));
                l.setStatus(rs.getInt("status"));
                l.setUpdatedAt(rs.getTimestamp("updated_at"));
                l.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                list1.add(l);
            }
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (list1.get(i).getChapter().getChapterID() == list.get(j).getChapter().getChapterID()) {
                        list3.add(list1.get(i));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list3;
    }

    public String getSortedLessonAsc(String id) {
        String order = "";
        switch (id) {
            case "ID": {
                order = "ORDER BY lesson_id ASC";
                break;
            }
            case "Name": {
                order = "ORDER BY lesson_name ASC";
                break;
            }
            case "Chapter": {
                order = "ORDER BY chapter_id ASC";
                break;
            }
            case "Type": {
                order = "ORDER BY lesson_type ASC";
                break;
            }
            case "Order": {
                order = "ORDER BY display_order ASC";
                break;
            }
            case "Status": {
                order = "ORDER BY status ASC";
                break;
            }
        }
        return order;
    }

    public String getSortedLessonDesc(String id) {
        String order = "";
        switch (id) {
            case "ID": {
                order = "ORDER BY lesson_id DESC";
                break;
            }
            case "Name": {
                order = "ORDER BY lesson_name DESC";
                break;
            }
            case "Chapter": {
                order = "ORDER BY chapter_id DESC";
                break;
            }
            case "Type": {
                order = "ORDER BY lesson_type DESC";
                break;
            }
            case "Order": {
                order = "ORDER BY display_order DESC";
                break;
            }
            case "Status": {
                order = "ORDER BY status DESC";
                break;
            }
        }
        return order;
    }

    public void actLesson(int n, int id) {

        try {
            String sql = "UPDATE Lesson SET status = ? WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, n);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
