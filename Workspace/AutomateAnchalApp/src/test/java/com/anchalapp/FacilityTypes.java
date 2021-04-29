package com.anchalapp;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import common.DriverConfig;
import common.PropertyReading;
import common.ReadWriteExcel;
import logger.MyLogger;

public class FacilityTypes 
{
	static WebDriver driver;
	static AnchalAppFunctions objAnchalAppFunctions;
	static DriverConfig objDriverConfig;
	static FacilityTypes objRoleVariations;
		
	static ReadWriteExcel r1 = new ReadWriteExcel(DriverConfig.resourcesDirectory());		
	static FacilityHeader facilityheader = new FacilityHeader();
	static PropertyReading objConfigPropertyReading = new PropertyReading();
	
	static String navigateurl= objConfigPropertyReading.configPropertiesReading().getProperty("navigateurltofacility");
	static String facilitytypevalue="";
	
	//log file
	static String logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
	static String logfilename= objConfigPropertyReading.configPropertiesReading().getProperty("logfilename");
	static MyLogger log = new MyLogger();
	static Logger debugger = log.configureLogger(logfilepath, logfilename);
	
	// column name	
	static String headerdhname = objConfigPropertyReading.configPropertiesReading().getProperty("facility_dhname");
	static String headerchcname= objConfigPropertyReading.configPropertiesReading().getProperty("facility_chcname");
	static String headerphcname=objConfigPropertyReading.configPropertiesReading().getProperty("facility_phcname");
	static String headerscname=objConfigPropertyReading.configPropertiesReading().getProperty("facility_scname");
	static String headerusername=objConfigPropertyReading.configPropertiesReading().getProperty("facility_username");
	static String headerlevel=objConfigPropertyReading.configPropertiesReading().getProperty("facility_level");
	static String headerstreet=objConfigPropertyReading.configPropertiesReading().getProperty("facility_street");
	static String headerstate=objConfigPropertyReading.configPropertiesReading().getProperty("facility_state");
	static String headerdistrict=objConfigPropertyReading.configPropertiesReading().getProperty("facility_district");
	static String headerblock=objConfigPropertyReading.configPropertiesReading().getProperty("facility_block");
	static String headervillage=objConfigPropertyReading.configPropertiesReading().getProperty("facility_village");
	static String headerpincode=objConfigPropertyReading.configPropertiesReading().getProperty("facility_pincode");
	static String headerlatitude=objConfigPropertyReading.configPropertiesReading().getProperty("facility_latitude");
	static String headerlongitude=objConfigPropertyReading.configPropertiesReading().getProperty("facility_longitude");
	
	// types of facility role
	static String facility_adddh= objConfigPropertyReading.configPropertiesReading().getProperty("facility_adddh");
	static String facility_addchc= objConfigPropertyReading.configPropertiesReading().getProperty("facility_addchc");
	static String facility_addphc= objConfigPropertyReading.configPropertiesReading().getProperty("facility_addphc");
	static String facility_addsc= objConfigPropertyReading.configPropertiesReading().getProperty("facility_addsc");
	
