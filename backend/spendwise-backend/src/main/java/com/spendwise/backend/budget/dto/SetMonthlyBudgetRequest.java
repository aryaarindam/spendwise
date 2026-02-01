package com.spendwise.backend.budget.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SetMonthlyBudgetRequest {

    @NotNull
    @Min(1)
    public Integer limitAmount;
}
