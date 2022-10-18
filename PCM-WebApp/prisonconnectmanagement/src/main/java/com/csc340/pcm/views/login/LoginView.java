//package com.csc340.pcm.views.login;
//
//import com.csc340.pcm.views.MainLayout;
//import com.vaadin.flow.component.html.H2;
//import com.vaadin.flow.component.html.Image;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.router.RouteAlias;
//
//@PageTitle("Login")
//@Route(value = "Login", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
//public class LoginView extends VerticalLayout {
//
//    public LoginView() {
//        setSpacing(false);
//
//        Image img = new Image("images/empty-plant.png", "placeholder plant");
//        img.setWidth("200px");
//        add(img);
//
//        add(new H2("This place intentionally left empty"));
//        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
//
//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");
//    }
//
//}
