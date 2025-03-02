package s3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	//@Test(priority=1)
	void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC", "AVcja2eH538WFV6BXhzql-uBFbBTRYr9tCuDdF-lQ6pov45XtCPPdibGBBw")
			.log()
			.all();
		
		//this test should fail --> expected
		//because when you execute the test again, it will generate 
		//different value of 'AEC'
		//i.e. each time the value of cookie will be different
	}
	
	@Test(priority=2)
	void getCookiesInfo() {
		
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//1.
		//get single cookie info
		//String cookie_value = res.getCookie("AEC");
		//System.out.println("The value of the cookie: " + cookie_value);
		/*o/p:
		 * The value of the cookie: AVcja2dwGNczDGHvb81Prwmse-QMI3y3EXX3TDAl7yMVo-ZwN5GKEbYuags
		 * */
		
		Map<String, String> allCookieValues = res.getCookies();
		//System.out.println(allCookieValues.keySet());//[AEC, NID]
		//System.out.println(allCookieValues.values());
		//[AVcja2foPagHUNjVA_qZGcJ5bwyjhHmfwlySAPyhA6705yy7KI7aBwRfuA, 522=QO_LbS6iCOgM2gaPbsJmOqC3ZONN7O3aYn3Y78kqOX7BYUI-u8rthnQo9BJMvM7SyhN2OpuLyXhNe5dgoAMF8bVM_rhqxkp1O5lientQ6-ZdOyGlKqTU1bFwfa_Ie5I9cDXrnZjKi83AL7LPwukk2wJFnxFvB78rj_UI--0SQnDMD2TEtvVlsdTWl8uHpoDDxgNyqgFt7FOp]
		
		for(String key:allCookieValues.keySet()) {
			String cookie_value = res.getCookie(key);
			System.out.println(key + ": " + cookie_value);
		}
	}
	
}
