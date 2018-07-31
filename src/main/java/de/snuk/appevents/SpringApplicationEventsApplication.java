package de.snuk.appevents;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringApplicationEventsApplication {

	@Bean
	Executor taskExecutor() {
		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setThreadNamePrefix("async-thread-");

		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationEventsApplication.class, args);
	}
}