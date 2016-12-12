package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IPUserTelDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.PUserTel;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class PUserTelDaoImpl implements IPUserTelDao {
	private DBAccess dbAccess;

	public PUserTelDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getCustomerDataBase());
	}

	@Override
	public boolean insertPUserTel(PUserTel pUserTel) {
		// TODO Auto-generated method stub
		if (pUserTel == null) {
			return false;
		}
		PreparedStatement ps = null;
		Connection con = dbAccess.getConnection();
		Statement statement = dbAccess.getStatement(con);
		if (con != null) {
			String sql = "insert into "
					+ PUserTel.TABLE_NAME
					+ " (TelNum,Type,InsertTime) values (?, ?, getdate()) ";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, pUserTel.getTelNum());
				ps.setString(2, pUserTel.getType());
				int i = ps.executeUpdate();
				System.out.println("i=" + i);
				boolean res = false;
				if (i == 1) {
					res = true;
				}
				DBAccess.close(statement);
				DBAccess.close(con);
				return res;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
