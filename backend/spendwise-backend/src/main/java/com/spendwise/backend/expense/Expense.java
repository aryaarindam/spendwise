package com.spendwise.backend.expense;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotBlank
    @Column(length = 120)
    private String title;

    @NotNull
    @Min(1)
    private Integer amount; 

    @NotBlank
    @Column(length = 40)
    private String category;

    @Column(length = 250)
    private String note;

    public Expense() {}

    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getTitle() { return title; }
    public Integer getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getNote() { return note; }

    public void setId(Long id) { this.id = id; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setTitle(String title) { this.title = title; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public void setCategory(String category) { this.category = category; }
    public void setNote(String note) { this.note = note; }
}
