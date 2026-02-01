package com.spendwise.backend.summary;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spendwise.backend.budget.MonthlyBudgetRepository;
import com.spendwise.backend.expense.ExpenseRepository;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final ExpenseRepository expenseRepo;
    private final MonthlyBudgetRepository budgetRepo;

    public SummaryController(ExpenseRepository expenseRepo, MonthlyBudgetRepository budgetRepo) {
        this.expenseRepo = expenseRepo;
        this.budgetRepo = budgetRepo;
    }

    @GetMapping("/monthly/{year}/{month}")
    public Map<String, Object> monthly(@PathVariable int year, @PathVariable int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        long spent = expenseRepo.sumAmountBetween(start, end);

        Integer budget = budgetRepo.findByYearAndMonth(year, month)
                .map(b -> b.getLimitAmount())
                .orElse(null);

        Long remaining = (budget == null) ? null : (budget - spent);
        boolean overBudget = (budget != null) && spent > budget;

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("year", year);
        res.put("month", month);
        res.put("spent", spent);
        res.put("budget", budget);
        res.put("remaining", remaining);
        res.put("overBudget", overBudget);
        return res;
    }
}
