package com.example.vod_demo.service;

import com.example.vod_demo.dto.VideoInfo;
import com.example.vod_demo.dto.VodChannel;
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
     * 添加视频播放凭证（回源鉴权）
     * @param videoInfo 传入vid和过期时间
     * resId:	标识需要设置播放凭证的资源，构造规则是：
     * appKey + "_" + vid + "_" + style（其中style为视频格式：
     * 0标识源视频、1标识流畅mp4、2标识标清mp4、3标识高清mp4、
     * 4标识流畅flv、5标识标清flv、6标识高清flv、7标识流畅hls、8标识标清hls、
     * 9标识高清hls），
     * @return 新的token放置在返回的videoInfo对象中
     */
    VideoInfo addVideoAuth(VideoInfo videoInfo);

    /**
     * 根据文件对象名称获取文件vid
     * @param videoInfo
     * @return
     */
     VideoInfo getVideoVID(VideoInfo videoInfo);

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




    /**
     * 创建频道
     * @param name 频道名称（最大长度64个字符，只支持中文、字母、数字和下划线）
     * @param type 频道类型（0:rtmp）
     * @return
     */
    public VodChannel createChannel(String name, String type);

    /**
     * 删除频道
     * @param cid 频道id
     * @return
     */
    public boolean deleteChannel(String cid);

    /**
     * 查询频道当前状态信息
     * @param cid 频道id
     * @return
     */
    public VodChannel getChannel(String cid);

    /**
     * 禁用频道
     * @param cid 频道id
     * @return
     */
    public boolean pauseChannel(String cid);

    /**
     * 恢复频道
     * @param cid 频道id
     * @return
     */
    public boolean resumeChannel(String cid);

}
