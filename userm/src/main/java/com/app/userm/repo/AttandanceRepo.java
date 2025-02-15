package com.app.userm.repo;

import com.app.userm.entity.Attendance;
import com.app.userm.entity.DistrictMaster;
import com.app.userm.model.AttendanceDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AttandanceRepo extends CrudRepository<Attendance,Integer> {

    @Query("SELECT a FROM Attendance a WHERE a.attandanceDate >= :startDate AND a.attandanceDate < :endDate")
    List<Attendance> findByYearMonth(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT emp.full_name, sm.site_name, att.attandance_date, att.is_present, att.ot_hours " +
            "FROM attendance att " +
            "JOIN emp_details emp ON emp.emp_id = att.employee_id " +
            "JOIN site_master sm ON sm.site_id = att.site_code " +
            "WHERE (:employeeId IS NULL OR att.employee_id = :employeeId) " +
            "AND att.attandance_date >= :startDate " +
            "AND att.attandance_date < :endDate",
            nativeQuery = true)
    List<Object[]> findByYearMonthOfEmployee(@Param("employeeId") Integer employeeId,
                                             @Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

}
