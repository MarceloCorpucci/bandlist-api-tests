package com.bandlist.api.band;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bandlist.api.entity.Band;
import com.bandlist.api.entity.Member;

public class TestPutBand {
	private static final String URI = "http://localhost:4567/api/v1";
	private Band origBand;
	private Band mdifBand;
	
	@Before
	public void setUp() {
		origBand = new Band.Builder()
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
			.body(origBand)
		.when()
			.post(URI + "/bands");
		
		mdifBand = new Band.Builder()
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
							.full_name("Tano Marciello")
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
	public void bandUpdateSuccessfully() {
		given()
			.contentType("application/json")
			.body(mdifBand)
			.log()
			.all()
		.when()
			.put(URI + "/bands/" + origBand.getName())
		.then()
			.assertThat()
			.body("members.full_name", hasItem("Tano Marciello"));
	}
	
	@After
	public void tearDown() {
		delete(URI + "/bands/" + mdifBand.getName());
	}
}
