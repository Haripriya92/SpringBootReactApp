package org.launchcode.givewise.service;


import org.launchcode.givewise.models.User;
import org.launchcode.givewise.models.data.UserRepository;
import org.launchcode.givewise.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User registerUser(UserRequest request) {
        User newUser = new User();
        newUser.setUserName(request.getUserName());
        newUser.setEmail(request.getEmail().toLowerCase());
        String encoded = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encoded);
        newUser.setPhone(request.getPhone());
        return userRepo.save(newUser);
    }
}
