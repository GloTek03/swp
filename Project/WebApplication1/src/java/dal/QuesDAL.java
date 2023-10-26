package dal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;

/**
 *
 * @author quany
 */
public class QuesDAL extends DBContext {

    public ArrayList<Question> getAll() {
        AccountDAL ad = new AccountDAL();
        try {
            String sql = "SELECT * FROM swp_prj.question;";
            ArrayList<Question> list;
            try ( PreparedStatement ps = DBContext().prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    Question question = new Question();
                    question.setQuesID(rs.getInt("question_id"));
                    question.setTopic(rs.getString("topic"));
                    question.setAnswer(rs.getString("answer"));
                    question.setDisplayOrder(rs.getInt("display_order"));
                    question.setCreatedBy(ad.getAccountByAccID(rs.getInt("created_by")));
                    question.setCreatedAt(rs.getTimestamp("created_at"));
                    question.setUpdatedBy(ad.getAccountByAccID(rs.getInt("updated_by")));
                    question.setUpdatedAt(rs.getTimestamp("updated_at"));
                    question.setStatus(rs.getInt("status"));
                    list.add(question);
                }
                rs.close();
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void add(String topic, String answer) {
        try {
            String sql = "INSERT INTO question (topic, answer) VALUES (?, ?)";
            PreparedStatement ps = DBContext().prepareStatement(sql);
            ps.setString(1, topic);
            ps.setString(2, answer);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountinclassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int quesID) {
        try {
            String sql = "DELETE FROM `swp_prj`.`question` WHERE question_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quesID);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuesDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
