package com.company;

import com.company.db.ConnectionUtil;

import java.sql.*;

public class OfficeBranchRepository {
    public static void main(String[] args) {
//        Add service
        int added_office_id = addOffice("Suprix technology (Third branch)",
                "no.1 Kwairanga Road");
        System.out.println(getOffice(added_office_id));

//        Find service by id
        OfficeBranch office = getOffice(2);
        System.out.println(office);

//        Update service by id
        updateOfficeInfo(1,"address","No 13 Adetokunbo Road GRA");

//        Delete service by id
        deleteOffice(3);
    }
    public static void deleteOffice(int id){
        String sql = "DELETE FROM officeBranch "
                + "WHERE office_id = ?";

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
    public static void updateOfficeInfo(int id, String column, String value){
        String sqlUpdate = "UPDATE officeBranch "
                + "SET "+column+" = ? "
                + "WHERE office_id = ?";
        Connection conn = ConnectionUtil.connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
            if(column.equals("office_id"))
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
    public static OfficeBranch getOffice(int id){
        String sql = "select * from officeBranch where office_id="+id;
        OfficeBranch office = new OfficeBranch();

        Connection conn = ConnectionUtil.connect();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            office.office_id = id;
            office.name = rs.getString("name");
            office.address = rs.getString("address");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return office;
    }
    public static int addOffice(String name,String address){

        String sql = "insert into officeBranch (name,address) values(?,?)";
        int officeId = 0;

        Connection conn = ConnectionUtil.connect();
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,name);
            ps.setString(2,address);

            int rowAffected = ps.executeUpdate();
            System.out.println("Row affected is: "+rowAffected);

            if(rowAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                    officeId = rs.getInt(1);
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
        return officeId;
    }

}
class OfficeBranch{
    int office_id;
    String name;
    String address;

    @Override
    public String toString() {
        return  "{" +
                "office_id=" + office_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
