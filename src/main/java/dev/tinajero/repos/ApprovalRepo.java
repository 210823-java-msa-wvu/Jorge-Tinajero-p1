package dev.tinajero.repos;

import dev.tinajero.models.Users;
import dev.tinajero.utils.ConnectionUtil;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.tinajero.models.Approvals;


public class ApprovalRepo extends HttpServlet {
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public Boolean getSuperApp(int id, boolean approval){

        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set directSuper = ? where id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval);
            ps.setInt(2,id);
            return ps.executeUpdate() != 0;

        }catch(SQLException e){
            System.out.println("couldnt get supervisor approval/denial - display repo");
        }
        return null;
    }

    public Boolean getDHApp(int id, boolean approval){
        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set departmentHead = ? where id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval);
            ps.setInt(2,id);
            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            System.out.println("couldnt get depH answer - display repo");
        }
        return false;
    }
    public Boolean getDanH(int id, boolean approval){
        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set departmentHead = ? and directSuper = ? where id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval);
            ps.setBoolean(2, approval);
            ps.setInt(2,id);
            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            System.out.println("couldnt get depHandS answer - display repo");
        }
        return false;
    }
    public Boolean getBencoApp(int id, boolean approval){
        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set benCo = ? where id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, approval);
            ps.setInt(2,id);
            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            System.out.println("couldnt get benco answer - display repo");
        }
        return false;
    }

    public Boolean updateApp(int id, boolean benApp){
        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set approved = ? where id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, benApp);
            ps.setInt(2,id);
            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            System.out.println("couldnt set approval - approval repo");
        }

        return null;
    }

    public Boolean updateReason(int id, String reason, String supervisorType){

        try(Connection conn = cu.getConnection()){
            String sql = "";
            if("explanationDS".equals(supervisorType)) {
                 sql = "update reimbursement set  explanationDS = ? where id = ?;";
            }
            else if("explanationDH".equals(supervisorType)){
                 sql = "update reimbursement set explanationDH = ? where id = ?;";
            }
            else if("explanation".equals(supervisorType)){
                 sql = "update reimbursement set explanation = ? where id= ?;";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,reason);
            ps.setInt(2, id);
            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            System.out.println("couldnt set reason - display repo");
        }

        return null;
    }
    public Boolean updateAdditionalInfo(String addInfo, int id, String supervisorType){

        try(Connection conn = cu.getConnection()){
            String sql = "";
            if("directSuper".equals(supervisorType)) {
                sql = "update reimbursement set  additionalInfoS = ? where id = ?;";
            }
            else if("departmentH".equals(supervisorType)){
                sql = "update reimbursement set additionalInfoH = ? where id = ?;";
            }
            else if("benCo".equals(supervisorType)){
                sql = "update reimbursement set additionalInfoBen = ? where id= ?;";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,addInfo);
            ps.setInt(2, id);
            return ps.executeUpdate() != 0;
        }catch(SQLException e){
            System.out.println("couldnt set reason - display repo");
        }

        return null;
    }
}
