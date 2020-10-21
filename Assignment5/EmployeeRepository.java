package com.company.lang;

import com.company.db.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM employees "
                + "WHERE emp_id = ?";

        Connection conn = ConnectionUtil.connect();

        int id = employee.getEmp_id();
        System.out.println(id);
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

    public void updateEmployee(Employee employee, String column, String value) {
        String sqlUpdate = "UPDATE employees "
                + "SET "+column+" = ? "
                + "WHERE emp_id = ?";
        Connection conn = ConnectionUtil.connect();

        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdate,Statement.RETURN_GENERATED_KEYS);
            if(column.equals("emp_id"))
                ps.setInt(1, Integer.parseInt(value));
            else
                ps.setString(1,value);
            ps.setInt(2,employee.getEmp_id());
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
    public Employee createEmployee(Employee employee){
        String sql = "insert into employees(fname,lname,phone,email,designation,salary,office_id)" +
                " values(?,?,?,?,?,?,?)";
        Connection conn = ConnectionUtil.connect();

        int employeeId = 0;

        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, employee.getFname());
            ps.setString(2,employee.getLname());
            ps.setString(3,employee.getPhone());
            ps.setString(4, employee.getEmail());
            ps.setString(5,employee.getDesignation());
            ps.setInt(6,employee.getSalary());
            ps.setInt(7,employee.getOffice_id());

            int rowAffected = ps.executeUpdate();
            System.out.println("Row affected is: "+rowAffected);

            if(rowAffected == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                    employeeId = rs.getInt(1);
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
        employee.setEmp_id(employeeId);
        return employee;
    }
    public List<Employee> getAllEmployees(){
        String sql = "select * from employees";
        Connection conn = ConnectionUtil.connect();
        List<Employee> list = new ArrayList<>();

        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                list.add(new Employee(rs.getInt("emp_id"),rs.getString("fname"),
                        rs.getString("lname")));
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
        }
       return list;
    }
    public Employee getEmployee(int id) {
        String sql = "select * from employees where emp_id="+id;
        Connection conn = ConnectionUtil.connect();
        Employee e = new Employee();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            e.setEmp_id(id);
            e.setFname(rs.getString("fname"));
            e.setLname(rs.getString("lname"));
            e.setPhone(rs.getString("phone"));
            e.setEmail(rs.getString("email"));
            e.setDesignation(rs.getString("designation"));
            e.setSalary(rs.getInt("salary"));
            e.setOffice_id(rs.getInt("office_id"));
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
        }
        return e;
    }
}
class Employee {
    private int emp_id;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String designation;
    private int salary;
    private int office_id;

    public Employee(){

    }
    public Employee(int emp_id, String fname, String lname) {
        this.emp_id = emp_id;
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDesignation() {
        return designation;
    }

    public int getSalary() {
        return salary;
    }

    public int getOffice_id() {
        return office_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setOffice_id(int office_id) {
        this.office_id = office_id;
    }

    @Override
    public String toString() {
        return  "{" +
                "emp_id=" + emp_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", salary='" + salary +
                ", office_id='" + office_id + '\'' +
                '}';
    }
}

