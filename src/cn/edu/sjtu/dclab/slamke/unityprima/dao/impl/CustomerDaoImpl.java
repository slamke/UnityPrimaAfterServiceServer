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
     * 更改为prepareStatement
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
				String Mail = rs.getString("Mail");
				String SmsTel= rs.getString("SmsTel");
				String FaxNum= rs.getString("FaxNum");
				String WebAddress= rs.getString("WebAddress");
				String DefaultContactor= rs.getString("DefaultContactor");
				String DefaultContactorTel= rs.getString("DefaultContactorTel");
				String Chairman= rs.getString("Chairman");
				String ChairmanTel= rs.getString("ChairmanTel");
				String LegalPerson= rs.getString("LegalPerson");
				String LegalPersonTel= rs.getString("LegalPersonTel");
				String GeneralManager= rs.getString("GeneralManager");
				String GeneralManagerTel= rs.getString("GeneralManagerTel");
				String FinanceSupervisor= rs.getString("FinanceSupervisor");
				String FinanceSupervisorTel= rs.getString("FinanceSupervisorTel");
				String MachineSupervisor= rs.getString("MachineSupervisor");
				String MachineSupervisorTel= rs.getString("MachineSupervisorTel");
				String OperationStaff= rs.getString("OperationStaff");
				String OperationStaffTel= rs.getString("OperationStaffTel");
				String remark = rs.getString("Remark");
				
				customer = new Customer();
				customer.setId(id);
				customer.setNum(num);
				customer.setName(name);
				customer.setMail(Mail);
				customer.setSmsTel(SmsTel);
				customer.setFaxNum(FaxNum);
				customer.setWebAddress(WebAddress);
				customer.setDefaultContactor(DefaultContactor);
				customer.setDefaultContactorTel(DefaultContactorTel);
				customer.setChairman(Chairman);
				customer.setChairmanTel(ChairmanTel);
				customer.setLegalPerson(LegalPerson);
				customer.setLegalPersonTel(LegalPersonTel);
				customer.setGeneralManager(GeneralManager);
				customer.setGeneralManagerTel(GeneralManagerTel);
				customer.setFinanceSupervisor(FinanceSupervisor);
				customer.setFinanceSupervisorTel(FinanceSupervisorTel);
				customer.setMachineSupervisor(MachineSupervisor);
				customer.setMachineSupervisorTel(MachineSupervisorTel);
				customer.setOperationStaff(OperationStaff);
				customer.setOperationStaffTel(OperationStaffTel);
				customer.setRemark(remark);
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
