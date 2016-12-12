package cn.edu.sjtu.dclab.slamke.unityprima.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.edu.sjtu.dclab.slamke.unityprima.ConnectTest;

public class Utils {
	private static final String FILE_NAME = "database.properties";
	private static final String DATABASE_IP = "database_ip";
	private static final String DATABASE_PORT = "database_port";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String PRODUCT_DATABASE = "product_database";
	private static final String CUSTOMER_DATABASE = "customer_database";
	private static final String EMPLOYEE_DATABASE = "employee_database";
	private static final String SMS_DATABASE = "sms_database";
	private static final String SEND_SN = "sendSN";
	private static final String SUB_ACCOUNT = "subAccount";
	
	
	private static final String DEFAULT_DATABASE_IP = "127.0.0.1";
	private static final String DEFAULT_DATABASE_PORT = "1433";
	private static final String DEFAULT_USERNAME = "SUPMID";
	private static final String DEFAULT_PASSWORD = "unityprima";
	private static final String DEFAULT_PRODUCT_DATABASE = "SUPMID";
	private static final String DEFAULT_CUSTOMER_DATABASE = "ServiceInformationManageSystem";
	private static final String DEFAULT_EMPLOYEE_DATABASE = "MessageManage";
	private static final String DEFAULT_SMS_DATABASE = "haduosmsserver";
	private static final String DEFAULT_SEND_SN = "50002";
	private static final String DEFAULT_SUB_ACCOUNT = "1111";
	
	
	

	
private Utils(){}

private static String getValueByProperty(String property){
	Properties p = new Properties();
	try {
		String path = Utils.class.getResource("/").getPath()+"/"+FILE_NAME;
		//System.out.print(path);
		InputStream in = new FileInputStream(new File(path));
            // FileInputStream(fileName),不过这种方式找不到配置文件。有人说是在classes下，我调过了，不行。
		p.load(in);
		in.close();
		if (p.containsKey(property)) {
			return p.getProperty(property);
		}else{
			return "";
		}
			
	} catch (IOException ex) {
		Logger.getLogger(ConnectTest.class.getName()).log(Level.SEVERE,
				null, ex);
		return "";
	}
}

public static String getDataBaseIP(){
	String result = getValueByProperty(DATABASE_IP);
	return result.equals("")?DEFAULT_DATABASE_IP:result;
}
public static String getDataBasePort(){
	String result = getValueByProperty(DATABASE_PORT);
	return result.equals("")?DEFAULT_DATABASE_PORT:result;
}

public static String getDataBaseUser(){
	String result = getValueByProperty(USERNAME);
	return result.equals("")?DEFAULT_USERNAME:result;
}
public static String getDataBasePassword(){
	String result = getValueByProperty(PASSWORD);
	return result.equals("")?DEFAULT_PASSWORD:result;
}
public static String getProductDataBase(){
	String result = getValueByProperty(PRODUCT_DATABASE);
	return result.equals("")?DEFAULT_PRODUCT_DATABASE:result;
}
public static String getCustomerDataBase(){
	String result = getValueByProperty(CUSTOMER_DATABASE);
	return result.equals("")?DEFAULT_CUSTOMER_DATABASE:result;
}

public static String getEmployeeDataBase(){
	String result = getValueByProperty(EMPLOYEE_DATABASE);
	return result.equals("")?DEFAULT_EMPLOYEE_DATABASE:result;
}

public static String getSMSDataBase(){
	String result = getValueByProperty(SMS_DATABASE);
	return result.equals("")?DEFAULT_SMS_DATABASE:result;
}
public static String getSendSN(){
	String result = getValueByProperty(SEND_SN);
	return result.equals("")?DEFAULT_SEND_SN:result;
}
public static String getSubAccount(){
	String result = getValueByProperty(SUB_ACCOUNT);
	return result.equals("")?DEFAULT_SUB_ACCOUNT:result;
}
}
