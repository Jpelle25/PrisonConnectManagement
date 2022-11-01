//package com.csc340.pcm.views.organization;
//
//import com.csc340.pcm.API.Giphy;
//import com.csc340.pcm.views.MainLayout;
//import com.google.gson.Gson;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.html.H2;
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//
//import javax.annotation.security.RolesAllowed;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//@PageTitle("Organization")
//@Route(value = "Organization", layout = MainLayout.class)
//@RolesAllowed("ROLE_ORGANIZATION")
//public class OrganizationView extends VerticalLayout {
//
//    public OrganizationView(){
//
//        setSpacing(false);
//
//        Image img = new Image("images/empty-plant.png", "placeholder plant");
//        img.setWidth("200px");
//        add(img);
//
//        add(new H2("This place intentionally left empty"));
//        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
//        add(new H2("This is a test"));
//
//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");
//    }
//
//}
