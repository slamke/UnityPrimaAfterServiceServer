package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;

public interface IProductTypeClassDao {
	/**
	 * ��ȡ���е��豸���ࣨ������ѯ��
	 * 
	 * @param typeClass
	 * @return
	 */
	public List<ProductTypeClass> getAllProductTypeClasses();
}
