package com.csc340.pcm.views.visitor;

import com.csc340.pcm.entity.Prisoner;
import com.csc340.pcm.service.PrisonerService;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@PageTitle("Visitor")
@Route(value = "Visitor", layout = MainLayout.class)
@RolesAllowed("ROLE_VISITOR")
public class VisitorView extends VerticalLayout {

    PrisonerService prisonerService;
    Grid<Prisoner> grid = new Grid<>(Prisoner.class);
    TextField prisoner = new TextField();
    PrisonersForm form;


    public VisitorView(PrisonerService prisonerService) {
        this.prisonerService = prisonerService;

        setSpacing(false);

        add(new H2("Welcome to Prison Connect Management (PCM)"));
        add(new Paragraph("Here you can select a meeting time with an Inmate"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        configureGrid();
        configureForm();

        add(getContent());
        updateList();

        add(new H2("Select a date"));
        DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setLabel("Appointment date and time");

        dateTimePicker.setMax(LocalDateTime.now().plusDays(30));
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

    /**
     * It gets the content of whatever is in the grid + form
     * @return content
     */
    private Component getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setWidthFull();

        return content;
    }

    /**
     * This makes a toolbar which allows the user to filter through items
     * @return toolbar
     */
//    private Component getToolbar(){
//        prisoner.setPlaceholder("Filter by name...");
//        prisoner.setClearButtonVisible(true);
//        prisoner.setValueChangeMode(ValueChangeMode.LAZY);
//
//        HorizontalLayout toolbar = new HorizontalLayout(prisoner);
//
//        return toolbar;
//    }

    /**
     * This makes the basic layout of the grid to show to the user in the Visitor View
     * @return nothing
     */
    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    /**
     * Configures the form of prisoners
     * @return nothing
     */
    private void configureForm() {
        form = new PrisonersForm();
        form.setWidth("25em");
    }

    /**
     * Updates the list with First & Last Names and an ID for each prisoner
     */
    private void updateList() {

        grid.setItems(prisonerService.findAllEvents());

//    }
//
//    /**
//     * This method saves the list of prisoners to a text file called: prisoner.txt
//     * @param prisoner List of prisoner objects
//     */
//    private void storeObject(List<Prisoner> prisoner){
//        try{
//            FileOutputStream fos = new FileOutputStream("prisoner.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            //oos.writeObject(prisoner);
//
//            for(int i = 0; i < prisoner.size(); i++){
////                oos.writeObject(prisoner.get(i).getFirstName() + " " + prisoner.get(i).getLastName() +
////                        "\n");
//            }
//
//            oos.flush();
//            oos.close();
//
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
}
