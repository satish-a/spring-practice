package org.sab.sampleqa.data.repo.qa;

import org.sab.sampleqa.data.model.qa.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QARepository extends MongoRepository<Question, String>,
		PagingAndSortingRepository<Question, String> {
	Question findBySubject(String subject);

	Question findById(String id);
}
