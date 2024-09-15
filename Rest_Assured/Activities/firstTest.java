package example;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class firstTest {

	@Test
	public void getRequestwithqueryParameter() {
		Response response = given().
									baseUri("https://petstore.swagger.io/v2/pet").
									header("Content-Type", "application/json").
									queryParam("status", "alive").
							when().
									get("/findByStatus");
		
		System.out.println(response.getHeaders());
		System.out.println(response.getBody().asString());
		System.out.println("----");
		System.out.println(response.getBody().asPrettyString());
		
		response.then().statusCode(200).body("[0].status", equalTo("alive"));
				
	}
	
	@Test
	public void getRequestwithpathParameter() {
		given().
			baseUri("https://petstore.swagger.io/v2/pet").
			header("Content-Type", "application/json").
			pathParam("petId", 1212).
			log().all(true).
		when().
			get("/{petId}").
		then().
			statusCode(200).
			body("status", equalTo("alive")).
			body("name", equalTo("Riley")).
			log().all(true);
	}
}
