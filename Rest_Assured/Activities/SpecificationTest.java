package example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;
import java.util.Map;

public class specificationTest {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	int petId;
	
	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://petstore.swagger.io/v2/pet").
				addHeader("Content-Type", "application/json").build();
	
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectResponseTime(lessThanOrEqualTo(3000L)).build();
	}
	
	@Test(priority = 1)
	public void postRequest() {
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("id", "1806");
		reqBody.put("name", "Rocky");
		reqBody.put("status", "alive");
		
		Response response = given().spec(requestSpec).body(reqBody).when().post();
		petId = response.then().extract().path("id");
		response.then().spec(responseSpec).body("name", equalTo("Rocky"));
	}
	
	@Test(dependsOnMethods = {"postRequest"}, priority = 2)
	public void getRequest() {
		given().spec(requestSpec).pathParam("petId", petId).when().get("/{petId}").then().spec(responseSpec).body("name", equalTo("Rocky"));
	}
	
	@Test(dependsOnMethods = {"postRequest"}, priority = 3)
	public void deleteRequest() {
		given().spec(requestSpec).pathParam("petId", petId).when().delete("/{petId}").then().spec(responseSpec).body("message", equalTo(""+petId)).log().all();
	}

}
