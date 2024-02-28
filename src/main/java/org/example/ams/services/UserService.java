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
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User update(User user) {
        return repo.save(user);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
