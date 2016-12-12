package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.Date;

/**
 * 投诉建议表 表名：tb_Suggestion 所在数据库：ServiceInformationManageSystem
 * 
 * @author sunke
 * 
 */
public class Suggestion {
    public static final String TABLE_NAME = "tb_Suggestion";
    /**
     * Num 投诉和建议的编号 nvarchar(50) 自动添加1可以用写好的函数自动添加
     */
    private String Num;
    /**
     * 内容 nvarchar(100)
     */
    private String Content;

    /**
     * 添加时间 datetime
     */
    private Date InsertTime;
    /**
     * 联系人 nvarchar(50)
     */
    private String Contacts;
    /**
     * 联系人电话 nvarchar(50)
     */
    private String ContactsTel;
    /**
     * 回复者工号 nvarchar(50)
     */
    private String ReplyEmpNum;
    /**
     * 回复时间 datetime
     */
    private Date ReplyTime;
    /**
     * 往来短信 nvarchar(MAX)
     */
    private String SMS;
    /**
     * Remark 备注 nvarchar(MAX)
     */
    private String Remark;

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(Date insertTime) {
        InsertTime = insertTime;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts) {
        Contacts = contacts;
    }

    public String getContactsTel() {
        return ContactsTel;
    }

    public void setContactsTel(String contactsTel) {
        ContactsTel = contactsTel;
    }

    public String getReplyEmpNum() {
        return ReplyEmpNum;
    }

    public void setReplyEmpNum(String replyEmpNum) {
        ReplyEmpNum = replyEmpNum;
    }

    public Date getReplyTime() {
        return ReplyTime;
    }

    public void setReplyTime(Date replyTime) {
        ReplyTime = replyTime;
    }

    public String getSMS() {
        return SMS;
    }

    public void setSMS(String sMS) {
        SMS = sMS;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
