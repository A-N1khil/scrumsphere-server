package com.anikhil.scrumsphere.users.dao;

import com.anikhil.scrumsphere.users.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserQuickReadDao extends MongoRepository<User, String> {

    @Query(value = "{ 'name' : ?0 }")
    User findUserByName(String name);

    @Query(value = "{ 'userId' : ?0 }")
    User findByUserId(String userId);

    @Query(value = "{ 'userId' : ?0, 'password' : ?1 }")
    User findUserByIdAndPassword(String userId, String password);
}