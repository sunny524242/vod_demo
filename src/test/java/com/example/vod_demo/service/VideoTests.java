package com.example.vod_demo.service;

import com.alibaba.fastjson.JSON;
import com.example.vod_demo.dto.VideoInfo;
import com.example.vod_demo.dto.VodUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoTests {

	@Autowired
	VideoService videoService;

	/**
	 * 测试视频终端用户的创建、屏蔽、恢复、删除
	 */
	@Test
	public void userOperationTest() {
		VodUser vodUser = new VodUser();
		String accid = UUID.randomUUID().toString().replace("-", "");
		vodUser.setAccid(accid);
		vodUser.setName("vodUser"+(int)(Math.random()*Math.pow(10,5)));

		VodUser user = videoService.createUser(vodUser);
		assert user!=null :"用户创建失败";
		System.out.println(JSON.toJSONString(user));

		boolean b = videoService.disableUser(vodUser);
		assert b :"用户屏蔽失败";

		b = videoService.recoverUser(vodUser);
		assert b :"用户恢复失败";

		b = videoService.deleteUser(vodUser);
		assert b :"用户删除失败";
	}


	/**
	 * 测试获取视频VID
	 */
	@Test
	public void getVideoInfoVidTest(){
		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setObjectName("02089593-5130-481b-aac4-f7b25a7143d2.mp4");
		videoInfo = videoService.getVideoVID(videoInfo);
		assert videoInfo.getVid()!=null : "获取视频VID失败";
	}

}
