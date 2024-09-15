package project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class project {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	String sshKey = "";
	int sshKeyId;
	
	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://api.github.com").
				addHeader("Content-Type", "application/json").
				addHeader("Authorization", "").build();
		
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectResponseTime(lessThanOrEqualTo(3000L)).build();
	}
	
	@Test(priority = 1)
	public void postRequest() {
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("title", "TestAPIKey");
		reqBody.put("key", sshKey);
		
		Response response = given().spec(requestSpec).body(reqBody).when().post("/user/keys");
		response.prettyPrint();
		sshKeyId = response.then().extract().path("id");
		int responseCode = response.getStatusCode();
		Assert.assertEquals(responseCode, 201);
}
	
	@Test(priority = 2)
	public void GetRequest() {
		Response response = given().spec(requestSpec).pathParam("keyId", sshKeyId).when().get("/user/keys/{keyId}");
		int responseCode = response.getStatusCode();
			Assert.assertEquals(responseCode, 200);

	}
	@Test(priority = 3)
	public void DeleteRequest() {
		Response response = given().spec(requestSpec).pathParam("keyId", sshKeyId).when().delete("/user/keys/{keyId}");
		int responseCode = response.getStatusCode();
		Assert.assertEquals(responseCode, 204);
	}
	
}
