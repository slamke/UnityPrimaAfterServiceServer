package cn.edu.sjtu.dclab.slamke.unityprima.dao;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.WSend;

public interface IWSendDao {
	/**
	 * 插入新的短息内容
	 * 
	 * @param send
	 * @return
	 */
	public int insertWSend(WSend send);
}
