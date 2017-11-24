package com.example.vod_demo.dto;

/**
 * 视频直播频道信息
 */
public class VodChannel {
    //视频ID
    private String cid;
    //频道名称
    private String name;
    //推流地址
    private String pushUrl;
    //http拉流地址
    private String httpPullUrl;
    //hls拉流地址
    private String hlsPullUrl;
    //rtmp拉流地址
    private String rtmpPullUrl;
    //创建频道的时间戳
    private Long ctime;
    //频道状态（0：空闲； 1：直播； 2：禁用； 3：直播录制）
    private String status;
    //1-开启录制； 0-关闭录制
    private String needRecord;
    //视频录制格式 1-flv; 0-mp4
    private String format;
    //录制切片时长(分钟)，默认120分钟
    private Long duration;
    //录制后文件名
    private String filename;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getHttpPullUrl() {
        return httpPullUrl;
    }

    public void setHttpPullUrl(String httpPullUrl) {
        this.httpPullUrl = httpPullUrl;
    }

    public String getHlsPullUrl() {
        return hlsPullUrl;
    }

    public void setHlsPullUrl(String hlsPullUrl) {
        this.hlsPullUrl = hlsPullUrl;
    }

    public String getRtmpPullUrl() {
        return rtmpPullUrl;
    }

    public void setRtmpPullUrl(String rtmpPullUrl) {
        this.rtmpPullUrl = rtmpPullUrl;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNeedRecord() {
        return needRecord;
    }

    public void setNeedRecord(String needRecord) {
        this.needRecord = needRecord;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
