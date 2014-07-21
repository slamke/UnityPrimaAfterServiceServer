package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.sql.Timestamp;

public class AuthCode {
	public static final String TABLE_NAME ="tb_AuthCode";
	
	private long id;
	
	private String tel;
	
	private String code;
	
	private Timestamp insertTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
}
