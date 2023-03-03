package com.czi.scanuvach.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.czi.scanuvach.model.Host;

@Repository
public class PortsDao {

    public static final String INSERT_HOSTS = """
            INSERT INTO hosts (ip, status, updated, created) 
            VALUES (:ip, :status, :updated, :created);
            """;
    private NamedParameterJdbcTemplate jdbcTemplate;

    protected NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void saveEmailSettings(List<Host> hosts) {
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(hosts);
        getJdbcTemplate().update(INSERT_HOSTS, source);
    }
}
