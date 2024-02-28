package org.example.ams.services;
import org.example.ams.models.AttendanceRecord;
import org.example.ams.repositories.AttendanceRecordRepositoryInterface;
import org.example.ams.services.interfaces.AttendanceRecordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRecordService implements AttendanceRecordServiceInterface {
    private final AttendanceRecordRepositoryInterface repo;

    @Autowired
    public AttendanceRecordService(AttendanceRecordRepositoryInterface attendanceRecordRepository) {
        this.repo = attendanceRecordRepository;
    }

    @Override
    public List<AttendanceRecord> getAll() {
        return repo.findAll();
    }

    @Override
    public AttendanceRecord getById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(AttendanceRecord attendanceRecord) {
        repo.save(attendanceRecord);
    }

    @Override
    public void update(AttendanceRecord attendanceRecord) {
        repo.save(attendanceRecord);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
