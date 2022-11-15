package com.csc340.pcm.views.visitor;

import com.csc340.pcm.entity.Appointment;
import com.csc340.pcm.entity.Prisoner;
import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.List;

@Route(value = "PrisonersForm", layout = MainLayout.class)
@RolesAllowed("ROLE_VISITOR")
public class PrisonersForm extends FormLayout {
    private Prisoner prisoner;
    private Appointment appointment;
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    DateTimePicker dateTimePicker = new DateTimePicker();
    Button saveButton = new Button("Save");
    Binder<Prisoner> binder = new BeanValidationBinder<>(Prisoner.class);
    Binder<Appointment> binder2 = new BeanValidationBinder<>(Appointment.class);

    public PrisonersForm(List<Prisoner> prisoners) {
        binder.bindInstanceFields(this);
        add(
                firstName,
                lastName,
                dateTimePickerConfig(),
                saveButtonConfig()
        );
        
    }

    private Component saveButtonConfig() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_SUCCESS);
        saveButton.addClickListener(buttonClickEvent -> {
            Notification notification = Notification
                    .show("Appointment submitted");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        saveButton.addClickListener(event -> validateAndSave());
        return saveButton;
    }

    private void validateAndSave() {
        try {
            binder2.writeBean(appointment);
            fireEvent(new SaveEvent(this, appointment));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }


    private Component dateTimePickerConfig() {
        dateTimePicker.setLabel("Appointment date and time");
        dateTimePicker.setMax(LocalDateTime.now().plusDays(30));
        dateTimePicker.setMin(LocalDateTime.now().minusDays(1));
        dateTimePicker.setValue(LocalDateTime.now().plusDays(7));

        return dateTimePicker;
    }

    public void setPrisoner(Prisoner prisoner){
        this.prisoner = prisoner;
        binder.readBean(prisoner);
    }
    public static class PrisonerFormEvent extends ComponentEvent<PrisonersForm>{
        private final Appointment appointment;

        protected PrisonerFormEvent(PrisonersForm source, Appointment appointment){
            super(source, false);
            this.appointment = appointment;
        }
        public Appointment getAppointment(){
            return appointment;
        }

    }
    public static class SaveEvent extends PrisonerFormEvent {
        SaveEvent(PrisonersForm source, Appointment appointment){
            super(source, appointment);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}