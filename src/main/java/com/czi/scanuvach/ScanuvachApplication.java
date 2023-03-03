package com.czi.scanuvach;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.quartz.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import de.martinspielmnann.nmapxmlparser.NmapParserException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaRepositories
@EnableJpaAuditing
public class ScanuvachApplication {

	private final Scheduler scheduler;

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, NmapParserException {

		SpringApplication.run(ScanuvachApplication.class, args);

	}

	@SneakyThrows
	@PostConstruct
	public void postConstruct(){
//		scheduler.getCurrentlyExecutingJobs()
//				.stream()
//				.map(ctx -> ctx.getJobDetail().)
		scheduler.start();
	}
}


