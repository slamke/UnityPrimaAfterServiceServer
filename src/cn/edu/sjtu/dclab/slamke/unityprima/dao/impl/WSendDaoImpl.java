package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IWSendDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.WSend;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;
/**
 * ÖØ¹¹´úÂë-07-05
 * @author sunke
 *
 */
public class WSendDaoImpl implements IWSendDao {

	private DBAccess dbAccess;

	public WSendDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getSMSDataBase());
	}

	@Override
	public int insertWSend(WSend send) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		Connection con = dbAccess.getConnection();
		//Statement statement = dbAccess.getStatement(con);
		ResultSet rSet;
		int result = -1;
		if (con != null) {
			String sql = "insert into "
					+ WSend.TABLE_NAME
					+ " (mbno,SMS,Wtime,SendSN,SubAccount) values (?, ?, ?, ?, ?) ";
			try {
				ps = con.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS);
				if (send == null) {
					return -1;
				}
				ps.setString(1, send.getMbno());
				ps.setString(2, send.getSMS());
				if (send.getwTime() != null) {
					ps.setDate(3, new java.sql.Date(send.getwTime().getTime()));
				} else {
					ps.setDate(3, new java.sql.Date(new Date().getTime()));
				}
				ps.setString(4, Utils.getSendSN());
				ps.setString(5, Utils.getSubAccount());
				int i = ps.executeUpdate();
				System.out.println("i=" + i);
				rSet = ps.getGeneratedKeys();
				if (i == 1) {
					if (rSet.next()) {
						result = rSet.getInt(1);
					}
				}
				DBAccess.close(rSet);
				//DBAccess.close(statement);
				DBAccess.close(ps);
				DBAccess.close(con);
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
}
