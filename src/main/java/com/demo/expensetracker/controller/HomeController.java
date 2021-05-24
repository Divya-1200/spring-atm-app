package com.demo.expensetracker.controller;

//import com.demo.ExpenseTracker.Expdao.ExpDao;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.expense.mapper.CityMapper;
import com.demo.expensetracker.model.Expense;


@Controller
@SessionAttributes("exp")
public class HomeController {

//	@Autowired
//	private ExpenseTrackerModel expenseTracker; 
	
	@Autowired
	private CityMapper cityMapper;
	
//	ExpDao dao;

//	@Autowired
//	private ExpenseService expenseService;

	@ModelAttribute("exp")
	   public Expense setUpUserForm() {
	      return new Expense();
	   }
	
	@RequestMapping("/")
	public String home() {
//		ModelAndView model = new ModelAndView("welcome");
		return "welcome";
	}
	/*
	@RequestMapping(value = "one", method = RequestMethod.GET)
    public <T> T listUsers() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
        map.put("three", "four");
        map.put("five", "six");
        map.put("seven", "eight");
        
        return (T) map;
    }
	
	@RequestMapping(value = "two", method = RequestMethod.GET)
    public <T> T createCity() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
//        map.put("three", "four");
//        map.put("five", "six");
//        map.put("seven", "eight");
         String city = "Paris";
         String state = "Tamil Nadu";
         String country = "Europe";
         cityMapper.insertCity(city, state, country);
        
        return (T) map;
    }
	
	@RequestMapping(value = "three", method = RequestMethod.GET)
    public <T> T updateCity() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
//        map.put("three", "four");
//        map.put("five", "six");
//        map.put("seven", "eight");
         String city = "Paris";
         String state = "France";
//         String country = "Europe";
         cityMapper.updateCity(state, city);
        
        return (T) map;
    }
	*/
	
    
	@RequestMapping(value="/login" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterLogin(Expense exp) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		System.out.println("inside login get");
		return "login";
	} 
	
	@RequestMapping(value="/login/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String loginForm(@ModelAttribute("exp")  Expense exp) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		System.out.println("inside login post");
		try {
			Expense output = cityMapper.findByUser(exp.getName(), exp.getPassword());
			if(output != null) {
				return "transactionPage";
			}
			
		}
		catch(Exception e){
			return "error";
		}
		
		return "error";
	}
	
	@GetMapping("/deposit")
	public String depositAmt(@SessionAttribute("exp") Expense exp) {

		System.out.println("Email: " + exp.getName());
		System.out.println("First Name: " + exp.getPassword());

		return "welcome";
	}
	
	@GetMapping("/withdrawal")
	public String withdrawalAmt(@SessionAttribute("exp") Expense exp) {

		System.out.println("Email: " + exp.getName());
		System.out.println("First Name: " + exp.getPassword());

		return "welcome";
	}
	
	@GetMapping("/check/balance")
	public String userInfo(@SessionAttribute("exp") Expense exp) {

		System.out.println("Email: " + exp.getName());
		System.out.println("First Name: " + exp.getPassword());

		return "welcome";
	}
	@RequestMapping(value="/register" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterRegister(Expense exp) {
		
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		System.out.println("inside register get");
		return "register";
	} 
	
	@RequestMapping(value="/register/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String RegisterForm(Expense exp) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		try {
			cityMapper.insertUser(exp.getName(), exp.getPassword(), 0);
			
		}
		catch(Exception e){
			return "error";
		}
		
	
		System.out.println("inside register post");
		return "transactionPage";
	}
	
	/*
	
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public <T> T getCity() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        map.put("city", cityMapper.findByState("CA"));
        
        return (T) map;
    }
	
	@RequestMapping(value = "/city/{state}", method = RequestMethod.GET)
    public <T> T getCity(
    	@PathVariable(value = "state") String state	
    	) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        map.put("city", cityMapper.findByStates(state));
        
        return (T) map;
    }
	
	*/
	
	
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