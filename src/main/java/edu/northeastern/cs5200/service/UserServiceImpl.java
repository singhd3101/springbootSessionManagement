package edu.northeastern.cs5200.service;


import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.model.Role;
import edu.northeastern.cs5200.model.User;
import edu.northeastern.cs5200.repository.RoleRepository;
import edu.northeastern.cs5200.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    HashSet<Role> allRoles = new HashSet<Role>((Collection<? extends Role>) roleRepository.findAll());

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(allRoles);
        userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}