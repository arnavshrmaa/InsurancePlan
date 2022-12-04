package com.example.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//************************ Controller ***************************

@RestController
@RequestMapping("/Plan")
public class PlanController {

	@Autowired
	private PlanService service;

	// For Adding
	@PostMapping
	public ResponseEntity<Plan> add(@RequestBody Plan plan) {
		return ResponseEntity.ok().body(service.add(plan));
	}

	// For Fetching
	@GetMapping
	public ResponseEntity<List<Plan>> view() {
		return ResponseEntity.ok().body(service.view());
	}

	// For Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		return ResponseEntity.ok().body(service.delete(id));
	}

	// For Update
	@PutMapping("/")
	public ResponseEntity<Boolean> update(@RequestBody Plan plan) {
		return ResponseEntity.ok().body(service.update(plan));
	}

	// For Excel Exporting
	@GetMapping("/Excel")
	public void generateAndDownloadExcelReport(HttpServletRequest rq, HttpServletResponse rs) throws IOException {

		rs.setContentType("application/vnd.ms-excel");
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dt.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = users_" + currentDateTime + ".xlsx";
		rs.setHeader(headerKey, headerValue);
		List<Plan> list = service.view();
		service.exportReport(list, "excel", rs);
	}

	@GetMapping("/Pdf")
	public void generateAndDownloadPdfReport(HttpServletRequest rq, HttpServletResponse rs) throws IOException {

		rs.setContentType("application/pdf");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = df.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";

		rs.setHeader(headerKey, headerValue);

		List<Plan> list = service.view();
		service.exportReport(list, "pdf", rs);

	}
}
