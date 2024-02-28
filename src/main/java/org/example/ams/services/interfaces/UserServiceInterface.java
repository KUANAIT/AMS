package org.example.ams.services.interfaces;

import org.example.ams.models.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
    List<User> getBySurname(String surname);
}