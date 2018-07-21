/**
 * 
 */
package com.techstack.spring._13.environmentabstraction.profile;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author KARTHIKEYAN N
 *
 */
public class EmbeddedDatabaseBuilder {

	private EmbeddedDatabaseType databaseType;
	
	private List<String> scripts = new LinkedList<String>();
	
	public EmbeddedDatabaseBuilder setType(EmbeddedDatabaseType databaseType) {
		this.databaseType = databaseType;
		return this;
	}

	public EmbeddedDatabaseBuilder addScript(String script) {
		scripts.add(script);
		return this;
	}

	public DataSource build() {
		return new DataSource() {

			@Override
			public PrintWriter getLogWriter() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getLoginTimeout() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Logger getParentLogger() throws SQLFeatureNotSupportedException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setLogWriter(PrintWriter out) throws SQLException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setLoginTimeout(int seconds) throws SQLException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public <T> T unwrap(Class<T> iface) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Connection getConnection() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Connection getConnection(String username, String password) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}
}
