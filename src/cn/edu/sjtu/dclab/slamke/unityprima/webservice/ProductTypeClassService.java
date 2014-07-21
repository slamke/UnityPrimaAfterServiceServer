package cn.edu.sjtu.dclab.slamke.unityprima.webservice;

import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import cn.edu.sjtu.dclab.slamke.unityprima.dao.ICustomerDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.IProductTypeClassDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.IProductTypeDao;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.CustomerDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.ProductTypeClassDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.dao.impl.ProductTypeDaoImpl;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductType;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;
import cn.edu.sjtu.dclab.slamke.unityprima.util.ClassParse;
import cn.edu.sjtu.dclab.slamke.unityprima.util.Message;
@Path("/product")
public class ProductTypeClassService {
	private static Logger Log = Logger.getLogger(ProductTypeClassService.class);
	private ICustomerDao dao;
	private IProductTypeDao typeDao;
	private IProductTypeClassDao typeClassDao;
	public ProductTypeClassService() {
		dao = new CustomerDaoImpl();
		typeDao = new ProductTypeDaoImpl();
		typeClassDao = new ProductTypeClassDaoImpl();
	}

	@POST
	@Path("/list")
	@Produces("application/json")
	public String getProductList(@FormParam("tel") String tel) {
		System.out.println("get product type list");
		Log.debug("tel:"+tel+" ��ȡ��Ʒ�����б�");
		Customer customer = dao.login(tel);
		if (customer == null) {
			Log.error("tel:"+tel+" �����֤ʧ��");
			return Message.ERROR;
		}
		List<ProductTypeClass> typeClasses = typeClassDao.getAllProductTypeClasses();
		if (typeClasses != null) {
			for (int i = 0; i < typeClasses.size(); i++) {
				List<ProductType> types = typeDao.getProductTypesByClass(typeClasses.get(i));
				typeClasses.get(i).setTypes(types);
			}
			ClassParse parse = new ClassParse();
			try {
				String result = URLEncoder.encode(parse.typeClasses2String(typeClasses), "UTF-8");
				Log.debug("tel:"+tel+" ��ȡ��Ʒ�����б�ɹ�");
				Log.debug("tel:"+tel+" ��ȡ��Ʒ�����б�ɹ�--result:"+parse.typeClasses2String(typeClasses));
				return result;
			} catch (Exception e) {
				// TODO: handle exception
				Log.error("tel:"+tel+" ��ȡ��Ʒ�����б�--���ݽ���ʧ��");
				e.printStackTrace();
			}
		}
		Log.debug("tel:"+tel+" ��ȡ��Ʒ�����б�--�޲�Ʒ����");
		return Message.SUCCESS;
	}
}
