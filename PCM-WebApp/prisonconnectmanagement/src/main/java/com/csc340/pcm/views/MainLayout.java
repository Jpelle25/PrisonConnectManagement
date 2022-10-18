package com.csc340.pcm.views;

import com.csc340.pcm.views.DashboardView;
import com.csc340.pcm.security.SecurityService;
import com.csc340.pcm.views.ListView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;

import javax.annotation.security.RolesAllowed;

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
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        if(securityService.getAuthenticatedUser().getUsername() == "admin"){
            addToDrawer(new VerticalLayout(
                    listLink,
                    new RouterLink("Dashboard", DashboardView.class))
            );
        } else {
            addToDrawer(new VerticalLayout(
                    listLink
            ));
        }


    }


}