package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.hiep.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
