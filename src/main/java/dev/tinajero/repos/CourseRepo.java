package dev.tinajero.repos;

import dev.tinajero.utils.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRepo {

    ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public BigDecimal getPercent(int eventType){

        try(Connection conn = cu.getConnection()){//getting the connection to the db
            String sql = "select * from course where id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,eventType); // sets the value for the question mark above, this way is more secure.

            ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return rs.getBigDecimal("courseWorth");
                }
        }catch(SQLException e){
            System.out.println("failed to get percentage from course rep");
        }


        return null;
    }


}
