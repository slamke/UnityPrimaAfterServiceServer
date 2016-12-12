package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.IProductTypeClassDao;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Utils;

public class ProductTypeClassDaoImpl implements IProductTypeClassDao {

	
	private DBAccess dbAccess;
	public ProductTypeClassDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		dbAccess = new DBAccess(Utils.getDataBaseIP(), Utils.getDataBasePort(),Utils.getDataBaseUser(), Utils.getDataBasePassword(), Utils.getProductDataBase());
	}
	
    /**
     * 更改为prepareStatement
     */
	@Override
	public List<ProductTypeClass> getAllProductTypeClasses() {
		// TODO Auto-generated method stub
		List<ProductTypeClass> myClasses = new ArrayList<ProductTypeClass>();
        try { 
        	String sql = "select * from "+ ProductTypeClass.TABLE_NAME+" ;";
    		Connection con = dbAccess.getConnection();  
            Statement statement = dbAccess.getStatement(con); 
            ResultSet rs = dbAccess.getResultSetQuery(statement, sql);  
            while(rs.next()){
            	ProductTypeClass class1 = new ProductTypeClass();
            	String num = rs.getString("Num");
            	String name = rs.getString("Name");
            	String remark = rs.getString("Remark");
            	class1.setNum(num);
            	class1.setName(name);
            	class1.setRemark(remark);
            	myClasses.add(class1);
            }  
            
            DBAccess.close(rs);
            DBAccess.close(statement);
            DBAccess.close(con);
            
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return myClasses;
	}

}
