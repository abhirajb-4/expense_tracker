package com.ust.expense_tracker.service;

import com.ust.expense_tracker.model.Expense;
import com.ust.expense_tracker.model.User;
import com.ust.expense_tracker.repository.ExpenseRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
        System.out.println(expense);
        return;
    }

    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Expense not found");
        }
        else{
            expenseRepository.deleteById(id);
            System.out.println(id+"  ajlkjlkflkjflkdjklfjdkljkldj");
        }
        return;
    }

}
