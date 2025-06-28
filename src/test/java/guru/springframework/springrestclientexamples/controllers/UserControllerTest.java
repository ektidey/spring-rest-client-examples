package guru.springframework.springrestclientexamples.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
class UserControllerTest {

    @Autowired
    ApplicationContext applicationContext;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    public void index() {
        webTestClient.get().uri("/")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    public void formPost() {
        var formData = new LinkedMultiValueMap<String, String>();
        formData.add("limit", "3");

        webTestClient.post().uri("/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .exchange()
            .expectStatus().isOk();
    }
}