package s2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class PostRequestWays {
	
	/*
	 * 1. Using HashMap
	 * 2. Using org.json
	 * 3. Using POJO (Plain Old Java Object)
	 * 4. Using External json file
	 * */
	
	Object id;
	
	//1. Using HashMap
	
	//@Test(priority=1)
	void postTestUsingHashMap() {
		
		HashMap data = new HashMap();
		data.put("name", "Suraj");
		data.put("location", "India");
		data.put("phone", "9770655643");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String courseArray[] = {"ruby", "selenium"};
		data.put("courses", courseArray);
		
		id= given()
			.contentType("application/json")
			.body(data)
	
	    .when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Suraj"))
			.body("location", equalTo("India"))
			.body("phone", equalTo("9770655643"))
			.body("courses[0]", equalTo("ruby"))
			.body("courses[1]", equalTo("selenium"))
		    .log()
		    .all()
		    .extract()
		    .path("id");
	}
	
	//2. Using JSONObject
	//@Test(priority=1)
	void postTestUsingJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Suraj");
		data.put("location", "USA");
		data.put("phone", "9667543655");
		String courseArray[] = {"ruby", "selenium"};
		data.put("courses", courseArray);
		
		id = given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.log()
			.all()
			.extract()
			.path("id");
		
	}
	
	//3. Using POJO (plain old java object) class
	//@Test
	void postTestUsingPOJO() {
		
		PojoPostRequest data = new PojoPostRequest();
		data.setName("Supriya");
		data.setLocation("Solapur");
		data.setPhone("1234567890");
		String courses[] = {"c", "c++"};
		data.setCourses(courses);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Supriya"))
			.body("location", equalTo("Solapur"))
			.body("phone", equalTo("1234567890"))
			.log()
			.all();
		
	}
	
	//4. Using external json file
	@Test
	void postTestUsingExternalJson() throws FileNotFoundException {
		
		File file = new File(".\\body.json");
		FileReader fr = new FileReader(file);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.log()
			.all();
		
		
	}
	
	//@Test(priority=2, dependsOnMethods = "postTestUsingJsonLibrary")
	void deleteTest() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/"+id)
		
		.then()
			.statusCode(200);
	}
}
