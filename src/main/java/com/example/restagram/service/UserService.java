package com.example.restagram.service;

import com.example.restagram.domain.users.Users;
import com.example.restagram.domain.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public boolean matchPassword(String userId, String newPassword) {
        Users user=usersRepository.findByUserId(userId);

        if(newPassword==null) {
            return false;
        }
        return newPassword.equals(user.getPassword());
    }
}
