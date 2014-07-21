package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ITaskDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.CommentInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Task;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Const;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class TaskDaoImpl implements ITaskDao {
	
	private DBAccess dbAccess;

	public TaskDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getCustomerDataBase());
	}

	@Override
	public boolean insertTask(Task task,String num) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = dbAccess.getConnection();
		if (con != null) {
//			String sql = "insert into "
//					+ Task.TABLE_NAME
//					+ " (Num,CustomerNum,Source,DefaultContactor,DefaultContactorTel,DeviceNum,Remark,Status,InsertTime) values (dbo.GetNewTaskNum(?), ?,?,?,?,?,?, ?, getdate()) ";
			String sql = "insert into "
			+ Task.TABLE_NAME
			+ " (Num,CustomerNum,Source,DefaultContactor,DefaultContactorTel,DeviceNum,Remark,Status,InsertTime) values (?, ?,?,?,?,?,?, ?, getdate()) ";
			try {
				ps = con.prepareStatement(sql);
				System.out.println(sql);
				if (task == null) {
					return false;
				}
				//ps.setString(1, num);
				ps.setString(1, ""+System.currentTimeMillis());
				ps.setString(2, task.getCustomerNum());
				ps.setString(3, task.getSource());
				ps.setString(4, task.getDefaultContactor());
				ps.setString(5, task.getDefaultContactorTel());
				ps.setString(6, task.getDeviceNum());
				ps.setString(7, task.getContent()+"###");
				ps.setString(8, task.getStatus());
				int i = ps.executeUpdate();
				System.out.println("i=" + i);
				boolean result =false;
				if (i == 1) {
					result = true;
				}
				DBAccess.close(ps);
				DBAccess.close(con);
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}

		return false;
	}
	/**
	 * 更改为prepareStatement
	 */
	@Override
	public List<Task> getUnEvaluatedTasks(String CustomerNum) {
		// TODO Auto-generated method stub
		List<Task> tasks = new ArrayList<Task>();
        try {
        	//检索所有未评估的task  
        	//String sql = "select * from "+ Task.TABLE_NAME+" where CustomerNum='"+CustomerNum+"' and datediff(day,'1905/6/12',ConfirmCompleteTime)=0 and [Close]=0 and datediff(day,'2000/6/12',CompleteTime)>0;";
        	String sql = "select * from "+ Task.TABLE_NAME+" where CustomerNum=? and datediff(day,'1905/6/12',ConfirmCompleteTime)=0 and [Close]=0 and datediff(day,'2000/6/12',CompleteTime)>0;";
    		System.out.println(sql);
    		Connection con = dbAccess.getConnection();  
    		PreparedStatement statement = con.prepareStatement(sql);
    		statement.setString(1, CustomerNum);
            ResultSet rs = statement.executeQuery();  
            while(rs.next()){
            	Task task = new Task();
            	String num = rs.getString("Num");
            	String name = rs.getString("Name");
            	String device = rs.getString("DeviceNum");
            	task.setNum(num);
            	task.setName(name);
            	task.setDeviceNum(device);
            	tasks.add(task);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return tasks;
	}
	/**
	 * 更改为prepareStatement
	 */
	@Override
	public Task getTaskByNum(String num) {
		// TODO Auto-generated method stub
		Task task = null;
        try {
        	//String sql = "select * from "+ Task.TABLE_NAME+" where Num='"+num+"';";
        	String sql = "select * from "+ Task.TABLE_NAME+" where Num= ?";
    		Connection con = dbAccess.getConnection();  
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, num);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
            	task = new Task();
            	String name = rs.getString("Name");
            	String device = rs.getString("DeviceNum");
            	String content = rs.getString("Content");
            	String status = rs.getString("Status");
            	String remark = rs.getString("Remark");
            	Date time = rs.getDate("InsertTime");
            	task.setInsertTime(time);
            	task.setNum(num);
            	task.setName(name);
            	task.setDeviceNum(device);
            	if (remark == null) {
            		task.setRemark("");
				}else {
					String sp = remark.substring(0, remark.indexOf("###"));
					task.setRemark(sp);	
				}
            	task.setStatus(status);
            	task.setContent(content);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return task;
	}
	/**
	 * 更改为prepareStatement
	 */
	@Override
	public List<Task> getUnClosedTasks(String CustomerNum) {
		// TODO Auto-generated method stub
		List<Task> tasks = new ArrayList<Task>();
        try { 
        	//String sql = "select * from "+ Task.TABLE_NAME+" where CustomerNum='"+CustomerNum+"' and [Close]=0;";
        	String sql = "select * from "+ Task.TABLE_NAME+" where CustomerNum=? and [Close]=0;";
        	Connection con = dbAccess.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, CustomerNum);
            ResultSet rs =statement.executeQuery();  
            while(rs.next()){
            	Task task = new Task();
            	String num = rs.getString("Num");
            	String name = rs.getString("Name");
            	String device = rs.getString("DeviceNum");
            	task.setNum(num);
            	task.setName(name);
            	task.setDeviceNum(device);
            	tasks.add(task);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return tasks;
	}
	/**
	 * 更改为prepareStatement
	 */
	@Override
	public List<Task> getUnClosedAndProgressTasks(String CustomerNum) {
		// TODO Auto-generated method stub
		List<Task> tasks = new ArrayList<Task>();
		
        try {
        	//String sql = "select * from "+ Task.TABLE_NAME+" where CustomerNum='"+CustomerNum+"' and [Close]=0 and [Status]!='完成';";
        	String sql = "select * from "+ Task.TABLE_NAME+" where CustomerNum=? and [Close]=0 and [Status]!='完成';";
    		Connection con = dbAccess.getConnection();  
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, CustomerNum);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
            	Task task = new Task();
            	String num = rs.getString("Num");
            	String name = rs.getString("Name");
            	String device = rs.getString("DeviceNum");
            	task.setNum(num);
            	task.setName(name);
            	task.setDeviceNum(device);
            	tasks.add(task);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return tasks;
	}
	/**
	 * 更改为prepareStatement
	 */
	@Override
	public boolean inertTaskEvaluation(CommentInfo info) {
		// TODO Auto-generated method stub
		//Task store = getTaskByNum(info.getNum());
		String storeRemark = "";
		PreparedStatement ps = null;
		Connection con = dbAccess.getConnection();  
        //String sql = "update "+ Task.TABLE_NAME+" set Evaluate=?,Remark=?,ConfirmCompleteTime=getdate() where Num='"+info.getNum()+ "';";
		String sql = "update "+ Task.TABLE_NAME+" set Evaluate=?,Remark=?,ConfirmCompleteTime=getdate() where Num=?;";
		if (con != null) {			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, info.getContent());
				//String storeSql = "select Remark from "+ Task.TABLE_NAME+" where Num='"+info.getNum()+"';";
				String storeSql = "select Remark from "+ Task.TABLE_NAME+" where Num= ?";
				PreparedStatement statement0 = con.prepareStatement(storeSql);
				statement0.setString(1, info.getNum());
				ResultSet rs0 = statement0.executeQuery();
		        if (rs0.next()) {
		        	storeRemark = rs0.getString("Remark");
				}
		        DBAccess.close(rs0);
		        DBAccess.close(statement0);
		        
		        System.out.println("Remark:"+storeRemark);
				if (info.getContent().equals(Const.TASK_EVALUATE_UNSATISFY)) {
					/*//使用默认时区和语言环境获得一个日历  
					Calendar cale = Calendar.getInstance();  
					//将Calendar类型转换成Date类型  
					Date tasktime=cale.getTime();  
					//设置日期输出的格式  
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					//格式化输出  
					String time = df.format(tasktime); */
					String remarkSQL = "select isnull('"+storeRemark+"'+char(13),'') as remark";
			        Statement statement1 = dbAccess.getStatement(con); 
			        ResultSet rs1 = dbAccess.getResultSetQuery(statement1, remarkSQL);
			        String remark = "";
			        if (rs1.next()) {
						remark = rs1.getString("remark");
					}
			        System.out.println("remark:"+remark);
		            DBAccess.close(rs1);
		            DBAccess.close(statement1);
		            
			        String timeSQL = "select convert(varchar(30), getdate(), 121) as time";
			        Statement statement2 = dbAccess.getStatement(con); 
			        ResultSet rs2 = dbAccess.getResultSetQuery(statement2, timeSQL);
			        String time = "";
			        if (rs2.next()) {
			        	time = rs2.getString("time");
					}
			        System.out.println("time:"+time);
		            DBAccess.close(rs2);
		            DBAccess.close(statement2);
		            
			        String charSQL = "select char(9) as cc";
			        Statement statement3 = dbAccess.getStatement(con); 
			        ResultSet rs3 = dbAccess.getResultSetQuery(statement3, charSQL);
			        String cc = "";
			        if (rs3.next()) {
			        	cc = rs3.getString("cc");
					}
			        System.out.println("cc:"+cc);
		            DBAccess.close(rs3);
		            DBAccess.close(statement3);
					
					ps.setString(2, remark+info.getContent()+":"+info.getRemark()+cc+time);
				}else {
					ps.setString(2, storeRemark);
				}
				ps.setString(3, info.getNum());
				int i = ps.executeUpdate();
				DBAccess.close(ps);
	            DBAccess.close(con);
				System.out.println("i=" + i);
				if (i == 1) {
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
