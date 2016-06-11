package org.sab.sampleqa.data.repo.qa;

import org.sab.sampleqa.data.model.qa.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends MongoRepository<Tag, String>,
		PagingAndSortingRepository<Tag, String> {
	Tag findByName(String name);

	Tag findById(String id);
}
