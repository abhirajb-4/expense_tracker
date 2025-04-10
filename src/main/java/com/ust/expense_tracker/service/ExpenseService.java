package com.ust.expense_tracker.service;

import com.ust.expense_tracker.model.Expense;
import com.ust.expense_tracker.model.User;
import com.ust.expense_tracker.repository.ExpenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepo expenseRepository;

    public List<Expense> getExpensesByUser(User user) {
        return expenseRepository.findByUser(user);
    }

    public void saveExpense(Expense expense) {
        System.out.println(expense);
        expenseRepository.save(expense);
    }

    public void editExpense(Expense expense) {
        Expense existExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(()-> new RuntimeException("Expense not found"));

        existExpense.setExpenseName(expense.getExpenseName());
        existExpense.setExpenseType(expense.getExpenseType());
        existExpense.setAmount(expense.getAmount());
        existExpense.setCategory(expense.getCategory());
        existExpense.setDate(expense.getDate());
        expenseRepository.save(existExpense);
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("EXpense not found"));

        expenseRepository.delete(expense);
    }
}
