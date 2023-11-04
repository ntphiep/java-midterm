package com.hiep.services.preService;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;

import com.hiep.models.User;
import com.hiep.repositories.UserRepository;


@Service
public interface UserS extends UserRepository {
    @Query(value = "FROM User AS u WHERE u.username = :username and u.password=:password")
    public User getUserValid(String username,String password);

    public User findUserByUsernameEquals(String username);
}
