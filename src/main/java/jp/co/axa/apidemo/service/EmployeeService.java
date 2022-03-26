package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.model.request.EmployeePutRequest;

import java.util.List;

public interface EmployeeService {
    public Employee getEmployee(Long employeeId);

    public List<Employee> getEmployees();

    public Employee saveEmployee(EmployeePostRequest employee);

    public void deleteEmployee(Long employeeId);

    public Employee updateEmployee(EmployeePutRequest employee, Long employeeId);
}