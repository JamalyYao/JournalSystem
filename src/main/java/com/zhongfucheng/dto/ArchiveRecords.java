package com.zhongfucheng.dto;

import java.io.Serializable;

/**
 * Created by ozc on 2018/1/17.
 *
 * @author ozc
 * @version 1.0
 *
 * 保存文章存档记录的数据
 */
public class ArchiveRecords implements Serializable {

    private String year;
    private String month;
    private String num;


    public ArchiveRecords() {
    }

    @Override
    public String toString() {
        return "ArchiveRecords{" +
                "year=" + year +
                ", month=" + month +
                ", num=" + num +
                '}';
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
