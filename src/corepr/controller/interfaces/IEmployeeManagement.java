package corepr.controller.interfaces;

import corepr.model.office.Employee;

import java.util.List;

/**
 *
 */
// add, remove, find, filter, show info
public interface IEmployeeManagement {
     Employee addStaff(String jobTitle, String fullName, String phone, int salary);
     Employee removeStaff(String fullName);
     Employee findStaffByName(String fullName);
     List<Employee> filterStaffByPosition(String jobTitle);
     List<Employee> showStaffInfo();
}
