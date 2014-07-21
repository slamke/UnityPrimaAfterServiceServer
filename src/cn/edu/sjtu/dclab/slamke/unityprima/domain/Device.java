package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.Date;

/**
 * 设备表 表名tb_Device所在数据库：ServiceInformationManageSystem
 * @author sunke
 *
 */
public class Device {
	
	public static final String TABLE_NAME = "tb_Device";
	/**
	 * Num	编号	nvarchar(50)	主键
	 */
	private String num;
	/**
	 * Type	型号	nvarchar(50)	非空
	 */
	private String type;
	/**
	 * AcceptTime	验收时间	datetime	可空
	 */
	private Date acceptTime;
	
	/**
	 * WarrantyPeriod	质保期	int	可空
	 */
	private int warrantyPeriod;
	
	/**
	 * Customer	客户编号	nvarchar(50)	非空，（对应客户表中的编号）
	 */
	private Customer customer;
	
	/**
	 * ContractNum	合同号	nvarchar(50)	可空
	 */
	private String contractNum;
	/**
	 * ControlSystem	控制系统	nvarchar(50)	可空
	 */
	private String controlSystem;
	
	/**
	 * ControlSystemSerial	控制系统序列号	nvarchar(50)	可空
	 */
	private String controlSystemSerial;
	
	/**
	 * Laser	激光器	nvarchar(50)	可空
	 */
	private String laser;
	
	/**
	 * LaserSerial	激光器序列号	nvarchar(50)	可空
	 */
	private String laserSerial;
	
	/**
	 * Programming	编程软件	nvarchar(50)	可空
	 */
	private String programming;
	
	/**
	 * DongleNum	加密狗序列号	nvarchar(50)	可空
	 */
	private String dongleNum;
	
	/**
	 * Remark	备注	nvarchar(MAX)	可空
	 */
	private String remark;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(int warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getControlSystem() {
		return controlSystem;
	}

	public void setControlSystem(String controlSystem) {
		this.controlSystem = controlSystem;
	}

	public String getControlSystemSerial() {
		return controlSystemSerial;
	}

	public void setControlSystemSerial(String controlSystemSerial) {
		this.controlSystemSerial = controlSystemSerial;
	}

	public String getLaser() {
		return laser;
	}

	public void setLaser(String laser) {
		this.laser = laser;
	}

	public String getLaserSerial() {
		return laserSerial;
	}

	public void setLaserSerial(String laserSerial) {
		this.laserSerial = laserSerial;
	}

	public String getProgramming() {
		return programming;
	}

	public void setProgramming(String programming) {
		this.programming = programming;
	}

	public String getDongleNum() {
		return dongleNum;
	}

	public void setDongleNum(String dongleNum) {
		this.dongleNum = dongleNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Device() {
		super();
	}

	public Device(String num, String type, Customer customer) {
		super();
		this.num = num;
		this.type = type;
		this.customer = customer;
	}
}
