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
import java.util.List;

@Route(value = "PrisonersForm", layout = MainLayout.class)
@RolesAllowed("ROLE_VISITOR")
public class PrisonersForm extends FormLayout{
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button cancel = new Button("Cancel");

    public PrisonersForm(){
        add(
                firstName,
                lastName
                //creatButtonLayout()
        );
    }

//    private Component creatButtonLayout() {
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
//        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
//
//        save.addClickShortcut(Key.ENTER);
//        cancel.addClickShortcut(Key.ESCAPE);
//
//        return new HorizontalLayout(save, delete, cancel);
//    }
}
