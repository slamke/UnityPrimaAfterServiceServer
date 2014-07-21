package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import java.util.List;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Device;

public interface IDeviceDao {
	/**
	 * 获取用户的所有设备清单
	 * @param customer
	 * @return
	 */
	public List<Device> getDevicesByCustomer(Customer customer);
}
