package com.example.vod_demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.vod_demo.service.VideoService;
import com.example.vod_demo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping(value = "video")
public class VodVideoController {

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
