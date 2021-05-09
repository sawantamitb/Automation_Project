package util; 

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
import org.testng.SkipException;

import base.PropertyReading;

import static base.Constants.*;

public class ReadWriteExcel 
{	
	//public static String filelocation = "D:/Automation/Java/Git_Repository/Automation_Project/Workspace/AutomateCoWINApp/data/Test_Suite.xlsx"; 
	static PropertyReading objPropertyReading = new PropertyReading();
	public static File resourcesDirectory = new File(System.getProperty("user.dir")+File.separator+objPropertyReading.configPropertiesReading().getProperty("testsuiteexcel"));
	public static FileInputStream ipstr = null; 
	public static FileOutputStream opstr =null; 
	public static XSSFWorkbook wb = null; 
	public static XSSFSheet ws = null;
	public static String delimiter=",";
	public static boolean flag = false;
	//Row Number
    public static int rowNumber;
 
    //Column Number
    public static int columnNumber;
    public static String data1,data2,data3,data4,data5,data6,data7,data8,data9,data10;
	public ReadWriteExcel() throws IOException 
	{		
			
	} 

	public static XSSFSheet setExcelFileSheet(String sheetname,String testcasename) throws IOException
	{
		try 
		{ 	
			ipstr = new FileInputStream(resourcesDirectory); 
			wb = new XSSFWorkbook(ipstr); 
			ws = wb.getSheet(sheetname); 	
			if(!checkToRunUtility(casetorun, testcasename))
			{
				throw new SkipException("Test case skipped due to flag was not set properly for "+testcasename);
			}
		} 
		catch (Exception e) 
		{	
			e.printStackTrace();
		}
		finally
		{
			if(ipstr!=null)
				ipstr.close();			
		}
		return ws;
	}
	
	public static boolean checkToRunUtility(String caseToRun, String testCaseName)
	{
		boolean flag = false;		
		if(retrieveToRunFlag(testsuite,caseToRun,testCaseName).equalsIgnoreCase("Y"))
		{
			flag = true;
		}		
		return flag;	
	}
	
//To retrieve No Of Rows from .xls file's sheets. 
	public static Map testsplitdata(String str)
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
	
	
	
	// To retrieve No Of Rows from .xls file's sheets.
		public static int retrieveNoOfRows(XSSFSheet sheetname) 
		{
			int sheetIndex = wb.getSheetIndex(sheetname);
			if (sheetIndex == -1)
				return 0;
			else {
				sheetname = wb.getSheetAt(sheetIndex);
				int rowCount = sheetname.getLastRowNum() + 1;
				return rowCount;
			}
		}

		// To retrieve No Of Columns from .cls file's sheets.
		public static int retrieveNoOfCols(XSSFSheet sheetname) 
		{
			int sheetIndex = wb.getSheetIndex(sheetname);
			if (sheetIndex == -1)
				return 0;
			else {
				sheetname = wb.getSheetAt(sheetIndex);
				int colCount = sheetname.getRow(0).getLastCellNum();
				return colCount;
			}
		}

		// To get the column name index by providing column name and sheet number
		public static int getColumnNameIndex(String sheetname,String colName) 
		{
			ws = wb.getSheet(sheetname); 
			int rowNum = retrieveNoOfRows(ws);
			int colNum = retrieveNoOfCols(ws);
			int colNumber = -1;

			XSSFRow Suiterow = ws.getRow(0);
			String data[] = new String[rowNum - 1]; // size of data array

			for (int i = 0; i < colNum; i++) {
				if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
					colNumber = i;
					break;
				}
			}

