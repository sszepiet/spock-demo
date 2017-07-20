package pl.sii.eu.spockdemo.product

import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.Matchers.contains

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerIT extends Specification {

    @Value('${local.server.port}')
    private int port

    def setup() {
        RestAssured.port = port
    }

    def "should return all available products"() {
        given:
        def request = given()
                .accept(ContentType.JSON)
                .log().all()
        when:
        def response = request.get("/products")
        then:
        response.then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("name", contains('Stock', 'Herring', 'Orange Juice'))
    }
}
