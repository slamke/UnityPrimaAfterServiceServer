package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Device;

public interface IDeviceDao {
	/**
	 * ��ȡ�û��������豸�嵥
	 * @param customer
	 * @return
	 */
	public List<Device> getDevicesByCustomer(Customer customer);
}
