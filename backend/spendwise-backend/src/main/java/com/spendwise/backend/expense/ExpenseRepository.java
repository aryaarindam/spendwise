package com.spendwise.backend.expense;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("select coalesce(sum(e.amount), 0) from Expense e where e.date between :start and :end")
    long sumAmountBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
