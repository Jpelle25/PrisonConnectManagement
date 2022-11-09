package com.csc340.pcm.views.visitor;

import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Route(value = "EventList", layout = MainLayout.class)
@PageTitle("EventsLists | PCM")
@RolesAllowed("ROLE_VISITOR")
public class EventList extends VerticalLayout {
    Grid<Events> grid = new Grid<>(Events.class, false);

    public EventList() {
        setSizeFull();
        configureGrid();
        add(grid);
        Button joinButton = new Button("Join");
        joinButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_SUCCESS);
        add(joinButton);
        joinButton.addClickListener(buttonClickEvent -> {
            Notification notification = Notification
                    .show("Joined Event!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("eventName", "date", "description");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        List<Events> events = Arrays.asList(new Events("BBQ", "07/04/2022", "Barbeque with MSS!!"),
                new Events("Carnival", "10/25/2022", "Come enjoy a great carnival!"));
        grid.setItems(events);
    }

}
