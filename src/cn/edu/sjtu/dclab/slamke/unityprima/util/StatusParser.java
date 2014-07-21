package cn.edu.sjtu.dclab.slamke.unityprima.util;

public class StatusParser {
	private StatusParser() {
	}

	public static String getStatus(String input) {
		if (input.equals("未处理")) {
			return "正在分析判断";
		} else if (input.equals("分析判断")) {
			return "正在下达";
		} else if (input.equals("下达")) {
			return "已下达，工程师正在途中";
		} else if (input.equals("执行中")) {
			return "执行中";
		} else if (input.equals("完成")) {
			return "完成";
		}
		return "正在分析判断";
	}
}
