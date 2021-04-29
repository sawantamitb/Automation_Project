package com.anchalapp;

import org.apache.log4j.Logger;

import common.DriverConfig;
import common.PropertyReading;
import common.ReadWriteExcel;
import logger.MyLogger;

public class MetaDataHeader
{	
	static DriverConfig objDriverConfig;
	static String username;
	static String mobilenumber;
	static String role;
	static String DH;
	static String talukahospital;
	static String talukaname;
	static String PHCsintaluka;
	static String nameofTHMO;
	static String mobilenoofTHMO;
	static String nameofsubcentre;
	static String villagename;
	static String ashaattendance;
	static String anmattendance;
	
	// property file object
	static PropertyReading objConfigPropertyReading = new PropertyReading();
	
	// sheet name
	static String exceldatasheet= objConfigPropertyReading.configPropertiesReading().getProperty("exceldatasheet");
	
	//log file
	static String logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
	static String logfilename= objConfigPropertyReading.configPropertiesReading().getProperty("logfilename");
	static MyLogger log = new MyLogger();
	static Logger debugger = log.configureLogger(logfilepath, logfilename);	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the mobilenumber
	 */
	public String getMobilenumber() {
		return mobilenumber;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @return the dH
	 */
	public String getDH() {
		return DH;
	}
	/**
	 * @return the talukahospital
	 */
	public String getTalukahospital() {
		return talukahospital;
	}
	/**
	 * @return the talukaname
	 */
	public String getTalukaname() {
		return talukaname;
	}
	/**
	 * @return the pHCsintaluka
	 */
	public String getPHCsintaluka() {
		return PHCsintaluka;
	}
	/**
	 * @return the nameofTHMO
	 */
	public String getNameofTHMO() {
		return nameofTHMO;
	}
	/**
	 * @return the mobilenoofTHMO
	 */
	public String getMobilenoofTHMO() {
		return mobilenoofTHMO;
	}
	/**
	 * @return the nameofsubcentre
	 */
	public String getNameofsubcentre() {
		return nameofsubcentre;
	}
	/**
	 * @return the villagename
	 */
	public String getVillagename() {
		return villagename;
	}
	/**
	 * @return the ashaattendance
	 */
	public String getAshaattendance() {
		return ashaattendance;
	}
	/**
	 * @return the anmattendance
	 */
	public String getAnmattendance() {
		return anmattendance;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param mobilenumber the mobilenumber to set
	 */
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @param dH the dH to set
	 */
	public void setDH(String dH) {
		this.DH = dH;
	}
	/**
	 * @param talukahospital the talukahospital to set
	 */
	public void setTalukahospital(String talukahospital) {
		this.talukahospital = talukahospital;
	}
	/**
	 * @param talukaname the talukaname to set
	 */
	public void setTalukaname(String talukaname) {
		this.talukaname = talukaname;
	}
	/**
	 * @param pHCsintaluka the pHCsintaluka to set
	 */
	public void setPHCsintaluka(String pHCsintaluka) {
		PHCsintaluka = pHCsintaluka;
	}
	/**
	 * @param nameofTHMO the nameofTHMO to set
	 */
	public void setNameofTHMO(String nameofTHMO) {
		this.nameofTHMO = nameofTHMO;
	}
	/**
	 * @param mobilenoofTHMO the mobilenoofTHMO to set
	 */
	public void setMobilenoofTHMO(String mobilenoofTHMO) {
		this.mobilenoofTHMO = mobilenoofTHMO;
	}
	/**
	 * @param nameofsubcentre the nameofsubcentre to set
	 */
	public void setNameofsubcentre(String nameofsubcentre) {
		this.nameofsubcentre = nameofsubcentre;
	}
	/**
	 * @param villagename the villagename to set
	 */
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	/**
	 * @param ashaattendance the ashaattendance to set
	 */
	public void setAshaattendance(String ashaattendance) {
		this.ashaattendance = ashaattendance;
	}
	/**
	 * @param anmattendance the anmattendance to set
	 */
	public void setAnmattendance(String anmattendance) {
		this.anmattendance = anmattendance;
	}
	
	public static void main(String a[])
	{
		MetaDataHeader metadata = new MetaDataHeader();
		ReadWriteExcel r1 = new ReadWriteExcel(objDriverConfig.resourcesDirectory());
		//metadata.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[5]));
		System.out.println(metadata.getUsername());		
	}

}
