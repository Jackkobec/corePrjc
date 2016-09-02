package corepr.controller;

import corepr.controller.interfaces.IEmployeeManagement;
import corepr.controller.interfaces.IReport;
import corepr.db.AppDataContainer;
import corepr.model.office.Employee;
import corepr.model.office.PostTicket;
import corepr.model.money.Report;
import corepr.model.money.Transaction;

import java.util.ArrayList;
import java.util.List;


public class DirectorController implements IEmployeeManagement,IReport {

    protected AppDataContainer appDataContainer;

    public DirectorController(AppDataContainer appDataContainer) {
        this.appDataContainer = appDataContainer;
    }

    public AppDataContainer getAppDataContainer() {
        return appDataContainer;
    }

    @Override
    public Employee addStaff(String jobTitle, String fullName, String phone, int salary) {
        Employee employee = new Employee(jobTitle,fullName,phone,salary);

        appDataContainer.getEmployees().add(employee);
        appDataContainer.getUsers().put(employee.getLogin(), employee);

        return employee;
    }

    @Override
    public Employee removeStaff(String fullName) {
        for(Employee employee:appDataContainer.getEmployees()){
           if(employee.getFullName().equals(fullName)) {
               appDataContainer.getEmployees().remove(employee);
               appDataContainer.getUsers().remove(employee.getLogin());
               return employee;
           }
        }
        return null;
    }

    @Override
    public Employee findStaffByName(String fullName) {
        for(Employee employee: appDataContainer.getEmployees()) {
            if (employee.getFullName().equals(fullName)) {
                return employee;
            }
        }
        return null;
    }

    @Override

    public List<Employee> filterStaffByPosition(String jobTitle) {
        List<Employee> list = new ArrayList<>();
        for(Employee employee: appDataContainer.getEmployees()){
            if(employee.getJobTitle().equals(jobTitle)){
                list.add(employee);
            }
          }
         return list;
    }

    @Override

    public List<Employee> showStaffInfo() {
        List<Employee> list = new ArrayList<>();
        for (Employee employee: appDataContainer.getEmployees()){
            System.out.println(employee.toString());
            list.add(employee);
        }
        return list;
    }



    @Override
    public Report finaleReport(int income, Transaction[] transaction, Employee[] employees, PostTicket[] postTickets) {

        Report report = new Report(transaction,postTickets,employees, income);
        appDataContainer.getReports().add(report);

        return report;
    }
}
