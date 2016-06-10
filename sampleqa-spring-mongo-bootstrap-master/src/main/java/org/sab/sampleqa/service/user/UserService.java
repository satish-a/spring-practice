package org.sab.sampleqa.service.user;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;
import org.sab.sampleqa.common.util.mapper.user.UserMapper;
import org.sab.sampleqa.data.model.user.User;
import org.sab.sampleqa.data.repo.user.UserRepository;
import org.sab.sampleqa.web.model.user.UserUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	private UserMapper mapper = new UserMapper();

	private Logger logger = Logger.getLogger(UserService.class);

	public UserUI create(UserUI uiBean) {
		User newUser = mapper.toPersistenceBean(uiBean);
		newUser.setPassword(generateHash(uiBean.getPassword()));
		User saved = userRepository.save(newUser);

		logger.debug("Created Account : " + saved);
		return mapper.toUIBean(saved);
	}

	public User find(User user) {
		return user;
	}

	public List<UserUI> findAll() {
		return mapper.toUIBean(userRepository.findAll());
	}

	public Page<UserUI> findAll(Pageable pageable) {
		return mapper.toUIBean(userRepository.findAll(pageable), pageable);
	}

	public UserUI findByUsername(String userName) {
		return mapper.toUIBean(userRepository.findByUserName(userName));
	}

	public User update(User user) {
		User existingUser = userRepository.findByUserName(user.getUserName());

		if (existingUser == null) {
			return null;
		}

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());

		if (!existingUser.getPassword().equals(user.getPassword())) {
			existingUser.setPassword(generateHash(user.getPassword()));
		}

		return userRepository.save(existingUser);
	}

	public Boolean delete(User user) {
		User existingUser = userRepository.findByUserName(user.getUserName());

		if (existingUser == null) {
			return false;
		}

		userRepository.delete(existingUser);
		return true;
	}

	private String generateHash(String input) {
		String md5 = null;

		if (null == input)
			return null;

		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}
