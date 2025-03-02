package s3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	//@Test(priority=1)
	void testHeaders() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
		.and()/*not mandatory to use 'and()'*/
			.header("Content-Encoding", "gzip")
		.and()
			.header("server", "gws");
		
	}
	
	@Test(priority=2)
	void getHeaders() {
		
		Response res = given()
						.when()
							.get("https://www.google.com/");
		
		//1.
		//String header_value = res.getHeader("Content-Type");
		//System.out.println("Header value for Content-Type: " + header_value);
		
		//2.
		Headers allHeadersValues = res.getHeaders();
		
		for(Header header : allHeadersValues) {
			
			//1.
			//String header_value = res.getHeader(header.toString());
			//System.out.println(header.toString() + ": " + header_value);
			//System.out.println(header + ": " + header_value);
			
			//2.
			System.out.println(header.getName() + ": " + header.getValue());
		}
	}
	
}
