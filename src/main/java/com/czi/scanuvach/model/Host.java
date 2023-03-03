package com.czi.scanuvach.model;

import static javax.persistence.CascadeType.ALL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hosts")
public class Host {
    public Host(String ip, String status, List<Port> ports) {
        this.ip = ip;
        this.status = status;
        this.ports = ports;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String ip;
    private String status;
    @OneToMany(cascade=ALL, orphanRemoval = true)
    @JoinColumn(name = "host_id")
    private List<Port> ports = new ArrayList<>();;

//    @Column(name = "created", updatable = false)
//    @CreatedDate
    @CreationTimestamp
    private LocalDateTime created;

//    @Column(name = "updated", updatable = false)
//    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updated;

}
