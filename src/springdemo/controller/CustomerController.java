package springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springdemo.entity.Customer;
import springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		// get customers from DAO
		List<Customer> customers = customerService.getCustomers();
		
		System.out.println(customers);
		
		// add customers to the model
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		System.out.println("1");
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		System.out.println("1");
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int id, Model model) {
		
		customerService.deleteCustomer(id);
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		
		return "redirect:/customer/list";
	}

}
