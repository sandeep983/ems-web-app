package com.cosmostaker.emswebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        // model.addAttribute("listEmployees", employeeService.getAllEmployees());
        // return "index";

        return findPaginated(1, model);
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
    @GetMapping("/updateEmployee{id}")
    public String showUpdateEmployeeForm(@PathVariable(value = "id") Long id, Model model) {
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);

        // send over to our form
        return "updateEmployeeForm";
    }



    // Delete employee
    @GetMapping("/deleteEmployee{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        // call delete employee method
        this.employeeService.deleteEmployeeById(id);

        return "redirect:/";
    }



    // Mapping for "search"
    @GetMapping("/search")
	public String search(@RequestParam("name") String theName, Model model) {
		// add to the spring model
		model.addAttribute("listEmployees", employeeService.searchBy(theName));

        // add searched name to the spring model so we can display it in the search box
        model.addAttribute("searchedName", theName);
       
		// send to home page
        return "index";
	}



    // Pagination
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);

        return "index";
    }
}
