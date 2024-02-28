package org.example.ams.services;
import org.example.ams.models.User;
import org.example.ams.repositories.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepositoryInterface repo;

    @Autowired
    public UserService(UserRepositoryInterface userRepository) {
        this.repo = userRepository;
    }

    public User find(int id) {
        Optional<User> user = repo.findById(id);
        return user.orElse(null);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public User update(User user) {
        return repo.save(user);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<User> getBySurname(String surname) {
        return repo.findBySurname(surname);
    }

    public void addAttendance(int userId, double attendanceToAdd) {
        User user = find(userId);
        if (user != null) {
            double newAttendance = user.getAttendance() + attendanceToAdd;
            user.setAttendance(newAttendance);
            update(user);
        }
    }
}
