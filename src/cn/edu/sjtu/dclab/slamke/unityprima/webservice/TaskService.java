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
		Log.debug("tel:"+tel+" �鿴����״̬�������ţ�"+num);
		if (customer != null) {
			try {
				num = URLDecoder.decode(num, "UTF-8");
				System.out.println("num:"+num);
				Task task = taskDao.getTaskByNum(num);
				if (task == null) {
					Log.error("tel:"+tel+" �鿴����״̬�������ţ�"+num+"--�������������");
					return Message.ERROR;
				}
				TaskPerson person = taskPersonDao.getTaskPersonByTaskNum(num);
				Employee employee = null;
				if (person != null) {
					employee = employeeDao.getEmployeeByNum(person.getEmpNum());
				}
				DateParse dateParse = new DateParse();
				StringBuffer sb = new StringBuffer();
				sb.append("����ѯ��������Ϊ");
				sb.append(num);
				if (task.getInsertTime() != null) {
					sb.append(",���ʱ��Ϊ");
					sb.append(dateParse.date2AnotherString(task.getInsertTime()));
				}			
				sb.append(",");
				sb.append(task.getRemark());
				sb.append(";Ŀǰ����Ϊ:");
				sb.append(StatusParser.getStatus(task.getStatus().trim()));
				if (employee != null) {
					sb.append(",������Ϊ");
					sb.append(employee.getName());
				}else {
					Log.error("tel:"+tel+" �鿴����״̬�������ţ�"+num+"�������˲���ʧ�ܣ�task num"+task.getNum());
					System.out.println("�����˲���ʧ�ܣ�task num"+task.getNum());
				}
				sb.append("���������Դ�绰����Ůʿ��13816156445���˽�����.");
				Log.debug("tel:"+tel+" �鿴����״̬�������ţ�"+num+"�鿴�ɹ�--result:"+sb.toString());
				return sb.toString();
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+" �鿴����״̬�������ţ�"+num+"--���ݽ�������");
				e.printStackTrace();
			}			
		}
		Log.error("tel:"+tel+" �鿴����״̬�������ţ�"+num+"--�����֤ʧ��");
		return Message.ERROR;
	}
	
	@GET
	@Path("/unevaluated_list")
	@Produces("application/json")
	public String getUnEvaluatedTaskList(@HeaderParam("tel") String tel) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" ��ȡδ���������б�");
		if (customer != null) {
			try {
				List<Task> tasks = taskDao.getUnEvaluatedTasks(customer.getNum());
				if (tasks == null || tasks.size() == 0) {
					//ȫ���������
					Log.debug("tel:"+tel+" ��ȡδ���������б�--ȫ������");
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
					//�������б�
					Log.debug("tel:"+tel+" ��ȡδ���������б�--���۳ɹ���result��"+result);
					return result;
				}
				Log.error("tel:"+tel+" ��ȡδ���������б�--���ݽ�������");
				//��������
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+" ��ȡδ���������б�--���ݽ�������");
				e.printStackTrace();
			}			
		}
		Log.error("tel:"+tel+" ��ȡδ���������б�--�����֤����");
		return Message.ERROR;
	}
	
	@GET
	@Path("/unclosed_list")
	@Produces("application/json")
	public String getUnclosedTaskList(@HeaderParam("tel") String tel) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" ��ȡδ�ر������б�");
		if (customer != null) {
			try {
				List<Task> tasks = taskDao.getUnClosedTasks(customer.getNum());
				if (tasks == null || tasks.size() == 0) {
					//ȫ�������Ѿ��ر�
					Log.debug("tel:"+tel+" ��ȡδ�ر������б�--ȫ���ر�");
					return Message.SUCCESS;
				}
				ClassParse parse = new ClassParse();
				String result = parse.taskList2String(tasks);
				if (result != null) {
					//����ѯ�б�
					Log.debug("tel:"+tel+" ��ȡδ�ر������б�--�ɹ���result:"+result);
					return result;
				}
				//��������
				Log.debug("tel:"+tel+" ��ȡδ�ر������б�--���ݽ�������");
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.debug("tel:"+tel+" ��ȡδ�ر������б�--���ݽ�������");
				e.printStackTrace();
			}			
		}
		Log.debug("tel:"+tel+" ��ȡδ�ر������б�--�����֤����");
		return Message.ERROR;
	}
	
	@GET
	@Path("/progress_list")
	@Produces("application/json")
	public String getUnclosedAndProgressTaskList(@HeaderParam("tel") String tel) {
		Customer customer = dao.login(tel);
		System.out.println("tel:"+tel);
		Log.debug("tel:"+tel+" ��ȡ���Ȳ�ѯ�����б�");
		if (customer != null) {
			try {
				List<Task> tasks = taskDao.getUnClosedAndProgressTasks(customer.getNum());
				if (tasks == null || tasks.size() == 0) {
					//ȫ�������Ѿ��ر�
					Log.debug("tel:"+tel+" ��ȡ���Ȳ�ѯ�����б�--ȫ���ر�");
					return Message.SUCCESS;
				}
				ClassParse parse = new ClassParse();
				String result = parse.taskList2String(tasks);
				if (result != null) {
					//����ѯ�б�
					Log.debug("tel:"+tel+" ��ȡ���Ȳ�ѯ�����б�--�ɹ���result:"+result);
					return result;
				}
				//��������
				Log.debug("tel:"+tel+" ��ȡ���Ȳ�ѯ�����б�--���ݽ�������");
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.debug("tel:"+tel+" ��ȡ���Ȳ�ѯ�����б�--���ݽ�������");
				e.printStackTrace();
			}			
		}
		Log.debug("tel:"+tel+" ��ȡ���Ȳ�ѯ�����б�--�����֤����");
		return Message.ERROR;
	}
}
