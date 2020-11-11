package com.kg.myapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kg.myapp.service.EmpService;
import com.kg.myapp.vo.EmpVO;

@Controller
//@RequestMapping("/emp") ->하면 매번 value 적을 때 마다 /emp를 생략해도 된다.
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@GetMapping(value="/emp") 
	public String mainPage(Model model) { 
		model.addAttribute("message", "Hello! Welcome to EmpApp."); 
		return "home";  
	}

	@GetMapping(value="/emp/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue="0")int deptId, Model model) {
		if (deptId == 0) {
			model.addAttribute("count", empService.getEmpCount());
		}else {
			model.addAttribute("count", empService.getEmpCount(deptId));
		}
		return "emp/count";
	}
	
	@GetMapping(value="/emp/list")
	public void empList(Model model) {
	model.addAttribute("empList", empService.getEmpList());
	}
	
	@RequestMapping(value="/emp/{employeeId}")
	public String getEmployees(@PathVariable int employeeId, Model model) {
	EmpVO emp = empService.getEmpInfo(employeeId);
	model.addAttribute("emp",emp);
	return "emp/view";
	}
	//@GetMapping(value="/emp/{employeeId}")
	//public String empView(@PathVariable int employeeId, Model model) {
	//	model.addAttribute("emp", empService.getEmpInfo(employeeId));
	//	return "emp/view";
	//}
	
	@GetMapping(value="/emp/deptlist")
	public String empDeptList(int deptId, Model model) {
		// "empList" 위에 empList와 동일하게 하는 이유는 같은 list.jsp 파일에 출력하기 위해
		// 같은 변수명 쓰는 거라고 생각하면 됨
		model.addAttribute("empList", empService.getEmpList(deptId));
		return "emp/list";
	}
	
	@GetMapping(value="/emp/namelist") 
	public String empNameList(String name, Model model) {
		name = "%"+name+"%"; //Repository에서 그냥 name 으로 했을 경우 Controller에서 %를 붙여주어야 한다.
		model.addAttribute("empList", empService.getEmpList(name));
		return "emp/list";
	}
	
	@GetMapping(value="/emp/empidlist")
	public String empIdList(int empId, Model model) {
		model.addAttribute("empList", empService.getEmpIdList(empId));
		return "emp/list";
	}
	
	/*
	 * @Autowired private EmpValidator empValidator;
	 * 
	 * @InitBinder private void initBinder(WebDataBinder binder) {
	 * binder.setValidator(empValidator); }
	 */
	
	@PostMapping(value="/emp/insert")
	public String empInsert(@ModelAttribute("emp") @Valid EmpVO emp, BindingResult result,
			Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
		return "emp/insert";
		}
		if((file!=null) && (!file.isEmpty())) {
			try {
				emp.setEmpPic(file.getBytes());
				emp.setFileSize(file.getSize());
			} catch (IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				redirectAttributes.addFlashAttribute("exception", e);
				throw new RuntimeException();
			}
			//요청을 했다가 끊어야 한다!! -> redirect로 끊어야 한다
		}
		empService.insertEmp(emp);
		redirectAttributes.addFlashAttribute("message", "회원 저장 완료");
		return "redirect:/emp/list";
	}
	
	@GetMapping(value="/emp/insert")
	public String empInsert(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "insert");
		return "emp/insert";
	}
	
	/*
	 * @PostMapping(value="/emp/insert") public String empInsert(EmpVO emp,
	 * RedirectAttributes redirectAttributes) { empService.insertEmp(emp);
	 * redirectAttributes.addFlashAttribute("message", "사원 정보 저장 완료"); return
	 * "redirect:/emp/list"; //Sendredirect 를 할 때 앞에 redirect하고 주소를 작성하면 된다.
	 * //model은 request에 기생한다고 생각하자 //addFlashAttribute -> 세션에 넣어서 전송? //pdf 195page
	 * 
	 * }
	 */
	//filter로 utf-8로 인코딩 할 수 있다 -> web.xml에 작성
	
	@GetMapping(value="/emp/update/{empId}")
	public String empUpdate(@PathVariable int empId, Model model) {
		model.addAttribute("emp", empService.getEmpInfo(empId));
		model.addAttribute("jobList", empService.getAllJobId());
		model.addAttribute("manList", empService.getAllManagerId());
		model.addAttribute("deptList", empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "emp/insert";
	}
	
	@PostMapping(value="/emp/update")
	public String empUpdate(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/emp/"+emp.getEmployeeId();
		//view의 value가 /emp/{employeeId}이기 때문에 뒤에 employeeId를 붙여주기 위해 +emp.getEmployeeId();를 해주어야 한다.		
	}
	
	@GetMapping(value="/emp/delete")
	public String deleteEmp(int empId, Model model) {
	model.addAttribute("emp",empService.getEmpInfo(empId));
	return "emp/delete";
	}
	@PostMapping(value="/emp/delete")
	public String deleteEmp(Model model, int empId) {
	empService.deleteEmp(empId);
	return "redirect:/emp/list";
	}
//	@GetMapping(value="/emp/delete")
//	public String deleteViewEmp(int emp, Model model) {
//		//model.addAttribute("emp", empService.getEmpInfo(emp));
//		//model.addAttribute("count", empService.getManagerCount(employeeId));
//		//model.addAttribute("dcount", empService.getDeptCount(employeeId));
//		model.addAttribute("emp", empService.getEmpInfo(emp));
//		model.addAttribute("count", empService.getUpdateCount(emp));
//		return "emp/delete";		
//	}
	
	//예외 처리 -> RuntimeException, CompileException 두 가지
	@ExceptionHandler(RuntimeException.class)
	public String runtimeException(Exception e, HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("exception", e);
		return "error/runtime";
	}

	@GetMapping("/emp/pic/{empId}")
	public ResponseEntity<byte[]> getPicture(@PathVariable int empId){
		EmpVO emp = empService.getEmpInfo(empId);
		final HttpHeaders headers = new HttpHeaders();
		if(emp.getEmpPic()!=null) {
			headers.setContentType(new MediaType("image","jpg"));
			//headers.setContentType(new MediaType("이미지, 글 등의 형식", "파일 형식(jpa, png, jpeg, pdf 등))
			headers.setContentDispositionFormData("attachment", "프로필 사진");
			headers.setContentLength(emp.getFileSize());
			return new ResponseEntity<byte[]>(emp.getEmpPic(), headers, HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND); 
		}
	}
	
	@PostMapping("/emp/check")
	@ResponseBody
	public String idCheck(int empId) {
		return empService.idCheck(empId)==0 ? "true" : null;
	}
}
	

















