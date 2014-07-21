package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.AuthCode;

public interface IAuthCodeDao {
	
	public boolean insertAuthCode(AuthCode authCode);
	
	public String getNewestAuthCodeByTel(String tel);
}
