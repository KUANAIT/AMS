package org.example.ams.controllers;

import org.example.ams.models.User;
import org.example.ams.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveAndFindUser(@RequestBody User user) {
        userService.save(user);
        User retrievedUser = userService.find(user.getId());

        if (retrievedUser != null) {
            if (user.getName().equals(retrievedUser.getName()) &&
                    user.getSurname().equals(retrievedUser.getSurname()) &&
                    user.getGroupNumber() == retrievedUser.getGroupNumber() &&
                    Math.abs(user.getAttendance() - retrievedUser.getAttendance()) < 0.01) {
                return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return new ResponseEntity<>("Deleted user with ID: " + id, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable int id) {
        User user = userService.find(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
