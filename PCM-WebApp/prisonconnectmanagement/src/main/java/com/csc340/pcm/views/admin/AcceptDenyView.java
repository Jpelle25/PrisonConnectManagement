//
//
//

package com.csc340.pcm.views.admin;

import com.csc340.pcm.entity.Request;
import com.csc340.pcm.views.MainLayout;
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
import java.util.List;

@Route(value = "PendingRequests", layout = MainLayout.class)
@PageTitle("Requests | PCM")
@RolesAllowed("ROLE_ADMIN")
public class AcceptDenyView extends VerticalLayout {


    public AcceptDenyView() {
        add(SelectionList(),CommentBox());
        HorizontalLayout buttons = new HorizontalLayout(AcceptButton(),DenyButton());
        buttons.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        add(buttons);
    }

    private Grid SelectionList(){
        Grid<Request> selectionList = new Grid<>(Request.class, false);
        selectionList.addColumn(Request::getRequestType).setHeader("Is Orgonization?");
        selectionList.addColumn(Request::getName).setHeader("Requester Name");
        selectionList.addColumn(Request::getDate).setHeader("Date Requested");

        List<Request> requests;

        selectionList.addSelectionListener(selectionEvent -> {
            Notification.show("You selected a thing");
        });

        return selectionList;
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

    /*/**
     * this method creates the selection to accept or deny requests
     *
     * @return the radio button object to place it on the webpage
     */
    /*public RadioButtonGroup AcceptDenyButton() {
        RadioButtonGroup<String> acceptDeny = new RadioButtonGroup<>();
        final String[] test = {""};
        acceptDeny.setLabel("Request status");
        acceptDeny.setItems("Approve", "Deny");
        acceptDeny.addValueChangeListener(radioButtonGroupStringComponentValueChangeEvent -> {
            test[0] = acceptDeny.getValue();
            Notification.show("Value changed to " + test[0]);

            if (test[0].equals("Approve") || test[0].equals("Deny")){
                SubmitButton().setEnabled(true);
            }
        });
        acceptDeny.setReadOnly(false);
        acceptDeny.setRequiredIndicatorVisible(true);
        return acceptDeny;
    }*/

    /**
     * This method creates the Accept button that sends the Accept flag to the database alongside the
     * person/orgonization and a comment
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
