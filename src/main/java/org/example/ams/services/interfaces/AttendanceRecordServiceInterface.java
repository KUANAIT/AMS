package org.example.ams.services.interfaces;

import org.example.ams.models.AttendanceRecord;

import java.util.List;

public interface AttendanceRecordServiceInterface {
    List<AttendanceRecord> getAll();
    AttendanceRecord getById(int id);
    void save(AttendanceRecord attendanceRecord);
    void update(AttendanceRecord attendanceRecord);
    void delete(int id);
}
