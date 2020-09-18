package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.beans.AdminBean;
import com.example.mapper.ParkingMapper;
import com.example.validator.ExampleValidator;


@Controller
public class ExampleController {

	@Autowired
	AdminBean adminBean;
	@Autowired
	ParkingMapper parkingMapper;
	
	
	@GetMapping("/")
//	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	@GetMapping("home")
	public String home1() {
		return "home";
	}
	
	@RequestMapping(value = "/parking", method = RequestMethod.GET)
	public String selectParking() {
		parkingMapper.selectAllParkings().stream().forEach(a -> System.out.println(a));
		return "logS";
	}
	
	
	@GetMapping("/member/{id}")
	public String member(@PathVariable String id) {
		System.out.println(id);
		return "home";
	}
	
	@PostMapping("/adminLogin")
	public String adminLogin(@Valid AdminBean adminbean, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			model.addAttribute("message", message);
			return "loginFail";
			
		}
		adminBean.setLoggedIn(true);
		model.addAttribute("admin_id", adminbean.getId());
		return "loginSuccess";
	}
	
	@GetMapping("/admin/memberId")
	public String memberView() {
		return "member";
	}
	
	
	@InitBinder   //유효성 검사
	private void initBinding(WebDataBinder webDataBinder) {
		ExampleValidator exampleValidator = new ExampleValidator();
		webDataBinder.addValidators(exampleValidator);
	}

}
