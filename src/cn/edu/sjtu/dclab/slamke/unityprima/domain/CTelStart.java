package cn.edu.sjtu.dclab.slamke.unityprima.domain;
/**
 * �ֻ��������� ������tb_TelStart �������ݿ⣺MessageManage
 * @author sunke
 *
 */
public class CTelStart {
	public static final String TABLE_NAME = "tb_TelStart";
	
	private int ID;
	private String TelStart;
	private String SendNo;
	private String AlisaNo;
	private String Supplier;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTelStart() {
		return TelStart;
	}
	public void setTelStart(String telStart) {
		TelStart = telStart;
	}
	public String getSendNo() {
		return SendNo;
	}
	public void setSendNo(String sendNo) {
		SendNo = sendNo;
	}
	public String getAlisaNo() {
		return AlisaNo;
	}
	public void setAlisaNo(String alisaNo) {
		AlisaNo = alisaNo;
	}
	public String getSupplier() {
		return Supplier;
	}
	public void setSupplier(String supplier) {
		Supplier = supplier;
	}
	
}
