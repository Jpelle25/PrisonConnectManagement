package com.csc340.pcm.views.visitor;

import com.csc340.pcm.generator.DataGenerator;
import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@PageTitle("Visitor")
@Route(value = "Visitor", layout = MainLayout.class)
@RolesAllowed("ROLE_VISITOR")
public class VisitorView extends VerticalLayout {
    Grid<Prisoner> grid = new Grid<>(Prisoner.class);
    TextField prisoner = new TextField();
    PrisonersForm form;

    public VisitorView() {
        setSpacing(false);

        add(new H2("Welcome to Prison Connect Management (PCM)"));
        add(new Paragraph("Here you can select a meeting time with an Inmate"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());

        add(new H2("Select a date"));
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLabel("Appointment date and time");

        dateTimePicker.setMax(LocalDateTime.now().plusDays(60));
        dateTimePicker.setValue(LocalDateTime.now().plusDays(7));
        add(dateTimePicker);

        Button saveButton = new Button("Save");
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_SUCCESS);
        add(saveButton);
        saveButton.addClickListener(buttonClickEvent -> {
            Notification notification = Notification
                    .show("Appointment submitted");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

    }

    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setWidthFull();

        return content;
    }

    private Component getToolbar(){
        prisoner.setPlaceholder("Filter by name...");
        prisoner.setClearButtonVisible(true);
        prisoner.setValueChangeMode(ValueChangeMode.LAZY);

        HorizontalLayout toolbar = new HorizontalLayout(prisoner);

        return toolbar;
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        List<Prisoner> prisoners = Arrays.asList(new Prisoner("john", "doe"), new Prisoner("jane", "doe"));
        grid.setItems(prisoners);
    }

    private void configureForm() {
        form = new PrisonersForm();
        form.setWidth("25em");
    }
}
