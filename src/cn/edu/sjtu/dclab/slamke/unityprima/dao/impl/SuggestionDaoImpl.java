package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ISuggestionDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Suggestion;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class SuggestionDaoImpl implements ISuggestionDao {
	
	private DBAccess dbAccess;

	public SuggestionDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getCustomerDataBase());
	}


	@Override
	public boolean insertSuggestion(Suggestion suggestion) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = dbAccess.getConnection();
		Statement statement = dbAccess.getStatement(con);
		if (con != null) {
//			String sql = "insert into "
//					+ Suggestion.TABLE_NAME
//					+ " (Num,Content,InsertTime,Contacts,ContactsTel,SMS) values (dbo.GetSuggestionNum(), ?, getdate(), ?, ?, ?) ";
			String sql = "insert into "
					+ Suggestion.TABLE_NAME
					+ " (Num,Content,InsertTime,Contacts,ContactsTel,SMS) values (?, ?, getdate(), ?, ?, ?) ";
			try {
				ps = con.prepareStatement(sql);
				if (suggestion == null) {
					return false;
				}
				ps.setString(1, ""+System.currentTimeMillis());
				ps.setString(2, suggestion.getContent());
				ps.setString(3, suggestion.getContacts());
				ps.setString(4, suggestion.getContactsTel());
				ps.setString(5, suggestion.getSMS());
//				ps.setString(1, suggestion.getContent());
//				ps.setString(2, suggestion.getContacts());
//				ps.setString(3, suggestion.getContactsTel());
//				ps.setString(4, suggestion.getSMS());
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
