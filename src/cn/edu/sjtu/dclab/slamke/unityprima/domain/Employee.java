package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.Date;

/**
 * 职员信息表 表名：tb_Employee 所在数据库：MessageManage
 * 
 * @author sunke
 * 
 */
public class Employee {

	public static final String TABLE_NAME = "tb_Employee";

	private String Num;
	private String Name;
	private String Gender;
	private String DepNum;
	private String TelNum;
	private String SkillNum;
	private String College;
	private String Speciality;
	private String Address;
	private Date Birthday;
	private String EmergencyContactPerson;
	private String EmergencyContactPersonTel;
	private boolean IsWork;

	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getDepNum() {
		return DepNum;
	}

	public void setDepNum(String depNum) {
		DepNum = depNum;
	}

	public String getTelNum() {
		return TelNum;
	}

	public void setTelNum(String telNum) {
		TelNum = telNum;
	}

	public String getSkillNum() {
		return SkillNum;
	}

	public void setSkillNum(String skillNum) {
		SkillNum = skillNum;
	}

	public String getCollege() {
		return College;
	}

	public void setCollege(String college) {
		College = college;
	}

	public String getSpeciality() {
		return Speciality;
	}

	public void setSpeciality(String speciality) {
		Speciality = speciality;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public String getEmergencyContactPerson() {
		return EmergencyContactPerson;
	}

	public void setEmergencyContactPerson(String emergencyContactPerson) {
		EmergencyContactPerson = emergencyContactPerson;
	}

	public String getEmergencyContactPersonTel() {
		return EmergencyContactPersonTel;
	}

	public void setEmergencyContactPersonTel(String emergencyContactPersonTel) {
		EmergencyContactPersonTel = emergencyContactPersonTel;
	}

	public boolean isIsWork() {
		return IsWork;
	}

	public void setIsWork(boolean isWork) {
		IsWork = isWork;
	}
}
