package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IProductTypeDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductType;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class ProductTypeDaoImpl implements IProductTypeDao {
	private DBAccess dbAccess;
	public ProductTypeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),Utils.getDataBaseUser(), Utils.getDataBasePassword(), Utils.getProductDataBase());
	}


	/**
	 * ¸ü¸ÄÎªprepareStatement
	 */
	@Override
	public List<ProductType> getProductTypesByClass(ProductTypeClass typeClass) {
		// TODO Auto-generated method stub
		List<ProductType> types = new ArrayList<ProductType>();
		 
        try { 
        	//String sql = "select * from "+ ProductType.TABLE_NAME+" where Class='"+typeClass.getNum()+"';";
        	String sql = "select * from "+ ProductType.TABLE_NAME+" where Class=?";
    		Connection con = dbAccess.getConnection();  
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, typeClass.getNum());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
            	ProductType type = new ProductType();
            	int id = rs.getInt("ID");
            	String num = rs.getString("Num");
            	String name = rs.getString("Name");
            	String remark = rs.getString("Remark");
            	String model = rs.getString("Model");
            	String spec = rs.getString("Specification");
            	type.setId(id);
            	type.setNum(num);
            	type.setName(name);
            	type.setModel(model);
            	type.setSpecification(spec);
            	type.setRemark(remark);
            	
            	types.add(type);
            }  
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return types;
	}

}
