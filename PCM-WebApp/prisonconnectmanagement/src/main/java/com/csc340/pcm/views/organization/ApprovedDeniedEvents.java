package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.service.EventSchedulerService;
import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("ApprovedDeniedEvents")
@Route(value = "ApprovedDeniedEvents", layout = MainLayout.class)
@RolesAllowed("ROLE_ORGANIZATION")
public class ApprovedDeniedEvents extends VerticalLayout {

    Grid<PendingScheduledEvents> completeEvents = new Grid<>(PendingScheduledEvents.class);

    EventSchedulerService eventSchedulerService;

    public ApprovedDeniedEvents(EventSchedulerService eventSchedulerService) {
        this.eventSchedulerService = eventSchedulerService;
        setSizeFull();
        configureGrid();
        add(pageContent());
        updateList();

    }

    private void configureGrid() {

        completeEvents.setColumns("organizationName", "organizationEmail", "organizationPhoneNumber",
                "organizationType", "eventName", "status", "comment", "startTime", "endTime");
        completeEvents.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private HorizontalLayout pageContent() {

        HorizontalLayout pageLayout = new HorizontalLayout(completeEvents);
        pageLayout.setSizeFull();
        return pageLayout;
    }

    private void updateList() {
        completeEvents.setItems(eventSchedulerService.findAllCompleteEvents());
    }
}
