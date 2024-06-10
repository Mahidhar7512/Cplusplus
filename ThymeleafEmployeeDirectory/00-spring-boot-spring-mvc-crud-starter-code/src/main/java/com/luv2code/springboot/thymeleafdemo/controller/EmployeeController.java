package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/list")
    public String listEmployees(Model model){
        //get from db
        List<Employee> employeelist = employeeService.findAll();
        //add to spring model
        model.addAttribute("employees",employeelist);
        return "employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee temployee = new Employee();
        model.addAttribute("employee",temployee);
        return "employees/employee-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model theModel){
        Employee employee = employeeService.findById(id);
        theModel.addAttribute("employee",employee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }
    //Delete
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }

}
