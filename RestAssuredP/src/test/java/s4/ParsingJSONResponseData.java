package s4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
	//@Test(priority=1)
	void testJsonResponce() {
		
		//Approach 1:
		/*given()
			.contentType("application/json")
		
		.when()
			.get("http://localhost:3000/store")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("book[0].title", equalTo("Sayings of the century"));
		*/
		
		
		//Approach 2: better approach than above one
		Response res = given()
							.contentType("ContentType.json")
		
					   .when()
					   		.get("http://localhost:3000/store");
		
		
		int sc = res.getStatusCode();
		Assert.assertEquals(sc, 200);
		String ctHeader = res.getHeader("Content-Type");
		Assert.assertEquals(ctHeader, "application/json");
		//System.out.println(res.getHeaders());
		
		String book3Name = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(book3Name, "The Lord of the Rings");
	}
	
	@Test(priority = 2)
	void testJsonResponseBodyData() {
		
		Response res = given()
							.contentType(ContentType.JSON)
							/*.contentType("application/json")*/ // valid
							/*.contentType("ContentType.json")*/ // valid
					   .when()
					   		.get("http://localhost:3000/store");

		//JSONObject class
		JSONObject jo = new JSONObject(res.asString());
		//covert response to JSON object type
		
		/*
		 for(int i = 0; i<jo.getJSONArray("book").length(); i++) {
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
		*/
		
		
		/*Searching the book title in the JSON*/
		boolean flag = false;
		for(int i = 0; i<jo.getJSONArray("book").length(); i++) {
			jo.getJSONArray("book").getJSONObject(i);
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.contains("The Lord of the Rings")) {
				
				flag = true;
				String bookAuthor = jo.getJSONArray("book").getJSONObject(i).get("author").toString();
				System.out.println("Author of the " + bookTitle + " is " + bookAuthor);
				break;
				
			}
		}
		Assert.assertEquals(flag, true);
		
		
		/*Calculating total price of the books*/
		double totalPrice = 0.0;
		for(int i = 0; i<jo.getJSONArray("book").length(); i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalPrice = totalPrice + Double.parseDouble(price);
		}
		System.out.println(totalPrice);
	}
}