	// types of facility role values
	static String facility_adddh_value = objConfigPropertyReading.configPropertiesReading().getProperty("facility_adddh_value");
	static String facility_addchc_value = objConfigPropertyReading.configPropertiesReading().getProperty("facility_addchc_value");
	static String facility_addphc_value = objConfigPropertyReading.configPropertiesReading().getProperty("facility_addphc_value");
	static String facility_addsc_value = objConfigPropertyReading.configPropertiesReading().getProperty("facility_addsc_value");
	
		
	public void executeAddFacility(String exceldatasheet)
	{		
		try
		{	 			
			  log.writeToInfoLog(debugger, " The excel sheet ("+exceldatasheet+") has been selected successfully to add the record.");
			 
			  for(int i=0; i<r1.retrieveNoOfRows(exceldatasheet)-1;i++)
			  {
					  AnchalAppFunctions.navigateToURL(navigateurl);
					  log.writeToInfoLog(debugger, "Anchal app has been navigate to admin role page.");
					  
					  if(exceldatasheet.equalsIgnoreCase(facility_adddh))
					  {
						  facilityheader.setDhname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerdhname))[i]));
					  }
					  else if(exceldatasheet.equalsIgnoreCase(facility_addchc))
					  {
						  facilityheader.setDhname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerdhname))[i]));
						  facilityheader.setChcname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerchcname))[i]));						  
					  }
					  else if(exceldatasheet.equalsIgnoreCase(facility_addphc))
					  {
						  facilityheader.setDhname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerdhname))[i]));
						  facilityheader.setChcname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerchcname))[i]));
						  facilityheader.setPhcname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerphcname))[i]));
					  }
					  else if(exceldatasheet.equalsIgnoreCase(facility_addsc))
					  {
						  facilityheader.setDhname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerdhname))[i]));
						  facilityheader.setChcname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerchcname))[i]));
						  facilityheader.setPhcname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerphcname))[i]));
						  facilityheader.setScname(((r1.retrieveToRunFlagTestData(exceldatasheet, headerscname))[i]));						  
					  }
					
					  facilityheader.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[i]));
					  facilityheader.setLevel(((r1.retrieveToRunFlagTestData(exceldatasheet, headerlevel))[i]));
					  facilityheader.setStreet(((r1.retrieveToRunFlagTestData(exceldatasheet, headerstreet))[i]));
					  facilityheader.setState(((r1.retrieveToRunFlagTestData(exceldatasheet, headerstate))[i]));
					  facilityheader.setDistrict(((r1.retrieveToRunFlagTestData(exceldatasheet, headerdistrict))[i]));
					  facilityheader.setBlock(((r1.retrieveToRunFlagTestData(exceldatasheet, headerblock))[i]));
					  facilityheader.setVillage(((r1.retrieveToRunFlagTestData(exceldatasheet, headervillage))[i]));
					  facilityheader.setPincode(((r1.retrieveToRunFlagTestData(exceldatasheet, headerpincode))[i]));
					  facilityheader.setLatitude(((r1.retrieveToRunFlagTestData(exceldatasheet, headerlatitude))[i]));
					  facilityheader.setLongitude(((r1.retrieveToRunFlagTestData(exceldatasheet, headerlongitude))[i]));
					
					
					  if(!facilityheader.getDhname().isEmpty() && AnchalAppFunctions.selectFacilityDH(facilityheader.getDhname()))
					  {
						  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: DH name ("+facilityheader.getDhname()+") has been selected successfully.");
						  
							  if(!facilityheader.getDhname().equalsIgnoreCase(facility_adddh_value))
							  {
								  if(!facilityheader.getChcname().isEmpty() && AnchalAppFunctions.selectFacilityCHC(facilityheader.getChcname()))
								  {
									  if(!facilityheader.getChcname().equalsIgnoreCase(facility_addchc_value))
									  {
										  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: CHC name ("+facilityheader.getChcname()+") has been selected successfully.");
										  
										  if(!facilityheader.getPhcname().isEmpty() && AnchalAppFunctions.selectFacilityPHC(facilityheader.getPhcname()))
										  {
											  if(!facilityheader.getPhcname().equalsIgnoreCase(facility_addphc_value))
											  {
												  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: PHC name ("+facilityheader.getPhcname()+") has been selected successfully.");
												  
												  if(!facilityheader.getScname().isEmpty() && AnchalAppFunctions.selectFacilitySC(facilityheader.getScname()))
												  {
													  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: SC name ("+facilityheader.getScname()+") has been selected successfully.");
													// code to fill the details for SC 
													  facilitytypevalue= "New SC Record ";
													  addData(i, facilitytypevalue,facilityheader.getDhname(), facilityheader.getChcname(), facilityheader.getPhcname(), facilityheader.getScname(), facilityheader.getUsername(), facilityheader.getLevel(), facilityheader.getStreet(), facilityheader.getState(), facilityheader.getDistrict(), facilityheader.getBlock(), facilityheader.getVillage(), facilityheader.getPincode(), facilityheader.getLatitude(), facilityheader.getLongitude());
												  }
												  else
												  {
													  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: SC is mandatory & it is require to proceed further.Ensure that it should have valid name.",facilityheader.getScname());
												  }
											  }
											  else
											  {
												// code to fill the details for PHP 
												  facilitytypevalue= "New PHC Record ";
												  addData(i,facilitytypevalue, facilityheader.getDhname(), facilityheader.getChcname(), facilityheader.getPhcname(),facilityheader.getScname(), facilityheader.getUsername(), facilityheader.getLevel(), facilityheader.getStreet(), facilityheader.getState(), facilityheader.getDistrict(), facilityheader.getBlock(), facilityheader.getVillage(), facilityheader.getPincode(), facilityheader.getLatitude(), facilityheader.getLongitude());
											  }												 
										  }
										  else
										  {
											  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: PHC is mandatory & it is require to proceed further.Ensure that it should have valid name.",facilityheader.getPhcname());
										  }	
									  }
									  else
									  {
										// code to fill the details for CHC 
										  facilitytypevalue= "New CHC Record ";
										  addData(i, facilitytypevalue,facilityheader.getDhname(),facilityheader.getChcname(), facilityheader.getPhcname(),facilityheader.getScname(), facilityheader.getUsername(), facilityheader.getLevel(), facilityheader.getStreet(), facilityheader.getState(), facilityheader.getDistrict(), facilityheader.getBlock(), facilityheader.getVillage(), facilityheader.getPincode(), facilityheader.getLatitude(), facilityheader.getLongitude());
									  }										 
								  }
								  else
								  {
									  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: CHC is mandatory & it is require to proceed further.Ensure that it should have valid name.",facilityheader.getChcname());
								  }
							  }
							  else
							  {
								 // code to fill the details for DH  
								  facilitytypevalue= "New DH Record ";
								  addData(i, facilitytypevalue,facilityheader.getDhname(), facilityheader.getChcname(), facilityheader.getPhcname(), facilityheader.getScname(), facilityheader.getUsername(), facilityheader.getLevel(), facilityheader.getStreet(), facilityheader.getState(), facilityheader.getDistrict(), facilityheader.getBlock(), facilityheader.getVillage(), facilityheader.getPincode(), facilityheader.getLatitude(), facilityheader.getLongitude());
							  }
					  }
					  else
					  {
						  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+facilityheader.getUsername()+" :: DH is mandatory & it is require to proceed further.Ensure that it should have valid name.",facilityheader.getDhname());
					  }	
				  				
			}									 					 							
		}
		catch(Exception e)
		{
			log.writeToFatalLog(debugger, "Exception during executing scenario", e);			
		}		
	}
	
	public void addData(int i, String facilitytypevalue, String dhname,String chcname, String phcname,String scname, String username, String level, String street, String state, String district, String block, String village, String pincode, String latitude, String longitude) throws InterruptedException
	{
		 if(AnchalAppFunctions.setFacilityName(username))
		  {
			  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Place name ("+username+") has been entered successfully.");
			  
			  if(AnchalAppFunctions.selectFacilityLevel(level))
			  {
				  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Level ("+level+") has been selected successfully.");
				  
				  if(AnchalAppFunctions.setFacilityStreet(street))
				  {
					  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Street name ("+street+") has been entered successfully.");
					  
					  if(dhname.equalsIgnoreCase(facility_adddh_value))
					  {
						  if(AnchalAppFunctions.selectFacilityState(state))
						  {
							  if(state.isEmpty())
							  {
								  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: State name is mandatory but it is null currently therefore skipping to add record.Ensure that it should have valid name.", state);
							  }
							  else
							  {
								  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: state name ("+state+") has been selected successfully.");
								  
								  if(AnchalAppFunctions.selectFacilityDistrict(district))
								  {
									  if(district.isEmpty())
									  {
										  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: District name is mandatory but it is null currently therefore skipping to add record.Ensure that it should have valid name.", district);
									  }
									  else
									  {
										  addBlockVillageData(i, facilitytypevalue,username, dhname, chcname, phcname, scname, block, village, pincode, latitude, longitude);
									  }
								  }
								  else
								  {
									  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: District name is mandatory & it is require to proceed further.",district);
								  }										  
							  }							 												  
						  }
						  else
						  {
							  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: State name is mandatory & it is require to proceed further.",state);
						  }							
					  }
					  else
					  {
						  addBlockVillageData(i,facilitytypevalue,username, dhname, chcname, phcname, scname, block, village, pincode, latitude, longitude);						  
					  }																 											
				  }
				  else
				  {
					  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Street name is mandatory & it is require to proceed further.Ensure that it should not be null.",street);
				  }
			  }
			  else
			  {
				  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Level is mandatory & it is require to proceed further.Ensure that it should have valid name.",level);
			  }											  
		  }
		  else
		  {
			  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: User name is mandatory & it is require to proceed further.Ensure that it should not be null.",username);
		  }											
	}
	
	public void addBlockVillageData(int i,String facilitytypevalue,String username, String dhname, String chcname, String phcname,String scname, String block, String village, String pincode, String latitude, String longitude) throws InterruptedException
	{
		if(dhname.equalsIgnoreCase(facility_adddh_value))
		{
			addCommonData(i,facilitytypevalue, username,pincode,latitude,longitude);
		}
		else if(chcname.equalsIgnoreCase(facility_addchc_value))
		{
			  if(AnchalAppFunctions.selectFacilityBlock(block))
			  {
				 if(!block.isEmpty())
				  {
					  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Block ("+block+") has been selected successfully.");
					  
					  if(AnchalAppFunctions.selectFacilityVillage(village))
					  {
						  if(village.isEmpty())
						  {
							  log.writeToWarnLog(debugger, "Record: "+(i+1)+" - "+username+" :: Village name is optional but it is null currently therefore skipping to add village name for this record.Ensure that it should have valid name.");
							  addCommonData(i,facilitytypevalue,username,pincode,latitude,longitude);
						  }
						  else
						  {
							  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Village ("+village+") has been selected successfully.");
							  addCommonData(i,facilitytypevalue,username,pincode,latitude,longitude);
						  }						  
					  }
					  else
					  {
						  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Village name is optional but it is require while adding record for this role.Ensure that it should have valid name.",village);
					  }
				  }
				  else
				  {
					  log.writeToWarnLog(debugger, "Record: "+(i+1)+" - "+username+" :: Block name is optional but it is null currently therefore skipping to add village name for this record.Ensure that it should have valid name.");
					  addCommonData(i,facilitytypevalue,username,pincode,latitude,longitude);
				  }				
			  }
			  else
			  {
				  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Block name is optional but it is require while adding record for this role.Ensure that it should have valid name.",block);
			  }			
		}
		else if(phcname.equalsIgnoreCase(facility_addphc_value) || scname.equalsIgnoreCase(facility_addsc_value))
		{
			 if(AnchalAppFunctions.selectFacilityVillage(village))
			  {
				  if(village.isEmpty())
				  {
					  log.writeToWarnLog(debugger, "Record: "+(i+1)+" - "+username+" :: Village name is optional but it is null currently therefore skipping to add village name for this record.Ensure that it should have valid name.");
					  addCommonData(i,facilitytypevalue,username,pincode,latitude,longitude);
				  }
				  else
				  {
					  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Village ("+village+") has been selected successfully.");
					  addCommonData(i,facilitytypevalue,username,pincode,latitude,longitude);
				  }				  
			  }
			  else
			  {
				  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Village name is optional but it is require while adding record for this role.Ensure that it should have valid name.",village);
			  }	
		}
	}
	
	public void addCommonData(int i, String facilitytypevalue, String username, String pincode, String latitude, String longitude) throws InterruptedException
	{
		  if(AnchalAppFunctions.setFacilityPinCode(pincode))
		  {
			  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Pincode ("+pincode+") has been entered successfully.");
			  
			  if(AnchalAppFunctions.setFacilityLatitude(latitude))
			  {
				  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Latitude ("+latitude+") has been entered successfully.");
				  
				  if(AnchalAppFunctions.setFacilityLongitude(longitude))
				  {
					  log.writeToInfoLog(debugger, "Record: "+(i+1)+" - "+username+" :: Longitude ("+longitude+") has been entered successfully.");
					  AnchalAppFunctions.clickAdd();
					  log.writeToLog(debugger, "Record: "+(i+1)+" - "+username+" :: "+facilitytypevalue+" for place "+username +" has been added successfully");
				  }
				  else
				  {
					  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Longitude is mandatory & it is require to proceed further.Ensure that it should have valid range from -180 to 180.",longitude);
				  }
			  }
			  else
			  {
				  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Latitude is mandatory & it is require to proceed further.Ensure that it should have valid range from -90 to 90.",latitude);
			  }
		  }
		  else
		  {
			  log.writeToErrorLog(debugger, "Record: "+(i+1)+" - "+username+" :: Pincode is mandatory & it is require to proceed further.Ensure that it should not be null.",pincode);
		  }
	}
}

