package cn.edu.sjtu.dclab.slamke.unityprima.domain;

/**
 * �������˱� ������tb_TaskPerson �������ݿ⣺ServiceInformationManageSystem
 * 
 * @author sunke
 * 
 */
public class TaskPerson {
	public static final String TABLE_NAME = "tb_TaskPerson";

	private int ID;

	private String TaskNum;

	private String EmpNum;
	private String ScorePercent;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTaskNum() {
		return TaskNum;
	}

	public void setTaskNum(String taskNum) {
		TaskNum = taskNum;
	}

	public String getEmpNum() {
		return EmpNum;
	}

	public void setEmpNum(String empNum) {
		EmpNum = empNum;
	}

	public String getScorePercent() {
		return ScorePercent;
	}

	public void setScorePercent(String scorePercent) {
		ScorePercent = scorePercent;
	}

}
