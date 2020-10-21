package com.company.lang;

import com.company.db.ConnectionUtil;

import java.sql.*;

public class ClientRepository {
    public static void main(String[] args) {
//        Delete client by id
        deleteClient(10);

//        Find client by id
        Clients client = getClient(11);
        System.out.println(client);

//        Update client by id
        updateClientInfo(11,"email","malamdabo@gmail.com");

//        Add client
        int added_client_id = addClient("Abdul","Mafia","07065449292",
                "abdulmafia@gmail.com","Alu Avenue",5);

        System.out.println(getClient(added_client_id));
    }
    public static void deleteClient(int id){
        String sql = "DELETE FROM clients "
                + "WHERE client_id = ?";

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
    public static void updateClientInfo(int id, String column, String value){
        String sqlUpdate = "UPDATE clients "
                + "SET "+column+" = ? "
                + "WHERE client_id = ?";
        Connection conn = ConnectionUtil.connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            if(column.equals("client_id"))
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
    public static Clients getClient(int id){
        String sql = "select * from clients where client_id="+id;
        Clients client = new Clients();

        Connection conn = ConnectionUtil.connect();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            client.client_id = id;
            client.fname = rs.getString("fname");
            client.lname = rs.getString("lname");
            client.phone = rs.getString("phone");
            client.email = rs.getString("email");
            client.address = rs.getString("address");
            client.employee_id = rs.getInt("employee_id");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return client;
    }
    public static int addClient(String fname,String lname,String phone,String email,
                                String address,int employee_id){

        String sql = "insert into clients(fname,lname,phone,email,address,employee_id)" +
                " values(?,?,?,?,?,?)";
        int clientId = 0;

        Connection conn = ConnectionUtil.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,phone);
            ps.setString(4,email);
            ps.setString(5,address);
            ps.setInt(6,employee_id);

            int rowAffected = ps.executeUpdate();
            System.out.println("Row affected is: "+rowAffected);

            if(rowAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                    clientId = rs.getInt(1);
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
        return clientId;
    }
}
class Clients{
    int client_id;
    String fname;
    String lname;
    String phone;
    String email;
    String address;
    int employee_id;

    @Override
    public String toString() {
        return  "{" +
                "client_id=" + client_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", employee_id=" + employee_id +
                '}';
    }
}