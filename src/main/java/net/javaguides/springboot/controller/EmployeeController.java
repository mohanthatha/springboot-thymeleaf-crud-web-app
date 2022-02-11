package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeservice;
	    @GetMapping
		public String viewHomepage(Model model) {
		model.addAttribute("listEmployees", employeeservice.getAllEmployees());
		return"index";
	}
	    @GetMapping("/showNewEmployeeForm")
	    public String showNewEmployeeForm(Model model) {
	    	Employee employee = new Employee();
	    	model.addAttribute("employee", employee);
	    	return  "new_Employee";
	    }
	    @PostMapping("/saveEmployee")
	    public String saveEmployee(@ModelAttribute("employee") Employee employee){
	    	employeeservice.saveEmployee(employee);
	    	return "redirect:/";
	    }	
	    @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable ( value = "id")long id, Model model) {
	    	Employee employee = employeeservice.getEmployeeById(id);
	    		model.addAttribute("employee",employee);
	    		return "update_employee";
	    	}
}

