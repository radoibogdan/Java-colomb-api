package com.aston.colomb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.JavaVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ColombApplicationTests {

	@Test
	void contextLoads() {
		assertEquals("5.3.21", SpringVersion.getVersion());
		assertEquals("17.0.4", System.getProperty("java.version"));
		assertEquals("1.8", JavaVersion.getJavaVersion().toString());
	}

}
