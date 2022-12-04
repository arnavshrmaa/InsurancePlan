package com.example.demo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//********************* Service Implementation ******************

@Service
public class ServiceImpl implements PlanService {

	@Autowired
	private PlanRepo repo;

	// For Adding
	@Override
	public Plan add(Plan plan) {
		repo.save(plan);
		return plan;
	}

	// For Fetching
	@Override
	public List<Plan> view() {
		return repo.findAll();
	}

	// For Deleting
	@Override
	public String delete(long id) {
		repo.deleteById(id);
		return "Sucessfully Deleted....!!";
	}

	// For Updating
	@Override
	public boolean update(Plan plan) {
		Optional<Plan> p = repo.findById(plan.getId());

		if (p.isPresent()) {
			Plan pp = p.get();
			pp.setPlanCategory(plan.getPlanCategory());
			pp.setPlanName(plan.getPlanName());
			pp.setPlanStartDate(plan.getPlanStartDate());
			pp.setPlanValidity(plan.getPlanValidity());
			repo.save(plan);
			return true;
		}

		return false;
	}

//********************* This is for Generating the Excel and PDF format ******************

	// For Exporting Service
	@Override
	public void exportReport(List<Plan> plans, String reportType, HttpServletResponse rs) throws IOException {
		if ("PDF".equalsIgnoreCase(reportType)) {
			PdfGenerator pdf = new PdfGenerator();
			pdf.export(rs, plans);
		} else {
			ExcelSheetGenerator excelSheet = new ExcelSheetGenerator();
			excelSheet.export(rs, plans);
		}
	}

}
