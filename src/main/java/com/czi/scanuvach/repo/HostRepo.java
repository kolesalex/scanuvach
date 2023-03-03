package com.czi.scanuvach.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.czi.scanuvach.model.Host;

@Repository
public interface HostRepo extends JpaRepository<Host, Long> {

    List<Host> findByIpIn(Set<String> ips);
}
