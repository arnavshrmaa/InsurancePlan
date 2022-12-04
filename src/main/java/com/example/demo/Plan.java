package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String PlanName;
	private String PlanCategory;
	private String PlanStartDate;
	private String PlanValidity;
	
	
	// Getter & Setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlanName() {
		return PlanName;
	}
	public void setPlanName(String planName) {
		PlanName = planName;
	}
	public String getPlanCategory() {
		return PlanCategory;
	}
	public void setPlanCategory(String planCategory) {
		PlanCategory = planCategory;
	}
	public String getPlanStartDate() {
		return PlanStartDate;
	}
	public void setPlanStartDate(String planStartDate) {
		PlanStartDate = planStartDate;
	}
	public String getPlanValidity() {
		return PlanValidity;
	}
	public void setPlanValidity(String planValidity) {
		PlanValidity = planValidity;
	}
	
	//Parameterized Constructor
	public Plan(long id, String planName, String planCategory, String planStartDate, String planValidity) {
		super();
		this.id = id;
		PlanName = planName;
		PlanCategory = planCategory;
		PlanStartDate = planStartDate;
		PlanValidity = planValidity;
	}
	
	
	//Default Constructor
	public Plan() {
		super();
 	}
	
	// To String Method
	@Override
	public String toString() {
		return "Plan [id=" + id + ", PlanName=" + PlanName + ", PlanCategory=" + PlanCategory + ", PlanStartDate="
				+ PlanStartDate + ", PlanValidity=" + PlanValidity + "]";
		}
	
	
	

}
