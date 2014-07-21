package cn.edu.sjtu.dclab.slamke.unityprima.webservice;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICustomerDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.IEmployeeDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.ITaskDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.ITaskPersonDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.CustomerDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.EmployeeDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.TaskDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.TaskPersonDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Employee;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Task;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.TaskPerson;
import cn.edu.sjtu.dclab.slamke.unityprima.util.ClassParse;
import cn.edu.sjtu.dclab.slamke.unityprima.util.DateParse;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Message;
import cn.edu.sjtu.dclab.slamke.unityprima.util.StatusParser;

@Path("/task")
public class TaskService {
	private static Logger Log = Logger.getLogger(TaskService.class);
	
	private ITaskDao taskDao;
	private ICustomerDao dao;
	private ITaskPersonDao taskPersonDao;
	private IEmployeeDao employeeDao;
	
	public TaskService() {
		taskDao = new TaskDaoImpl();
		dao = new CustomerDaoImpl();
		taskPersonDao = new TaskPersonDaoImpl();
		employeeDao = new EmployeeDaoImpl();
	}

	@GET
	@Path("/checkstatus")
	@Produces("application/json")
	public String checkTaskStatus(@HeaderParam("tel") String tel,@HeaderParam("num") String num) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" 查看任务状态，任务编号："+num);
		if (customer != null) {
			try {
				num = URLDecoder.decode(num, "UTF-8");
				System.out.println("num:"+num);
				Task task = taskDao.getTaskByNum(num);
				if (task == null) {
					Log.error("tel:"+tel+" 查看任务状态，任务编号："+num+"--任务检索不存在");
					return Message.ERROR;
				}
				TaskPerson person = taskPersonDao.getTaskPersonByTaskNum(num);
				Employee employee = null;
				if (person != null) {
					employee = employeeDao.getEmployeeByNum(person.getEmpNum());
				}
				DateParse dateParse = new DateParse();
				StringBuffer sb = new StringBuffer();
				sb.append("您查询的事项编号为");
				sb.append(num);
				if (task.getInsertTime() != null) {
					sb.append(",提出时间为");
					sb.append(dateParse.date2AnotherString(task.getInsertTime()));
				}			
				sb.append(",");
				sb.append(task.getRemark());
				sb.append(";目前进度为:");
				sb.append(StatusParser.getStatus(task.getStatus().trim()));
				if (employee != null) {
					sb.append(",责任人为");
					sb.append(employee.getName());
				}else {
					Log.error("tel:"+tel+" 查看任务状态，任务编号："+num+"，责任人查找失败：task num"+task.getNum());
					System.out.println("责任人查找失败：task num"+task.getNum());
				}
				sb.append("。您还可以打电话给奚女士（13816156445）了解详情.");
				Log.debug("tel:"+tel+" 查看任务状态，任务编号："+num+"查看成功--result:"+sb.toString());
				return sb.toString();
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+" 查看任务状态，任务编号："+num+"--数据解析错误");
				e.printStackTrace();
			}			
		}
		Log.error("tel:"+tel+" 查看任务状态，任务编号："+num+"--身份验证失败");
		return Message.ERROR;
	}
	
	@GET
	@Path("/unevaluated_list")
	@Produces("application/json")
	public String getUnEvaluatedTaskList(@HeaderParam("tel") String tel) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" 获取未评估任务列表");
		if (customer != null) {
			try {
				List<Task> tasks = taskDao.getUnEvaluatedTasks(customer.getNum());
				if (tasks == null || tasks.size() == 0) {
					//全部评价完成
					Log.debug("tel:"+tel+" 获取未评估任务列表--全部评价");
					return Message.SUCCESS;
				}
				Map<String, String> taskPersonPair = new HashMap<String, String>();
				for (int i = 0; i < tasks.size(); i++) {
					String tempNum = tasks.get(i).getNum();
					TaskPerson person = taskPersonDao.getTaskPersonByTaskNum(tempNum);
					Employee employee = null;
					if (person != null) {
						employee = employeeDao.getEmployeeByNum(person.getEmpNum());
					}
					if (employee != null) {
						taskPersonPair.put(tasks.get(i).getNum(), employee.getName());
					}else {
						taskPersonPair.put(tasks.get(i).getNum(), "***");
					}
				}
				ClassParse parse = new ClassParse();
				String result = parse.taskPersonPair2String(taskPersonPair);
				if (result != null) {
					//待评价列表
					Log.debug("tel:"+tel+" 获取未评估任务列表--评价成功，result："+result);
					return result;
				}
				Log.error("tel:"+tel+" 获取未评估任务列表--数据解析错误");
				//解析出错
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+" 获取未评估任务列表--数据解析错误");
				e.printStackTrace();
			}			
		}
		Log.error("tel:"+tel+" 获取未评估任务列表--身份验证错误");
		return Message.ERROR;
	}
	
	@GET
	@Path("/unclosed_list")
	@Produces("application/json")
	public String getUnclosedTaskList(@HeaderParam("tel") String tel) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" 获取未关闭任务列表");
		if (customer != null) {
			try {
				List<Task> tasks = taskDao.getUnClosedTasks(customer.getNum());
				if (tasks == null || tasks.size() == 0) {
					//全部任务已经关闭
					Log.debug("tel:"+tel+" 获取未关闭任务列表--全部关闭");
					return Message.SUCCESS;
				}
				ClassParse parse = new ClassParse();
				String result = parse.taskList2String(tasks);
				if (result != null) {
					//待查询列表
					Log.debug("tel:"+tel+" 获取未关闭任务列表--成功，result:"+result);
					return result;
				}
				//解析出错
				Log.debug("tel:"+tel+" 获取未关闭任务列表--数据解析错误");
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.debug("tel:"+tel+" 获取未关闭任务列表--数据解析错误");
				e.printStackTrace();
			}			
		}
		Log.debug("tel:"+tel+" 获取未关闭任务列表--身份验证错误");
		return Message.ERROR;
	}
	
	@GET
	@Path("/progress_list")
	@Produces("application/json")
	public String getUnclosedAndProgressTaskList(@HeaderParam("tel") String tel) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" 获取进度查询任务列表");
		if (customer != null) {
			try {
				List<Task> tasks = taskDao.getUnClosedAndProgressTasks(customer.getNum());
				if (tasks == null || tasks.size() == 0) {
					//全部任务已经关闭
					Log.debug("tel:"+tel+" 获取进度查询任务列表--全部关闭");
					return Message.SUCCESS;
				}
				ClassParse parse = new ClassParse();
				String result = parse.taskList2String(tasks);
				if (result != null) {
					//待查询列表
					Log.debug("tel:"+tel+" 获取进度查询任务列表--成功，result:"+result);
					return result;
				}
				//解析出错
				Log.debug("tel:"+tel+" 获取进度查询任务列表--数据解析错误");
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.debug("tel:"+tel+" 获取进度查询任务列表--数据解析错误");
				e.printStackTrace();
			}			
		}
		Log.debug("tel:"+tel+" 获取进度查询任务列表--身份验证错误");
		return Message.ERROR;
	}
}
