package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.Event;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.time.LocalDateTime;
import java.util.List;

public class EventScheduleForm extends FormLayout {

    TextField organizationName = new TextField("Organization Name");
    TextField organizationEmail = new TextField("Organization Email");
    TextField organizationPhoneNumber = new TextField("Organization Phone Number");
    TextField organizationType = new TextField("Organization Type");
    TextField eventName = new TextField("Event Name");
    TextArea eventDetails = new TextArea("Event Details");
    DateTimePicker eventStartTime = new DateTimePicker("Event Start Date/Time");
    DateTimePicker eventEndTime = new DateTimePicker("Event End Date/Time");
    Button eventSchedule = new Button("Schedule Event");
    Button cancelEventSchedule = new Button("Cancel");

    public EventScheduleForm(List<Event> events) {
        
        configureForm();
        add(organizationName,
                organizationEmail,
                organizationPhoneNumber,
                organizationType,
                eventName,
                eventDetails,
                eventStartTime,
                eventEndTime,
                configureButtons());
    }

    private HorizontalLayout configureButtons() {

        eventSchedule.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        eventSchedule.addClickShortcut(Key.ENTER);
        cancelEventSchedule.addClickShortcut(Key.ESCAPE);

        //button listeners

        return new HorizontalLayout(eventSchedule, cancelEventSchedule);

    }

    private void configureForm() {

        setResponsiveSteps(new ResponsiveStep("0", 1));
        eventStartTime.setMin(LocalDateTime.now());
        eventStartTime.addValueChangeListener(e -> eventEndTime.setMin(e.getValue()));
        eventEndTime.setMax(LocalDateTime.now().plusDays(60));
        eventEndTime.setHelperText("Must be within 60 days from today");

    }

}
