package com.csc340.pcm.views;

import com.csc340.pcm.security.SecurityService;
import com.csc340.pcm.views.admin.AcceptDenyView;
import com.csc340.pcm.views.admin.AdminView;
import com.csc340.pcm.views.organization.ApprovedDeniedEvents;
import com.csc340.pcm.views.organization.EventRegistration;
import com.csc340.pcm.views.organization.EventScheduler;
import com.csc340.pcm.views.visitor.VisitorView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    private SecurityService securityService;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("PCM");
        logo.addClassNames("text-l", "m-m");

        Button  logout = new Button("Log out", e -> securityService.logout());


        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                logo,
                logout
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink visitorLink = new RouterLink("Visitor", VisitorView.class);
        visitorLink.setHighlightCondition(HighlightConditions.sameLocation());

        //Admin View Direction
        if(securityService.getAuthenticatedUser().getUsername() == "admin"){
            addToDrawer(new VerticalLayout(
                    new RouterLink("Admin Dashboard", AdminView.class),
                    new RouterLink("Pending Requests", AcceptDenyView.class)
            ));
        }

        //Organization View Direction
        else if(securityService.getAuthenticatedUser().getUsername() == "organ"){

            VerticalLayout organizationTabs = new VerticalLayout(
                    new RouterLink("Approved/Denied Events", ApprovedDeniedEvents.class),
                    new RouterLink("Event Registration", EventRegistration.class),
                    new RouterLink("Event Scheduler", EventScheduler.class)
            );
            addToDrawer(organizationTabs);

        }

        //Visitor View Direction
        else{
            addToDrawer(new VerticalLayout(visitorLink));
        }

    }

}