package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.Date;

/**
 * 记录下每次验证成功的手机号码，并分类（安卓、苹果和其它）
 * 
 * @author sunke
 * 
 */
public class PUserTel {
    public static final String TABLE_NAME = "tb_PUserTel";

    /**
     * Id Int 主键，标识(1，1)
     */
    String Id;
    /**
     * 登录的手机号 Varchar(50)
     */
    String TelNum;
    /**
     * 类别 Varchar(20)
     */
    String Type;
    /**
     * 添加时间 Datetime 非空，默认值getdate()
     */
    Date InsertTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTelNum() {
        return TelNum;
    }

    public void setTelNum(String telNum) {
        TelNum = telNum;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(Date insertTime) {
        InsertTime = insertTime;
    }

    public final static String TYPE_ANDROID = "安卓";
    public final static String TYPE_IOS = "苹果";
    public final static String TYPE_ELSE = "其他";

    public PUserTel(String telNum, String type) {
        super();
        TelNum = telNum;
        Type = type;
    }

}
