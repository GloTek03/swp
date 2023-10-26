/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Quiz;
import model.QuizGrade;

/**
 *
 * @author acer
 */
public class QuizDAL extends DBContext {

    public Quiz getQuizByID(int quiz) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM quiz WHERE quiz_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, quiz);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                q.setCreatedAt((rs.getTimestamp("created_at")));
                q.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                q.setUpdatedAt((rs.getTimestamp("updated_at")));
                q.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                q.setDisplayOrder(rs.getInt("display_order"));
                q.setNoQ(rs.getInt("noQ"));
                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                q.setTimeLimit(rs.getDouble("time_limit"));
                q.setDescription(rs.getString("description"));
                return q;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Quiz getQuizByLessonID(int lesson) {
        SubjectSettingDAL ssd = new SubjectSettingDAL();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM quiz WHERE lesson_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, lesson);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Quiz q = new Quiz();
                q.setChapter(ssd.getChapterByID(rs.getInt("chapter_id")));
                q.setCreatedAt((rs.getTimestamp("created_at")));
                q.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                q.setUpdatedAt((rs.getTimestamp("updated_at")));
                q.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                q.setDisplayOrder(rs.getInt("display_order"));
                q.setNoQ(rs.getInt("noQ"));
                q.setQuizID(rs.getInt("quiz_id"));
                q.setQuizName(rs.getString("quiz_name"));
                q.setTimeLimit(rs.getDouble("time_limit"));
                q.setDescription(rs.getString("description"));
                return q;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<QuizGrade> getQuizGradeByAccAQuiz(int accID, int quizID) {
        List<QuizGrade> list = new ArrayList<>();
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM quiz_grade WHERE student_id = ? AND quiz_id = ?";
            PreparedStatement st = DBContext().prepareStatement(sql);
            st.setInt(1, accID);
            st.setInt(2, quizID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuizGrade qg = new QuizGrade();
                qg.setStudent(ad.getAccountByAccID(rs.getInt("student_id")));
                qg.setQuiz(getQuizByID(rs.getInt("quiz_id")));
                qg.setCount(rs.getInt("count"));
                qg.setGrade(rs.getDouble("grade"));
                list.add(qg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
