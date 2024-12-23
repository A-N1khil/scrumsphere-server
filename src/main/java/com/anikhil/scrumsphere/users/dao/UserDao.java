package com.anikhil.scrumsphere.users.dao;

import com.anikhil.scrumsphere.users.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {

	private final BaseUserDao userBaseUserDao;

	public Optional<User> findUserByUserId(String userId) {
		return Optional.ofNullable(userBaseUserDao.findByUserId(userId));
	}

	public Optional<User> findUserByIdAndPassword(String userId, String password) {
		return Optional.ofNullable(userBaseUserDao.findUserByIdAndPassword(userId, password));
	}

	public User save(User user) {
		return this.userBaseUserDao.save(user);
	}
}
