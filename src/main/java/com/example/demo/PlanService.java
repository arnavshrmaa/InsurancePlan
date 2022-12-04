package com.example.demo;

 import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

 

//******************* Service **********************

public interface PlanService {

	public Plan add(Plan plan);

	public List<Plan> view();
	
	public String delete(long id);

	public boolean update(Plan plan);
	
	//This method is for Generating the Excel and PDF...
	public void exportReport(List<Plan> list, String string,
			                   HttpServletResponse response) throws IOException;

}
