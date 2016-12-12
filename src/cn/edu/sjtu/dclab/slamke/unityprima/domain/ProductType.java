package cn.edu.sjtu.dclab.slamke.unityprima.domain;

/**
 * 1、 设备型号表 表名：tb_PoductType 所在数据库：SUPMID
 * 
 * @author sunke
 * 
 */
public class ProductType {
    public static final String TABLE_NAME = "tb_PoductType";
    /**
     * ID ID int 主键，ID：自增1
     */
    private int id;
    /**
     * Num 编号 nvarchar(50) 非空
     */
    private String num;

    /**
     * Name 名称 nvarchar(100) 可空
     */
    private String name;
    /**
     * Model 型号 nvarchar(100) 非空
     */
    private String model;
    /**
     * Class 分类编号 nvarchar(1000) 非空（对应设备型号分类表中的编号）
     */
    private ProductTypeClass TypeClass;
    /**
     * Specification 规格 nvarchar(1000) 可空
     */
    private String specification;

    /**
     * Remark 备注 nvarchar(1000) 可空
     */
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public ProductTypeClass getTypeClass() {
        return TypeClass;
    }

    public void setTypeClass(ProductTypeClass typeClass) {
        TypeClass = typeClass;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ProductType() {
        super();
    }

    @Override
    public String toString() {
        return "ProductType [id=" + id + ", num=" + num + ", name=" + name
                + ", model=" + model + ", TypeClass=" + TypeClass
                + ", specification=" + specification + ", remark=" + remark
                + "]";
    }
}
