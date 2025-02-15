package com.app.userm.repo;

import com.app.userm.entity.Attendance;
import com.app.userm.entity.MonthalyReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MonthalyReportRepo extends CrudRepository<MonthalyReport,Integer> {

    @Query("SELECT a FROM MonthalyReport a WHERE a.salaryMonth BETWEEN :startDate AND :endDate")
    List<MonthalyReport> findBySalaryMonth(@Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);
}
