package com.spendwise.backend.budget;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "monthly_budgets",
    uniqueConstraints = @UniqueConstraint(columnNames = {"year", "month"})
)
public class MonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month; // 1-12

    @NotNull
    @Min(1)
    private Integer limitAmount;

    public MonthlyBudget() {}

    public Long getId() { return id; }
    public Integer getYear() { return year; }
    public Integer getMonth() { return month; }
    public Integer getLimitAmount() { return limitAmount; }

    public void setId(Long id) { this.id = id; }
    public void setYear(Integer year) { this.year = year; }
    public void setMonth(Integer month) { this.month = month; }
    public void setLimitAmount(Integer limitAmount) { this.limitAmount = limitAmount; }
}
