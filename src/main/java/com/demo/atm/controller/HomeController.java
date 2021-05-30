package com.demo.atm.controller;


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
@SessionAttributes("atm")
public class HomeController {

 
	
	@Autowired
	private AtmMapper atmMapper;
	


	@ModelAttribute("atm")
	   public Atm setUpUserForm() {
	      return new Atm();
	   }
	
	@RequestMapping("/")
	public String home() {

		return "welcome";
	}

	@RequestMapping(value="/register" , method = {RequestMethod.POST,RequestMethod.GET})
	public String enterRegister(Atm atm) {

		return "register";
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
	public String withdrawalAmt(@ModelAttribute("atm") @SessionAttribute("atm") Atm atm, Model model) {
		if(atm.getName() != null) {
			int balance = atmMapper.findBalance(atm.getName());
			try {
				if(balance>=atm.getAmount()) {

					atmMapper.withdrawBalance(atm.getName(), atm.getAmount());
					return "redirect:/transactionPage";
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

	@GetMapping("/logout")
	public String closeSession(@ModelAttribute("atm") Atm atm, WebRequest request, SessionStatus status) {
		
		status.setComplete();
	    request.removeAttribute("atm", WebRequest.SCOPE_SESSION);
		return "redirect:/";
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
	
}