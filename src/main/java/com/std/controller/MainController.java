package com.std.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.std.Dto.StudentDto;
import com.std.Entity.StudentEntity;
import com.std.Service.StudentService;
import com.std.repository.StudentRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/")
	public String homePage() {
		return "homepage";
	}
	
	@GetMapping("/insertdata")
	public String RegistrationPage(Model model) {
		StudentDto studentDto = new StudentDto();
		model.addAttribute("std", studentDto);
		return "registerform";
	}
	
	@PostMapping("/insertdata")
	public String postRegistrationForm(@ModelAttribute("std") 
	StudentDto studentDto, Model model) {
		this.studentService.saveData(studentDto);
		return "homepage";
	}
	
	@GetMapping("/showpage")
	public String showpage(Model model) {
		List<StudentEntity> listShow = studentService.listShow();
		model.addAttribute("std", listShow);
		return "viewpage";
	}
	
	@GetMapping("/delete/{id}")
	public String DeletePage(@PathVariable("id") long id) {
		this.studentService.DeleteData(id);
		return "redirect:/showpage";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") long id,Model model) {
		StudentEntity studentEntity = this.studentService.updateData(id);
		model.addAttribute("std", studentEntity);
		return "updatepage";
	}
	
	@PostMapping("/updatedata")
	public String Updatex(@ModelAttribute("std") StudentDto studentDto, Model model, HttpServletRequest request) {
		StudentEntity studentEntity = this.studentService.updateData(Long.valueOf(String.valueOf(request.getParameter("idyourname"))));
		
		studentEntity.setAddress(studentDto.getAddress());
		studentEntity.setRollno(studentDto.getRollno());
		studentEntity.setStdname(studentDto.getStdname());
		
		studentRepository.save(studentEntity);
		
		return "redirect:/showpage";
	}
	
}
