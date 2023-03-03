package com.czi.scanuvach.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czi.scanuvach.model.Host;
import com.czi.scanuvach.model.PortStatus;
import com.czi.scanuvach.repo.HostRepo;
import com.czi.scanuvach.www.dto.HostPortDto;

@Service
public class HostService {

    @Autowired
    private HostRepo hostRepo;

    public List<HostPortDto> getHostPorts(){
         return hostRepo.findAll().stream().map(host -> getHostPortDto(host)).toList();
    }

    private  HostPortDto getHostPortDto(Host host) {
        return HostPortDto.of(
                host.getIp(),
                host.getStatus(),
                getPorts(host, PortStatus.OPEN.getValue()),
                getPorts(host, PortStatus.CLOSED.getValue()),
                host.getUpdated(),
                host.getCreated());
    }

    private String getPorts(Host host, String portStatus) {
        return host.getPorts()
                .stream()
                .filter(port -> portStatus.equalsIgnoreCase(port.getStatus()))
                .map(port -> String.valueOf(port.getPort()))
                .collect(Collectors.joining(","));
    }
}
