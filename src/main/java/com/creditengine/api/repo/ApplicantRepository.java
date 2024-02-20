package com.creditengine.api.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.creditengine.api.model.Applicant;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

	@Query("SELECT a FROM Applicant a WHERE a.applicant_mobile_no =?1 and a.dataentdt=?2")
	public Applicant findByApplicant_mobile_no(String applicant_mobile_no, LocalDate dataentdt);
	@Query("SELECT a FROM Applicant a WHERE a.applicant_id=?1")
	public Applicant findByApplicantid(Long applicant_id);
	@Query("SELECT a FROM Applicant a WHERE a.applicant_id in ?1")
	public List<Applicant> findByApplicantlist(List applicant_id);
	
}
