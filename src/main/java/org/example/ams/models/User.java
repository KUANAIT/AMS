package org.example.ams.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int groupNumber;
    private double attendance;
    private boolean retake;

    public void setAttendance(double attendance) {
        this.attendance = attendance;
        this.retake = attendance < 50;
    }
}
