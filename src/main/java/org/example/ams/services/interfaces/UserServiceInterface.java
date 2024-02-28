package org.example.ams.services.interfaces;

import org.example.ams.models.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> getAll();
    User getById(int id);
    User save(User user);
    User update(User user);
    void delete(int id);
    List<User> getBySurname(String surname);
}