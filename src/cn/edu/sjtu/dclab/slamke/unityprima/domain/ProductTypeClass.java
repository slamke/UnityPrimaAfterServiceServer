package cn.edu.sjtu.dclab.slamke.unityprima.domain;

import java.util.List;

/**
 * 设备型号分类表 表名：tb_ProductTypeClass 所在数据库：SUPMID
 * @author sunke
 *
 */
public class ProductTypeClass {
	
	public static final String TABLE_NAME = "tb_ProductTypeClass";
	/**
	 * Num	编号	nvarchar(50)	主键
	 */
	private String num;
	/**
	 * Name	名称	nvarchar(100)	非空
	 */
	private String  name;
	/**
	 * Remark	类型	nvarchar(1000)	可空
	 */
	private String  remark;
	
	private List<ProductType> types;
	
	
	public List<ProductType> getTypes() {
		return types;
	}
	public void setTypes(List<ProductType> types) {
		this.types = types;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ProductTypeClass() {
		super();
	}
	
	
	
}
