package com.zhongfucheng.dto;

/**
 * Created by ozc on 2018/1/10.
 *
 * @author ozc
 * @version 1.0
 *
 * 封装图片的相对路径和真实路径
 */
public class ImagePath {



    private String realPath;

    private String relativePath;

    public ImagePath() {
    }

    public ImagePath(String realPath, String relativePath) {
        this.realPath = realPath;
        this.relativePath = relativePath;
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
