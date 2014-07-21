package cn.edu.sjtu.dclab.slamke.unityprima.webservice;

import java.net.URLDecoder;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICustomerDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.ISuggestionDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.ITaskDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.CustomerDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.SuggestionDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.TaskDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.CommentInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.SellInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Suggestion;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Task;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.AdviceInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.ComplaintInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.ConsultInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.RepairInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.util.ClassParse;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Const;
import cn.edu.sjtu.dclab.slamke.unityprima.util.DateParse;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Message;

@Path("/submit")
public class UploadService {

	private static Logger Log = Logger.getLogger(UploadService.class);

	private ICustomerDao dao;
	private ISuggestionDao suggestionDao;
	private ITaskDao taskDao;

	public UploadService() {
		dao = new CustomerDaoImpl();
		suggestionDao = new SuggestionDaoImpl();
		taskDao = new TaskDaoImpl();
	}

	@POST
	@Path("/advice")
	@Produces("application/json")
	public String submitAdvice(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		System.out.println("tel:" + tel);
		Log.debug("tel " + tel + "�������");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "������飬����Ϊ��" + content);
				System.out.println("content:" + content);
				ClassParse parse = new ClassParse();
				AdviceInfo info = parse.string2AdviceInfo(content);
				if (info != null) {
					Suggestion suggestion = new Suggestion();
					suggestion.setContacts(customer.getDefaultContactor());
					suggestion.setContactsTel(tel);
					suggestion
							.setContent(Const.SUGGESTION_TYPE_ADVICE
									+ info.getContent() + "(���ͣ�"
									+ info.getType() + ")");
					suggestion.setSMS(info.toMessage());
					suggestionDao.insertSuggestion(suggestion);
					Log.debug("tel " + tel + "�������--�ɹ�");
					return Message.SUCCESS;
				} else {
					Log.error("tel " + tel + "�������--���ݽ���ʧ��");
					System.out.println("parse error!");
					return Message.ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel " + tel + "�������--���ݽ���ʧ��");
				e.printStackTrace();
				return Message.ERROR;
			}
		}
		Log.error("tel " + tel + "�������--�����֤ʧ��");
		return Message.ERROR;
	}

	@POST
	@Path("/comment")
	@Produces("application/json")
	public String submitComment(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		Log.debug("tel " + tel + "�������");
		System.out.println("tel:" + tel);
		try {
			content = URLDecoder.decode(content, "UTF-8");
			System.out.println("content:" + content);
			Log.debug("tel " + tel + "������ۣ�����Ϊ��" + content);
			if (customer != null) {
				ClassParse parse = new ClassParse();
				CommentInfo info = parse.string2CommentInfo(content);
				if (info != null) {
					boolean result = taskDao.inertTaskEvaluation(info);
					Log.debug("tel " + tel + "�������--�ɹ�");
					if (result) {
						return Message.SUCCESS;
					}else {
						return Message.ERROR;
					}
				} else {
					Log.error("tel " + tel + "�������--���ݽ���ʧ��");
					System.out.println("parse error!");
					return Message.ERROR;
				}
			} else {
				Log.error("tel " + tel + "�������--�����֤ʧ��");
				return Message.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("tel " + tel + "�������--���ݽ���ʧ��");
			e.printStackTrace();
			return Message.ERROR;
		}
	}

	@POST
	@Path("/complaint")
	@Produces("application/json")
	public String submitComplaint(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		System.out.println("tel:" + tel);
		Log.debug("tel " + tel + "���Ͷ��");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "���Ͷ�ߣ�����Ϊ��" + content);
				System.out.println("content:" + content);
				ClassParse parse = new ClassParse();
				ComplaintInfo info = parse.string2ComplaintInfo(content);
				if (info != null) {
					Suggestion suggestion = new Suggestion();
					suggestion.setContacts(customer.getDefaultContactor());
					suggestion.setContactsTel(tel);
					suggestion.setContent(Const.SUGGESTION_TYPE_COMPLAINT
							+ info.getContent());
					suggestion.setSMS(info.toMessage());
					suggestionDao.insertSuggestion(suggestion);
					Log.debug("tel " + tel + "���Ͷ��--�ɹ�");
					return Message.SUCCESS;
				}
				Log.error("tel " + tel + "���Ͷ�ߣ����ݽ�������");
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel " + tel + "���Ͷ�ߣ����ݽ�������");
				e.printStackTrace();
				return Message.ERROR;
			}

		}
		Log.error("tel " + tel + "���Ͷ��--�����֤����");
		return Message.ERROR;
	}

	@POST
	@Path("/repair")
	@Produces("application/json")
	public String submitRepair(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		System.out.println("tel:" + tel);
		Log.debug("tel " + tel + "����");
		Log.debug("content " + content + "����");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "���ޣ�����Ϊ��" + content);
				System.out.println("content:" + content);
				ClassParse parse = new ClassParse();
				RepairInfo info = parse.string2RepairInfo(content);
				if (info != null) {
					Task task = new Task();
					DateParse parse2 = new DateParse();
					String num = "U" + parse2.date2String(new Date());
					task.setCustomerNum(customer.getNum());
					task.setDefaultContactor(customer.getDefaultContactor());
					task.setDefaultContactorTel(tel);
					if (info.getDevice().getNum().equals("")
							|| info.getDevice().getNum() != null) {
						task.setDeviceNum(info.getDevice().getNum());
					} else {
						task.setDeviceNum(info.getDevice().getType());
					}
					task.setSource(Const.DEFAULT_WEBSERVICE_SOURCE);
					task.setStatus(Const.DEFAULT_WEBSERVICE_STATUS);
					task.setContent(info.toString());
					boolean result = taskDao.insertTask(task, num);
					Log.debug("tel " + tel + "����--�ɹ�");
					if (result) {
						return Message.SUCCESS;
					}else {
						return Message.ERROR;
					}
				} else {
					Log.debug("tel " + tel + "����--���ݽ���ʧ��");
					return Message.ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.debug("tel " + tel + "����--���ݽ���ʧ��");
				e.printStackTrace();
				return Message.ERROR;
			}
		}
		Log.debug("tel " + tel + "����--�����֤ʧ��");
		return Message.ERROR;
	}

	/**
	 * �ύ���������Ϣ
	 * 
	 * @param tel
	 * @param content
	 * @return
	 */
	@POST
	@Path("/sell")
	@Produces("application/json")
	public String submitSellInfo(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		System.out.println("tel:" + tel);
		try {
			content = URLDecoder.decode(content, "UTF-8");
			System.out.println("content:" + content);
			Log.debug("tel " + tel + "���򱸼�������Ϊ��" + content);
			if (customer != null) {
				ClassParse parse = new ClassParse();
				SellInfo info = parse.string2SellInfo(content);
				if (info != null) {
					Task task = new Task();
					DateParse parse2 = new DateParse();
					String num = "B" + parse2.date2String(new Date());
					task.setCustomerNum(customer.getNum());
					task.setDefaultContactor(customer.getDefaultContactor());
					task.setDefaultContactorTel(tel);
					task.setSource(Const.DEFAULT_WEBSERVICE_SOURCE);
					task.setStatus(Const.DEFAULT_WEBSERVICE_STATUS);
					task.setContent(info.toString());
					taskDao.insertTask(task, num);
					Log.debug("tel " + tel + "���򱸼����ɹ�");
					return Message.SUCCESS;
				} else {
					Log.debug("tel " + tel + "���򱸼�--���ݽ���ʧ��");
					return Message.ERROR;
				}
			} else {
				Log.debug("tel " + tel + "���򱸼�--�����֤ʧ��");
				return Message.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.debug("tel " + tel + "���򱸼�--���ݽ���ʧ��");
			return Message.ERROR;
		}
	}

	@POST
	@Path("/consult")
	@Produces("application/json")
	public String submitConsult(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		System.out.println("tel:" + tel);
		Log.debug("tel " + tel + "��ѯ");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "��ѯ������Ϊ��" + content);
				System.out.println("content:" + content);
				ClassParse parse = new ClassParse();
				ConsultInfo info = parse.string2ConsultInfo(content);
				if (info != null) {
					Task task = new Task();
					DateParse parse2 = new DateParse();
					String num = parse2.date2String(new Date());
					task.setCustomerNum(customer.getNum());
					task.setDefaultContactor(customer.getDefaultContactor());
					task.setDefaultContactorTel(tel);
					task.setSource(Const.DEFAULT_WEBSERVICE_SOURCE);
					task.setStatus(Const.DEFAULT_WEBSERVICE_STATUS);
					task.setContent(info.toString());
					taskDao.insertTask(task, num);
					Log.debug("tel " + tel + "��ѯ--�ɹ�");
					return Message.SUCCESS;
				} else {
					Log.debug("tel " + tel + "��ѯ--���ݽ���ʧ��");
					return Message.ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.debug("tel " + tel + "��ѯ--���ݽ���ʧ��");
				return Message.ERROR;
			}
		}
		Log.debug("tel " + tel + "��ѯ--�����֤ʧ��");
		return Message.ERROR;
	}
}
