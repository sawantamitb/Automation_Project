package com.anchalapp;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import common.DriverConfig;
import common.PropertyReading;
import common.ReadWriteExcel;
import logger.MyLogger;

public class RoleVariations 
{
	static WebDriver driver;
	static AnchalAppFunctions objAnchalAppFunctions;
	static DriverConfig objDriverConfig;
	static RoleVariations objRoleVariations;
		
	static ReadWriteExcel r1 = new ReadWriteExcel(DriverConfig.resourcesDirectory());		
	static MetaDataHeader metadata = new MetaDataHeader();
	static PropertyReading objConfigPropertyReading = new PropertyReading();
	
	static String navigateurl= objConfigPropertyReading.configPropertiesReading().getProperty("navigateurltoroles");
	
	//log file
	static String logfilepath= objConfigPropertyReading.configPropertiesReading().getProperty("logfilepath");
	static String logfilename= objConfigPropertyReading.configPropertiesReading().getProperty("logfilename");
	static MyLogger log = new MyLogger();
	static Logger debugger = log.configureLogger(logfilepath, logfilename);
	
	// column name	
	static String headerusername = objConfigPropertyReading.configPropertiesReading().getProperty("username");
	static String headermobilenumber= objConfigPropertyReading.configPropertiesReading().getProperty("mobilenumber");
	static String headerrole=objConfigPropertyReading.configPropertiesReading().getProperty("role");
	static String headerDH=objConfigPropertyReading.configPropertiesReading().getProperty("DH");
	static String headertalukahospital=objConfigPropertyReading.configPropertiesReading().getProperty("talukahospital");
	static String headertalukaname=objConfigPropertyReading.configPropertiesReading().getProperty("talukaname");
	static String headerPHCsintaluka=objConfigPropertyReading.configPropertiesReading().getProperty("PHCsintaluka");
	static String headernameofTHMO=objConfigPropertyReading.configPropertiesReading().getProperty("nameofTHMO");
	static String headermobilenoofTHMO=objConfigPropertyReading.configPropertiesReading().getProperty("mobilenoofTHMO");
	static String headernameofsubcentre=objConfigPropertyReading.configPropertiesReading().getProperty("nameofsubcentre");
	static String headervillagename=objConfigPropertyReading.configPropertiesReading().getProperty("villagename");
	static String headerashaattendance=objConfigPropertyReading.configPropertiesReading().getProperty("ashaattendance");
	static String headeranmattendance=objConfigPropertyReading.configPropertiesReading().getProperty("anmattendance");
	
	
	public void executeTHMO(String exceldatasheet)
	{		
		try
		{
			  for(int i=0; i<r1.retrieveNoOfRows(exceldatasheet)-1;i++)
			  {
					  AnchalAppFunctions.navigateToURL(navigateurl);
					  log.writeToInfoLog(debugger, "Anchal app has been navigate to admin role page.");
					  metadata.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[i]));
					  metadata.setMobilenumber(((r1.retrieveToRunFlagTestData(exceldatasheet, headermobilenumber))[i]));
					  metadata.setRole(((r1.retrieveToRunFlagTestData(exceldatasheet, headerrole))[i]));	 
					  metadata.setDH(((r1.retrieveToRunFlagTestData(exceldatasheet, headerDH))[i]));	
					  metadata.setTalukahospital(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukahospital))[i]));	
					  metadata.setTalukaname(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukaname))[i]));						  		
					
					  // mobile number regex
					  Boolean mobilenumberregexboolean = metadata.getMobilenumber().matches("^[0][1-9]\\d{9}$|^[1-9]\\d{9}$");
					  
					/*  if(i==0)
					  {
						  if(AnchalAppFunctions.selectDHForFirstTime(metadata.getDH(), metadata.getMobilenumber()))
						  {							  
							  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
							  {
								  	 AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
								  	 
								  	 if(mobilenumberregexboolean)
									 {
								  		if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
										  {
											  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
											  {
												  AnchalAppFunctions.save();											 
												  log.writeToLog(debugger, "Data has been saved successfully.");
											  }
											  else
											  {
												  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
											  }										  
										  }
										  else
										  {
											  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
										  }												 
									 }
									 else
									 {					
										 log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
										 AnchalAppFunctions.refreshPage();					  									 								 
									 }							  
							  }
							  else
							  {
								  if(metadata.getUsername().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
								  }
								  else if(metadata.getMobilenumber().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
								 
								  }else if(metadata.getRole().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
								  }
							   }				
						  }
						  else
						  {
							  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
						  }						 	 							  						
					  }
					  else
					  {*/
						  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
						  {
							  AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
							  
							  if(mobilenumberregexboolean)
							  {
								  if(AnchalAppFunctions.selectDH(metadata.getDH(), metadata.getMobilenumber()))
								  {
									  if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
									  {
										  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
										  {
											  AnchalAppFunctions.save();											 
											  log.writeToLog(debugger, "Data has been saved successfully.");
										  }
										  else
										  {
											  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
										  }										  
									  }
									  else
									  {
										  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
									  }		
								  }
								  else
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
								  }									 									  
							  }
							  else
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
								  AnchalAppFunctions.refreshPage();								  						  							  
							  }			
						  }
						  else
						  {
							  if(metadata.getUsername().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
							  }
							  else if(metadata.getMobilenumber().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
							 
							  }else if(metadata.getRole().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
							  }					  						  
						  }					 			  	 
					  }					  				 					 							
			   //} 
		}
		catch(Exception e)
		{
			log.writeToFatalLog(debugger, "Exception during testing scenario", e);			
		}		
	}
	
	
	public void executeASHA(String exceldatasheet)
	{		
		try
		{
			  for(int i=0; i<r1.retrieveNoOfRows(exceldatasheet)-1;i++)
			  {
					  AnchalAppFunctions.navigateToURL(navigateurl);
					  log.writeToInfoLog(debugger, "Anchal app has been navigate to admin role page.");
					  metadata.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[i]));
					  metadata.setMobilenumber(((r1.retrieveToRunFlagTestData(exceldatasheet, headermobilenumber))[i]));
					  metadata.setRole(((r1.retrieveToRunFlagTestData(exceldatasheet, headerrole))[i]));	 
					  metadata.setDH(((r1.retrieveToRunFlagTestData(exceldatasheet, headerDH))[i]));	
					  metadata.setTalukahospital(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukahospital))[i]));	
					  metadata.setPHCsintaluka(((r1.retrieveToRunFlagTestData(exceldatasheet, headerPHCsintaluka))[i]));						  		
					  metadata.setNameofsubcentre(((r1.retrieveToRunFlagTestData(exceldatasheet, headernameofsubcentre))[i]));
					  metadata.setTalukaname(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukaname))[i]));
					  metadata.setVillagename(((r1.retrieveToRunFlagTestData(exceldatasheet, headervillagename))[i]));
					  // mobile number regex
					  Boolean mobilenumberregexboolean = metadata.getMobilenumber().matches("^[0][1-9]\\d{9}$|^[1-9]\\d{9}$");
					  
					  /*if(i==0)
					  {
						  if(AnchalAppFunctions.selectDHForFirstTime(metadata.getDH(), metadata.getMobilenumber()))
						  {							  
							  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
							  {
								  	 AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
								  	 
								  	 if(mobilenumberregexboolean)
									 {
								  		if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
										  {
									  			if(AnchalAppFunctions.selectPHC(metadata.getPHCsintaluka(), metadata.getMobilenumber()))
									  			{
									  				if(AnchalAppFunctions.selectSC(metadata.getNameofsubcentre(), metadata.getMobilenumber()))
									  				{
									  					  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
														  {
									  						 if(AnchalAppFunctions.selectVillage(metadata.getVillagename(), metadata.getMobilenumber()))
									  						 {
									  							  AnchalAppFunctions.save();											 
																  log.writeToLog(debugger, "Data has been saved successfully.");
									  						 }
									  						 else
									  						 {
									  							 log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in village name.",metadata.getVillagename());					  
															 }															  
														  }
														  else
														  {
															  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
														  }										  					
									  				}
									  				else
									  				{
									  					log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in name of sub centre.",metadata.getNameofsubcentre());
									  				}									  				 	
									  			}
									  			else
									  			{
									  				log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in PHC name.",metadata.getPHCsintaluka());
									  			}											  								  
										  }
										  else
										  {
											  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
										  }												 
									 }
									 else
									 {					
										 log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
										 AnchalAppFunctions.refreshPage();					  									 								 
									 }							  
							  }
							  else
							  {
								  if(metadata.getUsername().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
								  }
								  else if(metadata.getMobilenumber().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
								 
								  }else if(metadata.getRole().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
								  }
							   }				
						  }
						  else
						  {
							  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
						  }						 	 							  						
					  }
					  else
					  {*/
						  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
						  {
							  AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
							  
							  if(mobilenumberregexboolean)
							  {
								  if(AnchalAppFunctions.selectDH(metadata.getDH(), metadata.getMobilenumber()))
								  {
									  if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
									  {
										  if(AnchalAppFunctions.selectPHC(metadata.getPHCsintaluka(), metadata.getMobilenumber()))
								  			{
								  				if(AnchalAppFunctions.selectSC(metadata.getNameofsubcentre(), metadata.getMobilenumber()))
								  				{
								  					  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
													  {
								  						 if(AnchalAppFunctions.selectVillage(metadata.getVillagename(), metadata.getMobilenumber()))
								  						 {
								  							  AnchalAppFunctions.save();											 
															  log.writeToLog(debugger, "Data has been saved successfully.");
								  						 }
								  						 else
								  						 {
								  							 log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in village name.",metadata.getVillagename());					  
														 }															  
													  }
													  else
													  {
														  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
													  }										  					
								  				}
								  				else
								  				{
								  					log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in name of sub centre.",metadata.getNameofsubcentre());
								  				}									  				 	
								  			}
								  			else
								  			{
								  				log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in PHC name.",metadata.getPHCsintaluka());
								  			}										  
									  }
									  else
									  {
										  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
									  }		
								  }
								  else
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
								  }									 									  
							  }
							  else
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
								  AnchalAppFunctions.refreshPage();								  						  							  
							  }			
						  }
						  else
						  {
							  if(metadata.getUsername().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
							  }
							  else if(metadata.getMobilenumber().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
							 
							  }else if(metadata.getRole().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
							  }					  						  
						  }					 			  	 
					  }					  				 					 							
			   //} 
		}
		catch(Exception e)
		{
			log.writeToFatalLog(debugger, "Exception during testing scenario", e);			
		}		
	}

	public void executeANM(String exceldatasheet)
	{		
		try
		{
			  for(int i=0; i<r1.retrieveNoOfRows(exceldatasheet)-1;i++)
			  {
					  AnchalAppFunctions.navigateToURL(navigateurl);
					  log.writeToInfoLog(debugger, "Anchal app has been navigate to admin role page.");
					  metadata.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[i]));
					  metadata.setMobilenumber(((r1.retrieveToRunFlagTestData(exceldatasheet, headermobilenumber))[i]));
					  metadata.setRole(((r1.retrieveToRunFlagTestData(exceldatasheet, headerrole))[i]));	 
					  metadata.setDH(((r1.retrieveToRunFlagTestData(exceldatasheet, headerDH))[i]));	
					  metadata.setTalukahospital(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukahospital))[i]));	
					  metadata.setPHCsintaluka(((r1.retrieveToRunFlagTestData(exceldatasheet, headerPHCsintaluka))[i]));						  		
					  metadata.setNameofsubcentre(((r1.retrieveToRunFlagTestData(exceldatasheet, headernameofsubcentre))[i]));
					  metadata.setTalukaname(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukaname))[i]));
					 
					  // mobile number regex
					  Boolean mobilenumberregexboolean = metadata.getMobilenumber().matches("^[0][1-9]\\d{9}$|^[1-9]\\d{9}$");
					  
					/*  if(i==0)
					  {
						  if(AnchalAppFunctions.selectDHForFirstTime(metadata.getDH(), metadata.getMobilenumber()))
						  {							  
							  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
							  {
								  	 AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
								  	 
								  	 if(mobilenumberregexboolean)
									 {
								  		if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
										  {
									  			if(AnchalAppFunctions.selectPHC(metadata.getPHCsintaluka(), metadata.getMobilenumber()))
									  			{
									  				if(AnchalAppFunctions.selectSC(metadata.getNameofsubcentre(), metadata.getMobilenumber()))
									  				{
									  					  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
														  {
									  						 AnchalAppFunctions.save();											 
															 log.writeToLog(debugger, "Data has been saved successfully.");									  																				  
														  }
														  else
														  {
															  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
														  }										  					
									  				}
									  				else
									  				{
									  					log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in name of sub centre.",metadata.getNameofsubcentre());
									  				}									  				 	
									  			}
									  			else
									  			{
									  				log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in PHC name.",metadata.getPHCsintaluka());
									  			}											  								  
										  }
										  else
										  {
											  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
										  }												 
									 }
									 else
									 {					
										 log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
										 AnchalAppFunctions.refreshPage();					  									 								 
									 }							  
							  }
							  else
							  {
								  if(metadata.getUsername().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
								  }
								  else if(metadata.getMobilenumber().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
								 
								  }else if(metadata.getRole().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
								  }
							   }				
						  }
						  else
						  {
							  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
						  }						 	 							  						
					  }
					  else
					  {*/
						  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
						  {
							  AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
							  
							  if(mobilenumberregexboolean)
							  {
								  if(AnchalAppFunctions.selectDH(metadata.getDH(), metadata.getMobilenumber()))
								  {
									  if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
									  {
										  if(AnchalAppFunctions.selectPHC(metadata.getPHCsintaluka(), metadata.getMobilenumber()))
								  			{
								  				if(AnchalAppFunctions.selectSC(metadata.getNameofsubcentre(), metadata.getMobilenumber()))
								  				{
								  					  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
													  {
								  						  AnchalAppFunctions.save();											 
														  log.writeToLog(debugger, "Data has been saved successfully.");								  						 														  
													  }
													  else
													  {
														  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
													  }										  					
								  				}
								  				else
								  				{
								  					log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in name of sub centre.",metadata.getNameofsubcentre());
								  				}									  				 	
								  			}
								  			else
								  			{
								  				log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in PHC name.",metadata.getPHCsintaluka());
								  			}										  
									  }
									  else
									  {
										  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
									  }		
								  }
								  else
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
								  }									 									  
							  }
							  else
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
								  AnchalAppFunctions.refreshPage();								  						  							  
							  }			
						  }
						  else
						  {
							  if(metadata.getUsername().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
							  }
							  else if(metadata.getMobilenumber().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
							 
							  }else if(metadata.getRole().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
							  }					  						  
						  }					 			  	 
					  }					  				 					 							
			  // } 
		}
		catch(Exception e)
		{
			log.writeToFatalLog(debugger, "Exception during testing scenario", e);			
		}		
	}
	
	
	public void executePHCMO(String exceldatasheet)
	{		
		try
		{
			  for(int i=0; i<r1.retrieveNoOfRows(exceldatasheet)-1;i++)
			  {
					  AnchalAppFunctions.navigateToURL(navigateurl);
					  log.writeToInfoLog(debugger, "Anchal app has been navigate to admin role page.");
					  metadata.setUsername(((r1.retrieveToRunFlagTestData(exceldatasheet, headerusername))[i]));
					  metadata.setMobilenumber(((r1.retrieveToRunFlagTestData(exceldatasheet, headermobilenumber))[i]));
					  metadata.setRole(((r1.retrieveToRunFlagTestData(exceldatasheet, headerrole))[i]));	 
					  metadata.setDH(((r1.retrieveToRunFlagTestData(exceldatasheet, headerDH))[i]));	
					  metadata.setTalukahospital(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukahospital))[i]));	
					  metadata.setPHCsintaluka(((r1.retrieveToRunFlagTestData(exceldatasheet, headerPHCsintaluka))[i]));				  
					  metadata.setTalukaname(((r1.retrieveToRunFlagTestData(exceldatasheet, headertalukaname))[i]));
					 
					  // mobile number regex
					  Boolean mobilenumberregexboolean = metadata.getMobilenumber().matches("^[0][1-9]\\d{9}$|^[1-9]\\d{9}$");
					  
					/*  if(i==0)
					  {
						  if(AnchalAppFunctions.selectDHForFirstTime(metadata.getDH(), metadata.getMobilenumber()))
						  {							  
							  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
							  {
								  	 AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
								  	 
								  	 if(mobilenumberregexboolean)
									 {
								  		if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
										  {
									  			if(AnchalAppFunctions.selectPHC(metadata.getPHCsintaluka(), metadata.getMobilenumber()))
									  			{
									  				  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
													  {
								  						 AnchalAppFunctions.save();											 
														 log.writeToLog(debugger, "Data has been saved successfully.");									  																				  
													  }
													  else
													  {
														  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
													  }								  													  				 	
									  			}
									  			else
									  			{
									  				log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in PHC name.",metadata.getPHCsintaluka());
									  			}											  								  
										  }
										  else
										  {
											  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
										  }												 
									 }
									 else
									 {					
										 log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
										 AnchalAppFunctions.refreshPage();					  									 								 
									 }							  
							  }
							  else
							  {
								  if(metadata.getUsername().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
								  }
								  else if(metadata.getMobilenumber().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
								 
								  }else if(metadata.getRole().isEmpty())
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
								  }
							   }				
						  }
						  else
						  {
							  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
						  }						 	 							  						
					  }
					  else
					  {*/
						  if(metadata.getUsername().length()>0 && metadata.getMobilenumber().length()>0 && metadata.getRole().length()>0)
						  {
							  AnchalAppFunctions.add_roles(metadata.getUsername(), metadata.getMobilenumber(), metadata.getRole());
							  
							  if(mobilenumberregexboolean)
							  {
								  if(AnchalAppFunctions.selectDH(metadata.getDH(), metadata.getMobilenumber()))
								  {
									  if(AnchalAppFunctions.selectCHC(metadata.getTalukahospital(), metadata.getMobilenumber()))
									  {
										  if(AnchalAppFunctions.selectPHC(metadata.getPHCsintaluka(), metadata.getMobilenumber()))
								  			{
												  if(AnchalAppFunctions.selectBlock(metadata.getTalukaname(), metadata.getMobilenumber()))
												  {
							  						  AnchalAppFunctions.save();											 
													  log.writeToLog(debugger, "Data has been saved successfully.");								  						 														  
												  }
												  else
												  {
													  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in block name.",metadata.getTalukaname());							  
												  }									  												  				 	
								  			}
								  			else
								  			{
								  				log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in PHC name.",metadata.getPHCsintaluka());
								  			}										  
									  }
									  else
									  {
										  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : There is an issue in chc name.",metadata.getTalukahospital());										  
									  }		
								  }
								  else
								  {
									  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : DH is mandatory.",metadata.getDH());
								  }									 									  
							  }
							  else
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile Number length is less than or greater than 10. It should be 10 digit number.", metadata.getMobilenumber());						  
								  AnchalAppFunctions.refreshPage();								  						  							  
							  }			
						  }
						  else
						  {
							  if(metadata.getUsername().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Username is mandatory and it should have valid name.",metadata.getUsername());
							  }
							  else if(metadata.getMobilenumber().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Mobile number is mandatory and it should have valid 10 digit number.", metadata.getMobilenumber());
							 
							  }else if(metadata.getRole().isEmpty())
							  {
								  log.writeToErrorLog(debugger, metadata.getMobilenumber()+" : Role is mandatory and it should have valid role category.",metadata.getRole());
							  }					  						  
						  }					 			  	 
					  }					  				 					 							
			  // } 
		}
		catch(Exception e)
		{
			log.writeToFatalLog(debugger, "Exception during testing scenario", e);			
		}		
	}
	
}
