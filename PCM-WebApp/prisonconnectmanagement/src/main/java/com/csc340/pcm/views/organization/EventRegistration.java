//package com.csc340.pcm.views.organization;
//
//import com.csc340.pcm.views.MainLayout;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.formlayout.FormLayout;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.notification.NotificationVariant;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.select.Select;
//import com.vaadin.flow.component.textfield.EmailField;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.value.ValueChangeMode;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//
//import javax.annotation.security.RolesAllowed;
//
//@PageTitle("EventRegistration")
//@Route(value = "EventRegistration", layout = MainLayout.class)
//@RolesAllowed("ROLE_ORGANIZATION")
//public class EventRegistration extends VerticalLayout {
//
//    TextField orgName = new TextField("Organization Name");
//    EmailField orgEmail = new EmailField("Organization Email");
//    TextField orgPhoneNo = new TextField("Organization Phone Number");
////    Select<String> orgType = new Select<>();
//    ComboBox<String> orgType = new ComboBox<>();
//    TextField eventName = new TextField("Event Name");
//    TextArea eventDetails = new TextArea("Event Details");
//    FormLayout orgForm = new FormLayout();
//
//
//    public EventRegistration() {
//        orgName.setMaxLength(20);
//        orgName.setValueChangeMode(ValueChangeMode.EAGER);
//        orgName.addValueChangeListener(e -> {
//            e.getSource().setHelperText(e.getValue().length() + "/" + "20");
//        });
//        orgName.setClearButtonVisible(true);
//        orgEmail.setHelperText("Format: JohnDoe@thisemail.com");
//        orgEmail.setClearButtonVisible(true);
//        orgPhoneNo.setHelperText("Format: (123)456-7890");
//        orgPhoneNo.setClearButtonVisible(true);
//        orgType.setLabel("Organization Type");
//        orgType.setItems("Fundraising", "Visitation", "Rehabilitation", "Arts & Craft");
//        orgType.setPlaceholder("Select Event Type...");
//        orgType.setClearButtonVisible(true);
//        eventName.setMaxLength(20);
//        eventName.setValueChangeMode(ValueChangeMode.EAGER);
//        eventName.addValueChangeListener(e -> {
//            e.getSource().setHelperText(e.getValue().length() + "/" + "20");
//        });
//        eventName.setClearButtonVisible(true);
//        eventDetails.setPlaceholder("Enter details here...");
//        eventDetails.setMaxLength(200);
//        eventDetails.setValueChangeMode(ValueChangeMode.EAGER);
//        eventDetails.addValueChangeListener(e -> {
//            e.getSource().setHelperText(e.getValue().length() + "/" + "200");
//        });
//        eventDetails.setClearButtonVisible(true);
//        orgForm.add(orgName, orgEmail, orgPhoneNo, orgType, eventName, eventDetails);
//        orgForm.setResponsiveSteps(
//                new FormLayout.ResponsiveStep("0", 3)
//        );
//        orgForm.setColspan(eventName, 1);
//        orgForm.setColspan(eventDetails, 3);
//        Button createEvent = new Button("Create Event");
//        createEvent.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        createEvent.addClickListener(event -> {
//            if ( orgName.isEmpty() ||
//                    orgEmail.isEmpty()||
//                    orgPhoneNo.isEmpty() ||
//                    orgType.isEmpty() ||
//                    eventName.isEmpty() ||
//                    eventDetails.isEmpty() ){
//
//                Notification emptyFormError = Notification.show("Please enter in all the fields for event registration");
//                emptyFormError.addThemeVariants(NotificationVariant.LUMO_ERROR);
//            }else{
//                Notification successForm = Notification.show("Event Successfully Submitted - Pending Approval");
//                successForm.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
//                orgName.clear();
//                orgEmail.clear();
//                orgPhoneNo.clear();
//                orgType.clear();
//                eventName.clear();
//                eventDetails.clear();
//            }
//
//        });
//        Button clearForm = new Button("Clear Form");
//        clearForm.addClickListener(event -> {
//                orgName.clear();
//                orgEmail.clear();
//                orgPhoneNo.clear();
//                orgType.clear();
//                eventName.clear();
//                eventDetails.clear();
//        });
//        HorizontalLayout buttonLayout = new HorizontalLayout(createEvent, clearForm);
//        add(orgForm, buttonLayout);
//    }
//}
