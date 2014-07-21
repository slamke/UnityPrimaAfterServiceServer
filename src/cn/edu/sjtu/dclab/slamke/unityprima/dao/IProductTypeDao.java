package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductType;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;

public interface IProductTypeDao {
	/**
	 * ��ȡĳclass��ȫ���豸�ͺţ�������ѯ��
	 * 
	 * @param typeClass
	 * @return
	 */
	public List<ProductType> getProductTypesByClass(ProductTypeClass typeClass);

}
