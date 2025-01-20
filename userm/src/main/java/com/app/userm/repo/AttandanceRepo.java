package com.app.userm.repo;

import com.app.userm.entity.Attendance;
import com.app.userm.entity.DistrictMaster;
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

    @Query("SELECT a FROM Attendance a WHERE a.employeeId= :employeeId and  a.attandanceDate >= :startDate AND a.attandanceDate < :endDate")
    List<Attendance> findByYearMonthOfEmployee(@Param("employeeId") Integer employeeId,@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
