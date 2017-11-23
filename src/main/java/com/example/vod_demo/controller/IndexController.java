package com.example.vod_demo.controller;

import com.example.vod_demo.service.VideoService;
import com.example.vod_demo.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * 默认页面
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {


    /**
     * 默认页面
     * @return
     */
    @RequestMapping(value = "")
    public ModelAndView getAuth(ModelAndView mv){
        mv.setViewName("vod_upload_with_token.html");
        return mv;
    }


}
