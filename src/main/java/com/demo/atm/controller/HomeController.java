package com.demo.atm.controller;


import java.util.ArrayList;
import java.util.List;

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
import com.demo.atm.model.Admin;
import com.demo.atm.model.Atm;
import com.demo.atm.model.Currency;


@Controller
@SessionAttributes({"atm","admin"})
public class HomeController {

	
    public static final int[] currValue = { 2000,500, 200, 100};
    
    public  int[] count = { 0, 0, 0, 0};
	
	@Autowired
	private AtmMapper atmMapper;
	
	@ModelAttribute("atm")
	   public Atm setUpUserForm() {
	      return new Atm();
	}
	
	@ModelAttribute("admin")
	   public Admin setUpAdminForm() {
	      return new Admin();
	 }
	
	@RequestMapping("/")
	public String home() {

		return "welcome";
	}
	
	/*    USER PAGE */

	@RequestMapping(value="/register" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterRegister(Atm atm) {

		return "register";
	}
	
	@RequestMapping(value="/admin" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterAdmin(Admin admin) {
		System.out.println("here");
		return "admin";
	}
	
	@RequestMapping(value="/register/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String RegisterForm(@ModelAttribute("atm")  Atm atm, Model model) {

		try {
			atmMapper.insertUser(atm.getName(), atm.getPassword(), 0);
			
		}
		catch(Exception e){
			model.addAttribute("msg", "Username password already exists");
			return "error";
		}
		
		return "redirect:/transactionPage";

	}
    
	@RequestMapping(value="/login" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterLogin(Atm atm) {

		return "login";
	} 
	
	@RequestMapping(value="/login/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String loginForm(@ModelAttribute("atm")  Atm atm, Model model) {

		try {
			Atm output = atmMapper.findByUser(atm.getName(), atm.getPassword());
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
	public String depositPage(@ModelAttribute("atm") @SessionAttribute("atm") Atm atm, Model model) {
		
		if(atm.getName() != null)
			return "deposit";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
		}
	}
	
	
	@RequestMapping(value="/deposit/amt" , method = {RequestMethod.POST,RequestMethod.GET})
	public String depositAmt(@ModelAttribute("atm") @SessionAttribute("atm") Atm atm, Model model) {

		if(atm.getName() != null) {
			try {
				atmMapper.depositBalance(atm.getName(), atm.getAmount());
				atmMapper.adminDepositBalance(atm.getAmount());
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
	public String withdrawal(@ModelAttribute("atm") @SessionAttribute("atm") Atm atm, Model model) {
		
		if(atm.getName() != null)
			return "withdrawal";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/withdraw/amt", method = {RequestMethod.POST,RequestMethod.GET})
	public String withdrawalAmt(@ModelAttribute("atm") @SessionAttribute("atm") Atm atm ,Model model) {
		if(atm.getName() != null) {
			int balance = atmMapper.findBalance(atm.getName());
			
			int atmbalance = atmMapper.findAtmBalance();
			try {
				if(atmbalance>=atm.getAmount()) {
					System.out.println("requested amount "+atm.getAmount());
					if(balance>=atm.getAmount()) {
						
						List<Integer> currencyCount = new ArrayList<>();
//						public static final int[] currValue = { 2000, 1000, 500, 100};
						/* Currency calculation */
						Currency output = atmMapper.checkCurrency();

						int requestedAmount = atm.getAmount();
	
						currencyCount.add(output.getTwoThousand());
						currencyCount.add(output.getFiveHundred());
						currencyCount.add(output.getTwoHundred());
						currencyCount.add(output.getOneHundred());
							
						for (int i = 0; i < currencyCount.size(); i++) {

			                if (currValue[i] <= requestedAmount) {
			                	
			                    int noteCount = requestedAmount / currValue[i];

			                    if(currencyCount.get(i)>0){

			                        count[i] = noteCount>=currencyCount.get(i)?currencyCount.get(i):noteCount;
			                        
			                        int temp = noteCount>=currencyCount.get(i)?0:currencyCount.get(i)- noteCount;

			                        currencyCount.set(i,temp);
			                        //Need to update in db
			                        
			                        System.out.println("notes = "+count[i]+" of "+currValue[i]+" rupees");
			                        
			                        atmbalance=atmbalance-(count[i]*currValue[i]);
			                        //Need to update in db
			                       
			                        requestedAmount = requestedAmount -(count[i]*currValue[i]);

			                    }                
			                }
			            }
						
						
						
						
						atmMapper.withdrawBalance(atm.getName(), atm.getAmount());
						atmMapper.adminWithdrawBalance(atm.getAmount());
						return "redirect:/transactionPage";
					}
					else {
						model.addAttribute("msg", "Insufficient Balance");
						return "errorwithdraw";
					}
					
				}
				else {
					model.addAttribute("msg", "Insufficient Balance in ATM");
					return "errorwithdraw";
					
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
	public String userInfo(@SessionAttribute("atm") Atm atm, Model model) {

		if(atm.getName() != null) {
			try {
				int balance = atmMapper.findBalance(atm.getName());
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
	
	@GetMapping("/transactionPage")
	public String transactionPage(@ModelAttribute("atm") @SessionAttribute("atm") Atm atm, Model model ) {
		
		if(atm.getName() != null)
			return "transactionPage";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
			
		}
			
	}

	@GetMapping("/logout")
	public String closeSession(@ModelAttribute("atm") Atm atm, WebRequest request, SessionStatus status) {
		
		status.setComplete();
	    request.removeAttribute("atm", WebRequest.SCOPE_SESSION);
		return "redirect:/";
	}
	
	/*    ADMIN PAGE */
	
	@RequestMapping(value="admin/form" , method = {RequestMethod.POST, RequestMethod.GET})
	public String adminLoginForm(@ModelAttribute("admin")  Admin admin, Model model) {

		try {
			Admin output = atmMapper.findByAdmin(admin.getName(), admin.getPassword());
			
			if(output != null) {
//				System.out.println(admin.getName());
				return "redirect:/admin/transactionPage";
			}
			
		}
		catch(Exception e){
			model.addAttribute("msg", "Incorrect username and password");
			return "error";
		}
		model.addAttribute("msg", "Incorrect username and password");
		return "error";
	}
	
	@GetMapping("/admin/transactionPage")
	public String AdminTransactionPage(@ModelAttribute("admin") @SessionAttribute("admin") Admin admin, Model model ) {
		
//		System.out.println(admin.getName());
		if(admin.getName() != null)
			return "adminTansactionPage";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
			
		}
			
	}
	
	@RequestMapping(value="/admin/deposit")
	public String adminDepositPage(@ModelAttribute("admin") @SessionAttribute("admin") Admin admin, Model model) {
		
		if(admin.getName() != null)
			return "adminDeposit";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
		}
	}
	
	
	@RequestMapping(value="/admin/deposit/amt" , method = {RequestMethod.POST,RequestMethod.GET})
	public String adminDepositAmt(@ModelAttribute("admin") @SessionAttribute("admin") Admin admin, Model model) {

		if(admin.getName() != null) {
			try {
//				System.out.println(admin.getAmount());
				atmMapper.adminDepositBalance(admin.getAmount());
				return "redirect:/admin/transactionPage";
				
			}
			catch(Exception e){
				System.out.println(e);
				model.addAttribute("msg", "Error Occured try again");
				return "error";
				
			}
			
		}
		else {
			model.addAttribute("msg", "Login required");
			return "error";

		}
	}
	
	@GetMapping("admin/withdrawal")
	public String adminWithdrawal(@ModelAttribute("admin") @SessionAttribute("admin") Admin admin, Model model) {
		
		if(admin.getName() != null)
			return "adminWithdrawal";
		else {
			model.addAttribute("msg", "Login required");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "admin/withdraw/amt", method = {RequestMethod.POST,RequestMethod.GET})
	public String adminWithdrawalAmt(@ModelAttribute("admin") @SessionAttribute("admin") Admin admin, Model model) {
		if(admin.getName() != null) {
			int balance = atmMapper.findAtmBalance();
//			System.out.println(balance);
			try {
				if(balance>=admin.getAmount()) {

					atmMapper.adminWithdrawBalance(admin.getAmount());
					return "redirect:/admin/transactionPage";
				}
				else {
					model.addAttribute("msg", "Insufficient Balance");
					return "errorwithdraw";
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
		

	@GetMapping("admin/check/balance")
	public String adminBalance(@SessionAttribute("admin") Admin admin, Model model) {

		if(admin.getName() != null) {
			try {
//				System.out.println("inside");
				int balance = atmMapper.findAtmBalance();
//				System.out.println(balance);
				model.addAttribute("balance", balance);
				return "adminCheckBalance";
				
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

	
	@GetMapping("admin/logout")
	public String adminCloseSession(@ModelAttribute("admin") Admin admin, WebRequest request, SessionStatus status) {
		
		status.setComplete();
	    request.removeAttribute("admin", WebRequest.SCOPE_SESSION);
		return "redirect:/";
	}
	
	
	
	
}