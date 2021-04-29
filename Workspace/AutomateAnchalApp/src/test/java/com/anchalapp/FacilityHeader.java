package com.anchalapp;

import org.apache.log4j.Logger;

import common.DriverConfig;
import common.PropertyReading;
import common.ReadWriteExcel;
import logger.MyLogger;

public class FacilityHeader 
{
	static DriverConfig objDriverConfig;
	static String dhname;
	static String chcname;
	static String phcname;
	static String scname;
	static String username;
	static String level;
	static String street;
	static String state;
	static String district;
	static String block;
	static String village;
	static String pincode;
	static String latitude;
	static String longitude;
	
	// property file object
	static PropertyReading objConfigPropertyReading = new PropertyReading();
		
	// sheet name
	static String exceldatasheet= objConfigPropertyReading.configPropertiesReading().getProperty("exceldatasheet");
		
	//log file
	static String logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
	static String logfilename= objConfigPropertyReading.configPropertiesReading().getProperty("logfilename");
	static MyLogger log = new MyLogger();
	static Logger debugger = log.configureLogger(logfilepath, logfilename);	
	static String headerusername = objConfigPropertyReading.configPropertiesReading().getProperty("facility_username");
	
	
	/**
	 * @return the dhname
	 */
	public String getDhname() {
		return dhname;
	}

	/**
	 * @param dhname the dhname to set
	 */
	public  void setDhname(String dhname) {
		this.dhname = dhname;
	}

	/**
	 * @return the chcname
	 */
	public String getChcname() {
		return chcname;
	}

	/**
	 * @param chcname the chcname to set
	 */
	public  void setChcname(String chcname) {
		this.chcname = chcname;
	}

	/**
	 * @return the phcname
	 */
	public String getPhcname() {
		return phcname;
	}


	/**
	 * @param phcname the phcname to set
	 */
	public void setPhcname(String phcname) {
		this.phcname = phcname;
	}


	/**
	 * @return the scname
	 */
	public  String getScname() {
		return scname;
	}

	/**
	 * @param scname the scname to set
	 */
	public  void setScname(String scname) {
		this.scname = scname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public  void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the level
	 */
	public  String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public  void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the street
	 */
	public  String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public  void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public  void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the district
	 */
	public  String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public  void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the block
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		this.block = block;
	}

	/**
	 * @return the village
	 */
	public String getVillage() {
		return village;
	}

	/**
	 * @param village the village to set
	 */
	public void setVillage(String village) {
		this.village = village;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	public static void main(String a[])
	{
		FacilityHeader facility = new FacilityHeader();
		ReadWriteExcel r1 = new ReadWriteExcel(objDriverConfig.resourcesDirectory());
		facility.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[2]));
		System.out.println(facility.getUsername());		
	}

}
