package cn.edu.sjtu.dclab.slamke.unityprima.util;

public class StatusParser {
	private StatusParser() {
	}

	public static String getStatus(String input) {
		if (input.equals("δ����")) {
			return "���ڷ����ж�";
		} else if (input.equals("�����ж�")) {
			return "�����´�";
		} else if (input.equals("�´�")) {
			return "���´����ʦ����;��";
		} else if (input.equals("ִ����")) {
			return "ִ����";
		} else if (input.equals("���")) {
			return "���";
		}
		return "���ڷ����ж�";
	}
}
