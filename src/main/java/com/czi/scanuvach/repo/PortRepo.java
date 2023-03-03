package com.czi.scanuvach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.czi.scanuvach.model.Port;

@Repository
public interface PortRepo extends JpaRepository<Port, Long> {
}
