package s3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LogTestDemo {
	
	@Test
	void testLogs() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.log()
			/*.all()*/
			/*.body()*///Response Body
			/*.cookies()*/
			.headers();
	}
	
}
