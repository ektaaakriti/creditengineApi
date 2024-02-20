package com.creditengine.api.response;

public class PredictionList {
	String applicant_id;
	public String getApplicant_id() {
		return applicant_id;
	}
	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}
	String eligible_loan_amount;
	String stress_one;
	String stress_two;
	String stress_three;
    String stress_four;
	String stress_five;
	String dataFetched;
	String applcnt_default_prob_number;
	String predicted_score;
	String estimated_income;
	String submitted;
	public String getEligible_loan_amount() {
		return eligible_loan_amount;
	}
	public void setEligible_loan_amount(String eligible_loan_amount) {
		this.eligible_loan_amount = eligible_loan_amount;
	}
	public String getStress_one() {
		return stress_one;
	}
	public void setStress_one(String stress_one) {
		this.stress_one = stress_one;
	}
	public String getStress_two() {
		return stress_two;
	}
	public void setStress_two(String stress_two) {
		this.stress_two = stress_two;
	}
	public String getStress_three() {
		return stress_three;
	}
	public void setStress_three(String stress_three) {
		this.stress_three = stress_three;
	}
	public String getStress_four() {
		return stress_four;
	}
	public void setStress_four(String stress_four) {
		this.stress_four = stress_four;
	}
	public String getStress_five() {
		return stress_five;
	}
	public void setStress_five(String stress_five) {
		this.stress_five = stress_five;
	}
	public String getDataFetched() {
		return dataFetched;
	}
	public void setDataFetched(String dataFetched) {
		this.dataFetched = dataFetched;
	}
	public String getApplcnt_default_prob_number() {
		return applcnt_default_prob_number;
	}
	public void setApplcnt_default_prob_number(String applcnt_default_prob_number) {
		this.applcnt_default_prob_number = applcnt_default_prob_number;
	}
	public String getPredicted_score() {
		return predicted_score;
	}
	public void setPredicted_score(String predicted_score) {
		this.predicted_score = predicted_score;
	}
	public String getEstimated_income() {
		return estimated_income;
	}
	public void setEstimated_income(String estimated_income) {
		this.estimated_income = estimated_income;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
}
