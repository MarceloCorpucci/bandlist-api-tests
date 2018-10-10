package com.bandlist.api.band;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bandlist.api.entity.Band;
import com.bandlist.api.entity.Member;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class TestPostBand {
	private static final String URI = "http://localhost:4567/api/v1";
	private List<Member> members;
	private Band band;
	
	@Before
	public void setUp() {
		members = new ArrayList<Member>();
		band = new Band();
		Member m1 = new Member("Ricardo Iorio", "Singer", true);
		Member m2 = new Member("Bin Valencia", "drummer", true);
		Member m3 = new Member("Claudio Marciello", "guitarist", true);
		Member m4 = new Member("Beto Ceriotti", "bassist", true);
		members.add(m1);
		members.add(m2);
		members.add(m3);
		members.add(m4);
		
		band.setName("Almafuerte");
		band.setGenre("Heavy Metal");
		band.setMembers(members);
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
			.statusCode(201);
	}
	
	@After
	public void tearDown() {
		delete(URI + "/bands/" + band.getName());
	}
}
