package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.TaskPerson;

public interface ITaskPersonDao {
	/**
	 * ͨ��taskNum������ص�task��Ա����
	 * @param taskNum
	 * @return
	 */
	public TaskPerson getTaskPersonByTaskNum(String taskNum);
}
