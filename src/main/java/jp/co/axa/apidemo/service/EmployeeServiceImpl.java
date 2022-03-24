package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.model.request.EmployeePutRequest;
import jp.co.axa.apidemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(Long employeeId) {

        List employeeList = new ArrayList<>();
        if (employeeId != null && employeeId > 0) {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            employeeList.add(employee);
        } else {
            employeeList = employeeRepository.findAll();
        }
        return employeeList;
    }


    public Employee saveEmployee(EmployeePostRequest postEmployee) {
        Employee employee = new Employee();
        employee.setId(postEmployee.getId());
        employee.setName(postEmployee.getName());
        employee.setDepartment(postEmployee.getDepartment());
        employee.setSalary(postEmployee.getSalary());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public Employee updateEmployee(EmployeePutRequest putEmployee) {

        Employee employee = new Employee();
        employee.setId(putEmployee.getId());
        employee.setName(putEmployee.getName());
        employee.setDepartment(putEmployee.getDepartment());
        employee.setSalary(putEmployee.getSalary());

        return employeeRepository.save(employee);

    }
}