package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.Event;
import com.csc340.pcm.service.EventService;
import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

@PageTitle("EventScheduler")
@Route(value = "EventScheduler", layout = MainLayout.class)
@RolesAllowed("ROLE_ORGANIZATION")
public class EventScheduler extends VerticalLayout {

    Grid<Event> approvedEvents = new Grid<>(Event.class);
    EventScheduleForm eventScheduleForm;
    EventService eventService;

    public EventScheduler(EventService eventService) {

        this.eventService = eventService;
        setSizeFull();
        configureGrid();
        configureForm();
        add(getContent());
        updateList();

    }

    private HorizontalLayout getContent() {

        HorizontalLayout eventScheduler = new HorizontalLayout(approvedEvents, eventScheduleForm);
        eventScheduler.setFlexGrow(2, approvedEvents);
        eventScheduler.setFlexGrow(1, eventScheduleForm);
        eventScheduler.setSizeFull();
        return eventScheduler;

    }

    private void configureForm() {

        eventScheduleForm = new EventScheduleForm(eventService.findAllEvents());
        eventScheduleForm.setWidth("25em");

    }

    private void configureGrid() {

        approvedEvents.setSizeFull();
        approvedEvents.setColumns("organizationName", "organizationEmail", "organizationPhoneNumber", "organizationType", "eventName");
        approvedEvents.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void updateList() {

        approvedEvents.setItems(eventService.findAllEvents());

    }

}
