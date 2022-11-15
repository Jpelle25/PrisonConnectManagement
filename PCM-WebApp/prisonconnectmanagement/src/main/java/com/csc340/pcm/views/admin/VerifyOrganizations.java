//
//
//

package com.csc340.pcm.views.admin;

import com.csc340.pcm.entity.PendingEventRegistration;
import com.csc340.pcm.service.EventRegistrationService;
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

    EventRegistrationService eventRegistrationService;
    Grid<PendingEventRegistration> selectionList = new Grid<>(PendingEventRegistration.class);

    public VerifyOrganizations(EventRegistrationService eventRegistrationService) {
        this.eventRegistrationService = eventRegistrationService;

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
            Optional<PendingEventRegistration> optionalEvent = selectionEvent.getFirstSelectedItem();
            if(optionalEvent.isPresent()){
                Notification.show("You selected " + optionalEvent.get().getOrganizationName());
            }
        });
    }

    private HorizontalLayout getContent() {
        HorizontalLayout eventScheduler = new HorizontalLayout(selectionList);
        eventScheduler.setSizeFull();
        return eventScheduler;
    }

    private void updateGrid(){
        selectionList.setItems(eventRegistrationService.findAllEvents());
    }

    private void configureGrid() {
        Grid<PendingEventRegistration> selectionList = new Grid<>(PendingEventRegistration.class, false);
        selectionList.setColumns("organizationName", "organizationEmail", "organizationPhoneNumber","organizationType","eventName");
        selectionList.getColumns().forEach(col -> col.setAutoWidth(true));
        selectionList.asSingleSelect().addValueChangeListener(event -> editEvent(event.getValue()));
    }

    private void editEvent(PendingEventRegistration value) {
        //value.setEvent
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
     * person/orgonization and a comment
     *
     * @return the accept button that will be displayed onto the webpage
     */
    private Button AcceptButton() {
        Button acceptButton = new Button("Accept");
        acceptButton.setEnabled(true);
        acceptButton.addClickListener(clickEvent -> {

        });
        return acceptButton;
    }

    /**
     * this method creates the deny button that sends the Deny flag to the database alongside the
     * person/orgonization and a comment
     *
     * @return the denybutton that will be dislayed onto the webpage
     */
    private Button DenyButton() {
        Button denyButton = new Button("Deny");
        denyButton.setEnabled(true);
        denyButton.addClickListener(buttonClickEvent -> {

        });
        return denyButton;
    }
}
