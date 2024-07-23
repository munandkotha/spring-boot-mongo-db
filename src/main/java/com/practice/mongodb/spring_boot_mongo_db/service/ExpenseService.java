package com.practice.mongodb.spring_boot_mongo_db.service;

import com.practice.mongodb.spring_boot_mongo_db.model.Expense;
import com.practice.mongodb.spring_boot_mongo_db.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find expense with Id %s", expense.getId())));
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        expenseRepository.save(savedExpense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(String id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find Expense with Id %s", id)));
    }

    public Expense getExpenseByName(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Coundn't find Expense with Name %s", name)));
    }
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
