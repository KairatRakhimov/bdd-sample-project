package com.kairatr.bdd.definitions;

import com.kairatr.bdd.BddApplication;
import com.kairatr.bdd.entity.Person;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonStepDefinitions {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity responseEntity;
    private Person body;

    static {
        // Tried some solutions to start SpringBoot app with Cucumber but without success...
        SpringApplication.run(BddApplication.class);
    }

    @When("^client call \"([^\"]*)\" \"([^\"]*)\"$")
    public void clientCallApi(String httpMethod, String url) {
        HttpMethod method = HttpMethod.resolve(httpMethod);
        responseEntity = restTemplate.exchange(url, method, new HttpEntity<>(body), Person.class);
    }

    @Then("^client should receive status (\\d+)$")
    public void clientShouldReceiveStatus(int status) {
        HttpStatus expectedStatus = HttpStatus.resolve(status);
        assertThat(responseEntity.getStatusCode()).as("wrong response status").isEqualTo(expectedStatus);
    }

    @And("^following person in response:$")
    public void followingPersonInResponse(@Transpose List<Person> personList) {
        Person person = personList.get(0);
        assertThat(person.getId()).describedAs("Person has wrong ID").isEqualTo(1L);
        assertThat(person.getFirstName()).isEqualTo(body.getFirstName());
        assertThat(person.getLastName()).isEqualTo(body.getLastName());
    }

    @Given("^following person:$")
    public void followingPerson(@Transpose List<Person> personList) {
        body = personList.get(0);
    }
}
