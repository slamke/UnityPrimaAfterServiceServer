package cn.edu.sjtu.dclab.slamke.unityprima.pojo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Device;

public class RepairInfo implements Serializable {
	private static final long serialVersionUID = 20L;
	private Customer customer;
	private Device device;
	private Date time;
	private String phoneo;
	private boolean isChange;
	private String changeContent;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPhoneo() {
		return phoneo;
	}

	public void setPhoneo(String phoneo) {
		this.phoneo = phoneo;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public String getChangeContent() {
		return changeContent;
	}

	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}

	@Override
	public String toString() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return "���Ϸ���ʱ��Ϊ" + year + "-" + month + "-" + day 
				+"�����ϲ�Ʒ���к�Ϊ"+ device.getNum()
				+"����������Ϊ"
				+ phoneo + "����������ǰ�Ƿ�Ķ�������������ز��֣�" + (isChange ? "��"+ "���Ķ�Ϊ" + (changeContent == null?"":changeContent) : "��")
				;
	}
}
