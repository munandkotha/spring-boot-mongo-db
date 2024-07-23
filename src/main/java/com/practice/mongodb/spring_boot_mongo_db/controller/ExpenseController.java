package com.practice.mongodb.spring_boot_mongo_db.controller;

import com.practice.mongodb.spring_boot_mongo_db.model.Expense;
import com.practice.mongodb.spring_boot_mongo_db.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    private ResponseEntity addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    private ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @PutMapping
    private ResponseEntity updateExpense(@RequestBody Expense expense) {
        expenseService.updateExpense(expense);
        return ResponseEntity.ok().build();
    }

    /*@GetMapping("/{id}")
    private ResponseEntity<Expense> getExpenseById(@PathVariable String id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }*/

    @GetMapping("/{name}")
    private ResponseEntity<Expense> getExpenseByName(@PathVariable String name) {
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
