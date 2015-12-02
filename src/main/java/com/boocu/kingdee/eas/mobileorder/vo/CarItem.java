package com.boocu.kingdee.eas.mobileorder.vo;

import java.util.Date;

/**
 * Created by jordan on 2015/12/1.
 */
public class CarItem extends BaseVO {

    private long id;
    private String num;
    private String author;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
