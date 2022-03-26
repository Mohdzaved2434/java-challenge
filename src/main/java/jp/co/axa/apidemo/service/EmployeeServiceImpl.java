package jp.co.axa.apidemo.service;

import jp.co.axa.apidemo.constants.ApiCodes;
import jp.co.axa.apidemo.constants.Constants;
import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.exception.MyCustomException;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.model.request.EmployeePutRequest;
import jp.co.axa.apidemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "employee")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(key = "#employeeId")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if (!optEmp.isPresent()) {
            throw new MyCustomException(Constants.EMPLOYEE_NOT_FOUND, ApiCodes.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return optEmp.get();
    }
    
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(EmployeePostRequest postEmployee) {
        Employee employee = new Employee();
        employee.setName(postEmployee.getName());
        employee.setDepartment(postEmployee.getDepartment());
        employee.setSalary(postEmployee.getSalary());
        return employeeRepository.save(employee);
    }

    @CachePut(key = "#employeeId")
    public Employee updateEmployee(EmployeePutRequest putEmployee, Long employeeId) {

        Employee currentEmployee = getEmployee(employeeId);
        if (currentEmployee != null) {
            currentEmployee.setEmployeeId(employeeId);
            if (putEmployee.getName() != null) {
                currentEmployee.setName(putEmployee.getName());
            }
            if (putEmployee.getDepartment() != null) {
                currentEmployee.setDepartment(putEmployee.getDepartment());
            }
            if (putEmployee.getSalary() != null) {
                currentEmployee.setSalary(putEmployee.getSalary());
            }
            currentEmployee = employeeRepository.save(currentEmployee);
        }
        return currentEmployee;
    }

    @CacheEvict(key = "#employeeId")
    public void deleteEmployee(Long employeeId) {
        if (getEmployee(employeeId) != null) {
            employeeRepository.deleteById(employeeId);
        }
    }
}