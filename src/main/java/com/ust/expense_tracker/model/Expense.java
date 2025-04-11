package com.ust.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String expenseName;

    @Positive(message = "Amount must be positive")
    @NotNull
    private double amount;

    @NotNull
    private String category;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType; //DEBIT CREDIT

    @NotNull
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference // Child side (ignored during serialization)
    private User user;

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseName='" + expenseName + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", expenseType=" + expenseType +
                ", date=" + date +
                '}';
    }
}
