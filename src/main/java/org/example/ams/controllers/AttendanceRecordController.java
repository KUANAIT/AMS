package org.example.ams.controllers;

import org.example.ams.models.AttendanceRecord;
import org.example.ams.models.User;
import org.example.ams.services.AttendanceRecordService;
import org.example.ams.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("attendancerecord")
public class AttendanceRecordController {
    private final AttendanceRecordService attendanceRecordService;
    private final UserService userService;


    @Autowired
    public AttendanceRecordController(AttendanceRecordService attendanceRecordService, UserService userService) {
        this.attendanceRecordService = attendanceRecordService;
        this.userService = userService;
    }

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/")
    public List<AttendanceRecord> getAll(){
        return attendanceRecordService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceRecord> getById(@PathVariable("id") int id){
        AttendanceRecord attendanceRecord = attendanceRecordService.getById(id);
        if(attendanceRecord == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404

        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK); //200
    }


    @PostMapping("/save")
    public ResponseEntity<AttendanceRecord> saveAndFindUser(@RequestBody AttendanceRecord attendanceRecord) {
        attendanceRecordService.save(attendanceRecord);
        AttendanceRecord retrievedRecord = attendanceRecordService.getById(attendanceRecord.getId());

        if (retrievedRecord != null) {
            if (attendanceRecord.getUserId() == retrievedRecord.getUserId() &&
                    attendanceRecord.getCourseId() == retrievedRecord.getCourseId() &&
                    attendanceRecord.getDate().equals(retrievedRecord.getDate()) &&
                    attendanceRecord.isPresent() == retrievedRecord.isPresent()) {
                User user = userService.getById(attendanceRecord.getUserId());
                if (user != null) {
                    userService.addAttendance(user.getId(), attendanceRecord.isPresent() ? 1.3 : -1.3);
                }
                return new ResponseEntity<>(retrievedRecord, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttendanceRecord> updateUser(@PathVariable int id, @RequestBody AttendanceRecord attendanceRecord) {
        attendanceRecordService.update(attendanceRecord);
        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        attendanceRecordService.delete(id);
        return new ResponseEntity<>("Deleted user with ID: " + id, HttpStatus.OK);
    }

}
