package pl.sii.eu.spockdemo.order

import com.jayway.restassured.RestAssured
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.containsString

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIT extends Specification {

    @Value('${local.server.port}')
    private int port

    def setup() {
        RestAssured.port = port
    }

    def "should respond with location upon creating new order"() {
        given:
        def request = given()
                .log().all()
        when:
        def response = request.post("/orders")
        then:
        response.then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header(HttpHeaders.LOCATION, containsString("/orders/"))
    }
}
