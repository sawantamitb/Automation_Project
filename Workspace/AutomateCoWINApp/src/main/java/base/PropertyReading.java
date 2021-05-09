package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import util.MyLogger;

import static base.Constants.*;

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

			
	public static void main(String a[])
	{
		PropertyReading pr = new PropertyReading();
		pr.configPropertiesReading();
	}

}
