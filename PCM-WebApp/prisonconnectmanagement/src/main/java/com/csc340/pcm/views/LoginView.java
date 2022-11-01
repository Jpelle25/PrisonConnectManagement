package com.csc340.pcm.views;

import com.csc340.pcm.API.Giphy;
import com.google.gson.Gson;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Route("login")
@PageTitle("Login | Prison Connect Management")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView() throws URISyntaxException, IOException, InterruptedException {

//        HttpRequest getRequest = HttpRequest.newBuilder()
//                .uri(new URI("https://api.giphy.com/v1/gifs/xUPGGDNsLvqsBOhuU0?api_key=ZjxFtJ4r2cjujC3zoAxwMpEdJRHne7iX"))
//                .GET()
//                .build();
//
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
//        Giphy giphy = new Giphy();
//        Gson gson = new Gson();
//
//        giphy = gson.fromJson(getResponse.body(), Giphy.class);
//        Image img2 = new Image(giphy.getData().getImages().getFixed_height_downsampled().getUrl(), giphy.getData().getTitle());
//        img2.setWidth("450px");


        addClassName("login-view");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

//        add(new H1("Prison Connect Management"),img2, login);
        add(new H1("Prison Connect Management"),apiLoginImage(), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    private Image apiLoginImage() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.giphy.com/v1/gifs/xUPGGDNsLvqsBOhuU0?api_key=ZjxFtJ4r2cjujC3zoAxwMpEdJRHne7iX"))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Giphy giphy = new Giphy();
        Gson gson = new Gson();

        giphy = gson.fromJson(getResponse.body(), Giphy.class);
        Image loginImage = new Image(giphy.getData().getImages().getFixed_height_downsampled().getUrl(), giphy.getData().getTitle());
        loginImage.setWidth("450px");
        return loginImage;
    }
}