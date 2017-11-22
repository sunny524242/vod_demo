package com.example.vod_demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.vod_demo.dto.VideoInfo;
import com.example.vod_demo.service.VideoService;
import com.example.vod_demo.util.CheckSumBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class VideoServiceImpl implements VideoService{

    @Value("${vod.app.key}")
    String appKey;
    @Value("${vod.app.secret}")
    String appSecret;

    /**
     * 获取视频读写权限
     * @return
     */
    @Override
    public HashMap<String, String> getAuth() {
        HashMap<String, String> auth = new HashMap<>();
        auth.put("appKey",appKey);
        //auth.put("appSecret",appSecret);
        String nonce=String.valueOf(Math.round(Math.random() * Math.pow(10, 16)));
        auth.put("nonce",nonce);
        String curTime=String.valueOf(System.currentTimeMillis());
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

}
