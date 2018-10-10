package com.bandlist.api.band;

import static io.restassured.RestAssured.given;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.bandlist.api.entity.Band;
import com.bandlist.api.entity.Member;

public class TestDeleteBand {
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
	public void bandDeletedSuccessfuly() {
		given()
			.contentType("application/json")
		.when()
			.delete(URI + "/bands/" + band.getName())
		.then()
			.assertThat()
			.statusCode(204);
	}

}
