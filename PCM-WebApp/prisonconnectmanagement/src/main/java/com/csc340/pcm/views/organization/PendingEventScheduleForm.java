package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.entity.ValidatedEvents;
import com.csc340.pcm.service.EventSchedulerService;
import com.csc340.pcm.service.ValidatedEventsService;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PendingEventScheduleForm extends FormLayout {

    TextField organizationName = new TextField("Organization Name");
    TextField organizationEmail = new TextField("Organization Email");
    TextField organizationPhoneNumber = new TextField("Organization Phone Number");
    TextField organizationType = new TextField("Organization Type");
    TextField eventName = new TextField("Event Name");
    TextArea eventDetails = new TextArea("Event Details");
    TextField status = new TextField("Status");
    TextArea comment = new TextArea("Comment");
    DateTimePicker eventStartTime = new DateTimePicker("Event Start Date/Time");
    DateTimePicker eventEndTime = new DateTimePicker("Event End Date/Time");
    Button scheduleEvent = new Button("Schedule Event");
    Button cancelEventSchedule = new Button("Cancel");

    Binder<ValidatedEvents> binder = new BeanValidationBinder<>(ValidatedEvents.class);

    private ValidatedEvents validatedEvents;

    public PendingEventScheduleForm(List<ValidatedEvents> validatedEvents) {
        binder.bindInstanceFields(this);
        
        configureFormComponents();
        add(organizationName,
                organizationEmail,
                organizationPhoneNumber,
                organizationType,
                eventName,
                eventDetails,
                status,
                comment,
                eventStartTime,
                eventEndTime,
                configureButtons());
    }

    private void configureFormComponents() {

        setResponsiveSteps(new ResponsiveStep("0", 1));
        eventStartTime.setMin(LocalDateTime.now());
        eventStartTime.addValueChangeListener(e -> eventEndTime.setMin(e.getValue()));
        eventEndTime.setMax(LocalDateTime.now().plusDays(60));
        eventEndTime.setHelperText("Must be within 60 days from today");

    }

    private HorizontalLayout configureButtons() {

        scheduleEvent.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        scheduleEvent.addClickShortcut(Key.ENTER);
        cancelEventSchedule.addClickShortcut(Key.ESCAPE);

        //button listeners
        scheduleEvent.addClickListener(event -> validateAndSave());
        cancelEventSchedule.addClickListener(event -> fireEvent(new CancelEventSchedule(this)));

        return new HorizontalLayout(scheduleEvent, cancelEventSchedule);

    }

    private void validateAndSave() {
        try{
            binder.writeBean(validatedEvents);
            fireEvent(new EventSchedule(this, validatedEvents));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }

    public void setEvent(ValidatedEvents validatedEvents) {
        this.validatedEvents = validatedEvents;
        binder.readBean(validatedEvents);
    }

    public static abstract class EventFormEvent extends ComponentEvent<PendingEventScheduleForm> {
        private ValidatedEvents validatedEvents;

        protected EventFormEvent(PendingEventScheduleForm source, ValidatedEvents validatedEvents) {
            super(source, false);
            this.validatedEvents = validatedEvents;
        }

        public ValidatedEvents getEvent() {
            return validatedEvents;
        }
    }

    public static class EventSchedule extends EventFormEvent {
        EventSchedule(PendingEventScheduleForm source, ValidatedEvents validatedEvents) {
            super(source, validatedEvents);
        }
    }

    public static class CancelEventSchedule extends EventFormEvent {
        CancelEventSchedule(PendingEventScheduleForm source) {
            super(source, null);
        }

    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
