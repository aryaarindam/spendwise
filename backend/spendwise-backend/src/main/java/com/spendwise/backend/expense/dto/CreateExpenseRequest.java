package com.spendwise.backend.expense.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateExpenseRequest {

    @NotNull
    public LocalDate date;

    @NotBlank
    public String title;

    @NotNull
    @Min(1)
    public Integer amount;

    @NotBlank
    public String category;

    public String note;
}
