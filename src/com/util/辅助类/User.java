package com.util.辅助类;

import java.util.Date;

public class User extends BaseModel {
    private String usid;

    private String nama;

    private String pawd;

    private String grup;

    private String clss;

    private String dept;
    
    private String ddsc;//额外添加，作为ttiitm221400表的ddsc字段

    private String comp;

    private String emno;

    private String emil;

    private Date lidt;

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid == null ? null : usid.trim();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama == null ? null : nama.trim();
    }

    public String getPawd() {
        return pawd;
    }

    public void setPawd(String pawd) {
        this.pawd = pawd == null ? null : pawd.trim();
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup == null ? null : grup.trim();
    }

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss == null ? null : clss.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
    
    public String getDdsc() {
        return ddsc;
    }

    public void setDdsc(String ddsc) {
        this.ddsc = ddsc == null ? null : ddsc.trim();
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp == null ? null : comp.trim();
    }

    public String getEmno() {
        return emno;
    }

    public void setEmno(String emno) {
        this.emno = emno == null ? null : emno.trim();
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil == null ? null : emil.trim();
    }

    public Date getLidt() {
        return lidt;
    }

    public void setLidt(Date lidt) {
        this.lidt = lidt;
    }
}