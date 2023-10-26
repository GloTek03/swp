/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class UserDAL extends DBContext {

    public List<Account> getAllAcounts() {
        List<Account> accounts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "select username, full_name, enrolment_date, role, status from account";
            stm = DBContext().prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString("username"), rs.getInt("status"), rs.getString("role"), rs.getString("full_name"), rs.getString("enrolment_date"));
                accounts.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public List<Account> getListByPage(int start, int end, List<Account> list) {
        List<Account> list2 = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list2.add(list.get(i));
        }
        return list2;
    }

    public void updateUser(String fullname, String role, boolean status, String mobile, String dob, String gender, String enroldate, String username) {
        try {
            String query = "update  account  \n"
                    + "set full_name = ?,\n"
                    + "role = ?,\n"
                    + "status = ?,\n"
                    + "mobile = ?,\n"
                    + "dob = ?',\n"
                    + "gender = ?,\n"
                    + "enrolment_date = ?\n"
                    + "where username = ?';";
            PreparedStatement stm = DBContext().prepareStatement(query);
            stm.setString(1, fullname);
            stm.setString(2, role);
            stm.setBoolean(3, status);
            stm.setString(4, mobile);
            stm.setString(5, dob);
            stm.setString(6, gender);
            stm.setString(7, enroldate);
            stm.setString(9, username);
        } catch (SQLException e) {

        }
    }

    public Account getAccbyUsername(String username) {
        Account acc = null;
        try {
            String query = "SELECT * FROM account WHERE username = ?";
            PreparedStatement stm = DBContext().prepareStatement(query);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            String roleName = "";
            int activate = 0;
            if (rs.next()) {
                acc = new Account();
                acc.setUser(rs.getString("username"));
                acc.setPass(rs.getString("password"));
                acc.setActivate(rs.getInt("status"));
                acc.setFullName(rs.getString("full_name"));
                acc.setDob(rs.getDate("dob"));
                acc.setG(Account.Gender.valueOf(rs.getString("gender")));
                acc.setEmail(rs.getString("email"));
                acc.setMobile(rs.getString("mobile"));
                acc.setDateEnroll(rs.getString("enrolment_date"));
                switch (rs.getString("role")) {
                    case "1":
                        roleName = "User";
                        break;
                    case "2":
                        roleName = "Trainer";
                        break;
                    case "3":
                        roleName = "Subject Manager";
                        break;
                    case "4":
                        roleName = "Administrator";
                        break;
                    default:
                        roleName = "User";
                }
                acc.setRoleName(roleName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }

    public List<Account> searchByName(String txtSearch) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select username, full_name, enrolment_date, role, status from account\n"
                    + "where full_name like ?";
            stm = DBContext().prepareStatement(sql);
            stm.setString(1, "%" + txtSearch + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString("username"), rs.getInt("status"), rs.getString("role"), rs.getString("full_name"), rs.getString("enrolment_date"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAccount() {
        String query = "select count(*) from account";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Account> pagingAccount(int index) {
        List<Account> list = new ArrayList<>();
        String query = "select a.account_id, a.username, a.full_name, a.enrolment_date, a.role, s.setting_name, a.status\n"
                + "from account a join system_setting s\n"
                + "where a.role = s.setting_id\n"
                + "order by account_id asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setInt(1, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setID(rs.getInt("account_id"));
                a.setUser(rs.getString("username"));
                a.setActivate(rs.getInt("status"));
                a.setRoleName(rs.getString("setting_name"));
                a.setFullName(rs.getString("full_name"));
                a.setDateEnroll(rs.getString("enrolment_date"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalNameBySearch(String txt) {
        String query = "select count(*)\n"
                + "from account a join system_setting s\n"
                + "where a.role = s.setting_id and (a.full_name like ? or s.setting_name like ?)";
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + txt + "%");
            stm.setString(2, "%" + txt + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Account> paggingAccountBySearch(String txtSearch, int index) {
        List<Account> list = new ArrayList<>();
        String query = "select row_number() over(order by account_id asc) as No, a.account_id, a.username, a.full_name, a.enrolment_date, a.role, s.setting_name, a.status\n"
                + "from account a join system_setting s\n"
                + "where a.role = s.setting_id and (a.full_name like ? or s.setting_name like ?)\n"
                + "order by No asc Limit 5 offset ?";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, "%" + txtSearch + "%");
            stm.setString(2, "%" + txtSearch + "%");
            stm.setInt(3, (index - 1) * 5);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setID(rs.getInt("account_id"));
                a.setUser(rs.getString("username"));
                a.setActivate(rs.getInt("status"));
                a.setRoleName(rs.getString("setting_name"));
                a.setFullName(rs.getString("full_name"));
                a.setDateEnroll(rs.getString("enrolment_date"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertUser(String username, String email, String password, int role, String full_name, String dob, String gender, String mobile, String enrolDate) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;
        String query = "insert into account(username,email,password,role,status,full_name,dob,gender,mobile,enrolment_date)\n"
                + "values(?,?,?,?,1,?,?,?,?,?)";
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setInt(4, role);
            stm.setString(5, full_name);
            stm.setString(6, dob);
            stm.setString(7, gender);
            stm.setString(8, mobile);
            stm.setString(9, enrolDate);
            stm.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void deleteUser(String username) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;
        String query = "delete from account\n"
                + "where username =?";
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, username);
            stm.executeUpdate();
        } catch (Exception e) {

        }
    }
    
    public void updateUser(String ID, String activate) {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stm = null;
        String query = "update account set status = ? where account_id=?";
        try {
            conn = new DBContext().DBContext();
            stm = conn.prepareStatement(query);
            stm.setString(1, activate);
            stm.setString(2,ID);
            stm.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        UserDAL dao = new UserDAL();
        dao.updateUser("13","0");
        int count = dao.getTotalNameBySearch("tran");

        String indexPage = "2";
        int index = Integer.parseInt(indexPage);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }

        List<Account> list = dao.paggingAccountBySearch("user", index);
         for(Account i:list){
             System.out.println(i);
         }
    }

    
}
