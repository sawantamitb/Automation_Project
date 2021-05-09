package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.remote.ExecuteMethod;

import static base.Constants.*;

public class ReadMergedDataExcelFile {
	public static String filelocation = "D:/Automation/Java/Git_Repository/Automation_Project/Workspace/AutomateCoWINApp/data/Test_Suite.xlsx";
	public static FileInputStream ipstr = null;
	public static FileOutputStream opstr = null;
	public static XSSFWorkbook wb = null;
	public static XSSFSheet ws = null;
	public static XSSFSheet testsuite = null;

	static String testcasenameheader = "TestCaseName";
	static String testcasemergedcolumnheader = "ValidationMessage";
	static String testheadertypesofrelationshipandfiletypes = "TypeOfRelationshipAndFileID";
	static String testcasename = "Invalid_User_Login";
	static List<CellRangeAddress> regionsList;
	static XSSFCell cell;
	static int numberofcells, firstrownum, lastrownum;
	static String delimiter = ",";

	// To retrieve No Of Rows from .xls file's sheets.
	public int retrieveNoOfRows(Sheet sheet, XSSFWorkbook wb) {
		int sheetIndex = wb.getSheetIndex(sheet);
		if (sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int rowCount = ws.getLastRowNum() + 1;
			return rowCount;
		}
	}

	// To retrieve No Of Columns from .cls file's sheets.
	public int retrieveNoOfCols(Sheet sheet, XSSFWorkbook wb) {
		int sheetIndex = wb.getSheetIndex(sheet);
		if (sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int colCount = ws.getRow(0).getLastCellNum();
			return colCount;
		}
	}

	// To retrieve SuiteToRun and CaseToRun flag of test suite and test case.
	public String retrieveToRunFlag(Sheet wsName, XSSFWorkbook wb, String colName, String rowName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName, wb);
			int colNum = retrieveNoOfCols(wsName, wb);
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

				if (Suitecol.getCell(colnameindex(wsName, wb, colName) - 1).getStringCellValue()
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

	// To retrieve DataToRun flag of test data.
	public String[] retrieveToRunFlagTestData(Sheet wsName, XSSFWorkbook wb, String colName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if (sheetIndex == -1)
			return null;
		else {
			int rowNum = retrieveNoOfRows(wsName, wb);
			int colNum = retrieveNoOfCols(wsName, wb);
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
				return null;
			}
			// continue -->

			for (int j = 0; j < rowNum - 1; j++) {
				XSSFRow Row = ws.getRow(j + 1);
				if (Row == null) {
					data[j] = "";
				} else {
					XSSFCell cell = Row.getCell(colNumber);
					if (cell == null) {
						data[j] = "";
					} else {
						// String value = cellToString(cell);
						DataFormatter df = new DataFormatter(Locale.US);
						String value = df.formatCellValue(cell);
						if (value.contains(delimiter)) {
							data[j] = value;
							testsplitdata(value);
						} else {
							data[j] = value;
						}
						// data[j] = value;
					}
				}
			}
			return data;
		}
	}

	// To convert cell data to string
	public String cellToString(XSSFCell cell) {
		CellType type;
		Object result;
		type = cell.getCellTypeEnum();

		switch (type) {
		case NUMERIC:
			result = cell.getNumericCellValue();
			break;

		case STRING:
			result = cell.getStringCellValue();
			break;

		case FORMULA:
			result = cell.getRawValue();
			break;

		case BLANK:
			result = cell.getStringCellValue();
			break;

		case BOOLEAN:
			result = cell.getBooleanCellValue();
			break;

		default:
			throw new RuntimeException("Unsupportd cell.");
		}
		return result.toString();
	}

	// To verify that cell having merged data
	public boolean isMergedRegion(Sheet sheet, int row) {
		boolean flag = false;
		if (getColumnIndexofMultipleDataRows(sheet, row) > 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	// To get the column index of multiple data rows
	public int getColumnIndexofMultipleDataRows(Sheet sheet, int row) {
		int count = 0;
		regionsList = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			regionsList.add(sheet.getMergedRegion(i));
			if (range.containsRow(row)) {
				range.getNumberOfCells();
				if (range.getFirstRow() <= row && range.getLastRow() >= row)
					++count;
			}
		}
		return count;
	}

	// To get the count of merged row
	public int getNbOfMergedRows(Sheet sheet, int row) {
		int count = 0;
		regionsList = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			regionsList.add(sheet.getMergedRegion(i));
			if (range.containsRow(row)) {
				count = range.getNumberOfCells();
			}
		}
		return count;
	}

