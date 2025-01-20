package com.app.userm.service.impl;

import com.app.userm.entity.EmployeeDetails;
import com.app.userm.exception.CustomException;
import com.app.userm.model.EmployeeRequest;
import com.app.userm.repo.AttandanceRepo;
import com.app.userm.repo.EmployeeDetailsRepo;
import com.app.userm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //autowire repo
    @Autowired
    private EmployeeDetailsRepo emplRepo;


    /**
     * @param request
     * @return
     */
    @Override
    public EmployeeDetails saveEmployee(EmployeeRequest request) throws CustomException {


        if(getEmployeeByAadhar(request.getAadharNumber())!=null)
              throw new CustomException("user already exist");

        EmployeeDetails employeeDetail=new EmployeeDetails();

        employeeObjectSetter(employeeDetail,request);

        return emplRepo.save(employeeDetail);
    }

    private void employeeObjectSetter(EmployeeDetails employeeDetail, EmployeeRequest request) {
        employeeDetail.setFullName(request.getFullName());
        employeeDetail.setAddress(request.getAddress());
employeeDetail.setAadhar(request.getAadharNumber());
employeeDetail.setEmail(request.getEmail());
employeeDetail.setPhone(request.getPhone());
employeeDetail.setDateOfBirth(request.getDateOfBirth());
employeeDetail.setRate(request.getRate());
employeeDetail.setIsActive(Boolean.TRUE);
employeeDetail.setIsDeleted(Boolean.FALSE);
employeeDetail.setCreatedBy("admin");
employeeDetail.setCreatedDate(LocalDateTime.now());
    }

    /**
     * @param empId
     * @return
     */
    @Override
    public EmployeeDetails getEmployeeById(Integer empId) {
        return emplRepo.findById(empId).get();
    }

    /**
     * @return
     */
    @Override
    public List<EmployeeDetails> getAllEmployee() {
        return (List<EmployeeDetails>) emplRepo.findAll();
    }

    /**
     * @param aadhar
     * @return
     */
    @Override
    public EmployeeDetails getEmployeeByAadhar(String aadhar) {

        if(aadhar!=null)
            return emplRepo.findByAadhar(aadhar);

        return null;
    }

    /**
     * @param employeeDetails
     * @return
     * @throws CustomException
     */
    @Override
    public EmployeeDetails updateEmployee(EmployeeRequest employeeDetails) throws CustomException {
        return null;
    }
}
