package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class MyLogger
{
	BufferedWriter bufferedwriter = null;
	FileWriter readfilewriter = null;
	
	public Logger configureLogger(String path,String testcaseName)
	{
		Logger logger = Logger.getLogger(testcaseName);
		PatternLayout layout=new PatternLayout();
		
       	RollingFileAppender fileAppender = new RollingFileAppender(); 
       	fileAppender.setLayout(layout);
       	fileAppender.setAppend(true);  
 	    fileAppender.setImmediateFlush(true); 
 	    fileAppender.setMaxFileSize("20MB");
 	    fileAppender.setThreshold(Level.ALL);
 	  
        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) 
        {
        	System.out.println("creating directory: " + theDir.getName());
        	boolean result = false;        	
	         try
	         {
	             theDir.mkdir();
	             result = true;
	         } 
	         catch(Exception se)
	         {
	             //handle it
	         }        
         
	         if(result) 
	         {    
	        	 System.out.println("DIR created");  
	         }
        }
          
        File f1 = new File(path+testcaseName+".txt");
        // check file is already exist or not
        //System.out.print(f1.exists());
        if(f1.exists())
        {        	
        	//try 
        	//{
        		/*readfilewriter = new FileWriter(f1, true);
        		bufferedwriter = new BufferedWriter(readfilewriter);
        		bufferedwriter.newLine();   //Add new line
        		fileAppender.setWriter(bufferedwriter);*/
	        		try 
	         	    {
	         	    	fileAppender.setWriter(new FileWriter(f1));			
	         	    } 
	                catch (IOException e)
	         	    {
	       				e.printStackTrace();
	       			}
	         	    finally
	         	    {
	         	    	try 
	         	    	{
	    					new FileWriter(f1).close();
	    				} 
	         	    	catch (IOException e) 
	         	    	{
	    					e.printStackTrace();
	    				}
	         	    }
				//} 
        	/*catch (IOException e) 
        	{
				e.printStackTrace();
			}  
        	finally
        	{
        		try 
        		{
        			if(readfilewriter!=null)
        			readfilewriter.close();
        			//if(bufferedwriter!=null)
        			//bufferedwriter.close();
				} 
        		catch (IOException e) 
        		{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}*/
        	
        }
        else
        {
        	// code if file already exist
        /*	fileAppender.setName("FILE");  
            fileAppender.setLayout(layout);  
     	    fileAppender.setAppend(true);  
     	    fileAppender.setImmediateFlush(true); 
     	    fileAppender.setMaxFileSize("20MB");
     	    fileAppender.setThreshold(Level.ALL); */ 
     	    try 
     	    {
     	    	fileAppender.setWriter(new FileWriter(f1));			
     	    } 
           catch (IOException e)
     	    {
   				e.printStackTrace();
   			}
     	    finally
     	    {
     	    	try 
     	    	{
					new FileWriter(f1).close();
				} 
     	    	catch (IOException e) 
     	    	{
					e.printStackTrace();
				}
     	    }
        }        
        
        logger.addAppender(fileAppender); 
        //logger.addAppender(consoleAppender);
        logger.setLevel(Level.ALL);    
        return logger;
	}
	
	
	public void writeToLog(Logger logger, String message)
	{
		logger.info((new Date()) +" :: "+" PASSED : "+message);
	}	
	public void writeToDebugLog(Logger logger, String debugMsg)
	{
		logger.debug((new Date()) +" :: "+ " DEBUDGGING : "+debugMsg);
	}
	
	public void writeToInfoLog(Logger logger,String infoMsg)
	{
		logger.info((new Date()) +" :: "+" INFORMATION : "+ infoMsg);
	}
	
	public void writeToWarnLog(Logger logger, String warnMsg)
	{
		logger.warn((new Date()) +" :: "+" WARNING : "+ warnMsg);
	}
	
	public void writeToErrorLog(Logger logger, String errorMsg, Object e)
	{
		logger.error((new Date()) +" :: "+ " ERROR : " +errorMsg+ " : "+e);		
	}
	
	public void writeToFatalLog(Logger logger, String fatalMsg, Object e)
	{
		logger.fatal((new Date()) +" :: "+" DEFECT : " +fatalMsg+" : "+e);
	}	
	
	public static void main(String[] args) throws IOException 
	{
		String path = "D:\\PageLoadLog\\";
		String filename = "log111";
		MyLogger ml = new MyLogger();
		
		//String path = "C:/Users/amit.sawant/Desktop/";
		//ml.configureLogger(path);
		Logger debugger = ml.configureLogger(path, filename);
		ml.writeToInfoLog(debugger, path);
		
	}
}
