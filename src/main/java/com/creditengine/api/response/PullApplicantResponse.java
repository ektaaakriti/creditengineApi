package com.creditengine.api.response;

import java.util.List;

public class PullApplicantResponse {
	List<ApplicantPredictionList> applicantList;
public List<ApplicantPredictionList> getApplicantList() {
		return applicantList;
	}
	public void setApplicantList(List<ApplicantPredictionList> applicantList) {
		this.applicantList = applicantList;
	}
public PullApplicantResponse(List<ApplicantPredictionList> applicantList, String message, String status) {
		super();
		this.applicantList = applicantList;
		Message = message;
		Status = status;
	}
ApplicantPredictionList applicant;
String Message;
String Status;
public ApplicantPredictionList getApplicant() {
	return applicant;
}
public void setApplicant(ApplicantPredictionList applicant) {
	this.applicant = applicant;
}
public String getMessage() {
	return Message;
}
public void setMessage(String message) {
	Message = message;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public PullApplicantResponse(ApplicantPredictionList applicant, String message, String status) {
	super();
	this.applicant = applicant;
	Message = message;
	Status = status;
}

}
