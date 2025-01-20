package com.app.userm.service;

import com.app.userm.entity.EmployeeDetails;
import com.app.userm.exception.CustomException;
import com.app.userm.model.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeDetails saveEmployee(EmployeeRequest employeeDetails) throws CustomException;

    EmployeeDetails getEmployeeById(Integer empId);

    List<EmployeeDetails> getAllEmployee();

    EmployeeDetails getEmployeeByAadhar(String aadhar);

    EmployeeDetails updateEmployee(EmployeeRequest employeeDetails) throws CustomException;
}
