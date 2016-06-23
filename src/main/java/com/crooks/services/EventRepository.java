/*
 * Copyright (c) 2016.
 */

package com.crooks.services;

import com.crooks.entities.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 6/23/16.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
}
