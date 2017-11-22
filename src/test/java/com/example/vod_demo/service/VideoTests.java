package com.example.vod_demo.service;

import com.example.vod_demo.dto.VodUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoTests {

	@Autowired
	VideoService videoService;

	@Test
	public void createUserTest() {
		videoService.createUser(new VodUser());
	}

}
