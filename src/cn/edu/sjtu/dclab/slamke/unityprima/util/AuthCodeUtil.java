package cn.edu.sjtu.dclab.slamke.unityprima.util;

import java.util.Random;

public class AuthCodeUtil {
	private AuthCodeUtil() {
	}

	public static String getAuthCode() {
		String str = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String str2[] = str.split(",");// ���ַ�����,�ָ�
		Random rand = new Random();// ����Random��Ķ���rand
		int index = 0;
		StringBuffer randStr = new StringBuffer();// ��������Ϊ���ַ�������randStr
		for (int i = 0; i < 6; ++i) {
			index = rand.nextInt(str2.length - 1);// ��0��str2.length-1����һ��α�������ֵ��index
			randStr.append(str2[index]);// ����Ӧ������������randStr�ı���ֵ������
		}
		return randStr.toString();
	}
}
