package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICTelStartDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.CTelStart;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class CTelStartDaoImpl implements ICTelStartDao {

	private DBAccess dbAccess;

	public CTelStartDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getEmployeeDataBase());
	}
	/**
	 * ¸ü¸ÄÎªprepareStatement
	 */
	@Override
	public CTelStart getCTelStartByNum(String num) {
		// TODO Auto-generated method stub
		if (num!=null&&num.startsWith("+86")) {
			num = num.substring(3);
		}
		String prefix = num.substring(0, 3);
		CTelStart telStart = null;
		
        try {
        	//String sql = "select * from "+ CTelStart.TABLE_NAME+" where TelStart='"+prefix+"';";
        	String sql = "select * from "+ CTelStart.TABLE_NAME+" where TelStart=?";
    		Connection con = dbAccess.getConnection();  
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, prefix);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
            	telStart = new CTelStart();
            	String AlisaNo = rs.getString("AlisaNo");
            	String Supplier = rs.getString("Supplier");
            	telStart.setAlisaNo(AlisaNo);
            	telStart.setSupplier(Supplier);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return telStart;
	}

}
