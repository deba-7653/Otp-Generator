package com.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.dao.OtpGenertaionDao;
import com.db.DBConn;
import com.email.SendEmail;
import com.otpgenerator.OtpGenerator;

public class Register {
	static String name;
	static String email;
	static String otp;
	static String password;
	static String adress;

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		conn = DBConn.getConn();
	 String otp1=null;
		Scanner sc = new Scanner(System.in);
		OtpGenertaionDao dao = new OtpGenertaionDao(DBConn.getConn());
		System.out.println("Enter A Valid Email:");
		email = sc.nextLine();
		System.out.println("Press 1 For Verify Email");
		int value = Integer.parseInt(sc.nextLine());
		if (value == 1) {
			char[] generateOTP = OtpGenerator.generateOTP(6);
			otp1 = String.copyValueOf(generateOTP);
			dao.addOtp(otp1);
			System.out.println("Preparing to sending msg.........");

			SendEmail.sendEmail("Your Otp is: " + otp1, "Otp", email, "debadatta2468.info@gmail.com");
		} else {
			System.out.println("Pls Choose Valid Option");
			return;
		}
		System.out.println("Enter The Otp: ");
		otp = sc.nextLine();
	
		if (otp.equals(dao.RetriveOtp(otp1))) {
			System.out.println("Otp validation Sucessfully.....");
			System.out.println();
			System.out.println("Enter Your Name : ");
			name = sc.nextLine();
			System.out.println("Enter Your Password : ");
			password = sc.nextLine();
			System.out.println("Enter Your Adress :");
			adress = sc.nextLine();
			String sql = "insert into customerdata(name,email,password,adress) values(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, adress);
			int i = ps.executeUpdate();
			 if (i == 1) {
				 System.out.println("Register Succesfully");
				}
			 else {
				 System.out.println("Something Wrong");
			}
			
		
		} else {
			System.out.println("Invalid Otp");
		}

	}
}
