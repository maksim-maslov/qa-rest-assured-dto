package org.example.qa.restassured.test;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.example.qa.restassured.dto.ClientDto;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;


public class TestClient {

    @Before
    public void configureRestAssured() {

        RestAssured.baseURI = "http://aqueous-forest-93594.herokuapp.com/api";

        RestAssured.requestSpecification = given()
                .filter(new ResponseLoggingFilter()) // logging response
                .log().everything() // logging request
                .contentType(ContentType.JSON); // adding header

        RestAssured.responseSpecification = given()
                .then()
                .statusCode(HttpStatus.SC_OK); // checking status-code
    }

    @Test
    public void loginGetOrdersAddDeposit() {

        // login and getting info about client
        List<ClientDto> clientDto = given()
            .when().get("/clients?name=test&email=test@test.ru")
            .then()
            .extract().jsonPath().getList("", ClientDto.class); // json-response to item of list of ClientDto type

        String userId = clientDto.get(0).get_id(); // item of map from json-response "_id"

        // getting orders of client
        given()
            .param("userId", userId)
            .when().get("/orders")
            .then();

        Map<String, Object> balance = new HashMap<>();
        balance.put("balance", clientDto.get(0).getBalance() + 100); // increment balance

        // updating balance
        given()
            .body(balance)
            .when().put("/clients/" + userId)
            .then()
            .assertThat().body("message", equalTo("Client updated"));
    }
}


