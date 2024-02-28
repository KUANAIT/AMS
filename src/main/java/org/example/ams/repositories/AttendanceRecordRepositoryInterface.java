package org.example.ams.repositories;

import org.example.ams.models.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepositoryInterface extends JpaRepository<AttendanceRecord, Integer> {
}

