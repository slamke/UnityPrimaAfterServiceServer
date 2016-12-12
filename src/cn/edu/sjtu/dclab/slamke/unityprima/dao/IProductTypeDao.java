package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductType;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;

public interface IProductTypeDao {
    /**
     * 获取某class的全部设备型号（用于咨询）
     * 
     * @param typeClass
     * @return
     */
    public List<ProductType> getProductTypesByClass(ProductTypeClass typeClass);

}
