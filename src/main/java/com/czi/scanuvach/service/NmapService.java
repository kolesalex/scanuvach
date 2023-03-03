package com.czi.scanuvach.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.czi.scanuvach.model.Host;
import com.czi.scanuvach.model.Port;
import com.czi.scanuvach.repo.HostRepo;

import de.martinspielmnann.nmapxmlparser.NmapParserException;
import de.martinspielmnann.nmapxmlparser.NmapXmlParser;
import de.martinspielmnann.nmapxmlparser.elements.NmapRun;
import de.martinspielmnann.nmapxmlparser.elements.Ports;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NmapService {

    private final HostRepo hostRepo;

    public void save(String xml) {
        NmapXmlParser nmapXmlParser = new NmapXmlParser();

        NmapRun nmapRunFromString = null;
        try {
            nmapRunFromString = nmapXmlParser.parse(xml);
            log.info(nmapRunFromString.toString());
            Map<String, Host> nmapHosts = map(nmapRunFromString);

            //update existing hosts
            List<Host> existingHosts = hostRepo.findByIpIn(nmapHosts.keySet());

            for (Host host : existingHosts) {
                Host nmappedHost = nmapHosts.get(host.getIp());
                if (nmappedHost != null) {
                    host.setStatus(nmappedHost.getStatus());
                    host.setPorts(nmappedHost.getPorts());
                    host.setUpdated(LocalDateTime.now());
                }
            }

            hostRepo.saveAll(existingHosts);

            //insert new ones
            Map<String, Host> existingHostsMappedByIp = existingHosts
                    .stream()
                    .collect(Collectors.toMap(Host::getIp, Function.identity()));
            List<Host> newHosts = nmapHosts.entrySet()
                    .stream()
                    .filter(e -> !existingHostsMappedByIp.containsKey(e.getKey()))
                    .map(e -> e.getValue())
                    .toList();
            hostRepo.saveAll(newHosts);

        } catch (NmapParserException e) {
            throw new RuntimeException(e);
        }
        System.out.println(nmapRunFromString);
    }

    private Map<String, Host> map(NmapRun nmapRunOutput) {
        return nmapRunOutput.hosts()
                .stream()
                .map(host -> new Host(host.address().addr(), host.status().state(), map(host.ports())))
                .collect(Collectors.toMap(Host::getIp, Function.identity()));
    }

    private List<Port> map(Ports ports) {
        return ports.ports()
                .stream()
                .map(port -> new Port(port.portId(), port.state().state(), port.service().name()))
                .toList();
    }
}
