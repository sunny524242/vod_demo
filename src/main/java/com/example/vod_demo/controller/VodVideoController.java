package com.example.vod_demo.controller;

import com.example.vod_demo.service.VideoService;
import com.example.vod_demo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping(value = "video")
public class VodVideoController {
    @Value("${vod.app.key}")
    String appKey;
    @Autowired
    VideoService videoService;

    /**
     * 获取视频读写权限信息
     * @return
     */
    @RequestMapping(value = "getAuth")
    @ResponseBody
    public JsonResult getAuth(){
        HashMap<String, String> auth = videoService.getAuth();
        return new JsonResult(200,"获取视频权限成功",auth);
    }

    /**
     * 获取视频终端用户信息
     * 配合页面做测试使用
     * @return
     */
    @RequestMapping(value = "getVodUser")
    @ResponseBody
    public JsonResult getVodUser(){
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("accid","12a9b1b9a56249eebec7fb7347620406");
        userInfo.put("token","7db7c24c9b94413d4ddc229a70f3eb6e832709db");
        userInfo.put("appKey",appKey);
        return new JsonResult(200,"获取视频终端用户信息成功",userInfo);
    }

    /**
     * 记录已上传的文件
     * @return
     */
    @RequestMapping(value = "recordUpload")
    @ResponseBody
    public JsonResult recordUpload(String fileInfo){
        System.out.println("recordUpload:"+fileInfo);
        videoService.recordUploadVideo(fileInfo);
        return new JsonResult(200,"视频上传记录成功",null);
    }




}
