package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICustomerDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class CustomerDaoImpl implements ICustomerDao {

	private DBAccess dbAccess;

	public CustomerDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),
				Utils.getDataBaseUser(), Utils.getDataBasePassword(),
				Utils.getCustomerDataBase());
	}

	/**
	 * ¸ü¸ÄÎªprepareStatement
	 */
	@Override
	public Customer login(String tel) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			// String sql = "select * from "+
			// Customer.TABLE_NAME+" where smsTel='"+tel+"' or smsTel='+86"+tel+"'"
			// + //SmsTel
			// " or ChairmanTel='"+tel+"' or ChairmanTel='+86"+tel+ "'"
			// +//ChairmanTel
			// " or LegalPersonTel='"+tel+"' or LegalPersonTel='+86"+tel+"'" +
			// // LegalPersonTel
			// " or OperationStaffTel='"+tel+"' or OperationStaffTel='+86"+tel+
			// "'" + //OperationStaffTel
			// " or MachineSupervisorTel='"+tel+"' or MachineSupervisorTel='+86"+tel+
			// "'" + //MachineSupervisorTel
			// " or FinanceSupervisorTel='"+tel+"' or FinanceSupervisorTel='+86"+tel+"'"
			// + //FinanceSupervisorTel
			// " or DefaultContactorTel='"+tel+"' or DefaultContactorTel='+86"+tel+"'"
			// + //DefaultContactorTel
			// " or GeneralManagerTel='"+tel+"' or GeneralManagerTel='+86"+tel+
			// "';"; //GeneralManagerTel
			String sql = "select * from " + Customer.TABLE_NAME
					+ " where smsTel=? or smsTel=?" + // SmsTel
					" or ChairmanTel=? or ChairmanTel=?" + // ChairmanTel
					" or LegalPersonTel=? or LegalPersonTel=?" + // LegalPersonTel
					" or OperationStaffTel=? or OperationStaffTel=?" + // OperationStaffTel
					" or MachineSupervisorTel=? or MachineSupervisorTel=?" + // MachineSupervisorTel
					" or FinanceSupervisorTel=? or FinanceSupervisorTel=?" + // FinanceSupervisorTel
					" or DefaultContactorTel=? or DefaultContactorTel=?" + // DefaultContactorTel
					" or GeneralManagerTel=? or GeneralManagerTel=? ;"; // GeneralManagerTel

			System.out.println(sql);
			Connection con = dbAccess.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			for (int i = 1; i < 17;) {
				statement.setString(i, tel);
				i++;
				statement.setString(i, "+86" + tel);
				i++;
			}
			ResultSet rs = statement.executeQuery();
			if (rs != null && rs.next()) {
				int id = rs.getInt("ID");
				String num = rs.getString("Num");
				String name = rs.getString("Name");
				String remark = rs.getString("Remark");
				String defaultContactor = rs.getString("DefaultContactor");
				customer = new Customer();
				customer.setId(id);
				customer.setNum(num);
				customer.setName(name);
				customer.setRemark(remark);
				customer.setSmsTel(tel);
				customer.setDefaultContactor(defaultContactor);
			}
			DBAccess.close(rs);
			DBAccess.close(statement);
			DBAccess.close(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

}
