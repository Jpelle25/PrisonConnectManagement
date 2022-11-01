package com.csc340.pcm.views;

import com.csc340.pcm.security.SecurityService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Welcome")
@PermitAll
public class UserWelcome extends VerticalLayout {

    private SecurityService securityService;

    public UserWelcome(SecurityService securityService) {

        if(securityService.getAuthenticatedUser().getUsername() == "admin"){
            add(new H1("Welcome Admins"));
        }
        else if(securityService.getAuthenticatedUser().getUsername() == "organ"){
            add(new H1("Welcome Organizations"));
        }
        else{
            add(new H1("Welcome Visitors"));
        }

    }

}