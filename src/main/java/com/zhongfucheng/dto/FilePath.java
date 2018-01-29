package com.zhongfucheng.dto;

/**
 * Created by ozc on 2018/1/10.
 *
 * @author ozc
 * @version 1.0
 *
 * 封装图片的相对路径和真实路径，还有对应的原名字
 */
public class FilePath {


    private String realPath;

    private String relativePath;


    private String originalFilename;

    public FilePath(String realPath, String relativePath, String originalFilename) {
        this.realPath = realPath;
        this.relativePath = relativePath;
        this.originalFilename = originalFilename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public FilePath() {
    }


    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
