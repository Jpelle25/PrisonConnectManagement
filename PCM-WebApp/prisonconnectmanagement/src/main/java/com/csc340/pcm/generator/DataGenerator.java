package com.csc340.pcm.generator;

import com.csc340.pcm.repository.PendingEventRegistrationRepository;
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

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(PendingEventRegistrationRepository pendingEventRegistrationRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());

            int seed = 123;

            logger.info("Generating demo data");

            Random r = new Random(seed);

            logger.info("Event generation");

//            ExampleDataGenerator<Event> eventGenerator = new ExampleDataGenerator<>(Event.class, LocalDateTime.now());
//            eventGenerator.setData(Event::setOrganizationName, DataType.COMPANY_NAME);
//            eventGenerator.setData(Event::setOrganizationEmail, DataType.EMAIL);
//            eventGenerator.setData(Event::setOrganizationPhoneNumber, DataType.PHONE_NUMBER);
//            List<String> eventTypes = Arrays.asList("Fundraising", "Visitation", "Rehabilitation", "Arts & Craft");
//            eventGenerator.setData(Event::setEventName, DataType.WORD);
//            eventGenerator.setData(Event::setEventDetails, DataType.SENTENCE);
//            List<Event> events = eventGenerator.create(10, seed).stream().map(event -> {
//                event.setOrganizationType(eventTypes.get(r.nextInt(eventTypes.size())));
//                return event;
//            }).collect(Collectors.toList());
//
//            eventRepository.saveAll(events);

            logger.info("Got to here after event generation");

            logger.info("Generated demo data");
        };
    }

}
