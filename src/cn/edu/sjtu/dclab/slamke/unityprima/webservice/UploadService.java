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
		Log.debug("tel " + tel + "提出建议");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "提出建议，内容为：" + content);
				System.out.println("content:" + content);
				ClassParse parse = new ClassParse();
				AdviceInfo info = parse.string2AdviceInfo(content);
				if (info != null) {
					Suggestion suggestion = new Suggestion();
					suggestion.setContacts(customer.getDefaultContactor());
					suggestion.setContactsTel(tel);
					suggestion
							.setContent(Const.SUGGESTION_TYPE_ADVICE
									+ info.getContent() + "(类型："
									+ info.getType() + ")");
					suggestion.setSMS(info.toMessage());
					suggestionDao.insertSuggestion(suggestion);
					Log.debug("tel " + tel + "提出建议--成功");
					return Message.SUCCESS;
				} else {
					Log.error("tel " + tel + "提出建议--数据解析失败");
					System.out.println("parse error!");
					return Message.ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel " + tel + "提出建议--数据解析失败");
				e.printStackTrace();
				return Message.ERROR;
			}
		}
		Log.error("tel " + tel + "提出建议--身份验证失败");
		return Message.ERROR;
	}

	@POST
	@Path("/comment")
	@Produces("application/json")
	public String submitComment(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		Log.debug("tel " + tel + "提出评价");
		System.out.println("tel:" + tel);
		try {
			content = URLDecoder.decode(content, "UTF-8");
			System.out.println("content:" + content);
			Log.debug("tel " + tel + "提出评价，内容为：" + content);
			if (customer != null) {
				ClassParse parse = new ClassParse();
				CommentInfo info = parse.string2CommentInfo(content);
				if (info != null) {
					boolean result = taskDao.inertTaskEvaluation(info);
					Log.debug("tel " + tel + "提出评价--成功");
					if (result) {
						return Message.SUCCESS;
					}else {
						return Message.ERROR;
					}
				} else {
					Log.error("tel " + tel + "提出评价--数据解析失败");
					System.out.println("parse error!");
					return Message.ERROR;
				}
			} else {
				Log.error("tel " + tel + "提出评价--身份验证失败");
				return Message.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("tel " + tel + "提出评价--数据解析失败");
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
		Log.debug("tel " + tel + "提出投诉");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "提出投诉，内容为：" + content);
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
					Log.debug("tel " + tel + "提出投诉--成功");
					return Message.SUCCESS;
				}
				Log.error("tel " + tel + "提出投诉，数据解析错误");
				return Message.ERROR;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel " + tel + "提出投诉，数据解析错误");
				e.printStackTrace();
				return Message.ERROR;
			}

		}
		Log.error("tel " + tel + "提出投诉--身份验证错误");
		return Message.ERROR;
	}

	@POST
	@Path("/repair")
	@Produces("application/json")
	public String submitRepair(@FormParam("tel") String tel,
			@FormParam("content") String content) {
		Customer customer = dao.login(tel);
		System.out.println("tel:" + tel);
		Log.debug("tel " + tel + "保修");
		Log.debug("content " + content + "保修");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "保修，内容为：" + content);
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
					Log.debug("tel " + tel + "保修--成功");
					if (result) {
						return Message.SUCCESS;
					}else {
						return Message.ERROR;
					}
				} else {
					Log.debug("tel " + tel + "保修--数据解析失败");
					return Message.ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.debug("tel " + tel + "保修--数据解析失败");
				e.printStackTrace();
				return Message.ERROR;
			}
		}
		Log.debug("tel " + tel + "保修--身份验证失败");
		return Message.ERROR;
	}

	/**
	 * 提交购买配件信息
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
			Log.debug("tel " + tel + "购买备件，内容为：" + content);
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
					Log.debug("tel " + tel + "购买备件，成功");
					return Message.SUCCESS;
				} else {
					Log.debug("tel " + tel + "购买备件--数据解析失败");
					return Message.ERROR;
				}
			} else {
				Log.debug("tel " + tel + "购买备件--身份验证失败");
				return Message.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.debug("tel " + tel + "购买备件--数据解析失败");
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
		Log.debug("tel " + tel + "咨询");
		if (customer != null) {
			try {
				content = URLDecoder.decode(content, "UTF-8");
				Log.debug("tel " + tel + "咨询，内容为：" + content);
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
					Log.debug("tel " + tel + "咨询--成功");
					return Message.SUCCESS;
				} else {
					Log.debug("tel " + tel + "咨询--数据解析失败");
					return Message.ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.debug("tel " + tel + "咨询--数据解析失败");
				return Message.ERROR;
			}
		}
		Log.debug("tel " + tel + "咨询--身份验证失败");
		return Message.ERROR;
	}
}
