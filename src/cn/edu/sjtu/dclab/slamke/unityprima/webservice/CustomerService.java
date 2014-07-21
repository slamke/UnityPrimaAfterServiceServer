package cn.edu.sjtu.dclab.slamke.unityprima.webservice;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IAuthCodeDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICTelStartDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICustomerDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.IDeviceDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.IWSendDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.AuthCodeDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.CTelStartDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.CustomerDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.DeviceDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.WSendDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.AuthCode;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.CTelStart;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Device;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.WSend;
import cn.edu.sjtu.dclab.slamke.unityprima.util.AuthCodeUtil;
import cn.edu.sjtu.dclab.slamke.unityprima.util.ClassParse;
import cn.edu.sjtu.dclab.slamke.unityprima.util.MD5;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Message;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

@Path("/customer")
public class CustomerService {
	
	private static Logger Log = Logger.getLogger(CustomerService.class);
	private final static String CUSTOMER = "customer";
	private final static String TEL_START = "tel_start";
	private final static String STATUS = "STATUS";
	private final static String ERROR_CODE = "ERROR_CODE";
	private ICustomerDao dao;
	private IDeviceDao deviceDao;
	private ICTelStartDao ctelDao;
	private IWSendDao wsendDao;
	
	private IAuthCodeDao authCodeDao;

	public CustomerService() {
		dao = new CustomerDaoImpl();
		deviceDao = new DeviceDaoImpl();
		ctelDao = new CTelStartDaoImpl();
		wsendDao = new WSendDaoImpl();
		authCodeDao = new AuthCodeDaoImpl();
	}

	@POST
	@Path("/login")
	@Produces("application/json")
	public String login(@FormParam("tel") String tel) {
		Customer customer = dao.login(tel);
		Log.debug("tel:"+tel+"登录");
		System.out.println("tel:"+tel);
		if (customer != null) {
			Map<String, String> map = new HashMap<String, String>();
			List<Device> devices = deviceDao.getDevicesByCustomer(customer);
			customer.setDevices(devices);
			System.out.println("读取device列表："+devices==null?"device null":"device size:"+devices.size());
			ClassParse parse = new ClassParse();
			try {
				String customerStr = parse.customer2String(customer);
				map.put(CUSTOMER, customerStr);
				CTelStart telStart = ctelDao.getCTelStartByNum(tel);
				String telString =  parse.telStart2String(telStart);
				map.put(TEL_START, telString);
				String result = URLEncoder.encode(parse.map2String(map), "UTF-8");
				Log.debug("tel:"+tel+"登录成功！");
				Log.debug("tel:"+tel+"登录成功--result:"+parse.map2String(map));
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+"登录--数据解析失败");
				e.printStackTrace();
			}
		}
		Log.error("tel:"+tel+"登录失败！");
		return Message.ERROR;
	}
	
	
	
	@GET
	@Path("/ios/authcode")
	@Produces("application/json")
	public String getAuthCodeFromIOS(@QueryParam("tel") String tel) {
		Customer customer = dao.login(tel);
		Log.debug("tel:"+tel+"登录");
		System.out.println("tel:"+tel);
		if (customer != null) {
			try {
				//生成一个验证码
				String authCode = AuthCodeUtil.getAuthCode();
				//发动短信
				WSend send = new WSend(tel, "欢迎使用服务快手手机客户端，您本次登录的验证码为："+authCode+"。请勿让他人查看该验证码，谢谢。", 
						new Timestamp(new Date().getTime()),Utils.getSendSN() , Utils.getSubAccount());
				wsendDao.insertWSend(send);
				//记录验证码
				AuthCode authCode2 = new AuthCode();
				authCode2.setCode(authCode);
				authCode2.setTel(tel);
				authCodeDao.insertAuthCode(authCode2);
				return Message.SUCCESS;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+"登录--数据解析失败");
				e.printStackTrace();
			}
		}
		Log.error("tel:"+tel+"登录失败！");
		return Message.ERROR;
	}
	
	@POST
	@Path("/ios/login")
	@Produces("application/json")
	public String loginFromIOS(@FormParam("tel") String tel,@FormParam("authCode") String authCode) {
		Customer customer = dao.login(tel);
		Log.debug("tel:"+tel+"登录 from ios");
		System.out.println("tel:"+tel);
		Map<String, String> map = new HashMap<String, String>();
		if (customer != null) {
			String code = authCodeDao.getNewestAuthCodeByTel(tel);
			if (authCode != null && new MD5().getMD5Str(tel+code).substring(0, 8).equals(authCode)) {
				map.put(STATUS, Message.SUCCESS);
				List<Device> devices = deviceDao.getDevicesByCustomer(customer);
				customer.setDevices(devices);
				System.out.println("读取device列表："+devices==null?"device null":"device size:"+devices.size());
				ClassParse parse = new ClassParse();
				try {
					String customerStr = parse.customer2String(customer);
					map.put(CUSTOMER, customerStr);
					CTelStart telStart = ctelDao.getCTelStartByNum(tel);
					String telString =  parse.telStart2String(telStart);
					map.put(TEL_START, telString);
					String result = URLEncoder.encode(parse.map2String(map), "UTF-8");
					Log.debug("tel:"+tel+"登录成功！");
					Log.debug("tel:"+tel+"登录成功--result:"+parse.map2String(map));
					return result;
				} catch (Exception e) {
					// TODO: handle exception
					Log.error("tel:"+tel+"登录--数据解析失败");
					e.printStackTrace();
				}
			}else {
				map.put(STATUS, Message.ERROR);
				//authcode  error
				map.put(ERROR_CODE, "01");
			}
		}else {
			map.put(STATUS, Message.ERROR);
			//tel  error
			map.put(ERROR_CODE, "02");
		}
		ClassParse parse = new ClassParse();
		Log.error("tel:"+tel+"登录失败！");
		String result = parse.map2String(map);
		return result;
	}
	
	@GET
	@Path("/test")
	@Produces("application/json")
	public String test() {
		try {
			String old = "中文的";
			String strUTF8 = URLEncoder.encode(old, "UTF-8");
			return strUTF8;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "测试";		
	}
}
