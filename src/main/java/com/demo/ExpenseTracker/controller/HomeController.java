package com.demo.ExpenseTracker.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.demo.ExpenseTracker.model.Expense;
import com.demo.ExpenseMapper.CityMapper;

import com.demo.ExpenseTracker.Expdao.ExpDao;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.ExpenseTracker.model.Expense;
import com.demo.ExpenseTracker.model.ExpenseTrackerModel;
import com.demo.ExpenseTracker.service.ExpenseService;


@Controller
public class HomeController {

	@Autowired
	private ExpenseTrackerModel expenseTracker; 
	
	@Autowired
	private CityMapper cityMapper;
	
	ExpDao dao;

	@Autowired
	private ExpenseService expenseService;

	
	@RequestMapping("/")
	public String home() {
		return "welcome";
	}


	@RequestMapping(value="/login" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterLogin() {
		System.out.println("inside login get");
		return "login";
	} 
	@RequestMapping(value="/login/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String loginForm() {
		System.out.println("inside login post");
		return "transactionPage";
	}
	@RequestMapping(value="/register" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterRegister(Expense exp) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		
		/* model.addAttribute("command", new Expense()); */
		System.out.println("inside register get");
		return "register";
	} 
	@RequestMapping(value="/register/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String RegisterForm(Expense exp) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		
		dao.save(exp);
		System.out.println("inside register post");
		return "transactionPage";
	}
	
	@RequestMapping(value = "/city/{state}", method = RequestMethod.GET)
    public <T> T getCity(
    	@PathVariable(value = "state") String state	
    	) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        map.put("city", cityMapper.findByStates(state));
        
        return (T) map;
    }
	
	
	
	
////	@RequestMapping(value = "/addExpense", method = { RequestMethod.POST, RequestMethod.GET })
////	public String addExpense(@ModelAttribute Expense exp, Model model) {
////		if (exp.getAmount() != 0 && expenseTracker.getInitialAmount() + exp.getAmount() >= 0) {
////			expenseService.saveItem(exp);
////		}
////
////		List<Expense> expList = expenseService.findAll();
////
////		int income = expenseTracker.getIncome();
////		int expense = expenseTracker.getExpense();
////		int balance = 0;
////
////		if (exp.getAmount() > 0) {
////			expenseTracker.setInitialAmount(income);
////			income += exp.getAmount();
////			expenseTracker.setIncome(income);
////
////			balance = exp.getAmount() + expenseTracker.getInitialAmount();
////			expenseTracker.setInitialAmount(balance);
////
////		} else if (exp.getAmount() < 0) {
////			if (expenseTracker.getInitialAmount() + exp.getAmount() >= 0) {
////				expense += exp.getAmount();
////				expenseTracker.setExpense(expense);
////
////				balance = expenseTracker.getInitialAmount() + exp.getAmount();
////				expenseTracker.setInitialAmount(balance);
////
////			} else {
////				return "error";
////			}
////		} else {
////			model.addAttribute("incomeVal", income);
////			model.addAttribute("expense", expense);
////			return "addInitialAmount";
////		}
////
////		model.addAttribute("amount", balance);
////		model.addAttribute("incomeVal", income);
////		model.addAttribute("expense", expense);
////		model.addAttribute("expList", expList);
////
////		return "index";
////	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public String deleteItem(@PathVariable("id") int id, Model model) {
//
//		List<Expense> expIdList = expenseService.findById(id);
//		
//	
//		int expenseAmount = expIdList.get(expIdList.size() - 1).getAmount();
//		expIdList.clear();
//		expenseService.deleteItem(id);
//		List<Expense> expList = expenseService.findAll();
//		
//		int income = expenseTracker.getIncome();
//		int expense = expenseTracker.getExpense();
//		int balance =  0;
//		if (expenseAmount > 0) {
//			income = expenseTracker.getIncome() - expenseAmount;
//			expenseTracker.setIncome(income);
//
//		} else {
//			expense = expenseTracker.getExpense() - expenseAmount;
//			expenseTracker.setExpense(expense);
//		}
//		
//		balance = expenseTracker.getIncome() + expenseTracker.getExpense();
//		expenseTracker.setInitialAmount(balance);
//		model.addAttribute("amount", balance);
//		model.addAttribute("incomeVal", income);
//		model.addAttribute("expense", expense);
//		model.addAttribute("expList", expList);
//		return "index";
//	}
}