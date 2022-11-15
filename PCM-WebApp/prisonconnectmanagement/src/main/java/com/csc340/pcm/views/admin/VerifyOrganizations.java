package com.csc340.pcm.views.admin;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.entity.PendingScheduledEvents;
import com.csc340.pcm.entity.Request;
import com.csc340.pcm.entity.ValidatedEvents;
import com.csc340.pcm.service.EventRegistrationService;
import com.csc340.pcm.service.ValidatedEventsService;
import com.csc340.pcm.views.MainLayout;
import com.csc340.pcm.views.organization.EventRegistration;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.grid.Grid;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;
import java.util.Random;

@Route(value = "VerifyOrganizations", layout = MainLayout.class)
@PageTitle("Org-Requests | PCM")
@RolesAllowed("ROLE_ADMIN")
public class VerifyOrganizations extends VerticalLayout {

    ValidatedEventsService validatedEventsService;
    EventRegistrationService eventRegistrationService;

    VerifyOrganizationForm verifyOrganizationForm;

    Button approve = new Button("Approve");
    Button deny = new Button("Approve");


    Grid<PendingEventRegistration> selectionList = new Grid<>(PendingEventRegistration.class);
    TextArea commentBox = new TextArea();

    public VerifyOrganizations(ValidatedEventsService validatedEventsService,
                               EventRegistrationService eventRegistrationService) {
        this.validatedEventsService = validatedEventsService;
        this.eventRegistrationService = eventRegistrationService;

        setSizeFull();
        configureGrid();
        configureForm();
        add(getContent());
        updateList();
        closeEditor();


    }

    private void configureGrid() {
        setSizeFull();
        selectionList.setColumns("organizationName", "organizationEmail", "organizationPhoneNumber","organizationType",
                "eventName", "eventDetails");
        selectionList.getColumns().forEach(col -> col.setAutoWidth(true));
        selectionList.asSingleSelect().addValueChangeListener(event -> editEvent(event.getValue()));
    }

    private void editEvent(PendingEventRegistration pendingEventRegistration) {
        verifyOrganizationForm.setEvent(pendingEventRegistration);
        verifyOrganizationForm.setVisible(true);
    }

    private void configureForm() {
        verifyOrganizationForm = new VerifyOrganizationForm(eventRegistrationService.findAllEvents());
        verifyOrganizationForm.setWidth("25em");
        verifyOrganizationForm.addListener(VerifyOrganizationForm.EventSchedule.class, this::eventSchedule);
        verifyOrganizationForm.addListener(VerifyOrganizationForm.CancelEventSchedule.class, e -> closeEditor());
    }

    private void eventSchedule(VerifyOrganizationForm.EventSchedule event) {
        if(event.getSource().comment.isEmpty()){
            Notification.show("Please fill in all fields in the form");
        }
        else {
            validatedEventsService.saveEvent(new ValidatedEvents(
                    event.getSource().organizationName.getValue(),
                    event.getSource().organizationEmail.getValue(),
                    event.getSource().organizationPhoneNumber.getValue(),
                    event.getSource().organizationType.getValue(),
                    event.getSource().eventName.getValue(),
                    event.getSource().eventDetails.getValue(),
                    "Approved",
                    event.getSource().comment.getValue()
            ));
            Notification eventProcessed = Notification.show("Event Validated - Pending Schedule");
            eventProcessed.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            eventRegistrationService.deleteEvent(event.getEvent());
            updateList();
            closeEditor();
        }

    }

    private HorizontalLayout getContent() {
        HorizontalLayout eventScheduler = new HorizontalLayout(selectionList, verifyOrganizationForm);
        eventScheduler.setFlexGrow(2, selectionList);
        eventScheduler.setFlexGrow(1, verifyOrganizationForm);
        eventScheduler.setSizeFull();
        return eventScheduler;
    }

    private void updateList() {

        selectionList.setItems(eventRegistrationService.findAllEvents());

    }

    private void closeEditor() {
        verifyOrganizationForm.setEvent(null);
        verifyOrganizationForm.setVisible(false);
    }
    /**
     * This method creates a listener to select an entry in the list to then write a comment on and approve/deny
     */
    private void gridListener(){
        selectionList.addSelectionListener(selectionEvent -> {
            Optional<PendingEventRegistration> currentEvent = selectionEvent.getFirstSelectedItem();
            if(currentEvent.isPresent()){
                Notification.show("You selected " + currentEvent.get().getOrganizationName());
            }
        });
    }
//    private void updateGrid(){

//        selectionList.setItems(eventRegistrationService.findAllEvents());

//    }

//    /**
//     * this method creates a comment box for the request page
//     *
//     * @return the comment box to place onto the webpage
//     */
//    private TextArea CommentBox() {
//        int charLimit = 600;
//
//        TextArea commentBox = new TextArea();
//        commentBox.setLabel("Place comment here");
//        commentBox.setMaxLength(charLimit);
//        commentBox.setMinHeight("200px");
//        commentBox.setMaxHeight("200px");
//        commentBox.setValueChangeMode(ValueChangeMode.EAGER);
//        commentBox.addValueChangeListener(e -> {
//            e.getSource()
//                    .setHelperText(e.getValue().length() + "/" + charLimit);
//        });
//        commentBox.setMinWidth("600px");
//        commentBox.setMaxWidth("600px");
//        return commentBox;
//    }

//    /**
//     * This method creates the Accept button that sends the Accept flag to the database alongside the
//     * person/orgonization and a comment
//     *
//     * @return the accept button that will be displayed onto the webpage
//     */
//    private Button AcceptButton() {
//        Button acceptButton = new Button("Accept");
//        acceptButton.setEnabled(true);
//        return acceptButton;
//    }
//
//    /**
//     * this method creates the deny button that sends the Deny flag to the database alongside the
//     * person/orgonization and a comment
//     *
//     * @return the denybutton that will be dislayed onto the webpage
//     */
//    private Button DenyButton() {
//        Button denyButton = new Button("Deny");
//        denyButton.setEnabled(true);
//        return denyButton;
//    }
}