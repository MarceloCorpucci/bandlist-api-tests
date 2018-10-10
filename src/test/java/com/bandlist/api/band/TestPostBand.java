package com.bandlist.api.band;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bandlist.api.entity.Band;
import com.bandlist.api.entity.Member;

public class TestPostBand {
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
	}
	
	@Test
	public void bandCreatedSuccessfuly() {
		given()
			.contentType("application/json")
			.body(band)
			.log()
			.all()
		.when()
			.post(URI + "/bands")
		.then()
			.assertThat()
			.statusCode(201)
			.log()
			.all();
	}
	
	@After
	public void tearDown() {
		delete(URI + "/bands/" + band.getName());
	}
}
