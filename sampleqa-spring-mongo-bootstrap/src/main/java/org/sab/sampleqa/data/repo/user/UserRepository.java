package org.sab.sampleqa.data.repo.user;

import org.sab.sampleqa.data.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends MongoRepository<User, String>,
		PagingAndSortingRepository<User, String> {

	User findByUserName(String userName);
}
