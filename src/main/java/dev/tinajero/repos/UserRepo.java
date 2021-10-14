package dev.tinajero.repos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dev.tinajero.models.Users;
import dev.tinajero.repos.UserRepo;
import dev.tinajero.utils.ConnectionUtil;

public class UserRepo implements CrudRepository {
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
//    select * from users u inner join on reimbursement r where u.id = r.employeeid

    @Override
    public int getById(Integer id) {
        return 0;
    }

    @Override
    public Object getId(Integer id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public List getAllUsers(Boolean val) {
        return null;
    }

    @Override
    public Boolean update(Object o) {
        return null;
    }

    public int getUserId(String username){

        try(Connection conn = cu.getConnection()){
            String sql = "select id from Users where userName =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }

        }catch(SQLException e){
            System.out.println("couldnt get the user id - user repo");
        }
        return 0;
    }

    public Users getByUsername(String user){
            try (Connection conn = cu.getConnection()) {
                String sql = "select * from Users where userName = ?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                     Users u = new Users();
                            u.setId(rs.getInt("id"));
                            u.setUserName(rs.getString("userName"));
                            u.setPassword(rs.getString("pass"));
                            u.setMoney(rs.getBigDecimal("funds"));
                            u.setBenCo(rs.getBoolean("benCo"));
                            u.setDepartH(rs.getBoolean("departmentHead"));
                            u.setDirectS(rs.getBoolean("directSuper"));
                            u.setEmployee(rs.getBoolean("employee"));
                            return u;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public Boolean updateUser(String username, BigDecimal money){

        try (Connection conn = cu.getConnection()) {
            String sql = "update Users set funds = ? where userName = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1,money);
            ps.setString(2, username);

            return ps.executeUpdate() !=0;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users getUserById(int id){
        try (Connection conn = cu.getConnection()) {
            String sql = "select * from Users where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users u = new Users();
                u.setId(rs.getInt("id"));
                u.setUserName(rs.getString("userName"));
                u.setPassword(rs.getString("pass"));
                u.setMoney(rs.getBigDecimal("funds"));
                u.setBenCo(rs.getBoolean("benCo"));
                u.setDepartH(rs.getBoolean("departmentHead"));
                u.setDirectS(rs.getBoolean("directSuper"));
                u.setEmployee(rs.getBoolean("employee"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
