package com.cosmostaker.emswebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cosmostaker.emswebapp.entity.Employee;
import com.cosmostaker.emswebapp.service.IEmployeeService;

@Controller
public class EmployeeController {
    
    @Autowired
    private IEmployeeService employeeService;


    // Display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";

        // return findPaginated(1, model);
    }


    // Mapping for add employee form
    @GetMapping("/showAddEmployeeForm")
    public String showEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();

        model.addAttribute("employee", employee);
        return "addEmployeeForm";
    }


    // Save or Update employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);

        return "redirect:/";
    }


    // Mapping for update employee form
    @GetMapping("/showUpdateEmployeeForm{id}")
    public String showUpdateEmployeeForm(@PathVariable(value = "id") int id, Model model) {
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);

        // send over to our form
        return "updateEmployeeForm";
    }


    // Delete employee
    @GetMapping("/deleteEmployee{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id) {
        // call delete employee method
        this.employeeService.deleteEmployeeById(id);

        return "redirect:/";
    }


    // Mapping for "search"
    @GetMapping("/search")
	public String search(@RequestParam("employeeName") String theName, Model theModel) {
		// add to the spring model
		theModel.addAttribute("listEmployees", employeeService.searchBy(theName));

        // add searched name to the spring model so we can display it in the search box
        theModel.addAttribute("searchedName", theName);
       
		// send to home page
		return "index";
	}
}
