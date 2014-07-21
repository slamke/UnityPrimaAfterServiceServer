package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.Employee;

public interface IEmployeeDao {
	public Employee getEmployeeByNum(String num);
}
