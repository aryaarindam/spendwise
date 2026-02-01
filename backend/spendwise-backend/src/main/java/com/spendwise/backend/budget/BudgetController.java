package com.spendwise.backend.budget;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final MonthlyBudgetRepository repo;

    public BudgetController(MonthlyBudgetRepository repo) {
        this.repo = repo;
    }

    @PutMapping("/monthly/{year}/{month}")
    public MonthlyBudget setMonthlyBudget(
            @PathVariable Integer year,
            @PathVariable Integer month,
            @jakarta.validation.Valid @RequestBody com.spendwise.backend.budget.dto.SetMonthlyBudgetRequest body
    ) {
        return repo.findByYearAndMonth(year, month)
                .map(existing -> {
                    existing.setLimitAmount(body.limitAmount);
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    MonthlyBudget b = new MonthlyBudget();
                    b.setYear(year);
                    b.setMonth(month);
                    b.setLimitAmount(body.limitAmount);
                    return repo.save(b);
                });
    }
}    