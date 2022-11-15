package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.service.EventSchedulerService;
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
    DateTimePicker eventStartTime = new DateTimePicker("Event Start Date/Time");
    DateTimePicker eventEndTime = new DateTimePicker("Event End Date/Time");
    Button scheduleEvent = new Button("Schedule Event");
    Button cancelEventSchedule = new Button("Cancel");


    EventSchedulerService eventSchedulerService;

    Binder<PendingEventRegistration> binder = new BeanValidationBinder<>(PendingEventRegistration.class);

    private PendingEventRegistration pendingEventRegistration;

    public PendingEventScheduleForm(List<PendingEventRegistration> pendingEventRegistrations) {
        binder.bindInstanceFields(this);
        
        configureFormComponents();
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
            binder.writeBean(pendingEventRegistration);
            fireEvent(new EventSchedule(this, pendingEventRegistration));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }

    public void setEvent(PendingEventRegistration pendingEventRegistration) {
        this.pendingEventRegistration = pendingEventRegistration;
        binder.readBean(pendingEventRegistration);
    }

    public static abstract class EventFormEvent extends ComponentEvent<PendingEventScheduleForm> {
        private PendingEventRegistration pendingEventRegistration;

        protected EventFormEvent(PendingEventScheduleForm source, PendingEventRegistration pendingEventRegistration) {
            super(source, false);
            this.pendingEventRegistration = pendingEventRegistration;
        }

        public PendingEventRegistration getEvent() {
            return pendingEventRegistration;
        }
    }

    public static class EventSchedule extends EventFormEvent {
        EventSchedule(PendingEventScheduleForm source, PendingEventRegistration pendingEventRegistration) {
            super(source, pendingEventRegistration);
//            if(source.eventStartTime.isEmpty() || source.eventEndTime.isEmpty()){
//                Notification.show("Please enter in all the fields for event registration");
//            }
//            else{
//                Notification.show("Event Successfully Submitted - Pending Schedule Approval");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
////                Notification.show(source.eventStartTime.getValue().format(formatter));
////                Notification.show(source.eventEndTime.getValue().format(formatter));
//                source.eventSchedulerService.saveEvent(new PendingScheduledEvents(
//                        source.organizationName.getValue(),
//                        source.organizationEmail.getValue(),
//                        source.organizationPhoneNumber.getValue(),
//                        source.organizationType.getValue(),
//                        source.eventName.getValue(),
//                        source.eventDetails.getValue(),
//                        source.eventStartTime.getValue().format(formatter),
//                        source.eventEndTime.getValue().format(formatter)
//                ));
//                Notification.show("got to here after saving new repo entry");
//            }
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
