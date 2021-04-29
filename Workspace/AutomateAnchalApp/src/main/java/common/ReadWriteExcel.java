package common; 

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*; 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.os.WindowsUtils;
import static common.Constants.*;

public class ReadWriteExcel 
{	
	public String filelocation; 
	//String filelocation1 = System.getProperty("user.dir") ;
	public FileInputStream ipstr = null; 
	public FileOutputStream opstr =null; 
	public XSSFWorkbook wb = null; 
	public XSSFSheet ws = null;
	public static String delimiter=",";
	
	public ReadWriteExcel(String filelocation) 
	{	
		this.filelocation=filelocation; 
		try 
		{ 
			ipstr = new FileInputStream(filelocation); 
			wb = new XSSFWorkbook(ipstr); 
			ws = wb.getSheetAt(0); 
			ipstr.close(); 
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();
		} 
	} 

//To retrieve No Of Rows from .xls file's sheets. 
	public Map testsplitdata(String str)
 	{
 		Map<String, String> map = new HashMap<String, String>();
 		for(String keyValue : str.split(","))
 		{
 			String[] pairs = keyValue.split("=");
 			map.put(pairs[0], pairs.length == 1 ? "": pairs[1]);
 		}			 			
 		
 		/*for(String s : map.keySet()) 
 		{
 	        System.out.println(s + " is " + map.get(s));
 	    }*/
 		
		return map; 		
 	}
	
	public String checkValueofHashMapkey(String str,String key)
 	{
 		Map<String, String> map = new HashMap<String, String>();
 		for(String keyValue : str.split(","))
 		{
 			String[] pairs = keyValue.split("=");
 			map.put(pairs[0], pairs.length == 1 ? "": pairs[1]);
 		}			
 		return map.get(key); 		
 	}
	
	public int retrieveNoOfRows(String wsName)
	{	
		int sheetIndex=wb.getSheetIndex(wsName); 
		if(sheetIndex==-1) 
			return 0; 
		else
		{ 
			ws = wb.getSheetAt(sheetIndex); 
			int rowCount=ws.getLastRowNum()+1;	
			return rowCount;	
		} 
	} 

//To retrieve No Of Columns from .cls file's sheets. 
	public int retrieveNoOfCols(String wsName)
	{ 
		int sheetIndex=wb.getSheetIndex(wsName); 
		if(sheetIndex==-1) 
			return 0; 
		else
		{ 
			ws = wb.getSheetAt(sheetIndex); 
			int colCount=ws.getRow(0).getLastCellNum();	
			return colCount; 
		} 
	} 

	//To retrieve SuiteToRun and CaseToRun flag of test suite and test case. 
	public String retrieveToRunFlag(String wsName, String colName, String rowName)
	{ 
			int sheetIndex=wb.getSheetIndex(wsName); 
			if(sheetIndex==-1) 
				return null; 
			else
			{ 
					int rowNum = retrieveNoOfRows(wsName); 
					int colNum = retrieveNoOfCols(wsName); 
					int colNumber=-1; 
					int rowNumber=-1;	
					XSSFRow Suiterow = ws.getRow(0);
					
					for(int i=0; i<colNum; i++)
					{ 
							if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim()))
							{ 
								colNumber=i;	
							}	
					} 
		
					if(colNumber==-1)
					{ 
						return "";	
					} 
		
		
					for(int j=0; j<rowNum; j++)
					{ 
						XSSFRow Suitecol = ws.getRow(j);	
						
						if(Suitecol.getCell(1).getStringCellValue().equals(rowName.trim()))
						{ 
							rowNumber=j;	
						}	
					} 
		
					if(rowNumber==-1)
					{ 
						return "";	
					} 
		
					XSSFRow row = ws.getRow(rowNumber); 
					XSSFCell cell = row.getCell(colNumber); 
					if(cell==null)
					{ 
							return ""; 
					}
					
