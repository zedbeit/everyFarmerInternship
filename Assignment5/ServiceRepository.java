package com.company;

import com.company.db.ConnectionUtil;

import java.sql.*;

public class ServiceRepository {
    public static void main(String[] args) {
//        Add service
        int added_service_id = addService("web application",250000);
        System.out.println(getService(added_service_id));

//        Find service by id
        Services service = getService(100);
        System.out.println(service);

//        Update service by id
        updateServiceInfo(100,"service_cost","120000");

//        Delete service by id
        deleteService(100);
    }
    public static void deleteService(int id){
        String sql = "DELETE FROM services "
                + "WHERE service_id = ?";

        Connection conn = ConnectionUtil.connect();

        try {
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setInt(1,id);

            int rowAffected = ps.executeUpdate();
            System.out.println("Row affected is: "+rowAffected);

        }catch (SQLException se) {
            System.out.println(se.getMessage());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }
    public static void updateServiceInfo(int id, String column, String value){
        String sqlUpdate = "UPDATE services "
                + "SET "+column+" = ? "
                + "WHERE service_id = ?";
        Connection conn = ConnectionUtil.connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            if(column.equals("service_id"))
                ps.setInt(1, Integer.parseInt(value));
            else
                ps.setString(1,value);
            ps.setInt(2,id);
            int rowAffected = ps.executeUpdate();
            System.out.println("Row affected is: "+rowAffected);

        }catch (SQLException se) {
            System.out.println(se.getMessage());
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static Services getService(int id){
        String sql = "select * from services where service_id="+id;
        Services service = new Services();

        Connection conn = ConnectionUtil.connect();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            service.service_id = id;
            service.service_type = rs.getString("service_type");
            service.service_cost = rs.getInt("service_cost");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return service;
    }
    public static int addService(String service_type,int service_cost){

        String sql = "insert into services(service_type,service_cost) values(?,?)";
        int serviceId = 0;

        Connection conn = ConnectionUtil.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,service_type);
            ps.setInt(2,service_cost);

            int rowAffected = ps.executeUpdate();
            System.out.println("Row affected is: "+rowAffected);

            if(rowAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                    serviceId = rs.getInt(1);
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return serviceId;
    }
}
class Services{
    int service_id;
    String service_type;
    int service_cost;

    @Override
    public String toString() {
        return  "{" +
                "service_id=" + service_id +
                ", service_type='" + service_type + '\'' +
                ", service_cost=" + service_cost +
                '}';
    }
}
