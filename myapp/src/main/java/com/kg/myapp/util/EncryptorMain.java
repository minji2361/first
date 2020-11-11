package com.kg.myapp.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptorMain {

	public static void main(String[] args) {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("pyleso");
		System.out.println("ENC("+enc.encrypt("net.sf.log4jdbc.DriverSpy")+")");
		System.out.println("ENC("+enc.encrypt("jdbc:log4jdbc:oracle:thin:@localhost:1521/XEPDB1")+")");
		System.out.println("ENC("+enc.encrypt("hr")+")");
		System.out.println("ENC("+enc.encrypt("hr")+")");
	}

}
