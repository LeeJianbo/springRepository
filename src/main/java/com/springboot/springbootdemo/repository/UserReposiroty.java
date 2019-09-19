package com.springboot.springbootdemo.repository;

import com.springboot.springbootdemo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposiroty extends BaseRepository<User,Long> {
    User findUserByName(String name);
}
