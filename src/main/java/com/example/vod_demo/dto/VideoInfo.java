package com.example.vod_demo.dto;

public class VideoInfo {
    //文件完整度
    private	String	progress;	/*100.00*/
    //文件大小（M）
    private	String	fileSizeMb;	/*2.13*/
    //文件Key
    private	String	fileKey;	/*8d53bc19a5fda6844cdd5f61be038656*/
    //文件存储名称
    private	String	objectName;	/*5ecbef3e-7fb4-4a73-9d65-296afa437ad3.mp4*/
    //状态
    private	String	status;
    //文件真实名称
    private	String	fileName;	/*VID_20171121_150935.mp4*/
    //视频格式
    private	String	format;	    /*mp4*/
    //桶名称
    //文件访问路径为:http://{bucketName}.vod.126.net/{bucketName}/{objectName}
    private	String	bucketName;	/*vodntv11lwt*/
    //网易云返回，暂不清楚意义及用途
    private	Boolean	checked;	/*true*/



    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
