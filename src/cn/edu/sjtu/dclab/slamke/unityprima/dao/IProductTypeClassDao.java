package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;

public interface IProductTypeClassDao {
	/**
	 * 获取所有的设备分类（用于咨询）
	 * 
	 * @param typeClass
	 * @return
	 */
	public List<ProductTypeClass> getAllProductTypeClasses();
}
