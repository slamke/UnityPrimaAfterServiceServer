package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.List;

/**
 * 客户信息表 表名：tb_Customer 所在数据库：ServiceInformationManageSystem
 * @author sunke
 *
 */
public class Customer {
	public static final String TABLE_NAME = "tb_Customer";
	
	/**
	 * ID	ID	int	主键，自增1
	 */
	private int id;
	/**
	 * Num	客户编号	nvarchar(50)	非空
	 */
	private String num;
	/**
	 * Name	客户名	nvarchar(100)	非空
	 */
	private String name;
	/**
	 * Mail	邮箱	nvarchar(100)	可空
	 */
	private String mail;
	/**
	 * SmsTel	短信账号	nvarchar(50)	非空
	 */
	private String smsTel;
	
	/**
	 * FaxNum	传真号	nvarchar(50)	非空
	 */
	private String faxNum;
	
	/**
	 * WebAddress	网址	nvarchar(500)	可空
	 */
	private String webAddress;
	/**
	 * DefaultContactor	默认联系人	nvarchar(50)	非空
	 */
	private String defaultContactor;
	
	/**
	 * DefaultContactorTel	默认联系人电话	nvarchar(50)	非空
	 */
	private String defaultContactorTel;
	
	/**
	 * Chairman	董事长	nvarchar(50)	可空
	 */
	private String chairman;
	/**
	 * ChairmanTel	董事长电话	nvarchar(50)	可空
	 */
	private String chairmanTel;
	
	/**
	 * LegalPerson	法人	nvarchar(50)	可空
	 */
	private String legalPerson;
	
	/**
	 * LegalPersonTel	法人电话	nvarchar(50)	可空
	 */
	private String legalPersonTel;
	
	/**
	 * GeneralManager	总经理	nvarchar(50)	可空
	 */
	private String generalManager;
	
	/**
	 * GeneralManagerTel	总经理电话	nvarchar(50)	可空
	 */
	private String generalManagerTel;
	
	/**
	 * FinanceSupervisor	财务主管	nvarchar(50)	可空
	 */
	private String financeSupervisor;
	
	/**
	 * FinanceSupervisorTel	财务主管电话	nvarchar(50)	可空
	 */
	private String financeSupervisorTel;
	
	/**
	 * MachineSupervisor	设备主管	nvarchar(50)	可空
	 */
	private String machineSupervisor;
	
	/**
	 * MachineSupervisorTel	设备主管电话	nvarchar(50)	可空
	 */
	private String machineSupervisorTel;
	
	/**
	 * OperationStaff	操作工	nvarchar(50)	可空
	 */
	private String operationStaff;
	
	/**
	 * OperationStaffTel	操作工电话	nvarchar(50)	可空
	 */
	private String operationStaffTel;
	
	/**
	 * Remark	备注	nvarchar(MAX)	可空
	 */
	private String remark;
	
	private List<Device> devices;
	
	

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSmsTel() {
		return smsTel;
	}

	public void setSmsTel(String smsTel) {
		this.smsTel = smsTel;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public String getDefaultContactor() {
		return defaultContactor;
	}

	public void setDefaultContactor(String defaultContactor) {
		this.defaultContactor = defaultContactor;
	}

	public String getDefaultContactorTel() {
		return defaultContactorTel;
	}

	public void setDefaultContactorTel(String defaultContactorTel) {
		this.defaultContactorTel = defaultContactorTel;
	}

	public String getChairman() {
		return chairman;
	}

	public void setChairman(String chairman) {
		this.chairman = chairman;
	}

	public String getChairmanTel() {
		return chairmanTel;
	}

	public void setChairmanTel(String chairmanTel) {
		this.chairmanTel = chairmanTel;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonTel() {
		return legalPersonTel;
	}

	public void setLegalPersonTel(String legalPersonTel) {
		this.legalPersonTel = legalPersonTel;
	}

	public String getGeneralManager() {
		return generalManager;
	}

	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}

	public String getGeneralManagerTel() {
		return generalManagerTel;
	}

	public void setGeneralManagerTel(String generalManagerTel) {
		this.generalManagerTel = generalManagerTel;
	}

	public String getFinanceSupervisor() {
		return financeSupervisor;
	}

	public void setFinanceSupervisor(String financeSupervisor) {
		this.financeSupervisor = financeSupervisor;
	}

	public String getFinanceSupervisorTel() {
		return financeSupervisorTel;
	}

	public void setFinanceSupervisorTel(String financeSupervisorTel) {
		this.financeSupervisorTel = financeSupervisorTel;
	}

	public String getMachineSupervisor() {
		return machineSupervisor;
	}

	public void setMachineSupervisor(String machineSupervisor) {
		this.machineSupervisor = machineSupervisor;
	}

	public String getMachineSupervisorTel() {
		return machineSupervisorTel;
	}

	public void setMachineSupervisorTel(String machineSupervisorTel) {
		this.machineSupervisorTel = machineSupervisorTel;
	}

	public String getOperationStaff() {
		return operationStaff;
	}

	public void setOperationStaff(String operationStaff) {
		this.operationStaff = operationStaff;
	}

	public String getOperationStaffTel() {
		return operationStaffTel;
	}

	public void setOperationStaffTel(String operationStaffTel) {
		this.operationStaffTel = operationStaffTel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
