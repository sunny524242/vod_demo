package com.example.vod_demo.service;

import java.util.HashMap;

public interface VideoService {
    /**
     * 获取视频读写权限
     * @return
     */
    HashMap<String,String> getAuth();


    /**
     * 记录上传的视频信息
     * @param fileInfo
     * @return
     */
    boolean recordUploadVideo(String fileInfo);
}
