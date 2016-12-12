package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.CommentInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Task;

public interface ITaskDao {
	public boolean insertTask(Task task,String num);
	
	public List<Task> getUnEvaluatedTasks(String CustomerNum);
	
	public Task getTaskByNum(String num);
	
	public List<Task> getUnClosedTasks(String CustomerNum);
	
	public boolean inertTaskEvaluation(CommentInfo info);
	
	public List<Task> getUnClosedAndProgressTasks(String CustomerNum);
	
	public String getProgress(String num);
}
