package com.example.vod_demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.vod_demo.dto.VideoInfo;
import com.example.vod_demo.dto.VodUser;
import com.example.vod_demo.service.VideoService;
import com.example.vod_demo.util.CheckSumBuilder;
import com.example.vod_demo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class VideoServiceImpl implements VideoService{

    @Value("${vod.app.key}")
    String appKey;
    @Value("${vod.app.secret}")
    String appSecret;
    @Autowired
    RestTemplate restTemplate;



    /**
     * 获取视频读写权限
     * @return
     */
    @Override
    public HashMap<String, String> getAuth() {
        HashMap<String, String> auth = new HashMap<>();
        auth.put("appKey",appKey);
        //auth.put("appSecret",appSecret);
        String nonce = getNonce();
        String curTime = getCurTime();
        auth.put("nonce",nonce);
        auth.put("curTime",curTime);
        auth.put("checkSum",CheckSumBuilder.getCheckSum(appSecret,nonce,curTime));
        return auth;
    }

    /**
     * 记录上传的视频信息
     * @param fileInfo
     * @return
     */
    @Override
    public boolean recordUploadVideo(String fileInfo){
        JSONObject fileInfoJO = JSON.parseObject(fileInfo);
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setBucketName(fileInfoJO.getString("bucketName"));
        videoInfo.setObjectName(fileInfoJO.getString("objectName"));
        videoInfo.setFileKey(fileInfoJO.getString("fileKey"));
        videoInfo.setFileName(fileInfoJO.getString("fileName"));
        videoInfo.setFileSizeMb(fileInfoJO.getString("fileSizeMb"));
        videoInfo.setFormat(fileInfoJO.getString("format"));
        videoInfo.setStatus(fileInfoJO.getString("status"));
        videoInfo.setProgress(fileInfoJO.getString("progress"));
        //videoInfo.setChecked(fileInfoJO.getBoolean(""));
        //存入数据库（略）
        System.out.println("videoInfo:"+JSON.toJSONString(videoInfo));
        return true;
    }


    @Override
    public JsonResult createUser(VodUser vodUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("appKey",appKey);
        String nonce = getNonce();
        headers.add("nonce",nonce);
        String curTime = getCurTime();
        headers.add("curTime",curTime);
        headers.add("checkSum",CheckSumBuilder.getCheckSum(appSecret, nonce,curTime));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HashMap<String, String> params = new HashMap<>();
        params.put("accid",vodUser.getId());
        params.put("name",vodUser.getName());
        params.put("type","1");
        params.put("props","test");
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/thirdpart/user/create", HttpMethod.POST, stringHttpEntity, String.class);
        System.out.println("\n\nresp:"+exchange.getBody());

        return null;
    }
    
    private String getNonce(){
        return String.valueOf(Math.round(Math.random() * Math.pow(10, 16)));
    }

    private String getCurTime(){
        return String.valueOf(System.currentTimeMillis());
    }

}
