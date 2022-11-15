package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.security.SecurityService;
import com.csc340.pcm.service.EventRegistrationService;
import com.csc340.pcm.service.EventSchedulerService;
import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;
import java.time.format.DateTimeFormatter;

@PageTitle("EventScheduler")
@Route(value = "EventScheduler", layout = MainLayout.class)
@RolesAllowed({"ROLE_ORGANIZATION", "ROLE_VISITOR"})
public class EventScheduler extends VerticalLayout {

    Grid<PendingEventRegistration> approvedEvents = new Grid<>(PendingEventRegistration.class);
    PendingEventScheduleForm pendingEventScheduleForm;
    EventRegistrationService eventRegistrationService;
    EventSchedulerService eventSchedulerService;

    public EventScheduler(EventRegistrationService eventRegistrationService,
                          EventSchedulerService eventSchedulerService) {

        this.eventSchedulerService = eventSchedulerService;
        this.eventRegistrationService = eventRegistrationService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getContent());
        updateList();
        closeEditor();

    }

    private void configureGrid() {

        approvedEvents.setSizeFull();
        approvedEvents.setColumns("organizationName", "organizationEmail", "organizationPhoneNumber", "organizationType", "eventName");
        approvedEvents.getColumns().forEach(col -> col.setAutoWidth(true));
        approvedEvents.asSingleSelect().addValueChangeListener(event -> editEvent(event.getValue()));

    }

    private void configureForm() {

        pendingEventScheduleForm = new PendingEventScheduleForm(eventRegistrationService.findAllEvents());
        pendingEventScheduleForm.setWidth("25em");
        pendingEventScheduleForm.addListener(PendingEventScheduleForm.EventSchedule.class, this::eventSchedule);
        pendingEventScheduleForm.addListener(PendingEventScheduleForm.CancelEventSchedule.class, e -> closeEditor());

    }

    private HorizontalLayout getContent() {

        HorizontalLayout eventSchedulerLayout = new HorizontalLayout(approvedEvents, pendingEventScheduleForm);
        eventSchedulerLayout.setFlexGrow(2, approvedEvents);
        eventSchedulerLayout.setFlexGrow(1, pendingEventScheduleForm);
        eventSchedulerLayout.setSizeFull();
        return eventSchedulerLayout;

    }

    private void updateList() {

        approvedEvents.setItems(eventRegistrationService.findAllEvents());

    }

    private void closeEditor() {
        pendingEventScheduleForm.setEvent(null);
        pendingEventScheduleForm.setVisible(false);
    }

    private void eventSchedule(PendingEventScheduleForm.EventSchedule event){
//        if(event.getEvent().)
//        eventRegistrationService.deleteEvent(event.getEvent());
//        eventRegistrationService.saveEvent(event.getEvent());
//        this.pendingEventScheduleForm
        if(event.getSource().eventStartTime.isEmpty() || event.getSource().eventEndTime.isEmpty()){
            Notification.show("please fill in all fields in the form");
        }else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
            eventSchedulerService.saveEvent(new PendingScheduledEvents(

                    event.getSource().organizationName.getValue(),
                    event.getSource().organizationEmail.getValue(),
                    event.getSource().organizationPhoneNumber.getValue(),
                    event.getSource().organizationType.getValue(),
                    event.getSource().eventName.getValue(),
                    event.getSource().eventDetails.getValue(),
                    event.getSource().eventStartTime.getValue().format(formatter),
                    event.getSource().eventEndTime.getValue().format(formatter)
                ));
            Notification.show("Event Successfully Submitted - Pending Schedule Approval");
            eventRegistrationService.deleteEvent(event.getEvent());
            updateList();
            closeEditor();
        }
    }

    private void editEvent(PendingEventRegistration pendingEventRegistration) {

        pendingEventScheduleForm.setEvent(pendingEventRegistration);
        pendingEventScheduleForm.setVisible(true);

    }

}
