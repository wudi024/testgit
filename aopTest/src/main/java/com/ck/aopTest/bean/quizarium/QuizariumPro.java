package com.ck.aopTest.bean.quizarium;

import java.io.Serializable;

public class QuizariumPro implements Serializable {
    private Integer proNo;

    private String openid;

    private String proTele;

    private Integer proGrade;

    private String proDetails;

    private String note1;

    private String note2;

    private String createtime;

    private static final long serialVersionUID = 1L;

    public Integer getProNo() {
        return proNo;
    }

    public void setProNo(Integer proNo) {
        this.proNo = proNo;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getProTele() {
        return proTele;
    }

    public void setProTele(String proTele) {
        this.proTele = proTele == null ? null : proTele.trim();
    }

    public Integer getProGrade() {
        return proGrade;
    }

    public void setProGrade(Integer proGrade) {
        this.proGrade = proGrade;
    }

    public String getProDetails() {
        return proDetails;
    }

    public void setProDetails(String proDetails) {
        this.proDetails = proDetails == null ? null : proDetails.trim();
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1 == null ? null : note1.trim();
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2 == null ? null : note2.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}