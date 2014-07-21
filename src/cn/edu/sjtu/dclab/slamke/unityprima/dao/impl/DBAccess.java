package cn.edu.sjtu.dclab.slamke.unityprima.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {

	private static Connection con = null;
	private static Statement statement = null;
	private static ResultSet set = null;

	// ����SqlServer JDBC����
	private static String driverNameOfSqlServer = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// IP��ַ(��Ϊ�Լ���IP��ַ)
	private String DatabaseIP;
	
	private String DatabasePort;
	// ���ݿ��û���
	private String DatabaseUser;
	// ���ݿ�����
	private String DatabasePassword;
	// ���ݿ�����
	private String DatabaseName;
	// URL
	private String DatabaseUrl = "jdbc:sqlserver://" + DatabaseIP + ":"+DatabasePort+";DatabaseName = " + DatabaseName;

	
	
	public DBAccess(String databaseIP, String databasePort,String databaseUser,
			String databasePassword, String databaseName) {
		super();
		DatabasePort = databasePort;
		DatabaseIP = databaseIP;
		DatabaseUser = databaseUser;
		DatabasePassword = databasePassword;
		DatabaseName = databaseName;
		DatabaseUrl = "jdbc:sqlserver://" + DatabaseIP + ":1433;DatabaseName = " + DatabaseName;
	}
	//��ȡһ�����ݿ������
	public Connection getConnection() {
		try {
			//ע����������
			Class.forName(driverNameOfSqlServer);
			// ��ȡ����
			con = DriverManager.getConnection(DatabaseUrl, DatabaseUser,DatabasePassword);
		} catch (Exception e) {
			System.out.println("getConnection���ִ���");
			e.printStackTrace();
		}
		return con;
	}
	//�����Ự
	public Statement getStatement(Connection con){
		if(con != null){
			try {
				statement = con.createStatement();
				return statement;
			} catch (SQLException e) {
				System.out.println("getStatement���ִ���");
				e.printStackTrace();
			}
		}
		return null;
	}
	//��ѯ
	public ResultSet getResultSetQuery(Statement statement,String sql) {
		if(statement != null){
			try {
				set = statement.executeQuery(sql);
				return set;
			} catch (SQLException e) {
				System.out.println("getResultSetQuery���ִ���");
				e.printStackTrace();
			}
		}
		return null;
	}
	//���ӣ��޸ģ�ɾ����¼
	public void getResultSetUpdate(Statement statement,String sql) {
		if(statement != null){
			try {
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("getResultSetUpdate���ִ���");
				e.printStackTrace();
			}
		}
	}
	//�ر�����
	public static void close(Connection con){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//�رջỰ
	public static void close(Statement  statement){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//�رղ�ѯ��
	public static void close(ResultSet set){
		if(set != null){
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// �ͷ�����
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // �رս����
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close(); // �ر�Statement
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // �ر�����
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