	// To get the merged region index.
	public int getMergedRegionIndex(Sheet sheet, XSSFWorkbook wb, int row, String testcasenameheader) {
		int i;
		Row r1 = sheet.getRow(row);
		int cn1 = colnameindex(sheet, wb, testcasenameheader);
		Cell address = r1.getCell(cn1);
		regionsList = new ArrayList<CellRangeAddress>();
		for (i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			regionsList.add(sheet.getMergedRegion(i));
			String formattedaddress = sheet.getMergedRegion(i).formatAsString();
			if (formattedaddress.contains(address.getAddress().toString())) {
				break;
			}
		}
		return i;
	}

	/*
	 * public static void mergedDataProcess(Sheet sheet, int row,String
	 * colName,String testcasenameheader) { DataFormatter dataFormatter=new
	 * DataFormatter(); CellRangeAddress range1 =
	 * sheet.getMergedRegion(getMergedRegion(sheet, row, testcasenameheader));
	 * 
	 * int col = colnameindex(sheet, colName); int col1=col;
	 * 
	 * for(int j = range1.getFirstRow(); j <=range1.getLastRow();j++) { XSSFRow Row
	 * = ws.getRow(j); int row1 = Row.getRowNum(); for(int i = col+1;
	 * i<=Row.getLastCellNum();i++) {
	 * if(dataFormatter.formatCellValue(Row.getCell(col)).contains(",")) {
	 * System.out.println(dataFormatter.formatCellValue(Row.getCell(col))); String
	 * str1 = dataFormatter.formatCellValue(Row.getCell(col)); testsplitdata(str1);
	 * System.out.println("Size: "+testsplitdata(str1).size()); //break; } else {
	 * System.out.println(dataFormatter.formatCellValue(Row.getCell(col))); } col++;
	 * } col = col1; } }
	 */

	public String[][] mergedDataProcess(Sheet sheet, XSSFWorkbook wb, int row, String colName,
			String testcasenameheader) {
		int rowNum = retrieveNoOfRows(sheet, wb);
		int colNum = retrieveNoOfCols(sheet, wb);
		String data[][] = new String[rowNum][colNum + 1];
		DataFormatter dataFormatter = new DataFormatter();
		CellRangeAddress range1 = sheet.getMergedRegion(getMergedRegionIndex(sheet, wb, row, testcasenameheader));

		// int col = colnameindex(sheet, colName);
		int col = getColumnIndexofMultipleDataRows(sheet, row);
		int col1 = col;

		for (int i = range1.getFirstRow(); i <= range1.getLastRow(); i++) {
			XSSFRow Row = ws.getRow(i);
			for (int j = col + 1; j <= Row.getLastCellNum(); j++) {
				if (dataFormatter.formatCellValue(Row.getCell(col)).contains(delimiter)) {
					// System.out.println(dataFormatter.formatCellValue(Row.getCell(col)));
					String str1 = dataFormatter.formatCellValue(Row.getCell(col));
					if (str1 == null) {
						data[i][j] = "";
					} else {
						data[i][j] = str1;
					}
					testsplitdata(str1);
					// System.out.println("Size: "+testsplitdata(str1).size());
					// break;
				} else {
					String str2 = dataFormatter.formatCellValue(Row.getCell(col));
					// System.out.println(dataFormatter.formatCellValue(Row.getCell(col)));
					if (str2 == null) {
						data[i][j] = "";
					} else {
						data[i][j] = str2;
					}
				}
				col++;
			}
			col = col1;
		}
		return data;
	}

	//
	public Map testsplitdata(String str) {
		Map<String, String> map = new HashMap<String, String>();
		for (String keyValue : str.split(",")) {
			String[] pairs = keyValue.split("=");
			map.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
		}

		/*
		 * for(String s : map.keySet()) { System.out.println(s + " is " + map.get(s)); }
		 */

		return map;

	}

