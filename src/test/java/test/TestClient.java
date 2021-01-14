package test;

import dto.ClientDto;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TestClient {

    @Before
    public void configureRestAssured() {

        RestAssured.baseURI = "http://aqueous-forest-93594.herokuapp.com/api";

        RestAssured.requestSpecification = given()
                .filter(new ResponseLoggingFilter())
                .log().everything()
                .contentType(ContentType.JSON);

        RestAssured.responseSpecification = given()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void loginGetOrdersAddDeposit() {

        List<ClientDto> clientDto = given()
            .when().get("/clients?name=test&email=test@test.ru")
            .then()
            .extract().jsonPath().getList("", ClientDto.class);

        String userId = clientDto.get(0).get_id();

        given()
            .param("userId", userId)
            .when().get("/orders")
            .then();

        Map<String, Object> balance = new HashMap<>();
        balance.put("balance", clientDto.get(0).getBalance() + 100);

        given()
            .body(balance)
            .when().put("/clients/" + userId)
            .then()
            .assertThat().body("message", equalTo("Client updated"));
    }
}