			if (colNumber == -1) {
				return 0;
			}
			return colNumber;
		}
		
		public static int getRowCellIndex(String sheetname,String rowName, String colName)
		{
			ws = wb.getSheet(sheetname); 
			int rowNum = retrieveNoOfRows(ws);
			int colNum = retrieveNoOfCols(ws);
			int rowNumber = -1;
			
			for (int i = 0; i < rowNum - 1; i++) 
			{
				XSSFRow row = ws.getRow(i + 1);
				XSSFCell cell = row.getCell(getColumnNameIndex(sheetname, colName));
				cell.setCellType(cell.getCellTypeEnum());
				// cell.setCellType();
				// cell.setCellType(Cell.CELL_TYPE_STRING);
				// String value =

				String value = cellToString(cell);
				if (value.equalsIgnoreCase(rowName)) 
				{
					rowNumber = i + 1;
					break;
				}
			}
			return rowNumber;
		}
		
		public static void setCellData(String sheetname,int rowNumber,int colNumber,String value) throws IOException
		{
			ws = wb.getSheet(sheetname); 
			try
			{
				XSSFRow Row = ws.getRow(rowNumber);
	
				XSSFCell cell = Row.getCell(colNumber);
				if (cell == null)
					cell = Row.createCell(colNumber);
				cell.setCellValue(value);
				try 
				{
					opstr = new FileOutputStream(resourcesDirectory);
					wb.write(opstr);
				} 
				catch (FileNotFoundException fnfe) 
				{
					fnfe.printStackTrace();
				}
				
				opstr = new FileOutputStream(resourcesDirectory);
				wb.write(opstr);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();				
			} 
			finally 
			{
				if (opstr != null)
				{
					opstr.flush();
					opstr.close();
				}
			}
		}
		
		 //Setter and Getters of row and columns
	    public static void setRowNumber(int pRowNumber) {
	        rowNumber = pRowNumber;
	    }
	 
	    public static int getRowNumber() {
	        return rowNumber;
	    }
	 
	    public static void setColumnNumber(int pColumnNumber) {
	        columnNumber = pColumnNumber;
	    }
	 
	    public static int getColumnNumber() {
	        return columnNumber;
	    }	    
	    
	// To retrieve SuiteToRun and CaseToRun flag of test suite and test case.
		public static String retrieveToRunFlag(String sheetname, String colName, String rowName) {
			ws = wb.getSheet(sheetname); 
			int sheetIndex = wb.getSheetIndex(ws);
			if (sheetIndex == -1)
				return null;
			else {
				int rowNum = retrieveNoOfRows(ws);
				int colNum = retrieveNoOfCols(ws);
				int colNumber = -1;
				int rowNumber = -1;
				XSSFRow Suiterow = ws.getRow(0);

				for (int i = 0; i < colNum; i++) {
					if (Suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
						colNumber = i;
						break;
					}
				}

				if (colNumber == -1) {
					return "";
				}

				for (int j = 0; j < rowNum; j++) {
					XSSFRow Suitecol = ws.getRow(j);

					if (Suitecol.getCell(getColumnNameIndex(sheetname, colName) - 1).getStringCellValue()
							.equals(rowName.trim())) {
						rowNumber = j;
					}
				}

				if (rowNumber == -1) {
					return "";
				}

				XSSFRow row = ws.getRow(rowNumber);
				XSSFCell cell = row.getCell(colNumber);
				if (cell == null) {
					return "";
				}

				String value = cellToString(cell);
				return value;
			}
		}

		
	//To retrieve DataToRun flag of test data. 
	public static String[] retrieveToRunFlagTestData(String sheetname, String colName)
	{ 
		ws = wb.getSheet(sheetname); 		
		int sheetIndex=wb.getSheetIndex(ws); 
		if(sheetIndex==-1) 
			return null; 
		else
		{ 
			int rowNum = retrieveNoOfRows(ws); 
			int colNum = retrieveNoOfCols(ws); 
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
	
	 //Setter and Getters of row and columns data
	public static void setParameter1(String param) 
    {
    	data1 = param;
    }
 
    public static String getParameter1(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter1))[rowdataindex-1].trim().toString();
    }
 
    public static void setParameter2(String param) 
    {
    	data2 = param;
    }
 
    public static String getParameter2(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter2))[rowdataindex-1].trim().toString();
    }
    
    public static void setParameter3(String param) 
    {
    	data3 = param;
    }
 
    public static String getParameter3(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter3))[rowdataindex-1].trim().toString();
    }
    
    public static void setParameter4(String param) 
    {
    	data4 = param;
    }
 
    public static String getParameter4(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter4))[rowdataindex-1].trim().toString();
    }
    public static void setParameter5(String param) 
    {
    	data5 = param;
    }
 
    public static String getParameter5(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter5))[rowdataindex-1].trim().toString();
    }
    public static void setParameter6(String param) 
    {
    	data6 = param;
    }
 
    public static String getParameter6(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter6))[rowdataindex-1].trim().toString();
    }
    public static void setParameter7(String param) 
    {
    	data7 = param;
    }
 
    public static String getParameter7(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter7))[rowdataindex-1].trim().toString();
    }
    public static void setParameter8(String param) 
    {
    	data8 = param;
    }
 
    public static String getParameter8(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter8))[rowdataindex-1].trim().toString();
    }
    public static void setParameter9(String param) 
    {
    	data9 = param;
    }
 
    public static String getParameter9(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter9))[rowdataindex-1].trim().toString();
    }
    public static void setParameter10(String param) 
    {
    	data10 = param;
    }
 
    public static String getParameter10(String testdatasheetname, String testcasename) 
    {
    	int rowdataindex = ReadWriteExcel.getRowCellIndex(testdatasheetname,testcasename,testcasenamecolumn);
    	return (retrieveToRunFlagTestData(testdatasheetname,parameter10))[rowdataindex-1].trim().toString();
    }
	/*
	 * //To retrieve test data from test case data sheets.
	 * 
	 * @SuppressWarnings("deprecation") public Object[][] retrieveTestData() { int
	 * sheetIndex=wb.getSheetIndex(ws);
	 * 
	 * if(sheetIndex==-1) return null; else { int rowNum = retrieveNoOfRows(ws); int
	 * colNum = retrieveNoOfCols(ws);
	 * 
	 * Object[][] data = new Object[rowNum-1][colNum-2];
	 * 
	 * for (int i=0; i<rowNum-1; i++) { XSSFRow row = ws.getRow(i+1); for(int j=0;
	 * j< colNum-2; j++) { if(row==null) { data[i][j] = ""; } else { XSSFCell cell =
	 * row.getCell(j);
	 * 
	 * if(cell==null) { data[i][j] = ""; } else {
	 * cell.setCellType(Cell.CELL_TYPE_STRING); //String value = cellToString(cell);
	 * DataFormatter df = new DataFormatter(Locale.US); String value
	 * =df.formatCellValue(cell); data[i][j] = value; } } } } return data; } }
	 */


	public static String cellToString(XSSFCell cell)
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
	

		// To write result In test suite list sheet.
		public static boolean writeResult(String sheetname, String colName, String rowName, String Result) throws IOException 
		{
			FileOutputStream opstr = null;
			ws = wb.getSheet(sheetname); 
			try 
			{
				int rowNum = retrieveNoOfRows(ws);
				int rowNumber = -1;
				int sheetIndex = wb.getSheetIndex(ws);

				if (sheetIndex == -1)
					return false;

				int colNum = retrieveNoOfCols(ws);
				int colNumber = -1;

				XSSFRow suiterow = ws.getRow(0);
				for (int i = 0; i < colNum; i++) {
					if (suiterow.getCell(i).getStringCellValue().equals(colName.trim()))
					{
						colNumber = i;
						break;
					}
				}

				if (colNumber == -1) {
					return false;
				}

				for (int i = 0; i < rowNum - 1; i++) 
				{
					XSSFRow row = ws.getRow(i + 1);
					XSSFCell cell = row.getCell(getColumnNameIndex(sheetname, colName) - 2);
					cell.setCellType(cell.getCellTypeEnum());
					// cell.setCellType();
					// cell.setCellType(Cell.CELL_TYPE_STRING);
					// String value =

					String value = cellToString(cell);
					if (value.equalsIgnoreCase(rowName)) {
						rowNumber = i + 1;
						break;
					}
				}

				XSSFRow Row = ws.getRow(rowNumber);

				XSSFCell cell = Row.getCell(colNumber);
				if (cell == null)
					cell = Row.createCell(colNumber);
				cell.setCellValue(Result);
				try {
					opstr = new FileOutputStream(resourcesDirectory);
					wb.write(opstr);
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}
				opstr = new FileOutputStream(resourcesDirectory);
				wb.write(opstr);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (opstr != null) {
					opstr.flush();
					opstr.close();
					// WindowsUtils.killByName("EXCEL.EXE");
				}
			}
			return true;
		}

	public static String resourcesDirectory()
	{
		resourcesDirectory = new File(System.getProperty("user.dir")+File.separator+objPropertyReading.configPropertiesReading().getProperty("testsuiteexcel"));		
		System.out.println(resourcesDirectory.getAbsolutePath());
		return resourcesDirectory.toString();
	}
	
	public static void main(String arg[]) throws IOException
	{
		ReadWriteExcel.setExcelFileSheet(testsuite, "ValidLoginPageTest");
		int x = ReadWriteExcel.getColumnNameIndex(testsuite, passfailcolumn);
		System.out.println("X: "+x);
		
		int y = ReadWriteExcel.getRowCellIndex(testsuite,"ValidLoginPageTest",testcasenamecolumn);
		System.out.println("Y: "+y);
		
		ReadWriteExcel.setRowNumber(ReadWriteExcel.getRowCellIndex(testsuite,"ValidLoginPageTest",testcasenamecolumn));
		ReadWriteExcel.setColumnNumber(ReadWriteExcel.getColumnNameIndex(testsuite,passfailcolumn));
		
		ReadWriteExcel.setCellData(testsuite,ReadWriteExcel.getRowNumber(), ReadWriteExcel.getColumnNumber(), "Skip");
		//ReadWriteExcel.writeResult(passfailcolumn, "ValidLoginPageTest", "Skip");
		int z = ReadWriteExcel.getRowCellIndex("Self Registration","ValidLoginPageTest",testcasenamecolumn);
		System.out.println("Z: "+z);
		System.out.println((retrieveToRunFlagTestData("Self Registration", "Parameter 1"))[z-1].trim().toString());
		setParameter1(ReadWriteExcel.getParameter1("Self Registration","ValidLoginPageTest"));
		System.out.println("P1 "+ReadWriteExcel.getParameter1("Self Registration","ValidLoginPageTest"));
		System.out.println("P2 "+ReadWriteExcel.getParameter9("Self Registration","ValidLoginPageTest"));
		System.out.println("P3 "+ReadWriteExcel.getParameter10("Self Registration","ValidLoginPageTest"));

		
		/*
		 * System.out.println(retrieveNoOfRows("TestSuiteList"));
		 * System.out.println(retrieveNoOfCols("TestSuiteList"));(retrieveToRunFlagTestData("Self Registration", "Parameter 1"))[z]
		 * System.out.println((retrieveToRunFlagTestData("TestSuiteList",
		 * "CaseToRun"))[0]); System.out.println(writeResult(ws, wb, "Pass/Fail/Skip",
		 * "ValidLoginPageTest","Skip", resourcesDirectory()));
		 */
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