package com.creditengine.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.creditengine.api.model.Uploadapplicants;

public interface UploadApplicantsRepo extends JpaRepository<Uploadapplicants,Integer> {
	@Query("SELECT a.applicant_id FROM Uploadapplicants a WHERE a.Filename =?1 and a.File_id=?2")
	public List<Long> findApplicants(String Filename,int File_id);
}
