package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.sql.Timestamp;

/**
 * 短信发送表 表名：WSend 所在数据库：haduosmsserver
 * 
 * @author sunke
 * 
 */
public class WSend {
    public static final String TABLE_NAME = "WSend";

    /**
     * mbno 接收手机号 varchar(30) 非空
     */
    private String mbno;
    /**
     * SMS 短信内容 varchar(700) 非空
     */
    private String SMS;

    /**
     * Wtime 写入的时间 datetime 非空
     */
    private Timestamp wTime;

    /**
     * SendSN 发送账号 varchar(20) 统一用：50002
     */
    private String sendSN;

    /**
     * SubAccount 子账号 varchar(10) 统一用：1111
     */
    private String subAccount;

    public String getMbno() {
        return mbno;
    }

    public void setMbno(String mbno) {
        this.mbno = mbno;
    }

    public String getSMS() {
        return SMS;
    }

    public void setSMS(String sMS) {
        SMS = sMS;
    }

    public Timestamp getwTime() {
        return wTime;
    }

    public void setwTime(Timestamp wTime) {
        this.wTime = wTime;
    }

    public String getSendSN() {
        return sendSN;
    }

    public void setSendSN(String sendSN) {
        this.sendSN = sendSN;
    }

    public String getSubAccount() {
        return subAccount;
    }

    public void setSubAccount(String subAccount) {
        this.subAccount = subAccount;
    }

    public WSend(String mbno, String sMS, Timestamp wTime, String sendSN,
            String subAccount) {
        super();
        this.mbno = mbno;
        SMS = sMS;
        this.wTime = wTime;
        this.sendSN = sendSN;
        this.subAccount = subAccount;
    }

    public WSend() {
        super();
    }

}
