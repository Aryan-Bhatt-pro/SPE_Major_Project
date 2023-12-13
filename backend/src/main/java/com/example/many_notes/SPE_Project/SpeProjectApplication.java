package com.example.many_notes.SPE_Project;

import com.example.many_notes.SPE_Project.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SpeProjectApplication implements CommandLineRunner {
	private static final Logger logger =
			LogManager.getLogger(SpeProjectApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SpeProjectApplication.class, args);
	}

	@Autowired
	private NoteRepo noteRepository;

	@Override
	public void run(String ...args) throws Exception{
		logger.debug("This is a debug message"); // DEBUG level
		logger.info("Spring Application Running"); // INFO level
		logger.warn("This is a warning message"); // WARN level
		logger.error("This is an error message"); // ERROR level
	}

}
