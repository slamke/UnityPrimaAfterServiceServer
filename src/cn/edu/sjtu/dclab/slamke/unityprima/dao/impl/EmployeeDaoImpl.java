package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IEmployeeDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Employee;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class EmployeeDaoImpl implements IEmployeeDao {
	private DBAccess dbAccess;

	public EmployeeDaoImpl() {
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
	public Employee getEmployeeByNum(String num) {
		// TODO Auto-generated method stub		
		Employee employee = null;
		 
        try {  
        	//String sql = "select * from "+ Employee.TABLE_NAME+" where Num='"+num+"';";
        	String sql = "select * from "+ Employee.TABLE_NAME+" where Num=?";
    		Connection con = dbAccess.getConnection();  
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, num);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
            	employee = new Employee();
            	String name = rs.getString("Name");
            	employee.setNum(num);
            	employee.setName(name);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return employee;
	}

}
