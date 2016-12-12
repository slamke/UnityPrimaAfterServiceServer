package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IDeviceDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Device;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class DeviceDaoImpl implements IDeviceDao {

	
	private DBAccess dbAccess;
	public DeviceDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),Utils.getDataBaseUser(), Utils.getDataBasePassword(), Utils.getCustomerDataBase());
	}


	
    /**
     * 更改为prepareStatement
     */
	@Override
	public List<Device> getDevicesByCustomer(Customer customer) {		
		List<Device> devices = new ArrayList<Device>();
        try {
        	//String sql = "select * from "+ Device.TABLE_NAME+" where Customer='"+customer.getNum()+"';";
        	String sql = "select * from "+ Device.TABLE_NAME+" where Customer=?";
            System.out.println("读取device列表：" + sql);
    		Connection con = dbAccess.getConnection();  
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, customer.getNum());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
            	Device device = new Device();
            	String num = rs.getString("Num");
            	String type = rs.getString("Type");
            	String remark = rs.getString("Remark");
            	device.setNum(num);
            	device.setType(type);
            	device.setRemark(remark);
                // device.setCustomer(customer); //防止gson解析错误
            	devices.add(device);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return devices;
	}

}
