package com.bandlist.api.band;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bandlist.api.entity.Band;
import com.bandlist.api.entity.Member;

public class TestGetBand {
	private static final String URI = "http://localhost:4567/api/v1";
	private Band band;
	
	@Before
	public void setUp() {
		band = new Band.Builder()
						.name("Almafuerte")
						.genre("Heavy Metal")
						.members(
							Arrays.asList(
								new Member.Builder()
									.full_name("Ricardo Iorio")
									.role("singer")
									.alive(true)
									.build(),
								new Member.Builder()
									.full_name("Bin Valencia")
									.role("drummer")
									.alive(true)
									.build(),
								new Member.Builder()
									.full_name("Claudio Marciello")
									.role("guitarist")
									.alive(true)
									.build(),
									new Member.Builder()
									.full_name("Beto Ceriotti")
									.role("bassist")
									.alive(true)
									.build()
								)
							)
						.build();
		
		given()
			.contentType("application/json")
			.body(band)
		.when()
			.post(URI + "/bands");
	}
	
	@Test
	public void bandCreatedSuccessfuly() {
		given()
			.contentType("application/json")
		.when()
			.get(URI + "/bands/" + band.getName())
		.then()
			.assertThat()
			.body("members.full_name", hasItems(
										"Ricardo Iorio", 
										"Bin Valencia", 
										"Claudio Marciello", 
										"Beto Ceriotti"))
			.log()
			.all();
	}
	
	@After
	public void tearDown() {
		delete(URI + "/bands/" + band.getName());
	}
}