					String value = cellToString(cell); 
					return value;					
			}
	} 

		
	//To retrieve DataToRun flag of test data. 
	public String[] retrieveToRunFlagTestData(String wsName, String colName)
	{ 
	
		int sheetIndex=wb.getSheetIndex(wsName); 
		if(sheetIndex==-1) 
			return null; 
		else
		{ 
			int rowNum = retrieveNoOfRows(wsName); 
			int colNum = retrieveNoOfCols(wsName); 
			int colNumber=-1; 
	
			XSSFRow Suiterow = ws.getRow(0);	
			String data[] = new String[rowNum-1]; // size of data array
			
			for(int i=0; i<colNum; i++)
			{ 
				if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim()))
				{ 
					colNumber=i;
					break;
				}	
			} 
	
			if(colNumber==-1)
			{ 
				return null;	
			} 
	// continue -->
			
			for(int j=0; j<rowNum-1; j++)
			{ 
				XSSFRow Row = ws.getRow(j+1); 
				if(Row==null)
				{ 
					data[j] = ""; 
				} 
				else
				{ 
					XSSFCell cell = Row.getCell(colNumber); 
					if(cell==null)
					{ 
						data[j] = ""; 
					} 
					else
					{ 
						//String value = cellToString(cell); 
						DataFormatter df = new DataFormatter(Locale.US);
						String value =df.formatCellValue(cell);
						if(value.contains(delimiter))
			 			{
							data[j] = value;
							testsplitdata(value);			 				
			 			}
			 			else
			 			{
			 				data[j] = value;
			 			}
					}	
				} 
			} 
			return data;	
		}	
	} 

//To retrieve test data from test case data sheets. 
	@SuppressWarnings("deprecation")
	public Object[][] retrieveTestData(String wsName)
	{ 
			int sheetIndex=wb.getSheetIndex(wsName); 
			
			if(sheetIndex==-1) 
			return null; 
			else
			{ 
				int rowNum = retrieveNoOfRows(wsName); 
				int colNum = retrieveNoOfCols(wsName); 
			
				Object[][] data = new Object[rowNum-1][colNum-2]; 
			
				for (int i=0; i<rowNum-1; i++)
				{ 
					XSSFRow row = ws.getRow(i+1); 
					for(int j=0; j< colNum-2; j++)
					{	
						if(row==null)
						{ 
							data[i][j] = ""; 
						} 
						else
						{ 
							XSSFCell cell = row.getCell(j);	
			
							if(cell==null)
							{ 
								data[i][j] = "";	
							} 
							else
							{ 
								cell.setCellType(Cell.CELL_TYPE_STRING);
								//String value = cellToString(cell);
								DataFormatter df = new DataFormatter(Locale.US);
								String value =df.formatCellValue(cell);
								data[i][j] = value;	
							} 
						} 
					}	
				}	
			return data;	
			} 
	}	


	public String cellToString(XSSFCell cell)
	{ 
		CellType type; 
		Object result; 
		type = cell.getCellTypeEnum();	
		
		switch (type)
		{ 
			case NUMERIC : 
				result = cell.getNumericCellValue();
				break; 
	
			case STRING : 
				result = cell.getStringCellValue(); 
				break; 
				
			case FORMULA : 
				result = cell.getRawValue(); 
				break; 
				
			case BLANK : 
				result = cell.getStringCellValue(); 
				break; 
				
			case BOOLEAN : 
				result = cell.getBooleanCellValue(); 
				break; 
	
			default : 
				throw new RuntimeException("Unsupportd cell.");	
		} 
		return result.toString(); 
	} 
	
	//To write result In test data and test case list sheet. 
