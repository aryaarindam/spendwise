package com.spendwise.backend.budget;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {
    Optional<MonthlyBudget> findByYearAndMonth(Integer year, Integer month);
}
