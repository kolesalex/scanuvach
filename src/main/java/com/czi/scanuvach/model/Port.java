package com.czi.scanuvach.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "ports")
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private long port;
    private String status;
    private String service;

//    @Column(name = "updated", updatable = false)
//    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updated;

//    @Column(name = "created", updatable = false)
//    @CreatedDate
    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;

    public Port(long port, String status, String service) {
        this.port = port;
        this.status = status;
        this.service = service;
    }
}
