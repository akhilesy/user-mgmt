package com.app.userm.repo;

import com.app.userm.entity.EmployeeDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepo extends CrudRepository<EmployeeDetails,Integer> {

    EmployeeDetails findByAadhar(String aadhar);

  //  EmployeeDetails findByMobile(String aadhar);
}