/*		public boolean writeResult(String wsName, String colName, int rowNumber, String Result)
		{ 
		
			try
			{ 
				int sheetIndex=wb.getSheetIndex(wsName); 
				
				if(sheetIndex==-1) 
					return false;	
				int colNum = retrieveNoOfCols(wsName); 
				int colNumber=-1; 
		
				XSSFRow Suiterow = ws.getRow(0);	
		
				for(int i=0; i<colNum; i++)
				{	
					if(Suiterow.getCell(i).getStringCellValue().equals(colName.trim()))
					{ 
						colNumber=i;	
					}	
				} 
		
				if(colNumber==-1)
				{ 
					return false;	
				} 
		
				XSSFRow Row = ws.getRow(rowNumber); 
				XSSFCell cell = Row.getCell(colNumber); 
				if (cell == null) 
		        cell = Row.createCell(colNumber);	
				cell.setCellValue(Result); 
		
				opstr = new FileOutputStream(filelocation); 
				wb.write(opstr); 
				opstr.close(); 
			
			}
			catch(Exception e)
			{ 
				e.printStackTrace(); 
				return false; 
			} 
			return true; 
		}*/ 

		//To write result In test suite list sheet. 
		public boolean writeResult(String wsName, String colName, String rowName, String Result) throws IOException
		{ 
			FileOutputStream opstr = null;
			try
			{ 
				int rowNum = retrieveNoOfRows(wsName); 
				int rowNumber=-1; 
				int sheetIndex=wb.getSheetIndex(wsName); 
				
				if(sheetIndex==-1) 
					return false;	
				
				int colNum = retrieveNoOfCols(wsName); 
				int colNumber=-1; 
				
		
				XSSFRow suiterow = ws.getRow(0);	
				for(int i=0; i<colNum; i++)
				{	
					if(suiterow.getCell(i).getStringCellValue().equals(colName.trim()))
					{ 
						colNumber=i;	
					}	
				} 
		
				if(colNumber==-1)
				{ 
					return false;	
				} 
		
				for (int i=0; i<rowNum-1; i++)
				{ 
					XSSFRow row = ws.getRow(i+1);	
					XSSFCell cell = row.getCell(1);	
					cell.setCellType(cell.getCellTypeEnum());
					//cell.setCellType();
					//cell.setCellType(Cell.CELL_TYPE_STRING); 
					//String value = 
				
					String  value = cellToString(cell);
					if(value.equalsIgnoreCase(rowName))
					{ 
						rowNumber=i+1; 
						break; 
					} 
				}
				
				XSSFRow Row = ws.getRow(rowNumber); 
				
				XSSFCell cell = Row.getCell(colNumber); 
				if (cell == null) 
				cell = Row.createCell(colNumber);	
				cell.setCellValue(Result); 
				try
				{
					opstr = new FileOutputStream(filelocation);
					wb.write(opstr);
				}
				catch(FileNotFoundException fnfe)
				{
					fnfe.printStackTrace();
					WindowsUtils.killByName("EXCEL.EXE");
				}			
				opstr = new FileOutputStream(filelocation);
				wb.write(opstr);
			}
			catch(Exception e)
			{ 
				e.printStackTrace(); 
				return false; 
			} 
			finally
			{
				if(opstr!=null)
				{
					opstr.flush();
					opstr.close();
					WindowsUtils.killByName("EXCEL.EXE");
				}
			}
		return true; 
		} 


	public static String resourcesDirectory()
	{
		File resourcesDirectory = new File("D:/Automation_Project/Installation/Eclipse_Workspace/AutomateAnchalApp/data/MetaData.xlsx");
		
		System.out.println(resourcesDirectory.getAbsolutePath());
		return resourcesDirectory.toString();
	}
	
	public static void main(String arg[]) throws IOException
	{
		ReadWriteExcel r1 = new ReadWriteExcel(resourcesDirectory());
		System.out.println(r1.retrieveNoOfRows("TH MO"));
		System.out.println(r1.retrieveNoOfCols("TH MO"));
		System.out.println((r1.retrieveToRunFlagTestData("TH MO", "Name of User"))[0]);
		//System.out.println(Arrays.toString(r1.retrieveTestData("TH MO")));
		
		/*
		 * System.out.println(Arrays.toString(r1.retrieveToRunFlagTestData("TestCases", "TestCaseName")));
		System.out.println(Arrays.asList(r1.retrieveToRunFlagTestData("TestCases", "TestCaseName")).indexOf("Admin_Valid_User"));
		int rn1 = Arrays.asList(r1.retrieveToRunFlagTestData("TestCases", "TestCaseName")).indexOf("Admin_Valid_User");
		System.out.println((r1.retrieveToRunFlagTestData("TestCases", "UserName"))[rn1]);
		System.out.println((r1.retrieveToRunFlagTestData("TestCases", "Password"))[rn1]);
		//System.out.println(new BigDecimal((r1.retrieveToRunFlagTestData("TestCases", "Password"))[rn1]).toPlainString());
		System.out.println((r1.retrieveToRunFlagTestData("TestCases", "ValidationMessage"))[rn1]);
		String str3 = (r1.retrieveToRunFlagTestData("TestCases", "Administration"))[rn1].trim();
		//System.out.println(r1.retrieveToRunFlag("TestCasesList","CaseToRun","Invalid_User_Login"));
		//System.out.println(r1.writeResult("TestCasesList", "Pass/Fail/Skip", "Invalid_User_Login", "Fail"));
		if(str3.contains(delimiter))
		{
			Map<String, String> m1 = testsplitdata(str3);
			System.out.println("Key value is " + m1.get("madridconfig"));
			for(String s : m1.keySet()) 
	 		{
	 	        System.out.println(s + " is " + m1.get(s));
	 	    }
		}
		*/
	}
} 