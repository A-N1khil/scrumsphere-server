package com.anikhil.scrumsphere.users.services;

import com.anikhil.scrumsphere.shared.BaseService;
import com.anikhil.scrumsphere.users.dao.UserDao;
import com.anikhil.scrumsphere.users.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BaseService<User> {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User create(User user) {
        return this.userDao.save(user);
    }

    public User createUser(String userId, String name, String email) {
        return this.create(new User(userId, name, email));
    }
}
