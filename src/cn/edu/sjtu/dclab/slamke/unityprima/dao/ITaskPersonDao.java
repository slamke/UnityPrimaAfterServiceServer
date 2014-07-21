package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.TaskPerson;

public interface ITaskPersonDao {
	/**
	 * 通过taskNum检索相关的task人员分配
	 * @param taskNum
	 * @return
	 */
	public TaskPerson getTaskPersonByTaskNum(String taskNum);
}
