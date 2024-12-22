package com.anikhil.scrumsphere.users.dao;

import com.anikhil.scrumsphere.shared.BaseWriteDao;
import com.anikhil.scrumsphere.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao implements BaseWriteDao<User> {

    private final UserQuickReadDao userQuickReadDao;

    @Autowired
    public UserDao(UserQuickReadDao userQuickReadDao) {
        this.userQuickReadDao = userQuickReadDao;
    }

    public Optional<User> findUserByUserId(String userId) {
        return Optional.ofNullable(userQuickReadDao.findByUserId(userId));
    }

    @Override
    public User save(User user) {
        return this.userQuickReadDao.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {
        throw new UnsupportedOperationException("Delete operation not supported for User");
    }
}