	// To get the column name index by providing column name and sheet number
	public int colnameindex(Sheet sheet, XSSFWorkbook wb, String colName) {
		int rowNum = retrieveNoOfRows(sheet, wb);
		int colNum = retrieveNoOfCols(sheet, wb);
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

	// To write result In test suite list sheet.
	public boolean writeResult(Sheet wsName, XSSFWorkbook wb, String colName, String rowName, String Result,
			String filelocation) throws IOException {
		FileOutputStream opstr = null;
		try {
			int rowNum = retrieveNoOfRows(wsName, wb);
			int rowNumber = -1;
			int sheetIndex = wb.getSheetIndex(wsName);

			if (sheetIndex == -1)
				return false;

			int colNum = retrieveNoOfCols(wsName, wb);
			int colNumber = -1;

			XSSFRow suiterow = ws.getRow(0);
			for (int i = 0; i < colNum; i++) {
				if (suiterow.getCell(i).getStringCellValue().equals(colName.trim())) {
					colNumber = i;
					break;
				}
			}

			if (colNumber == -1) {
				return false;
			}

			for (int i = 0; i < rowNum - 1; i++) {
				XSSFRow row = ws.getRow(i + 1);
				XSSFCell cell = row.getCell(colnameindex(wsName, wb, colName) - 2);
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
				opstr = new FileOutputStream(filelocation);
				wb.write(opstr);
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
			opstr = new FileOutputStream(filelocation);
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

	// check value of hash map key
	public String checkValueofHashMapkey(String str, String key) {
		Map<String, String> map = new HashMap<String, String>();
		for (String keyValue : str.split(",")) {
			String[] pairs = keyValue.split("=");
			map.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
		}
		return map.get(key);
	}

	//
	public static void main(String arg[]) throws IOException {
		ReadMergedDataExcelFile objReadMergedDataExcelFile = new ReadMergedDataExcelFile();
		File resourcesDirectory = new File(filelocation);
		System.out.println("File Path : " + resourcesDirectory.getAbsolutePath());
		ipstr = new FileInputStream(resourcesDirectory);
		wb = new XSSFWorkbook(ipstr);
		ws = wb.getSheetAt(0);
		testsuite = wb.getSheet("TestSuiteList");
		System.out.println(
				objReadMergedDataExcelFile.retrieveToRunFlag(testsuite, wb, "CaseToRun", "Invalid_User_Login"));
		System.out.println(objReadMergedDataExcelFile.writeResult(testsuite, wb, "Pass/Fail/Skip", "Invalid_User_Login",
				"Skip", filelocation));
		/*
		 * int rn1 =
		 * Arrays.asList(objReadMergedDataExcelFile.retrieveToRunFlagTestData(ws,wb,
		 * testcasenameheader)).indexOf(testcasename);
		 * System.out.println("Row Index of Test Case Name : "+rn1);
		 * 
		 * //System.out.println("Index of Matched Address :"+objReadMergedDataExcelFile.
		 * getMergedRegionIndex(ws,wb, rn1+1, testcasenameheader));
		 * 
		 * if(objReadMergedDataExcelFile.isMergedRegion(ws,rn1+1)) { String data1[][]=
		 * objReadMergedDataExcelFile.mergedDataProcess(ws, wb, rn1+1,
		 * testcasemergedcolumnheader, testcasenameheader); for(int
		 * k=0;k<objReadMergedDataExcelFile.getNbOfMergedRows(ws,rn1+1);k++) {
		 * System.out.println(data1[rn1+1+k][objReadMergedDataExcelFile.colnameindex(ws,
		 * wb, testheadertypesofrelationshipandfiletypes)+1]); String str3 =
		 * data1[rn1+1+k][objReadMergedDataExcelFile.colnameindex(ws, wb,
		 * testheadertypesofrelationshipandfiletypes)+1]; if(str3.contains(delimiter)) {
		 * Map<String, String> m1 = objReadMergedDataExcelFile.testsplitdata(str3);
		 * System.out.println("Key value is " + m1.get("RT1")); for(String s :
		 * m1.keySet()) { System.out.println(s + " is " + m1.get(s)); } } } } else {
		 * System.out.println("There is no record");
		 * //System.out.println((objReadMergedDataExcelFile.retrieveToRunFlagTestData(ws
		 * ,wb, testcasemergedcolumnheader))[rn1].trim());
		 * System.out.println(objReadMergedDataExcelFile.retrieveToRunFlag(testsuite,wb,
		 * "CaseToRun","Invalid_User_Login"));
		 * System.out.println(objReadMergedDataExcelFile.writeResult(testsuite, wb,
		 * "Pass/Fail/Skip", "Invalid_User_Login", "Skip", filelocation)); }
		 */
	}

}
