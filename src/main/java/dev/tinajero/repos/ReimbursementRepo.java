package dev.tinajero.repos;

import dev.tinajero.models.Reimbursement;
import dev.tinajero.utils.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementRepo implements CrudRepository<Reimbursement>{
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public int getById(Integer id) {


        return 0;
    }

    @Override
    public Reimbursement getId(Integer id) {

        try(Connection conn = cu.getConnection()){
            String sql = "select * from reimbursement where employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Reimbursement re = new Reimbursement();
                re.setEmployeeId(rs.getInt("employeeId"));
                re.setCourseStartdate(rs.getString("courseStartdate"));
                re.setTime(rs.getString("reqTime"));
                re.setPlace(rs.getString("place"));
                re.setDescription(rs.getString("description"));
                re.setMoney(rs.getBigDecimal("costClass"));
                re.setReimburse(rs.getBigDecimal("reimburse"));
                re.setGrading(rs.getString("grading"));
                re.setEventType(rs.getBigDecimal("eventType"));
                re.setApproved(rs.getBoolean("approved"));
                re.setPass(rs.getString("pass"));
                re.setUrgent(rs.getBoolean("urgent"));
                re.setJustification(rs.getString("justification"));
                return re;

            }



        }catch(SQLException e){
            System.out.println("couldn't get reimbursement with emp id - Reimbursement Repo");
        }


        return null;
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }


    public List<Reimbursement> getAllReimbursements(int empId) {
        List<Reimbursement> arr = new ArrayList<Reimbursement>();
        try(Connection conn = cu.getConnection()){
            String sql = "select * from reimbursement where employeeId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reimbursement re = new Reimbursement();
                re.setId(rs.getInt("id"));
                re.setEmployeeId(rs.getInt("employeeId"));
                re.setEmployeeUser(rs.getString("employeeUser"));
                re.setUrgent(rs.getBoolean("urgent"));
                re.setPass(rs.getString("pass"));
                re.setApproved(rs.getBoolean("approved"));
                re.setEventType(rs.getBigDecimal("eventType"));
                re.setGrading(rs.getString("grading"));
                re.setReimburse(rs.getBigDecimal("reimburse"));
                re.setMoney(rs.getBigDecimal("costClass"));
                re.setDescription(rs.getString("description"));
                re.setPlace(rs.getString("place"));
                re.setTime(rs.getString("reqTime"));
                re.setCourseStartdate(rs.getString("courseStartdate"));
                re.setJustification(rs.getString("justification"));
                re.setAdditionalInfoBenb(rs.getBoolean("additionalInfoBenb"));
                re.setAdditionalInfoHb(rs.getBoolean("additionalInfoHb"));
                re.setAdditionalInfoSb(rs.getBoolean("additionalInfoSb"));
                re.setAdditionalInfoBen(rs.getString("additionalInfoBen"));
                re.setAdditionalInfoH(rs.getString("additionalInfoH"));
                re.setAdditionalInfoS(rs.getString("additionalInfoS"));
                re.setAmountIncrease(rs.getBoolean("amountIncrease"));
                arr.add(re);
            }
        }catch(SQLException e){
            System.out.println("couldnt get list of requirements - Reimbursement repo");
        }
        return arr;
    }

    @Override
    public List getAllUsers(Boolean val) {
        return null;
    }

    @Override
    public Boolean update(Reimbursement re) {
        try(Connection conn = cu.getConnection()){
            String sql = "insert into reimbursement (courseStartdate, reqTime, place, description, costClass, grading, eventType, urgent, reimburse, employeeId, employeeUser, pass, justification) values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, re.getCourseStartdate());
            ps.setString(2, re.getTime());
            ps.setString(3,re.getPlace());
            ps.setString(4,re.getDescription());
            ps.setBigDecimal(5, re.getMoney());
            ps.setString(6, re.getGrading());
            ps.setBigDecimal(7,re.getEventType());
            ps.setBoolean(8, re.isUrgent());
            ps.setBigDecimal(9, re.getReimburse());
            ps.setInt(10,re.getEmployeeId());
            ps.setString(11, re.getEmployeeUser());
            ps.setString(12, re.getPass());
            ps.setString(13,re.getJustification());
            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            System.out.println("failed to update reimbursements - reimbursement repo");
        }
        return null;
    }
    public Boolean updateReimbursement(int id, BigDecimal newAmount){

        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set reimburse = ? where id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBigDecimal(1,newAmount);
            ps.setInt(2,id);
            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            System.out.println("failed to update reimbursements - reimbursement repo");
        }
        return null;

    }
    public Reimbursement getWithId(int id){
        try(Connection conn = cu.getConnection()){
            String sql = "select * from reimbursement where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Reimbursement re = new Reimbursement();
                re.setEmployeeId(rs.getInt("employeeId"));
                re.setCourseStartdate(rs.getString("courseStartdate"));
                re.setTime(rs.getString("reqTime"));
                re.setPlace(rs.getString("place"));
                re.setDescription(rs.getString("description"));
                re.setMoney(rs.getBigDecimal("costClass"));
                re.setReimburse(rs.getBigDecimal("reimburse"));
                re.setGrading(rs.getString("grading"));
                re.setEventType(rs.getBigDecimal("eventType"));
                re.setApproved(rs.getBoolean("approved"));
                re.setPass(rs.getString("pass"));
                re.setUrgent(rs.getBoolean("urgent"));
                re.setJustification(rs.getString("justification"));
                re.setAdditionalInfoBenb(rs.getBoolean("additionalInfoBenb"));
                re.setAdditionalInfoHb(rs.getBoolean("additionalInfoHb"));
                re.setAdditionalInfoSb(rs.getBoolean("additionalInfoSb"));
                re.setAdditionalInfoBen(rs.getString("additionalInfoBen"));
                re.setAdditionalInfoH(rs.getString("additionalInfoH"));
                re.setAdditionalInfoS(rs.getString("additionalInfoS"));
                re.setAmountIncrease(rs.getBoolean("amountIncrease"));
                return re;
            }

        }catch(SQLException e){
            System.out.println("couldn't get reimbursement with emp id - Reimbursement Repo");
        }


        return null;
    }
    public Boolean updateGrade(int empId, int id, String grade) {
        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set pass = ? where employeeId = ? and id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, grade);
            ps.setInt(2, empId);
            ps.setInt(3,id);
            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            System.out.println("failed to update reimbursements - reimbursement repo");
        }
        return null;
    }

    public Boolean updateAdditional(String user, int id, boolean requested) {
        try(Connection conn = cu.getConnection()){
            String sql = "";
            if("directSuper".equals(user)) {
                sql = "update reimbursement set additionalInfoSb = ? where id = ?;";
            }
            else if("departmentH".equals(user)){
                sql = "update reimbursement set additionalInfoHb = ? where id = ?;";
            }
            else if("benCo".equals(user)){
                sql = "update reimbursement set additionalInfoBenb = ? where id= ?;";
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(  1, requested);
            ps.setInt(2, id);
            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            System.out.println("failed to update additional info - approval repo");
        }
        return null;
    }

    public Boolean updateAmountIncrease(int id, boolean value){
        try(Connection conn = cu.getConnection()){
            String sql = "update reimbursement set amountIncrease = ? where id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, value);
            ps.setInt(2,id);
            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            System.out.println("failed to update amount Increase - reimbursement repo");
        }
        return null;
    }

    public Boolean deleteReimbursement(int id){
        try(Connection conn = cu.getConnection()){
            String sql = "delete from reimbursement where id = ? ;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            return ps.executeUpdate() !=0;

        }catch(SQLException e){
            System.out.println("failed to delete - reimbursement repo");
        }
        return null;
    }
}
