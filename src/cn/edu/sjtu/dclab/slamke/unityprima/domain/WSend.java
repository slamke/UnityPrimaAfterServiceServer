package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.sql.Timestamp;

/**
 * ���ŷ��ͱ� ������WSend �������ݿ⣺haduosmsserver
 * @author sunke
 *
 */
public class WSend {
	public static final String TABLE_NAME ="WSend";
	
	/**
	 * mbno	�����ֻ���	varchar(30)	�ǿ�
	 */
	private String mbno;
	/**
	 * SMS	��������	varchar(700)	�ǿ�
	 */
	private String SMS;
	
	/**
	 * Wtime	д���ʱ��	datetime	�ǿ�
	 */
	private Timestamp wTime;
	
	/**
	 * SendSN	�����˺�	varchar(20)	ͳһ�ã�50002
	 */
	private String sendSN;
	
	/**
	 * SubAccount	���˺�	varchar(10)	ͳһ�ã�1111
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
