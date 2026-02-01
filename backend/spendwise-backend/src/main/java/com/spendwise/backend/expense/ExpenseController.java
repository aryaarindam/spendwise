package com.spendwise.backend.expense;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spendwise.backend.expense.dto.CreateExpenseRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Expense create(@Valid @RequestBody CreateExpenseRequest request) {
        Expense e = new Expense();
        e.setDate(request.date);
        e.setTitle(request.title);
        e.setAmount(request.amount);
        e.setCategory(request.category);
        e.setNote(request.note);
        return repository.save(e);
    }

    @GetMapping
    public List<Expense> list() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
