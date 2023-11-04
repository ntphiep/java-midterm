package com.hiep.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiep.models.User;
import com.hiep.services.preService.UserS;

@Service
public class UserService {
    @Autowired
    private UserS userS;

    public User userLoginValid(String username, String password) {
        return userS.getUserValid(username, password);
    }
    public boolean checkUserExist(String username){
        return userS.findUserByUsernameEquals(username) != null;
    }

    public void save(User user) {
        userS.save(user);
    }
    public User findById(Long id){
        return userS.findById(id).get();
    }
}
