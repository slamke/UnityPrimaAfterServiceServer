package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ITaskPersonDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.TaskPerson;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class TaskPersonDaoImpl implements ITaskPersonDao {
	private DBAccess dbAccess;

	public TaskPersonDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getCustomerDataBase());
	}
	/**
	 * ¸ü¸ÄÎªPrepareStatement
	 */
	@Override
	public TaskPerson getTaskPersonByTaskNum(String taskNum) {
		// TODO Auto-generated method stub
		TaskPerson taskPerson = null;
        try {
        	//String sql = "select * from "+ TaskPerson.TABLE_NAME+" where TaskNum='"+taskNum+"';";
    		String sql = "select * from "+ TaskPerson.TABLE_NAME+" where TaskNum= ?";
    		Connection con = dbAccess.getConnection();  
    		PreparedStatement ps = con.prepareStatement(sql);
    		ps.setString(1, taskNum);
    		ResultSet rs = ps.executeQuery();  
            if(rs.next()){
            	taskPerson = new TaskPerson();
            	String empNum = rs.getString("EmpNum");
            	taskPerson.setTaskNum(taskNum);
            	taskPerson.setEmpNum(empNum);
            }  
            //rs.close();
            DBAccess.close(rs);
            DBAccess.close(ps);
            DBAccess.close(con);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return taskPerson;
	}
}
