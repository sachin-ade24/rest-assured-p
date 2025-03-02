package s1;

/*
 * given(): pre-requisite
 * content type, set cookies, add auth, add param, set headers info etc..
 * 
 * when(): API requests
 * get, post, put, patch, delete
 * 
 * then(): validation
 * validate status code, extract response, extract headers, cookies, response body
 * */

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;


public class HTTPRequests {
	
	int id;
	
	@Test(priority=1)
	void getUser() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log()
			.all();
	}
	
	void createUser1() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Sachin");
		data.put("job", "QA Engineer");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			
		.then()
			.statusCode(201)
			.log()
			.all();
		
	}
	
	@Test(priority=2)
	void createUser() {
		
		HashMap data = new HashMap();
		
		data.put("name", "Sachin");
		data.put("job", "QA Engineer");
		
		id = given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath()
			.getInt("id");
		
	}
	
	@Test(priority=3, dependsOnMethods = "createUser")
	void updateUser() {
		
		HashMap object = new HashMap();
		
		object.put("name", "Suraj");
		object.put("job", "Manager");
		
		given()
			.contentType("application/json")
			.body(object)
		
		.when()
			.put("https://reqres.in/api/users/" + id)
		
		.then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(priority=4, dependsOnMethods = {"createUser", "updateUser"})
	void deleteUser() {
		
		given()
		
		.when()
			.delete("https://reqres.in/api/users/" + id)
			
		.then()
			.statusCode(204)
			.log()
			.all();
	}
}
