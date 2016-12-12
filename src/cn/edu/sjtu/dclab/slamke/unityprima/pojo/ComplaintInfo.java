package cn.edu.sjtu.dclab.slamke.unityprima.pojo;

import java.io.Serializable;

public class ComplaintInfo implements Serializable 
{
    private static final long serialVersionUID = 23L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ComplaintInfo() {
        super();
    }

    public ComplaintInfo(String content) {
        super();
        this.content = content;
    }

    @Override
    public String toString() {
        return "内容：" + content;
    }

    public String toMessage() {
        return "GKTS\n" + content;
    }
}
