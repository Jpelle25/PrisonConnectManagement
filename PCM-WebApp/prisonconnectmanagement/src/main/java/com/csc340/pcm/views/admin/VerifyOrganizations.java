//Created By Marcus Harrington
//Last updated 11/15/22
//This file Verifies organizations that request approval from the organ. page
//

package com.csc340.pcm.views.admin;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.Request;
import com.csc340.pcm.entity.ValidatedEvents;
import com.csc340.pcm.service.EventRegistrationService;
import com.csc340.pcm.service.ValidatedEventsService;
import com.csc340.pcm.views.MainLayout;
import com.csc340.pcm.views.organization.EventRegistration;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.grid.Grid;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@Route(value = "VerifyOrganizations", layout = MainLayout.class)
@PageTitle("Org-Requests | PCM")
@RolesAllowed("ROLE_ADMIN")
public class VerifyOrganizations extends VerticalLayout {

    ValidatedEventsService validatedEventsService;
    EventRegistrationService eventRegistrationService;

    Grid<PendingEventRegistration> selectionList = new Grid<>(PendingEventRegistration.class);
    TextArea commentBox = new TextArea();
    Optional<PendingEventRegistration> currentEvent;

    public VerifyOrganizations(ValidatedEventsService validatedEventsService, EventRegistrationService eventRegistrationService) {
        this.validatedEventsService = validatedEventsService;
        this.eventRegistrationService = eventRegistrationService;
        eventRegistrationService.saveEvent(new PendingEventRegistration("1","1","1","1","1","1"));

        configureGrid();
        HorizontalLayout buttons = new HorizontalLayout(AcceptButton(), DenyButton());
        buttons.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(getContent(),CommentBox(),buttons);
        updateGrid();
        gridListener();


    }

    /**
     * This method creates a listener to select an entry in the list to then write a comment on and approve/deny
     */
    private void gridListener(){
        selectionList.addSelectionListener(selectionEvent -> {
            currentEvent = selectionEvent.getFirstSelectedItem();
            if(currentEvent.isPresent()){
                Notification.show("You selected " + currentEvent.get().getOrganizationName());
            }
        });
    }

    /**
     * This method allows the grid to be places properly in the webpage
     * @return the grid
     */
    private HorizontalLayout getContent() {
        HorizontalLayout eventScheduler = new HorizontalLayout(selectionList);
        eventScheduler.setSizeFull();
        return eventScheduler;
    }

    /**
     * updates the Grid's information in real time
     */
    private void updateGrid(){
        selectionList.setItems(eventRegistrationService.findAllEvents());
    }

    /**
     * Configures the grid that will be used to select and verify organizations
     */
    private void configureGrid() {
        Grid<PendingEventRegistration> selectionList = new Grid<>(PendingEventRegistration.class, false);
        selectionList.setColumns("organizationName", "organizationEmail", "organizationPhoneNumber","organizationType", "eventName", "eventDetails");
        selectionList.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    /**
     * this method creates a comment box for the request page
     *
     * @return the comment box to place onto the webpage
     */
    private TextArea CommentBox() {
        int charLimit = 600;

        TextArea commentBox = new TextArea();
        commentBox.setLabel("Place comment here");
        commentBox.setMaxLength(charLimit);
        commentBox.setMinHeight("200px");
        commentBox.setMaxHeight("200px");
        commentBox.setValueChangeMode(ValueChangeMode.EAGER);
        commentBox.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + charLimit);
        });
        commentBox.setMinWidth("600px");
        commentBox.setMaxWidth("600px");
        return commentBox;
    }

    /**
     * This method creates the Accept button that sends the Accept flag to the database alongside the
     * orgonization and a comment
     *
     * @return the accept button that will be displayed onto the webpage
     */
    private Button AcceptButton() {
        Button acceptButton = new Button("Accept");
        acceptButton.setEnabled(true);
        acceptButton.addClickListener(buttonClickEvent -> {
            if(commentBox.getValue().isEmpty()){
                Notification.show("Please enter a comment.");
            }
            else{
                validatedEventsService.saveEvent(new ValidatedEvents(
                        currentEvent.get().getOrganizationName(),
                        currentEvent.get().getOrganizationEmail(),
                        currentEvent.get().getOrganizationPhoneNumber(),
                        currentEvent.get().getOrganizationType(),
                        currentEvent.get().getEventName(),
                        currentEvent.get().getEventDetails(),
                        "Approved",
                        commentBox.getValue()
                ));
            }
        });
        commentBox.clear();
        eventRegistrationService.deleteEvent(currentEvent.get());
        return acceptButton;
    }

    /**
     * this method creates the deny button that sends the Deny flag to the database alongside the
     * orgonization and a comment
     *
     * @return the denybutton that will be dislayed onto the webpage
     */
    private Button DenyButton() {
        Button denyButton = new Button("Deny");
        denyButton.setEnabled(true);
        denyButton.addClickListener(buttonClickEvent -> {
            if(commentBox.getValue().isEmpty()){
                Notification.show("Please enter a comment.");
            }
            else{
                validatedEventsService.saveEvent(new ValidatedEvents(
                        currentEvent.get().getOrganizationName(),
                        currentEvent.get().getOrganizationEmail(),
                        currentEvent.get().getOrganizationPhoneNumber(),
                        currentEvent.get().getOrganizationType(),
                        currentEvent.get().getEventName(),
                        currentEvent.get().getEventDetails(),
                        "Deny",
                        commentBox.getValue()
                ));
            }
        });
        commentBox.clear();
        eventRegistrationService.deleteEvent(currentEvent.get());
        return denyButton;
    }
}
