package com.demo.atm.controller;

//import com.demo.ExpenseTracker.Expdao.ExpDao;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.demo.atm.mapper.AtmMapper;
import com.demo.atm.model.Atm;


@Controller
@SessionAttributes("exp")
public class HomeController {

//	@Autowired
//	private ExpenseTrackerModel expenseTracker; 
	
	@Autowired
	private AtmMapper cityMapper;
	
//	ExpDao dao;

//	@Autowired
//	private ExpenseService expenseService;

	@ModelAttribute("exp")
	   public Atm setUpUserForm() {
	      return new Atm();
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
	public String enterLogin(Atm exp) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		System.out.println("inside login get");
		return "login";
	} 
	
	@RequestMapping(value="/login/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String loginForm(@ModelAttribute("exp")  Atm exp, Model model) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		System.out.println("inside login post");
		try {
			Atm output = cityMapper.findByUser(exp.getName(), exp.getPassword());
			if(output != null) {
				return "redirect:/transactionPage";
			}
			
		}
		catch(Exception e){
			model.addAttribute("msg", "Incorrect username and password");
			return "error";
		}
		model.addAttribute("msg", "Incorrect username and password");
		return "error";
	}
	
	@RequestMapping(value="/deposit")
	public String depositPage(@ModelAttribute("exp") @SessionAttribute("exp") Atm exp, Model model) {
		
		if(exp.getName() != null)
			return "deposit";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
		}
	}
	
	
	@RequestMapping(value="/deposit/amt" , method = {RequestMethod.POST,RequestMethod.GET})
	public String depositAmt(@ModelAttribute("exp") @SessionAttribute("exp") Atm exp, Model model) {

		System.out.println("deposit Email: " + exp.getName());
		System.out.println("deposit First Name: " + exp.getPassword());
		System.out.println("deposit amount:" +exp.getAmount());	
		if(exp.getName() != null) {
			try {
				cityMapper.depositBalance(exp.getName(), exp.getAmount());
				return "redirect:/transactionPage";
				
			}
			catch(Exception e){
				model.addAttribute("msg", "Error Occured try again");
				return "error";
				
			}
			
		}
		else {
			model.addAttribute("msg", "Login required");
			return "error";

		}

		
		
	}
	
	@GetMapping("/withdrawal")
	public String withdrawal(@ModelAttribute("exp") @SessionAttribute("exp") Atm exp, Model model) {
		
		if(exp.getName() != null)
			return "withdrawal";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
		}

		
	}
	
	@RequestMapping(value = "/withdraw/amt", method = {RequestMethod.POST,RequestMethod.GET})
	public String withdrawalAmt(@ModelAttribute("exp") @SessionAttribute("exp") Atm exp, Model model) {
		if(exp.getName() != null) {
			int balance = cityMapper.findBalance(exp.getName());
			try {
				if(balance>=exp.getAmount()) {

					cityMapper.withdrawBalance(exp.getName(), exp.getAmount());
					return "redirect:/transactionPage";
				}
				else {
					model.addAttribute("msg", "Insufficient Balance");
					return "error";
				}
			}
			catch(Exception e){
					model.addAttribute("msg", "Error Occured try again");
					return "error";
			}
		}
		else {
			model.addAttribute("msg", "Login Required");
			return "error";
			
		}
	}
		

	@GetMapping("/check/balance")
	public String userInfo(@SessionAttribute("exp") Atm exp, Model model) {

		System.out.println("balance Email: " + exp.getName());
		System.out.println("balance First Name: " + exp.getPassword());
		if(exp.getName() != null) {
			try {
				int balance = cityMapper.findBalance(exp.getName());
				model.addAttribute("balance", balance);
				return "checkBalance";
				
			}
			catch(Exception e) {
				model.addAttribute("msg", "Error occured try again");
				return "error";
			}
			
		}
		else {
			model.addAttribute("msg", "Login Required");
			return "error";
			
		}
		
		
		
		
		
	}
	@RequestMapping(value="/register" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterRegister(Atm exp) {
		
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		System.out.println("inside register get");
		return "register";
	} 
	
	@RequestMapping(value="/register/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String RegisterForm(Atm exp, Model model) {
		System.out.println("Username= "+ exp.getName());
		System.out.println("password= "+ exp.getPassword());
		try {
			cityMapper.insertUser(exp.getName(), exp.getPassword(), 0);
			
		}
		catch(Exception e){
			model.addAttribute("msg", "Username password already exists");
			return "error";
		}
		
	
		System.out.println("inside register post");
		return "redirect:/transactionPage";
//		return "transactionPage";
	}
	
	@GetMapping("/logout")
	public String closeSession(@ModelAttribute("exp") Atm exp, WebRequest request, SessionStatus status) {
		
		status.setComplete();
	    request.removeAttribute("exp", WebRequest.SCOPE_SESSION);
		return "welcome";
	}
	
	@GetMapping("/transactionPage")
	public String transactionPage(@ModelAttribute("exp") @SessionAttribute("exp") Atm exp, Model model ) {
		
		if(exp.getName() != null)
			return "transactionPage";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
			
		}
			
	
		
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