package org.example.ams.services;
import org.example.ams.models.User;
import org.example.ams.repositories.UserRepositoryInterface;
import org.example.ams.services.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface repo;

    @Autowired
    public UserService(UserRepositoryInterface userRepository) {
        this.repo = userRepository;
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<User> getBySurname(String surname) {
        return repo.findBySurname(surname);
    }

    @Override
    public void save(User user) {
        repo.save(user);
    }

    @Override
    public void update(User user) {
        repo.save(user);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    public void addAttendance(int userId, double attendanceToAdd) {
        User user = getById(userId);
        if (user != null) {
            double newAttendance = user.getAttendance() + attendanceToAdd;
            newAttendance = Math.max(0, Math.min(100, newAttendance));
            user.setAttendance(newAttendance);
            update(user);
        }
    }
}
