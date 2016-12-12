package cn.edu.sjtu.dclab.slamke.unityprima.pojo;

import java.io.Serializable;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductType;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;

public class ConsultInfo implements Serializable 
{
	private static final long serialVersionUID = 21L;
	
	private ProductTypeClass typeClass;
	private ProductType type;
	private String remark;
	public ProductTypeClass getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(ProductTypeClass typeClass) {
		this.typeClass = typeClass;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
        return "设备型号分类：" + typeClass.getName()
                + "\n设备型号：" + type.getName()
                + "\n留言：" + remark;
	}

}
