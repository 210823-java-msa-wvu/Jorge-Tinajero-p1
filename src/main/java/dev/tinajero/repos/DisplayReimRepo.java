package dev.tinajero.repos;

import dev.tinajero.models.Approvals;
import dev.tinajero.models.Reimbursement;
import dev.tinajero.utils.ConnectionUtil;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisplayReimRepo implements CrudRepository{
    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

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

    public List<Approvals> getApprovals(){
        List<Approvals> arr = new ArrayList<Approvals>();
        try(Connection conn = cu.getConnection()){
            String sql = "select * from approval";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Approvals app= new Approvals();
                app.setAppDS(rs.getBoolean("directSuper"));
                app.setAppDH(rs.getBoolean("departmentHead"));
                app.setAppBC(rs.getBoolean("benCo"));
                arr.add(app);
            }

        }catch(SQLException e){
            System.out.println("error in getApprovals - display repo");
        }
        return arr;
    }
    public List<Reimbursement> getAllRequests(){
        List<Reimbursement> arr = new ArrayList<Reimbursement>();
        try(Connection conn = cu.getConnection()){
            String sql = "select * from reimbursement where approved = 'false';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement re = new Reimbursement();
                re.setId(rs.getInt("id"));
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
                re.setAmountApproved(rs.getBigDecimal("amountApproved"));
                re.setDirectSuper(rs.getBoolean("directSuper"));
                re.setAdditionalInfoS(rs.getString("additionalInfoS"));
                re.setExplanationDS(rs.getString("explanationDS"));
                re.setDepartmentHead(rs.getBoolean("departmentHead"));
                re.setAdditionalInfoHb(rs.getBoolean("additionalInfoHb"));
                re.setAdditionalInfoH(rs.getString("additionalInfoH"));
                re.setExplanationDH(rs.getString("explanationDH"));
                re.setBenCo(rs.getBoolean("benCo"));
                re.setAdditionalInfoBenb(rs.getBoolean("additionalInfoBenB"));
                re.setAdditionalInfoBen(rs.getString("additionalInfoBen"));
                re.setExplanation(rs.getString("explanation"));
                System.out.println(re);
                arr.add(re);

            }


        }catch(SQLException e){
            System.out.println("couldnt send all requests - display repo");
        }

        return arr;
    }


}
