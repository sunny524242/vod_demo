package com.example.vod_demo.service;

import com.example.vod_demo.dto.VodUser;
import com.example.vod_demo.vo.JsonResult;

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

    /**
     * 创建网易云用户
     * @return
     */
    VodUser createUser(VodUser vodUser);

    /**
     * 屏蔽终端用户
     * @param vodUser 传入id即可
     * @return
     */
    boolean disableUser(VodUser vodUser);

    /**
     * 恢复终端用户
     * @param vodUser 传入id即可
     * @return
     */
    boolean recoverUser(VodUser vodUser);

    /**
     * 删除终端用户
     * @param vodUser 传入id即可
     * @return
     */
    boolean deleteUser(VodUser vodUser);
}
