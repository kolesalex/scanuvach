package com.czi.scanuvach.job;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.czi.scanuvach.client.NmapPortJobClient;
import com.czi.scanuvach.service.JobConfigService;
import com.czi.scanuvach.service.NmapService;

import de.martinspielmnann.nmapxmlparser.NmapParserException;

@Component
public class NmapPortsJob implements Job {

    private static final String NMAP_PORTS_JOB = "nmapPortsJob";
    private static final String NMAP_PORTS_TO_SCAN = "nmap.ports.to.scan";
    private static final String NMAP_IPS_TO_SCAN = "nmap.ips.to.scan";
    private static final String NMAP_CRON = "nmap.cron.expression";
    private static final String NMAP_COMMAND = "nmap.command";

    @Autowired
    private NmapPortJobClient nmapPortJobClient;
    @Autowired
    private NmapService nmapService;
    @Autowired
    private JobConfigService jobConfigService;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            Map<String,String> nmapJobProperties = jobConfigService.getPropertiesByJobName(NMAP_PORTS_JOB);

            String xml = nmapPortJobClient.scanNmap(
                    nmapJobProperties.get(NMAP_COMMAND),
                    nmapJobProperties.get(NMAP_PORTS_TO_SCAN),
                    nmapJobProperties.get(NMAP_IPS_TO_SCAN));

            nmapService.save(xml);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (NmapParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
