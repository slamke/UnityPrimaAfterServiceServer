package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IAuthCodeDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.AuthCode;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class AuthCodeDaoImpl implements IAuthCodeDao {
	
	private DBAccess dbAccess;

	public AuthCodeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getCustomerDataBase());
	}
	@Override
	public boolean insertAuthCode(AuthCode authCode) {
		// TODO Auto-generated method stub
		if (authCode == null) {
			return false;
		}
		PreparedStatement ps = null;
		Connection con = dbAccess.getConnection();
		String sql = "insert into "
				+ AuthCode.TABLE_NAME
				+ " (Tel,AuthCode) values (?, ?) ";
		if (con != null) {			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, authCode.getTel());
				ps.setString(2, authCode.getCode());
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

	@Override
	public String getNewestAuthCodeByTel(String tel) {
		// TODO Auto-generated method stub
		if (tel == null) {
			return null;
		}
		String code = "";
		try {
			String sql = "select AuthCode from " + AuthCode.TABLE_NAME
					+ " where Tel=? order by Id desc;";
			System.out.println(sql);
			Connection con = dbAccess.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, tel);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				code = rs.getString("AuthCode");
			}
			DBAccess.close(rs);
			DBAccess.close(statement);
			DBAccess.close(con);
			return code;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
