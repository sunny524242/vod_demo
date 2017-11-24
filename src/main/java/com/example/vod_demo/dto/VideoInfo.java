package com.example.vod_demo.dto;

/**
 * 视频信息
 * http://dev.netease.im/docs/product/%E7%82%B9%E6%92%AD/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3?pos=toc-4-0
 */
public class VideoInfo {
    //文件大小（M）
    private	String	fileSizeMb;	/*2.13*/
    //文件Key
    private	String	fileKey;	/*8d53bc19a5fda6844cdd5f61be038656*/
    //文件存储名称
    private	String	objectName;	/*5ecbef3e-7fb4-4a73-9d65-296afa437ad3.mp4*/
    //文件真实名称
    private	String	fileName;	/*VID_20171121_150935.mp4*/
    //视频格式
    private	String	format;	    /*mp4*/
    //桶名称
    private	String	bucketName;	/*vodntv11lwt*/
    //网易云返回，暂不清楚意义及用途
    private	Boolean	checked;	/*true*/
    //视频水印图片ID
    private String waterImgaeId;

    //视频访问地址（回源鉴权）
    private String playToken;
    //回源鉴权过期时间
    private int expire;


    private	Long	createTime;	/*1466578358729*/
    private	String	origUrl;	/*http://vodk32ywxdf.vod.126.net/vodk32ywxdf/44d30332-7402-4b2f-82c8-154dbb6b4e14.mp4*/
    private	String	downloadOrigUrl;	/*http://vodk32ywxdf.nosdn.127.net/44d30332-7402-4b2f-82c8-154dbb6b4e14.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558005&download=watermark_test_1.mp4&Signature=%2BGu%2BvgiUP1rL4pbx%2B52GH4QCo%2FOHAtlhgzCzPV9f0vc%3D*/
    private	String	shdMp4Url;	/*http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/mp4/2016/06/22/v32_shd.mp4*/
    private	Long	sdMp4Size;	/*17906823*/
    private	String	videoName;	/*watermark_test_1*/
    private	String	downloadSdMp4Url;	/*http://vodk32ywxdf.nosdn.127.net/nos%2Fmp4%2F2016%2F06%2F22%2Fv32_sd.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E6%25A0%2587%25E6%25B8%2585_watermark_test_1.mp4&Signature=64FOWYzLciWyTe8hmLMRVCYRCsQLbEOWtNqcB9rUj18%3D*/
    private	String	description;	/*Object*/
    private	Long	hdMp4Size;	/*25227850*/
    private	String	vid;	/*32*/
    private	Long	shdMp4Size;	/*39874022*/
    private	String	hdMp4Url;	/*http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/mp4/2016/06/22/v32_hd.mp4*/
    private	Integer	status;	/*40*/
    private	Long	updateTime;	/*1466663164342*/
    private	String	sdMp4Url;	/*http://vodk32ywxdf.vod.126.net/vodk32ywxdf/nos/mp4/2016/06/22/v32_sd.mp4*/
    private	String	downloadHdMp4Url;	/*http://vodk32ywxdf.nosdn.127.net/nos%2Fmp4%2F2016%2F06%2F22%2Fv32_hd.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E9%25AB%2598%25E6%25B8%2585_watermark_test_1.mp4&Signature=kMUKJGcW8aSFauL2836pXA5UrEeowq2hLdpgMpjmr44%3D*/
    private	String	downloadShdMp4Url;	/*http://vodk32ywxdf.nosdn.127.net/nos%2Fmp4%2F2016%2F06%2F22%2Fv32_shd.mp4?NOSAccessKeyId=ab1856bb39044591939d7b94e1b8e5ee&Expires=1498558006&download=%25E8%25B6%2585%25E6%25B8%2585_watermark_test_1.mp4&Signature=ch30%2BtX20b54UeskhFZs37r55jkt2WIV87jlaZgKmPY%3D*/
    private	String	typeName;	/*默认分类*/
    private	Integer	duration;	/*195*/
    private	String	snapshotUrl;	/*http://vodk32ywxdf.nosdn.127.net/6c4a9501-ee3c-4e00-9a5f-20f6616f0ad3.jpg*/
    private	Long	initialSize;	/*15601202*/
    private	Long	completeTime;	/*1466663164342*/
    private	String	typeId;	/*38*/


    public String getFileSizeMb() {
        return fileSizeMb;
    }

    public void setFileSizeMb(String fileSizeMb) {
        this.fileSizeMb = fileSizeMb;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getWaterImgaeId() {
        return waterImgaeId;
    }

    public void setWaterImgaeId(String waterImgaeId) {
        this.waterImgaeId = waterImgaeId;
    }

    public String getPlayToken() {
        return playToken;
    }

    public void setPlayToken(String playToken) {
        this.playToken = playToken;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getOrigUrl() {
        return origUrl;
    }

    public void setOrigUrl(String origUrl) {
        this.origUrl = origUrl;
    }

    public String getDownloadOrigUrl() {
        return downloadOrigUrl;
    }

    public void setDownloadOrigUrl(String downloadOrigUrl) {
        this.downloadOrigUrl = downloadOrigUrl;
    }

    public String getShdMp4Url() {
        return shdMp4Url;
    }

    public void setShdMp4Url(String shdMp4Url) {
        this.shdMp4Url = shdMp4Url;
    }

    public Long getSdMp4Size() {
        return sdMp4Size;
    }

    public void setSdMp4Size(Long sdMp4Size) {
        this.sdMp4Size = sdMp4Size;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getDownloadSdMp4Url() {
        return downloadSdMp4Url;
    }

    public void setDownloadSdMp4Url(String downloadSdMp4Url) {
        this.downloadSdMp4Url = downloadSdMp4Url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getHdMp4Size() {
        return hdMp4Size;
    }

    public void setHdMp4Size(Long hdMp4Size) {
        this.hdMp4Size = hdMp4Size;
    }


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public Long getShdMp4Size() {
        return shdMp4Size;
    }

    public void setShdMp4Size(Long shdMp4Size) {
        this.shdMp4Size = shdMp4Size;
    }


    public String getHdMp4Url() {
        return hdMp4Url;
    }

    public void setHdMp4Url(String hdMp4Url) {
        this.hdMp4Url = hdMp4Url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getSdMp4Url() {
        return sdMp4Url;
    }

    public void setSdMp4Url(String sdMp4Url) {
        this.sdMp4Url = sdMp4Url;
    }

    public String getDownloadHdMp4Url() {
        return downloadHdMp4Url;
    }

    public void setDownloadHdMp4Url(String downloadHdMp4Url) {
        this.downloadHdMp4Url = downloadHdMp4Url;
    }

    public String getDownloadShdMp4Url() {
        return downloadShdMp4Url;
    }

    public void setDownloadShdMp4Url(String downloadShdMp4Url) {
        this.downloadShdMp4Url = downloadShdMp4Url;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    public Long getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Long initialSize) {
        this.initialSize = initialSize;
    }

    public Long getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Long completeTime) {
        this.completeTime = completeTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
