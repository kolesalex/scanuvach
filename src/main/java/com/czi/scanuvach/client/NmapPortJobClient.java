package com.czi.scanuvach.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import de.martinspielmnann.nmapxmlparser.NmapParserException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NmapPortJobClient {


    public String scanNmap(String commandTemplate, String ports, String ips) throws InterruptedException, ExecutionException, NmapParserException, IOException {
        Process process;
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        if (isWindows) {
            throw new RuntimeException("Windows is not supported");
        } else {
            String command = String.format(commandTemplate, ports, ips);
            log.info("nmap command is about to run: [{}]", command);
            process = Runtime.getRuntime().exec(command);
        }
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream());
        Future<String> future = Executors.newSingleThreadExecutor().submit(streamGobbler);

        int exitCode = process.waitFor();
        assert exitCode == 0;

        return future.get();

    }

    private static class StreamGobbler implements Callable<String> {
        private InputStream inputStream;

        public StreamGobbler(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public String call() throws IOException {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        }
    }
}
