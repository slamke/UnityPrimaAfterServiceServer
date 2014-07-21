package cn.edu.sjtu.dclab.slamke.unityprima.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import cn.edu.sjtu.dclab.slamke.unityprima.domain.CTelStart;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.CommentInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Customer;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.ProductTypeClass;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.SellInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.domain.Task;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.AdviceInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.ComplaintInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.ConsultInfo;
import cn.edu.sjtu.dclab.slamke.unityprima.pojo.RepairInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ClassParse {

	private Gson gson;

	public ClassParse() {
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	}

	public String customer2String(Customer customer) {
		try {
			if (customer != null) {
				String result = gson.toJson(customer);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String telStart2String(CTelStart telStart) {
		try {
			if (telStart != null) {
				String result = gson.toJson(telStart);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String map2String(Map<String, String> map) {
		try {
			if (map != null) {
				String result = gson.toJson(map);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String typeClasses2String(List<ProductTypeClass> typeClasses) {
		try {
			if (typeClasses != null) {
				String result = gson.toJson(typeClasses);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String taskList2String(List<Task> tasks) {
		try {
			if (tasks != null) {
				String result = gson.toJson(tasks);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String taskPersonPair2String(Map<String, String> tasks) {
		try {
			if (tasks != null) {
				String result = gson.toJson(tasks);
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public AdviceInfo string2AdviceInfo(String content) {
		try {
			Type type = new TypeToken<AdviceInfo>() {
			}.getType();
			if (content != null) {
				AdviceInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}	
	
	public CommentInfo string2CommentInfo(String content) {
		try {
			Type type = new TypeToken<CommentInfo>() {
			}.getType();
			if (content != null) {
				CommentInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}	
	
	public ComplaintInfo string2ComplaintInfo(String content) {
		try {
			Type type = new TypeToken<ComplaintInfo>() {
			}.getType();
			if (content != null) {
				ComplaintInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	
	public RepairInfo string2RepairInfo(String content) {
		try {
			Type type = new TypeToken<RepairInfo>() {
			}.getType();
			if (content != null) {
				RepairInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public SellInfo string2SellInfo(String content) {
		try {
			Type type = new TypeToken<SellInfo>() {
			}.getType();
			if (content != null) {
				SellInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public ConsultInfo string2ConsultInfo(String content) {
		try {
			Type type = new TypeToken<ConsultInfo>() {
			}.getType();
			if (content != null) {
				ConsultInfo record = gson.fromJson(content, type);
				return record;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}
//	public List<CaijiaRecord> string2CaijiaRecordList(String content) {
//		try {
//			Type type = new TypeToken<List<CaijiaRecord>>() {
//			}.getType();
//			if (content != null) {
//
//				List<CaijiaRecord> records = gson.fromJson(content, type);
//				return records;
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			return null;
//		}
//
//	}
}
