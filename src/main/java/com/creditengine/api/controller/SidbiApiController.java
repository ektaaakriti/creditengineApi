package com.creditengine.api.controller;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.creditengine.api.EncryptionDecryptionClass;
import com.creditengine.api.model.Applicant;
import com.creditengine.api.model.Uploadapplicants;
import com.creditengine.api.model.User;
import com.creditengine.api.payload.ApplicantPayload;
import com.creditengine.api.payload.Applicant_idPayload;
import com.creditengine.api.payload.FileNamePayload;
import com.creditengine.api.repo.ApplicantRepository;
import com.creditengine.api.repo.UploadApplicantsRepo;
import com.creditengine.api.response.GeneralResponse;
import com.creditengine.api.response.PredictionResponse;
import com.creditengine.api.response.PullApplicantResponse;
import com.creditengine.api.response.PredictionList;
import com.creditengine.api.response.ApplicantPredictionList;
@RestController
@CrossOrigin()
@RequestMapping("/borrower/SidbiApi")
public class SidbiApiController {
	EncryptionDecryptionClass encdec=new EncryptionDecryptionClass();
	private final Logger LOGGER = LoggerFactory.getLogger(SidbiApiController.class);
	@Autowired
	ApplicantRepository appRepo;
	@Autowired
	UploadApplicantsRepo uploadRepo;
	@RequestMapping(value = { "/Push Applicant" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<GeneralResponse> push_applicant(@RequestBody ApplicantPayload appPayload) {
		LOGGER.info("add new applicant api has been called !!! Start Of Method add new applicant");
		
		HttpStatus httpstatus=null;
		String response="";
		String status=null;
		User users=null;
		try {
			Applicant app=new Applicant();
	                                     
			 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			if(!(appPayload.getVehicle_no()==null)) {
				app.setVehicle_no(encdec.decryptnew(appPayload.getVehicle_no()));
			}
			if(!(appPayload.getCompany_name()==null)) {
				app.setCompany_name(encdec.decryptnew(appPayload.getCompany_name()));
			}
			if(!(appPayload.getApplicant_firstname()==null)) {
				app.setApplicant_firstname(encdec.decryptnew(appPayload.getApplicant_firstname()));
			}
			if(!(appPayload.getApplicant_date_of_birth()==null)) {
				String applicant=encdec.decryptnew(appPayload.getApplicant_date_of_birth()) ;
				 app.setApplicant_date_of_birth(sdf1.parse(applicant));
			}
			
			if(!(appPayload.getAge()==null)) {
				 app.setAge(Integer.parseInt(encdec.decryptnew(appPayload.getAge())));}
			if(!(appPayload.getMaritalstatus()==null)) {
				 app.setMaritalstatus(encdec.decryptnew(appPayload.getMaritalstatus()));}
			
			if(!(appPayload.getNominee_name()==null))
				{ app.setNominee_name(encdec.decryptnew(appPayload.getNominee_name()));}
			if(!(appPayload.getNominee_dob()==null))
				{String dob=encdec.decryptnew(appPayload.getNominee_dob());
				 app.setNominee_dob(sdf1.parse(dob));
				}
			if(!(appPayload.getNominee_age()==null))
			{ app.setNominee_age(Integer.parseInt(encdec.decryptnew(appPayload.getNominee_age())));}				
				if(!(appPayload.getNominee_relation()==null))  {
					app.setNominee_relation(encdec.decryptnew(appPayload.getNominee_relation()));
				}
				if(!(appPayload.getSpouse_name()==null))
				{app.setSpouse_name(encdec.decryptnew(appPayload.getSpouse_name()));}
				if(!(appPayload.getApplicant_father_firstname()==null))
					app.setApplicant_father_firstname(encdec.decryptnew(appPayload.getApplicant_father_firstname()));
				if(!(appPayload.getReligion()==null))
				{app.setReligion(encdec.decryptnew(appPayload.getReligion()));}
			if(!(appPayload.getApplicant_qualification()==null)) {
				app.setApplicant_qualification(encdec.decryptnew(appPayload.getApplicant_qualification()));}
			if(!(appPayload.getApplicant_employment_type()==null))	{
			app.setApplicant_employment_type(encdec.decryptnew(appPayload.getApplicant_employment_type()));}
			if(!(appPayload.getApplicant_address_line_1()==null)) {
			 app.setApplicant_address_line_1(encdec.decryptnew(appPayload.getApplicant_address_line_1()));}
			if(!(appPayload.getApplicant_city_name()==null)) {
				 app.setApplicant_city_name(encdec.decryptnew(appPayload.getApplicant_city_name()));}
			if(!(appPayload.getApplicant_pin()==null)) {
				 app.setApplicant_PIN(Long.parseLong(encdec.decryptnew(appPayload.getApplicant_pin())));}
			if(!(appPayload.getApplicant_mobile_no()==null)) {
				 app.setApplicant_mobile_no((encdec.decryptnew(appPayload.getApplicant_mobile_no())));}
			if(!(appPayload.getNo_of_family_member()==null)) {
				 app.setNo_of_family_member(Integer.parseInt(encdec.decryptnew(appPayload.getNo_of_family_member())));}
			if(!(appPayload.getNo_of_earning_member()==null)) {
				app.setNo_of_earning_member(Integer.parseInt(encdec.decryptnew(appPayload.getNo_of_earning_member())));}
			if(!(appPayload.getHouse_type()==null)) {
				app.setHouse_type(encdec.decryptnew(appPayload.getHouse_type()));}
			if(!(appPayload.getRation_Card()==null)) {	
			app.setRation_card(encdec.decryptnew(appPayload.getRation_Card()));}
			if(!(appPayload.getMedical_insurance()==null)) {
			app.setMedical_insurance(encdec.decryptnew(appPayload.getMedical_insurance()));}
			if(!(appPayload.getCurrent_loan_outstanding_principal()==null)) {
				 app.setCurrent_loan_outstanding_principal(Float.parseFloat(encdec.decryptnew(appPayload.getCurrent_loan_outstanding_principal())));}
			if(!(appPayload.getCurrent_loan_outstanding_Stringerest()==null)) {	
			 app.setCurrent_loan_outstanding_interest(Float.parseFloat(encdec.decryptnew(appPayload.getCurrent_loan_outstanding_Stringerest())));}
			if(!(appPayload.getApplicant_income()==null))	{
			app.setApplicant_income((encdec.decryptnew(appPayload.getApplicant_income())));}
			if(!(appPayload.getIncome_from_other_sources()==null)) {
				app.setIncome_from_other_sources(Float.parseFloat(encdec.decryptnew(appPayload.getIncome_from_other_sources())));}
			if(!(appPayload.getFood_expenses()==null))	{
			app.setFood_expenses(Float.parseFloat(encdec.decryptnew(appPayload.getFood_expenses())));}
			if(!(appPayload.getHouserent()==null)) {
				 app.setHouserent((encdec.decryptnew(appPayload.getHouserent())));}
			if(!(appPayload.getHouse_renovation_expenses()==null)) {
			 app.setHouse_renovation_expenses(Float.parseFloat(encdec.decryptnew(appPayload.getHouse_renovation_expenses())));}
			if(!(appPayload.getTotal_monthly_bill_payment()==null)) {
				 app.setTotal_monthly_bill_payment(Float.parseFloat(encdec.decryptnew(appPayload.getTotal_monthly_bill_payment())));}
			if(!(appPayload.getApplicant_expense_monthly()==null)) {
			app.setApplicant_expense_monthly((encdec.decryptnew(appPayload.getApplicant_expense_monthly())));}
			
			if(!(appPayload.getCreated_by()==null)) {
				app.setCreated_by(encdec.decryptnew(appPayload.getCreated_by()));
			}
			LocalDate moddt=LocalDate.now();
			app.setDataentdt(moddt);
			appRepo.save(app);
			response="data has been submitted successfully";
				System.out.println(response);
				
			
			status="true";
			httpstatus=HttpStatus.OK;
			}
					
		catch (Exception e) {
			LOGGER.error("Error While adding applicant" + e.getMessage());
			response="Error While adding applicant" + e.getMessage();
			status="false";
			httpstatus=HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpstatus).body(new GeneralResponse(encdec.encryptnew(response),encdec.encryptnew(status)));
	}
	@RequestMapping(value = { "/Get Prediction" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<PredictionResponse> getPrediction(@RequestBody ApplicantPayload appPayload) {
		LOGGER.info(" get prediction of new applicant api has been called !!! Start Of Method get prediction of new applicant");
		
		HttpStatus httpstatus=null;
		String response="";
		String status=null;
		User users=null;
		PredictionList pre=new PredictionList();
		Applicant Prediction=new Applicant();
		try {
			Applicant app=new Applicant();
	                                     
			 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			if(!(appPayload.getVehicle_no()==null)) {
				app.setVehicle_no(encdec.decryptnew(appPayload.getVehicle_no()));
			}
			if(!(appPayload.getCompany_name()==null)) {
				app.setCompany_name(encdec.decryptnew(appPayload.getCompany_name()));
			}
			if(!(appPayload.getApplicant_firstname()==null)) {
				app.setApplicant_firstname(encdec.decryptnew(appPayload.getApplicant_firstname()));
			}
			if(!(appPayload.getApplicant_date_of_birth()==null)) {
				String applicant=encdec.decryptnew(appPayload.getApplicant_date_of_birth()) ;
				 app.setApplicant_date_of_birth(sdf1.parse(applicant));
			}
			
			if(!(appPayload.getAge()==null)) {
				 app.setAge(Integer.parseInt(encdec.decryptnew(appPayload.getAge())));}
			if(!(appPayload.getMaritalstatus()==null)) {
				 app.setMaritalstatus(encdec.decryptnew(appPayload.getMaritalstatus()));}
			
			if(!(appPayload.getNominee_name()==null))
				{ app.setNominee_name(encdec.decryptnew(appPayload.getNominee_name()));}
			if(!(appPayload.getNominee_dob()==null))
				{String dob=encdec.decryptnew(appPayload.getNominee_dob());
				 app.setNominee_dob(sdf1.parse(dob));
				}
			if(!(appPayload.getNominee_age()==null))
			{ app.setNominee_age(Integer.parseInt(encdec.decryptnew(appPayload.getNominee_age())));}				
				if(!(appPayload.getNominee_relation()==null )) {
					app.setNominee_relation(encdec.decryptnew(appPayload.getNominee_relation()));
				}
				if(!(appPayload.getSpouse_name()==null))
				{app.setSpouse_name(encdec.decryptnew(appPayload.getSpouse_name()));}
				if(!(appPayload.getApplicant_father_firstname()==null))
					app.setApplicant_father_firstname(encdec.decryptnew(appPayload.getApplicant_father_firstname()));
				if(!(appPayload.getReligion()==null))
				{app.setReligion(encdec.decryptnew(appPayload.getReligion()));}
			if(!(appPayload.getApplicant_qualification()==null)) {
				app.setApplicant_qualification(encdec.decryptnew(appPayload.getApplicant_qualification()));}
			if(!(appPayload.getApplicant_employment_type()==null))	{
			app.setApplicant_employment_type(encdec.decryptnew(appPayload.getApplicant_employment_type()));}
			if(!(appPayload.getApplicant_address_line_1()==null)) {
			 app.setApplicant_address_line_1(encdec.decryptnew(appPayload.getApplicant_address_line_1()));}
			if(!(appPayload.getApplicant_city_name()==null)) {
				 app.setApplicant_city_name(encdec.decryptnew(appPayload.getApplicant_city_name()));}
			if(!(appPayload.getApplicant_pin()==null)) {
				 app.setApplicant_PIN(Long.parseLong(encdec.decryptnew(appPayload.getApplicant_pin())));}
			if(!(appPayload.getApplicant_mobile_no()==null)) {
				 app.setApplicant_mobile_no((encdec.decryptnew(appPayload.getApplicant_mobile_no())));}
			if(!(appPayload.getNo_of_family_member()==null)) {
				 app.setNo_of_family_member(Integer.parseInt(encdec.decryptnew(appPayload.getNo_of_family_member())));}
			if(!(appPayload.getNo_of_earning_member()==null)) {
				app.setNo_of_earning_member(Integer.parseInt(encdec.decryptnew(appPayload.getNo_of_earning_member())));}
			if(!(appPayload.getHouse_type()==null)) {
				app.setHouse_type(encdec.decryptnew(appPayload.getHouse_type()));}
			if(!(appPayload.getRation_Card()==null)) {	
			app.setRation_card(encdec.decryptnew(appPayload.getRation_Card()));}
			if(!(appPayload.getMedical_insurance()==null)) {
			app.setMedical_insurance(encdec.decryptnew(appPayload.getMedical_insurance()));}
			if(!(appPayload.getCurrent_loan_outstanding_principal()==null)) {
				 app.setCurrent_loan_outstanding_principal(Float.parseFloat(encdec.decryptnew(appPayload.getCurrent_loan_outstanding_principal())));}
			if(!(appPayload.getCurrent_loan_outstanding_Stringerest()==null)) {	
			 app.setCurrent_loan_outstanding_interest(Float.parseFloat(encdec.decryptnew(appPayload.getCurrent_loan_outstanding_Stringerest())));}
			if(!(appPayload.getApplicant_income()==null))	{
			app.setApplicant_income((encdec.decryptnew(appPayload.getApplicant_income())));}
			if(!(appPayload.getIncome_from_other_sources()==null)) {
				app.setIncome_from_other_sources(Float.parseFloat(encdec.decryptnew(appPayload.getIncome_from_other_sources())));}
			if(!(appPayload.getFood_expenses()==null))	{
			app.setFood_expenses(Float.parseFloat(encdec.decryptnew(appPayload.getFood_expenses())));}
			if(!(appPayload.getHouserent()==null)) {
				 app.setHouserent((encdec.decryptnew(appPayload.getHouserent())));}
			if(!(appPayload.getHouse_renovation_expenses()==null)) {
			 app.setHouse_renovation_expenses(Float.parseFloat(encdec.decryptnew(appPayload.getHouse_renovation_expenses())));}
			if(!(appPayload.getTotal_monthly_bill_payment()==null)) {
				 app.setTotal_monthly_bill_payment(Float.parseFloat(encdec.decryptnew(appPayload.getTotal_monthly_bill_payment())));}
			if(!(appPayload.getApplicant_expense_monthly()==null)) {
			app.setApplicant_expense_monthly((encdec.decryptnew(appPayload.getApplicant_expense_monthly())));}
			
			if(!(appPayload.getCreated_by()==null)) {
				app.setCreated_by(encdec.decryptnew(appPayload.getCreated_by()));
			}
			LocalDate entdt=LocalDate.now();
			app.setDataentdt(entdt);
			try {
				 Prediction=appRepo.findByApplicant_mobile_no(app.getApplicant_mobile_no(), entdt);}
					catch(Exception e){
					Prediction=null;	
					}
					
					if (Prediction==null) {
						response="Mobile number alredy exist. kindly provide new mobile number";
					}else {
			appRepo.save(app);
			response="data has been submitted successfully";
				System.out.println(response);
				
				
				pre.setApplicant_id(encdec.encryptnew(String.valueOf(Prediction.getApplicant_id())));
				if(Prediction.getEstimated_income()==null) {
					 pre=null;
					 response="Prediction list is not yet retrieved";
				}
				else {
					
				
				pre.setEligible_loan_amount(encdec.encryptnew(String.valueOf(Prediction.getEligible_loan_amount())));
				pre.setStress_one(encdec.encryptnew(Prediction.getStress_one()));
				pre.setStress_two(encdec.encryptnew(Prediction.getStress_two()));
				pre.setStress_three(encdec.encryptnew(Prediction.getStress_three()));
				pre.setStress_four(encdec.encryptnew(Prediction.getStress_four()));
				pre.setStress_five(encdec.encryptnew(Prediction.getStress_five()));
				pre.setDataFetched(encdec.encryptnew(Prediction.getDataFetched()));
				pre.setApplcnt_default_prob_number(encdec.encryptnew(Prediction.getApplcnt_default_prob_number()));
				pre.setEstimated_income(encdec.encryptnew(String.valueOf(Prediction.getEstimated_income())));
				pre.setSubmitted(encdec.encryptnew(Prediction.getSubmitted()));}}
			status="true";
			httpstatus=HttpStatus.OK;
			}
					
		catch (Exception e) {
			LOGGER.error("Error While returning prediction of new  applicant" + e.getMessage());
			response="Error While adding applicant" + e.getMessage();
			status="false";
			httpstatus=HttpStatus.BAD_REQUEST;
		}
		System.out.println(response);
		return ResponseEntity.status(httpstatus).body(new PredictionResponse(pre,encdec.encryptnew(response),encdec.encryptnew(status)));
	}
	@PostMapping("/PushFiles")
	public ResponseEntity<GeneralResponse> uploadData(@RequestParam("file") MultipartFile file) throws IOException {
		HttpStatus httpstatus=null;
		String response="";
		String status=null;
		try{
		//	List<LicenseDetails> list1=new ArrayList<>();
		
		InputStream inputstream=file.getInputStream();
		String name=file.getOriginalFilename();
		System.out.println("inputstream size is"+inputstream.toString());
		System.out.println("name of file is"+name);
		String target=writeToFile(inputstream,name);
		response="Upload applicants process is initiated.Please wait for few minutes to get it uploaded in database.";
		status="True";
		 httpstatus=HttpStatus.OK;
		 System.out.println(response);
	  
	}
	 catch(Exception e) {
	  System.out.println("error in uploading applicant details"+e);
	  response="error in uploading applicant detail"+e;
		//Log.error(response);
		 httpstatus=HttpStatus.BAD_REQUEST;
		 status="False";
	 }
		 System.out.println(response);
		 System.out.println(status);
	  return ResponseEntity.status(httpstatus)
					.body(new GeneralResponse(encdec.encryptnew(response), encdec.encryptnew(status)));
	 
	}
	public String writeToFile(InputStream stream,String name) {
		String target="";
		try {
			    File targetFile = new File("C://uploadbatchfile//UploadFiles//"+name);

			    FileUtils.copyInputStreamToFile(stream, targetFile);
		target=targetFile.getPath();
		
	}
		catch (Exception e) {
			System.out.println(e);
		}
	    return target;
	}
	
	@RequestMapping(value = { "/Pull Applicant" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<PullApplicantResponse> GetApplicantPrediction(@RequestBody Applicant_idPayload appPayload) {
		LOGGER.info("get  applicant prediction  by id api has been called !!! Start Of Method get applicant by id");
		
		HttpStatus httpstatus=null;
		String response="";
		String status=null;
		
		ApplicantPredictionList app= new ApplicantPredictionList();
		
		try {
		 Applicant applicantDetails=appRepo.findByApplicantid(Long.parseLong(encdec.decryptnew(appPayload.getApplicant_id())));
		System.out.println("details retrived successfully");
			if (applicantDetails==null) {
				
			response=" applicant details not available"	;
			}
			else
			{ 
				System.out.println("encyption of list");
				
					System.out.println("for loop");
						
						
							 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
								if(!(((Applicant) applicantDetails).getVehicle_no()==null)) {
									app.setVehicle_no(encdec.encryptnew(((Applicant) applicantDetails).getVehicle_no()));
								}
								if(!(((Applicant) applicantDetails).getCompany_name()==null)) {
									app.setCompany_name(encdec.encryptnew(((Applicant) applicantDetails).getCompany_name()));
								}
								if(!((Applicant) applicantDetails).getApplicant_firstname().isEmpty()) {
									app.setApplicant_firstname(encdec.encryptnew(((Applicant) applicantDetails).getApplicant_firstname()));
								}System.out.println("dummy1");
								
								if(!(applicantDetails.getApplicant_date_of_birth()==null)) {
									System.out.println("DOB"+applicantDetails.getApplicant_date_of_birth());
									String dob=sdf1.format( applicantDetails.getApplicant_date_of_birth()) ;
									System.out.println("dob string"+dob);
									 app.setApplicant_Date_of_birth(encdec.encryptnew(dob));
								}
								System.out.println("dummy2");
								if(!(((Applicant) applicantDetails).getAge()==0)) {
									String age= (String.valueOf(applicantDetails.getAge()));
									 app.setAge(encdec.encryptnew(age));}
								if(!(applicantDetails.getMaritalstatus()==null)) {
									 app.setMaritalstatus(encdec.encryptnew(applicantDetails.getMaritalstatus()));}
								
								if(!(applicantDetails.getNominee_name()==null))
									{ app.setNominee_name(encdec.encryptnew(applicantDetails.getNominee_name()));}
								if(applicantDetails.getNominee_dob() != null)
									{String dob=(encdec.encryptnew(sdf1.format(applicantDetails.getNominee_dob())));
									 app.setNominee_dob((dob));
									}
								if(!(applicantDetails.getNominee_age()==0))
								{ app.setNominee_age(encdec.encryptnew(String.valueOf(applicantDetails.getNominee_age())));}				
									if(!(applicantDetails.getNominee_relation()==null) ) {
										app.setNominee_relation(encdec.encryptnew(applicantDetails.getNominee_relation()));
									}
									if(!(applicantDetails.getSpouse_name()==null))
									{app.setSpouse_name(encdec.encryptnew(applicantDetails.getSpouse_name()));}
									if(!(applicantDetails.getApplicant_father_firstname()==null))
										app.setApplicant_father_firstname(encdec.encryptnew(applicantDetails.getApplicant_father_firstname()));
									if(!(applicantDetails.getReligion()==null))
									{app.setReligion(encdec.encryptnew(applicantDetails.getReligion()));}
								if(!(applicantDetails.getApplicant_qualification()==null)) {
									app.setApplicant_qualification(encdec.encryptnew(applicantDetails.getApplicant_qualification()));}
								if(!(applicantDetails.getApplicant_employment_type()==null))	{
								app.setApplicant_employment_type(encdec.encryptnew(applicantDetails.getApplicant_employment_type()));}
								if(!(applicantDetails.getApplicant_address_line_1()==null)) {
								 app.setApplicant_address_line_1(encdec.encryptnew(applicantDetails.getApplicant_address_line_1()));}
								if(!(applicantDetails.getApplicant_city_name()==null)) {
									 app.setApplicant_city_name(encdec.encryptnew(applicantDetails.getApplicant_city_name()));}
								if(!(applicantDetails.getApplicant_PIN()==0)) {
									 app.setApplicant_pin(
											 (encdec.encryptnew(String.valueOf(applicantDetails.getApplicant_PIN()))));}
								if(!(applicantDetails.getApplicant_mobile_no()==null)) {
									 app.setApplicant_mobile_no((encdec.encryptnew(applicantDetails.getApplicant_mobile_no())));}
								if(!(applicantDetails.getNo_of_family_member()==0)) {
									 app.setNo_of_family_member(encdec.encryptnew(String.valueOf(applicantDetails.getNo_of_family_member())));}
								if(!(applicantDetails.getNo_of_earning_member()==0) ){
									app.setNo_of_earning_member(encdec.encryptnew(String.valueOf(applicantDetails.getNo_of_earning_member())));}
								if(!(applicantDetails.getHouse_type()==null)) {
									app.setHouse_type(encdec.encryptnew(applicantDetails.getHouse_type()));}
								if(!(applicantDetails.getRation_card()==null)) {	
								app.setRation_Card(encdec.encryptnew(applicantDetails.getRation_card()));}
								if(!(applicantDetails.getMedical_insurance()==null)) {
								app.setMedical_insurance(encdec.encryptnew(applicantDetails.getMedical_insurance()));}
								if(!(applicantDetails.getCurrent_loan_outstanding_principal()==null)) {
									 app.setCurrent_loan_outstanding_principal(encdec.encryptnew(String.valueOf(applicantDetails.getCurrent_loan_outstanding_principal())));}
								if(!(applicantDetails.getCurrent_loan_outstanding_interest()==null)) {	
								 app.setCurrent_loan_outstanding_Stringerest(encdec.encryptnew(String.valueOf(applicantDetails.getCurrent_loan_outstanding_interest())));}
								if(!(applicantDetails.getApplicant_income()==null))	{
								app.setApplicant_income((encdec.encryptnew(applicantDetails.getApplicant_income())));}
								if(!(applicantDetails.getIncome_from_other_sources()==null)) {
									app.setIncome_from_other_sources(encdec.encryptnew(String.valueOf(applicantDetails.getIncome_from_other_sources())));}
								if(!(applicantDetails.getFood_expenses()==0))	{
								app.setFood_expenses(encdec.encryptnew(String.valueOf(applicantDetails.getFood_expenses())));}
								if(!(applicantDetails.getHouserent()==null)) {
									 app.setHouserent((encdec.encryptnew(applicantDetails.getHouserent())));}
								if(!(applicantDetails.getHouse_renovation_expenses()==0)) {
								 app.setHouse_renovation_expenses(encdec.encryptnew(String.valueOf(applicantDetails.getHouse_renovation_expenses())));}
								if(!(applicantDetails.getTotal_monthly_bill_payment()==null)) {
									 app.setTotal_monthly_bill_payment(encdec.encryptnew(String.valueOf(applicantDetails.getTotal_monthly_bill_payment())));}
								if(!(applicantDetails.getApplicant_expense_monthly()==null)) {
								app.setApplicant_expense_monthly((encdec.encryptnew(applicantDetails.getApplicant_expense_monthly())));}
								if(applicantDetails.getEstimated_income()==null) {
								
									 response="Prediction list is not yet retrieved";
								}
								else {
									
								
								app.setEligible_loan_amount(encdec.encryptnew(String.valueOf(applicantDetails.getEligible_loan_amount())));
								app.setStress_one(encdec.encryptnew(applicantDetails.getStress_one()));
								app.setStress_two(encdec.encryptnew(applicantDetails.getStress_two()));
								app.setStress_three(encdec.encryptnew(applicantDetails.getStress_three()));
								app.setStress_four(encdec.encryptnew(applicantDetails.getStress_four()));
								app.setStress_five(encdec.encryptnew(applicantDetails.getStress_five()));
								app.setDataFetched(encdec.encryptnew(applicantDetails.getDataFetched()));
								app.setApplcnt_default_prob_number(encdec.encryptnew(applicantDetails.getApplcnt_default_prob_number()));
								app.setEstimated_income(encdec.encryptnew(String.valueOf(applicantDetails.getEstimated_income())));
								app.setSubmitted(encdec.encryptnew(applicantDetails.getSubmitted()));}
								
				}
				//System.out.println("dummy2");
				response=" Applicant detail is retrieved successfully";
				
			
			System.out.println("dummy3");
			status="true";
			httpstatus=HttpStatus.OK;
			}
					
		catch (Exception e) {
			LOGGER.error("Error While retreiving user" + e.getMessage());
			response="Error While retreiving  user" + e.getMessage();
			status="false";
			httpstatus=HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpstatus).body(new PullApplicantResponse
				(app,(response),(status)));
	}
	@RequestMapping(value = { "/PullApplicantsResponse" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<PullApplicantResponse> PullApplcaintsResponse(@RequestBody FileNamePayload filePayload) {
		LOGGER.info("get  applicant prediction by file name api has been called !!! Start Of Method get applicant by  file name ");
		
		HttpStatus httpstatus=null;
		String response="";
		String status=null;
		List<ApplicantPredictionList> appList=new ArrayList<ApplicantPredictionList>();
		//List<AdvanceRequest> list=new ArrayList<AdvanceRequest>();
		
		try {
			List<Long> applicant_ids=uploadRepo.findApplicants(encdec.decryptnew(filePayload.getFileName()),Integer.parseInt(encdec.decryptnew(filePayload.getFile_id())));
			List<Applicant> applicantDetails=appRepo.findByApplicantlist(applicant_ids);
		System.out.println("details retrived successfully");
		System.out.println("applicant_ids"+applicant_ids);
			if (applicantDetails==null) {
				
			response=" applicant details not available"	;
			}
			else
			{ 
				System.out.println("encyption of list");
				for( int i=0; i<applicantDetails.size();i++) {
					System.out.println("for loop");
						
					ApplicantPredictionList app= new ApplicantPredictionList();
							 SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
								System.out.println("dummy1");	
							 if(!( applicantDetails.get(i).getVehicle_no()==null)) {
									System.out.println("dummy2");	
								 app.setVehicle_no(encdec.encryptnew(applicantDetails.get(i).getVehicle_no()));
								}
								System.out.println("dummy3");
								if(!( applicantDetails.get(i).getCompany_name()==null)) {
									app.setCompany_name(encdec.encryptnew( applicantDetails.get(i).getCompany_name()));
								}
								if(!( applicantDetails.get(i).getApplicant_firstname()==null) ){
									app.setApplicant_firstname(encdec.encryptnew( applicantDetails.get(i).getApplicant_firstname()));
								}System.out.println("dummy1");
								
								if(!(applicantDetails.get(i).getApplicant_date_of_birth()==null)) {
									System.out.println("DOB"+applicantDetails.get(i).getApplicant_date_of_birth());
									String dob=sdf1.format( applicantDetails.get(i).getApplicant_date_of_birth()) ;
									System.out.println("dob string"+dob);
									 app.setApplicant_Date_of_birth(encdec.encryptnew(dob));
								}
								System.out.println("dummy21");
								if(!(applicantDetails.get(i).getAge()==0)) {
									String age= (String.valueOf(applicantDetails.get(i).getAge()));
									 app.setAge(encdec.encryptnew(age));}
								if(!(applicantDetails.get(i).getMaritalstatus()==null)) {
									 app.setMaritalstatus(encdec.encryptnew(applicantDetails.get(i).getMaritalstatus()));}
								
								if(!(applicantDetails.get(i).getNominee_name()==null))
									{ app.setNominee_name(encdec.encryptnew(applicantDetails.get(i).getNominee_name()));}
								if(applicantDetails.get(i).getNominee_dob() != null)
									{String dob=(encdec.encryptnew(sdf1.format(applicantDetails.get(i).getNominee_dob())));
									 app.setNominee_dob((dob));
									}
								if(!(applicantDetails.get(i).getNominee_age()==0))
								{ app.setNominee_age(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getNominee_age())));}				
									if(!(applicantDetails.get(i).getNominee_relation()==null) ) {
										app.setNominee_relation(encdec.encryptnew(applicantDetails.get(i).getNominee_relation()));
									}
									if(!(applicantDetails.get(i).getSpouse_name()==null))
									{app.setSpouse_name(encdec.encryptnew(applicantDetails.get(i).getSpouse_name()));}
									if(!(applicantDetails.get(i).getApplicant_father_firstname()==null))
										app.setApplicant_father_firstname(encdec.encryptnew(applicantDetails.get(i).getApplicant_father_firstname()));
									if(!(applicantDetails.get(i).getReligion()==null))
									{app.setReligion(encdec.encryptnew(applicantDetails.get(i).getReligion()));}
								if(!(applicantDetails.get(i).getApplicant_qualification()==null)) {
									app.setApplicant_qualification(encdec.encryptnew(applicantDetails.get(i).getApplicant_qualification()));}
								if(!(applicantDetails.get(i).getApplicant_employment_type()==null))	{
								app.setApplicant_employment_type(encdec.encryptnew(applicantDetails.get(i).getApplicant_employment_type()));}
								if(!(applicantDetails.get(i).getApplicant_address_line_1()==null)) {
								 app.setApplicant_address_line_1(encdec.encryptnew(applicantDetails.get(i).getApplicant_address_line_1()));}
								if(!(applicantDetails.get(i).getApplicant_city_name()==null)) {
									 app.setApplicant_city_name(encdec.encryptnew(applicantDetails.get(i).getApplicant_city_name()));}
								if(!(applicantDetails.get(i).getApplicant_PIN()==0)) {
									 app.setApplicant_pin(
											 (encdec.encryptnew(String.valueOf(applicantDetails.get(i).getApplicant_PIN()))));}
								if(!(applicantDetails.get(i).getApplicant_mobile_no()==null)) {
									 app.setApplicant_mobile_no((encdec.encryptnew(applicantDetails.get(i).getApplicant_mobile_no())));}
								if(!(applicantDetails.get(i).getNo_of_family_member()==0)) {
									 app.setNo_of_family_member(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getNo_of_family_member())));}
								if(!(applicantDetails.get(i).getNo_of_earning_member()==0) ){
									app.setNo_of_earning_member(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getNo_of_earning_member())));}
								if(!(applicantDetails.get(i).getHouse_type()==null)) {
									app.setHouse_type(encdec.encryptnew(applicantDetails.get(i).getHouse_type()));}
								if(!(applicantDetails.get(i).getRation_card()==null)) {	
								app.setRation_Card(encdec.encryptnew(applicantDetails.get(i).getRation_card()));}
								if(!(applicantDetails.get(i).getMedical_insurance()==null)) {
								app.setMedical_insurance(encdec.encryptnew(applicantDetails.get(i).getMedical_insurance()));}
								if(!(applicantDetails.get(i).getCurrent_loan_outstanding_principal()==null)) {
									 app.setCurrent_loan_outstanding_principal(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getCurrent_loan_outstanding_principal())));}
								if(!(applicantDetails.get(i).getCurrent_loan_outstanding_interest()==null)) {	
								 app.setCurrent_loan_outstanding_Stringerest(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getCurrent_loan_outstanding_interest())));}
								if(!(applicantDetails.get(i).getApplicant_income()==null))	{
								app.setApplicant_income((encdec.encryptnew(applicantDetails.get(i).getApplicant_income())));}
								if(!(applicantDetails.get(i).getIncome_from_other_sources()==null)) {
									app.setIncome_from_other_sources(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getIncome_from_other_sources())));}
								if(!(applicantDetails.get(i).getFood_expenses()==0))	{
								app.setFood_expenses(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getFood_expenses())));}
								if(!(applicantDetails.get(i).getHouserent()==null)) {
									 app.setHouserent((encdec.encryptnew(applicantDetails.get(i).getHouserent())));}
								if(!(applicantDetails.get(i).getHouse_renovation_expenses()==0)) {
								 app.setHouse_renovation_expenses(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getHouse_renovation_expenses())));}
								if(!(applicantDetails.get(i).getTotal_monthly_bill_payment()==null)) {
									 app.setTotal_monthly_bill_payment(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getTotal_monthly_bill_payment())));}
								if(!(applicantDetails.get(i).getApplicant_expense_monthly()==null)) {
								app.setApplicant_expense_monthly((encdec.encryptnew(applicantDetails.get(i).getApplicant_expense_monthly())));}
								if(applicantDetails.get(i).getEstimated_income()==null) {
								
								
									response="Prediction list is not yet retrieved";
								System.out.println(response);
								}
								else {
									
									System.out.println("prediction"+i);
								app.setEligible_loan_amount(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getEligible_loan_amount())));
								app.setStress_one(encdec.encryptnew(applicantDetails.get(i).getStress_one()));
								app.setStress_two(encdec.encryptnew(applicantDetails.get(i).getStress_two()));
								app.setStress_three(encdec.encryptnew(applicantDetails.get(i).getStress_three()));
								app.setStress_four(encdec.encryptnew(applicantDetails.get(i).getStress_four()));
								app.setStress_five(encdec.encryptnew(applicantDetails.get(i).getStress_five()));
								app.setDataFetched(encdec.encryptnew(applicantDetails.get(i).getDataFetched()));
								app.setApplcnt_default_prob_number(encdec.encryptnew(applicantDetails.get(i).getApplcnt_default_prob_number()));
								app.setEstimated_income(encdec.encryptnew(String.valueOf(applicantDetails.get(i).getEstimated_income())));
								app.setSubmitted(encdec.encryptnew(applicantDetails.get(i).getSubmitted()));}
								appList.add(i,app);
								System.out.println("i data added"+i);
				}
				
			}
				//System.out.println("dummy2");
				response=" Applicant detail is retrieved successfully";
				
			
			System.out.println("dummy3");
			status="true";
			httpstatus=HttpStatus.OK;
			}
					
		catch (Exception e) {
			LOGGER.error("Error While retreiving applicant" + e.getMessage());
			response="Error While retreiving  applicant" + e.getMessage();
			status="false";
			httpstatus=HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpstatus).body(new PullApplicantResponse
				(appList,(response),(status)));
	}
	/*
	@RequestMapping(value = { "/transfer_applicant_db" }, method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<GeneralResponse> transfer_applicant_db (@RequestBody UpdateTruckersDetails appPayload) {
		LOGGER.info("add new applicant api has been called !!! Start Of Method add new applicant");
		
		HttpStatus httpstatus=null;
		String response="";
		String status=null;
	
		try {
			
			response="Applicant data transfered successfully.";
				System.out.println(response);
				
			
			status="true";
			httpstatus=HttpStatus.OK;
			}
					
		catch (Exception e) {
			LOGGER.error("Error While transfering applicant data" + e.getMessage());
			response="Error While transfering applicant data" + e.getMessage();
			status="false";
			httpstatus=HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(httpstatus).body(new GeneralResponse(encdec.encryptnew(response),encdec.encryptnew(status)));
	} 
	@PostMapping("/downloadApplicantResposne")
	public ResponseEntity<ApplicantPredictionFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {
	  String response = "";
	  String status=null;
	  HttpStatus httpstatus=null;
	  File Applicantreponse=null;
	  try {
		  response="File generated successfully";
		  httpstatus=HttpStatus.OK;
		  status="true";
	  }
		  catch (Exception e) {
				LOGGER.error("Error While downloading applicant  reponse data" + e.getMessage());
				response="Error While downloading applicant response data" + e.getMessage();
				status="false";
				httpstatus=HttpStatus.BAD_REQUEST;
			}
			return ResponseEntity.status(httpstatus).body(new ApplicantPredictionFileResponse(Applicantreponse,encdec.encryptnew(response),encdec.encryptnew(status)));
		} */
	
}
