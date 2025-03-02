package s3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameters {
	
	//https://reqres.in/api/users?page=2
	
	@Test
	void testQueryAndPathParam() {
		
		given()
			/*.pathParam("myPath1", "api")*/
			.pathParam("myPath2", "users")
			.queryParam("page", "2")
			.queryParam("id", "5")
		
		.when()
			/*.get("https://reqres.in/{myPath1}/{myPath2}")*/
			.get("https://reqres.in/api/{myPath2}")
		
		.then()
			.statusCode(200)
			.log()
			.all();
		
	}
}
