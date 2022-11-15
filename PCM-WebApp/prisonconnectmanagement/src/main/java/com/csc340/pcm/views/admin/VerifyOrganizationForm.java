package com.csc340.pcm.views.admin;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.entity.ValidatedEvents;
import com.csc340.pcm.views.organization.PendingEventScheduleForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class VerifyOrganizationForm extends FormLayout {
    TextField organizationName = new TextField("Organization Name");
    TextField organizationEmail = new TextField("Organization Email");
    TextField organizationPhoneNumber = new TextField("Organization Phone Number");
    TextField organizationType = new TextField("Organization Type");
    TextField eventName = new TextField("Event Name");
    TextArea eventDetails = new TextArea("Event Details");
    TextField status = new TextField("Status");
    TextArea comment = new TextArea("Comment");
    Button approve = new Button("Approve Event");
//    Button deny = new Button("Deny Event");
    Button cancel = new Button("Cancel");
    Binder<PendingEventRegistration> binder = new Binder<>(PendingEventRegistration.class);

    PendingEventRegistration pendingEventRegistration;

    public VerifyOrganizationForm(List<PendingEventRegistration> pendingEventRegistrations) {
        binder.bindInstanceFields(this);
        configureFormComponents();
        add(
                organizationName,
                organizationEmail,
                organizationPhoneNumber,
                organizationType,
                eventName,
                eventDetails,
                comment,
                configureButtons()

        );

    }

    private void configureFormComponents() {
        setResponsiveSteps(new ResponsiveStep("0", 1));
    }

    private HorizontalLayout configureButtons() {

        approve.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addClickShortcut(Key.ESCAPE);

        approve.addClickListener(event -> validateAndSave());
        return new HorizontalLayout(approve, cancel);
    }
    public void setEvent(PendingEventRegistration pendingEventRegistration) {
        this.pendingEventRegistration = pendingEventRegistration;
        binder.readBean(pendingEventRegistration);
    }
    public static abstract class VerifyOrgEvent extends ComponentEvent<VerifyOrganizationForm> {


        private PendingEventRegistration pendingEventRegistration;

        protected VerifyOrgEvent(VerifyOrganizationForm source, PendingEventRegistration pendingEventRegistration) {
            super(source, false);
            this.pendingEventRegistration = pendingEventRegistration;
        }
        public PendingEventRegistration getEvent() {
            return pendingEventRegistration;
        }
    }
    public static class EventSchedule extends VerifyOrganizationForm.VerifyOrgEvent {

        EventSchedule(VerifyOrganizationForm source, PendingEventRegistration pendingEventRegistration) {
            super(source, pendingEventRegistration);
        }
    }
    public static class CancelEventSchedule extends VerifyOrgEvent {

        CancelEventSchedule(VerifyOrganizationForm source) {
            super(source, null);
        }

    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    private void validateAndSave() {
        try{
            binder.writeBean(pendingEventRegistration);
            fireEvent(new EventSchedule(this, pendingEventRegistration));
        }catch (ValidationException e){
            e.printStackTrace();
        }
    }
}
