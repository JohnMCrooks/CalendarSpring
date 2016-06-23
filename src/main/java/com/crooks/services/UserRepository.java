/*
 * Copyright (c) 2016.
 */

package com.crooks.services;

import com.crooks.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 6/23/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByName(String name);

}
