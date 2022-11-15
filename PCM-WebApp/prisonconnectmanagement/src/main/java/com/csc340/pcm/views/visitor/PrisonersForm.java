package com.csc340.pcm.views.visitor;

import com.csc340.pcm.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@Route(value = "PrisonersForm", layout = MainLayout.class)
@RolesAllowed("ROLE_VISITOR")
public class PrisonersForm extends FormLayout {
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");


    public PrisonersForm() {
        add(
                firstName,
                lastName
        );
    }
}