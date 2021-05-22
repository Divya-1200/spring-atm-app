package com.demo.ExpenseTracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ExpenseTracker.model.Expense;


@Repository("repo")
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
	List<Expense> findById(int id);
}
