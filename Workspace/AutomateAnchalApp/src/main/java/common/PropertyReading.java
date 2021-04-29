package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import logger.MyLogger;

import static common.Constants.*;

public class PropertyReading 
{
	private Properties properties;
		
	public Properties configPropertiesReading()
	{
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(configpropertyFilePath).getAbsoluteFile();
		//File file = new File(classLoader.getResource("//propertyfiles/Config.properties").getFile());
		//System.out.println(file.getAbsolutePath());
		Properties prop = new Properties();
		FileInputStream inout = null;
		try 
		{
			inout = new FileInputStream(file);
			prop.load(inout);
			return prop;
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(inout!=null)
				inout.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		return null;				
	}

	public Properties objectRepositoryPropertiesReading()
	{
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(objectrepositorypropertyFilePath).getAbsoluteFile();
		//File file = new File(classLoader.getResource("//propertyfiles/Config.properties").getFile());
		//System.out.println(file.getAbsolutePath());
		Properties prop = new Properties();
		FileInputStream inout = null;
		try 
		{
			inout = new FileInputStream(file);
			prop.load(inout);
			return prop;
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(inout!=null)
				inout.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		return null;				
	}

	
	public Properties excelConfigPropertiesReading()
	{
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("propertyfiles/ExcelConfig.properties").getFile());
		//System.out.println(file.getAbsolutePath());
		Properties prop = new Properties();
		FileInputStream inout = null;
		try 
		{
			inout = new FileInputStream(file);
			
			prop.load(inout);
			return prop;
		} 
		catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(inout!=null)
				inout.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;			
	}
	
	public String getTestDataResourcePath()
	{
		String testDataResourcePath = properties.getProperty("testDataResourcePath");
		if(testDataResourcePath!= null) return testDataResourcePath;
		else throw new RuntimeException("Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");		
	}
	
	public static String setExtentReportParameters()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Date curDate = new Date();
		String strDate = sdf.format(curDate);
		return "ExtentReport-"+strDate+".html";		
	}
	
	public static void main(String a[])
	{
		PropertyReading pr = new PropertyReading();
		pr.configPropertiesReading();
	}

}
