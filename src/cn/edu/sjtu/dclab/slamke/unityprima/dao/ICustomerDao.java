package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;

public interface ICustomerDao {

    /**
     * 用户通过电话号码登录
     * 
     * @param tel
     * @return
     */
    public Customer login(String tel);

}
