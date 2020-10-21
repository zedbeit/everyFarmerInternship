package com.company.lang;

import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        
        Employee employee = new Employee();
        employee.setDesignation("Developer");
        employee.setEmail("devakd@gmail");
        employee.setFname("John");
        employee.setLname("Doe");
        employee.setOffice_id(1);
        employee.setSalary(30000);
        employee.setPhone("0905450543");

        Employee savedEmployee = employeeRepository.createEmployee(employee);
        System.out.println(savedEmployee.getEmp_id());

//        Get employee
        Employee emp1 = employeeRepository.getEmployee(4);
        Employee emp2 = employeeRepository.getEmployee(5);

//        Update employee
        employeeRepository.updateEmployee(emp1,"fname","Zane");

        System.out.println(emp1.getFname()+" "+emp1.getLname());
        System.out.println(emp2.getFname()+" "+emp2.getLname());

//        Delete employee
        employeeRepository.deleteEmployee(emp1);

        List<Employee> employeeList = employeeRepository.getAllEmployees();

        employeeList.forEach(emp -> System.out.println(emp.getEmp_id()+" "+
                emp.getFname()+" "+ emp.getLname()));
    }
}
