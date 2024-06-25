package com.csc3402.lab.staff.service;

import com.csc3402.lab.staff.model.User;
import com.csc3402.lab.staff.model.UserDto;

import java.util.List;
import java.util.Optional;
public interface UserService {


    List<User> listAllUsers();
    User addNewUser(User user);
    User findUserByEmail(String email);

    Optional<User> findUserById(int id);
    User updateUser(User user);
    void deleteUser(User user);
}
