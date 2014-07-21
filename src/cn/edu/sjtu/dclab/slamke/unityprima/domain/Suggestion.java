package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.Date;
/**
 * Ͷ�߽���� ������tb_Suggestion �������ݿ⣺ServiceInformationManageSystem
 * @author sunke
 *
 */
public class Suggestion {
	public static final String TABLE_NAME = "tb_Suggestion";
	/**
	 * Num	Ͷ�ߺͽ���ı��	nvarchar(50)	�Զ����1������д�õĺ����Զ����
	 */
	private String Num;
	/**
	 * 	����	nvarchar(100)	
	 */
	private String Content;
	
	/**
	 * 	���ʱ��	datetime	
	 */
	private Date InsertTime;
	/**
	 * 	��ϵ��	nvarchar(50)	
	 */
	private String Contacts;
	/**
	 * 	��ϵ�˵绰	nvarchar(50)	
	 */
	private String ContactsTel;
	/**
	 * 	�ظ��߹���	nvarchar(50)	
	 */
	private String ReplyEmpNum;
	/**
	 * 	�ظ�ʱ��	datetime	
	 */
	private Date ReplyTime;
	/**
	 * 	��������	nvarchar(MAX)
	 */
	private String SMS;
	/**
	 * Remark	��ע	nvarchar(MAX)	
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
