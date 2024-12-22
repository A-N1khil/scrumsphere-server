package com.anikhil.scrumsphere.users.services;

import com.anikhil.scrumsphere.shared.BaseService;
import com.anikhil.scrumsphere.users.dao.UserDao;
import com.anikhil.scrumsphere.users.models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService implements BaseService<User> {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        return this.userDao.save(user);
    }

    public User createUser(String userId, String password) {
        return this.create(new User(userId, password));
    }

    public User findUserByUserId(String userId) {
        Optional<User> user = this.userDao.findUserByUserId(userId);
        return user.orElse(null);
    }

    public boolean isUserNameValid(String userId) {
        Optional<User> user = this.userDao.findUserByUserId(userId);
        return user.isEmpty();
    }

    public User findUserByIdAndPassword(String userId, String password) {
        Optional<User> user = this.userDao.findUserByIdAndPassword(userId, password);
        return user.orElse(null);
    }
}
