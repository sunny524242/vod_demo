package com.example.vod_demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.vod_demo.dto.VideoInfo;
import com.example.vod_demo.dto.VodChannel;
import com.example.vod_demo.dto.VodUser;
import com.example.vod_demo.service.VideoService;
import com.example.vod_demo.util.CheckSumBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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
     * 获取视频读写权限(非token方式)
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
     * 记录网易接口上传完后回调的视频信息
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
        videoInfo.setVid(getVideoVID(videoInfo).getVid());
        videoInfo=getVideoDetail(videoInfo);
        //存入数据库（略）
        System.out.println("videoInfo:"+JSON.toJSONString(videoInfo));
        return true;
    }


    //根据视频名称获取视频唯一标识VID（网易云视频接口没有在上传回调中传回此ID）
    //http://dev.netease.im/docs/product/%E7%82%B9%E6%92%AD/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3?pos=toc-5-4
    public VideoInfo getVideoVID(VideoInfo videoInfo){
        HttpHeaders headers = getHeaders();

        HashMap<String, Object> params = new HashMap<>();
        params.put("objectNames", Arrays.asList(videoInfo.getObjectName()));
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/video/query",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject userJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(userJO.getString("code"))){
            videoInfo.setVid(userJO.getJSONObject("ret")
                    .getJSONArray("list")
                    .getJSONObject(0)
                    .getString("vid"));
            videoInfo.setWaterImgaeId(userJO.getJSONObject("ret")
                    .getJSONArray("list")
                    .getJSONObject(0)
                    .getString("imgId"));
            return videoInfo;
        }else{
            return null;
        }
    }


    /**
     * 获取视频详情
     * @param videoInfo 传入vid即可
     * @return 详情信息放置在返回的videoInfo对象中
     */
    public VideoInfo getVideoDetail(VideoInfo videoInfo){
        HttpHeaders headers = getHeaders();
        HashMap<String, Object> params = new HashMap<>();
        params.put("vid", videoInfo.getVid());
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/video/get",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject videoJO = JSON.parseObject(exchange.getBody());
        System.out.println("ret:"+JSON.toJSONString(exchange.getBody()));
        if ("200".equals(videoJO.getString("code"))){
            JSONObject retJO = videoJO.getJSONObject("ret");
            videoInfo=parseVideoJO(retJO,videoInfo);
            return videoInfo;
        }else{
            return null;
        }
    }


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
    public VideoInfo addVideoAuth(VideoInfo videoInfo){
        HttpHeaders headers = getHeaders();
        HashMap<String, String> params = new HashMap<>();
        params.put("resId",appKey+"_"+videoInfo.getVid()+"_"+0);
        params.put("expirationTime",videoInfo.getExpire()+"");
        params.put("randSeed",((int)Math.random()*Math.pow(10,10))+"");
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/authorization/add",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject videoJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(videoJO.getString("code"))){
            videoInfo.setPlayToken(videoJO.getString("playToken"));
            return videoInfo;
        }else{
            return null;
        }
    }


    /**
     * 更新视频权限信息
     * @param videoInfo 传入vid 和过期时间
     * @return 新的token放置在返回的videoInfo对象中
     */
    public VideoInfo updateVideoAuth(VideoInfo videoInfo){
        HttpHeaders headers = getHeaders();
        HashMap<String, String> params = new HashMap<>();
        params.put("resId",appKey+"_"+videoInfo.getVid()+"_"+0);
        params.put("expirationTime",videoInfo.getExpire()+"");
        params.put("randSeed",((int)Math.random()*Math.pow(10,10))+"");
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/authorization/update",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject videoJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(videoJO.getString("code"))){
            videoInfo.setPlayToken(videoJO.getString("playToken"));
            return videoInfo;
        }else{
            return null;
        }
    }


    /**
     * 创建终端用户
     * @param vodUser
     * @return
     */
    @Override
    public VodUser createUser(VodUser vodUser) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("accid",vodUser.getAccid());
        params.put("name",vodUser.getName());
        params.put("type","1");
        params.put("props","test");
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/thirdpart/user/create",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject userJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(userJO.getString("code"))){
            vodUser.setToken(userJO.getJSONObject("ret").getString("token"));
            return vodUser;
        }else{
            return null;
        }
    }


    /**
     * 屏蔽终端用户
     * @param vodUser 传入id即可
     * @return
     */
    @Override
    public boolean disableUser(VodUser vodUser) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("accid",vodUser.getAccid());

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/thirdpart/user/userDisable",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject userJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(userJO.getString("code"))){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 恢复终端用户
     * @param vodUser 传入id即可
     * @return
     */
    @Override
    public boolean recoverUser(VodUser vodUser) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("accid",vodUser.getAccid());

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/thirdpart/user/userRecover",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject userJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(userJO.getString("code"))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 删除终端用户
     * @param vodUser 传入id即可
     * @return
     */
    @Override
    public boolean deleteUser(VodUser vodUser) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("accid",vodUser.getAccid());

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/vod/thirdpart/user/userDelete",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject userJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(userJO.getString("code"))){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 创建频道
     * @param name 频道名称（最大长度64个字符，只支持中文、字母、数字和下划线）
     * @param type 频道类型（0:rtmp）
     * @return
     */
    @Override
    public VodChannel createChannel(String name,String type) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("name",name);
        params.put("type",type);

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/channel/create",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject channelJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(channelJO.getString("code"))){
            VodChannel vodChannel = new VodChannel();
            JSONObject retJO=channelJO.getJSONObject("ret");
            vodChannel.setCid(retJO.getString("cid"));
            vodChannel.setName(retJO.getString("name"));

            vodChannel.setPushUrl(retJO.getString("pushurl"));
            vodChannel.setHttpPullUrl(retJO.getString("httpPullUrl"));
            vodChannel.setHlsPullUrl(retJO.getString("hlsPullUrl"));
            vodChannel.setRtmpPullUrl(retJO.getString("rtmpPullUrl"));
            return vodChannel;
        }else{
            return null;
        }
    }


    /**
     * 删除频道
     * @param cid 频道id
     * @return
     */
    @Override
    public boolean deleteChannel(String cid) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("cid",cid);

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/channel/delete",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject channelJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(channelJO.getString("code"))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询频道当前状态信息
     * @param cid 频道id
     * @return
     */
    @Override
    public VodChannel getChannel(String cid) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("cid",cid);

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/channelstats",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject channelJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(channelJO.getString("code"))){
            VodChannel vodChannel = new VodChannel();
            JSONObject retJO=channelJO.getJSONObject("ret");
            vodChannel.setCid(retJO.getString("cid"));
            vodChannel.setName(retJO.getString("name"));

            vodChannel.setPushUrl(retJO.getString("pushurl"));
            vodChannel.setHttpPullUrl(retJO.getString("httpPullUrl"));
            vodChannel.setHlsPullUrl(retJO.getString("hlsPullUrl"));
            vodChannel.setRtmpPullUrl(retJO.getString("rtmpPullUrl"));

            vodChannel.setStatus(retJO.getString("status"));
            vodChannel.setNeedRecord(retJO.getString("needRecord"));
            vodChannel.setFormat(retJO.getString("format"));
            vodChannel.setDuration(retJO.getLong("duration"));
            vodChannel.setFilename(retJO.getString("filename"));
            return vodChannel;
        }else{
            return null;
        }
    }


    /**
     * 禁用频道
     * @param cid 频道id
     * @return
     */
    @Override
    public boolean pauseChannel(String cid) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("cid",cid);

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/channel/pause",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject channelJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(channelJO.getString("code"))){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 恢复频道
     * @param cid 频道id
     * @return
     */
    @Override
    public boolean resumeChannel(String cid) {
        HttpHeaders headers = getHeaders();

        HashMap<String, String> params = new HashMap<>();
        params.put("cid",cid);

        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(JSON.toJSONString(params),headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://vcloud.163.com//app/channel/resume",
                HttpMethod.POST, stringHttpEntity, String.class);
        JSONObject channelJO = JSON.parseObject(exchange.getBody());
        if ("200".equals(channelJO.getString("code"))){
            return true;
        }else{
            return false;
        }
    }

    private String getNonce(){
        return String.valueOf(Math.round(Math.random() * Math.pow(10, 16)));
    }

    private String getCurTime(){
        return String.valueOf(System.currentTimeMillis());
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("appKey",appKey);
        String nonce = getNonce();
        headers.add("nonce",nonce);
        String curTime = getCurTime();
        headers.add("curTime",curTime);
        headers.add("checkSum",CheckSumBuilder.getCheckSum(appSecret, nonce,curTime));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        return headers;
    }

    /**
     * 解析http返回的视频信息json
     * @param retJO http响应的ret部分
     */
    private VideoInfo parseVideoJO(JSONObject retJO,VideoInfo videoInfo){
        videoInfo.setVideoName(retJO.getString("videoName"));
        videoInfo.setStatus(retJO.getInteger("status"));
        videoInfo.setDescription(retJO.getString("description"));
        videoInfo.setDuration(retJO.getInteger("duration"));
        videoInfo.setTypeId(retJO.getString("typeId"));
        videoInfo.setTypeName(retJO.getString("typeName"));
        videoInfo.setSnapshotUrl(retJO.getString("snapshotUrl"));
        videoInfo.setOrigUrl(retJO.getString("origUrl"));
        videoInfo.setDownloadOrigUrl(retJO.getString("downloadOrigUrl"));

        videoInfo.setInitialSize(retJO.getLong("initialSize"));
        videoInfo.setSdMp4Url(retJO.getString("sdMp4Url"));
        videoInfo.setDownloadSdMp4Url(retJO.getString("downloadSdMp4Url"));
        videoInfo.setSdMp4Size(retJO.getLong("sdMp4Size"));
        videoInfo.setHdMp4Url(retJO.getString("hdMp4Url"));
        videoInfo.setDownloadHdMp4Url(retJO.getString("downloadHdMp4Url"));
        videoInfo.setHdMp4Size(retJO.getLong("hdMp4Size"));
        videoInfo.setShdMp4Url(retJO.getString("shdMp4Url"));
        videoInfo.setDownloadShdMp4Url(retJO.getString("downloadShdMp4Url"));
        videoInfo.setShdMp4Size(retJO.getLong("shdMp4Size"));
        videoInfo.setCreateTime(retJO.getLong("createTime"));
        videoInfo.setUpdateTime(retJO.getLong("updateTime"));
        return videoInfo;
    }

}
