package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.model.request.EmployeePutRequest;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getEmployees(Long employeeId);

    public Employee saveEmployee(EmployeePostRequest employee);

    public void deleteEmployee(Long employeeId);

    public Employee updateEmployee(EmployeePutRequest employee);
}