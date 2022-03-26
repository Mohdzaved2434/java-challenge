package jp.co.axa.apidemo.controller;

import io.swagger.annotations.*;
import jp.co.axa.apidemo.entity.Employee;
import jp.co.axa.apidemo.helper.ResponseProvider;
import jp.co.axa.apidemo.model.request.EmployeePostRequest;
import jp.co.axa.apidemo.model.request.EmployeePutRequest;
import jp.co.axa.apidemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Controller", tags = "Employee Controller")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "get employees")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Id not found"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @GetMapping("/employees")
    public ResponseEntity<?> get(@RequestParam(value = "employee_id", required = false) Long employeeId) {

        if (employeeId != null) {
            return ResponseProvider.success(employeeService.getEmployee(employeeId));
        } else {
            return ResponseProvider.success(employeeService.getEmployees());
        }
    }

    @ApiOperation(value = "save employees")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @PostMapping(value = "/employees", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> post(@Valid @RequestBody EmployeePostRequest employeePostRequest) {

        Employee employee = employeeService.saveEmployee(employeePostRequest);
        return ResponseProvider.success(employee);
    }

    @ApiOperation(value = "update employees")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 422, message = "Id not found")})
    @PutMapping(value = "/employees", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> put(@Valid @RequestBody EmployeePutRequest putEmployee,
                                 @Valid @RequestParam("employee_id") Long employeeId) {

        Employee employee = employeeService.updateEmployee(putEmployee, employeeId);
        return ResponseProvider.success(employee);
    }

    @ApiOperation(value = "delete employees")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Something went wrong"),
            @ApiResponse(code = 422, message = "Id not found"),
            @ApiResponse(code = 401, message = "Unauthorized")})
    @DeleteMapping("/employees")
    public ResponseEntity<?> delete(@RequestParam(value = "employee_id") Long employeeId) {

        employeeService.deleteEmployee(employeeId);
        return ResponseProvider.success("");
    }
}
