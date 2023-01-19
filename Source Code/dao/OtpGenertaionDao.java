package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpGenertaionDao {

	private Connection conn;

	public OtpGenertaionDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public String RetriveOtp(String otp) throws Exception {
		String sql = "select * from otpdata where otp=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, otp);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			rs.getString(1);
		}
		return otp;
	}
	public String addOtp(String otp) throws Exception {
		String sql = "insert into otpdata(otp) values(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, otp);
		ps.execute();
		return otp;
	}

	
	
}
