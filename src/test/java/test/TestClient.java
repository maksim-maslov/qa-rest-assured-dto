package test;

import io.restassured.http.ContentType;
import org.junit.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TestClient {

    @Test
    public void loginGetOrdersAddDeposit() {

        String userId;

        userId = given()
                .baseUri("http://aqueous-forest-93594.herokuapp.com/api")
                .log().everything()
                .contentType(ContentType.JSON)
                .when().get("/clients?name=test&email=test@test.ru")
                .then().log().everything()
                .assertThat().statusCode(200)
                .extract().response().body().path("[0]._id");


        given()
                .baseUri("http://aqueous-forest-93594.herokuapp.com/api")
                .log().everything()
                .contentType(ContentType.JSON)
                .param("userId", userId)
                .when().get("/orders")
                .then().log().everything()
                .assertThat().statusCode(200);


        given()
                .baseUri("http://aqueous-forest-93594.herokuapp.com/api")
                .log().everything()
                .contentType(ContentType.JSON)
                .when().put("/clients/" + userId)
                .then().log().everything()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Client updated"));
    }
}
