package com.csc340.pcm.generator;

import com.csc340.pcm.repository.PendingEventRegistrationRepository;
import com.csc340.pcm.entity.Prisoner;
import com.csc340.pcm.repository.PrisonerRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;

import static com.vaadin.exampledata.DataType.*;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PrisonerRepository prisonerRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());

            int seed = 123;

            logger.info("Generating demo data");

            Random r = new Random(seed);

            logger.info("Event generation");


            ExampleDataGenerator<Prisoner> prisonerGenerator = new ExampleDataGenerator<>(Prisoner.class, LocalDateTime.now());
            prisonerGenerator.setData(Prisoner::setFirstName, DataType.FIRST_NAME);
            prisonerGenerator.setData(Prisoner::setLastName, DataType.LAST_NAME);
            List<Prisoner> prisoners = prisonerGenerator.create(20, seed).stream().collect(Collectors.toList());

            prisonerRepository.saveAll(prisoners);

            logger.info("Got to here after event generation");

            logger.info("Generated demo data");
        };
    }

}
