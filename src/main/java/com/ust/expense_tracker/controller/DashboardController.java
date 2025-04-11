package com.ust.expense_tracker.controller;

import org.springframework.ui.Model;
import com.ust.expense_tracker.model.Expense;
import com.ust.expense_tracker.model.User;
import com.ust.expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final ExpenseService expenseService;

    @GetMapping
    public String showDashboard(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("expenses", expenseService.getExpensesByUser(user));
        model.addAttribute("newExpense", new Expense());
        System.out.println(user);
        return "dashboard";
    }

    @PostMapping("/add")
    public String addExpense(@AuthenticationPrincipal User user,
                             @ModelAttribute Expense expense) {
        expense.setUser(user);
        System.out.println(expense);
        expenseService.saveExpense(expense);
        return "redirect:/dashboard";
    }

    @PostMapping("/edit")
    public String editExpense(@ModelAttribute Expense expense){
        expenseService.editExpense(expense);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        System.out.println("DELETE METHOD INVOKEDD..........."+id);
        return "redirect:/dashboard";
    }
}
