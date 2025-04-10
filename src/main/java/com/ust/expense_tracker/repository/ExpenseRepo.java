package com.ust.expense_tracker.repository;

import com.ust.expense_tracker.model.Expense;
import com.ust.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {

    List<Expense> findByUser(User user);
}
