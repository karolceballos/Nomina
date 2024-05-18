package com.humanblend.saas.event.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "EVENT")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EMPLOYEE")
    private Integer employeeId;

    @Column(name = "TYPE")
    private Integer typeId;

    @Temporal(TemporalType.DATE)
    @Column(name = "ENTRY_DATE")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date entryDate;
}
