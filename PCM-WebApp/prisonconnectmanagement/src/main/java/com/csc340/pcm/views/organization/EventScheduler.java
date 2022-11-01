package com.csc340.pcm.views.organization;

import com.csc340.pcm.entity.Contact;
import com.csc340.pcm.entity.Event;
import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("EventScheduler")
@Route(value = "EventScheduler", layout = MainLayout.class)
@RolesAllowed("ROLE_ORGANIZATION")
public class EventScheduler extends VerticalLayout {

    Grid<Event> grid = new Grid<>(Event.class);


    public EventScheduler() {
    }
}
