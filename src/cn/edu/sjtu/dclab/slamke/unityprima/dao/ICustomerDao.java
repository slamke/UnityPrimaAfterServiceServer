package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;

public interface ICustomerDao {
	/**
	 * �û�ͨ���绰�����¼
	 * @param tel
	 * @return
	 */
	public Customer login(String tel);
	
}
