package selenium.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.openqa.selenium.os.Kernel32;

import com.sun.jna.Native;

public class TestData {

	public static final String DEFAULT_SEPARATOR = "\\|";

	private Hashtable<String, ArrayList<String>> internalData = null;
	private boolean isTable = false;
	/**
	 * This property defines sheet in excel which stores test data that is common for test suite
	 */
	private static String commonSheet = "Common";

	private TestData(Hashtable<String, ArrayList<String>> internalData, boolean isTable) {
		this.internalData = internalData;
		this.isTable = isTable;
	}

	public String toString() {
		return internalData.toString();
	}

	public static String getCommonSheet() {
		return TestData.commonSheet;
	}

	public static void setCommonSheet(String sheetName) {
		TestData.commonSheet = sheetName;
	}

	public boolean isTable() {
		return isTable;
	}

	public static String getAbsoluteTestDataPath(String fileName) throws IOException {
		File tmpFile = new File(fileName);
		if (!tmpFile.isAbsolute()) {
			File tmpDir = new File("." + File.separator + Config.getCurrentProjectName() + File.separator + "testdata" + File.separator);
			if (!tmpDir.exists()) {
				tmpDir = new File("." + File.separator + "testdata" + File.separator);
			}
			fileName = tmpDir.getCanonicalPath() + File.separator + fileName;
		}
		return fileName;
	}

	public static String getShortPath(String filePath) throws Exception {
		char[] shortt = new char[256];
		int result = Kernel32.INSTANCE.GetShortPathName(filePath, shortt, 256);
		if(result == 0){
			throw new Exception("Failed converting path " + filePath + " to short path. Most likely this path is invalid.");
		}
        return Native.toString(shortt);
	}

	public static TestData getTestData() {
		return getTestData("TestData");
	}

	public static TestData getEmptyTestData() {
		return new TestData(new Hashtable<String, ArrayList<String>>(), false);
	}

	public static TestData getTestData(String tableName) {
		return getTestData(Config.getCurrentRequirementName() + ".xls", tableName);
	}

	public static TestData getTestData(String fileName, String tableName) {
		Hashtable<String, ArrayList<String>> tmpInternalData = getInternalTestDataTable(fileName, tableName, false);
		return new TestData(tmpInternalData, false);
	}

	public static TestData getTestDataTable(String tableName) {
		return getTestDataTable(Config.getCurrentRequirementName() + ".xls", tableName);
	}
	public static TestData getTestDataTable(String fileName, String tableName) {
		Hashtable<String, ArrayList<String>> tmpInternalData = getInternalTestDataTable(fileName, tableName, true);
		return new TestData(tmpInternalData, true);
	}

	private static Hashtable<String, ArrayList<String>> getInternalTestDataTable(String fileName, String tableName, boolean asTable) {
		Hashtable<String, ArrayList<String>> localData = getInternalTestDataTableOnSheet(fileName, Config.getCurrentTestName(), tableName, asTable);
		Hashtable<String, ArrayList<String>> commonData = getInternalTestDataTableOnSheet(fileName, getCommonSheet(), tableName, asTable);
		Hashtable<String, ArrayList<String>> result = null;
		if (asTable) {
			result = CustomListener.getBatchMode() ? commonData : localData;
			if (result.isEmpty()) {
				result = CustomListener.getBatchMode() ? localData : commonData;
			}
		} else {
			result = CustomListener.getBatchMode() ? localData : commonData;
			result.putAll(CustomListener.getBatchMode() ? commonData : localData);
		}
		if (result.isEmpty()) {
			Logger.error("Failed to read test data: " + tableName + ", " + fileName);
		}
		return result;
	}

	private static Hashtable<String, ArrayList<String>> getInternalTestDataTableOnSheet(String fileName, String sheetName, String tableName, boolean asTable) {
		Hashtable<String, ArrayList<String>> result = new Hashtable<String, ArrayList<String>>();
		InputStream testDataExel = null;
		try {
			testDataExel = new FileInputStream(getAbsoluteTestDataPath(fileName));
			Workbook wb = new HSSFWorkbook(testDataExel);
			Name namedRange = getNamedRangeForSheet(wb, sheetName, tableName);
			if (namedRange != null) {
				if (asTable) {
					result = getAsTable(wb, namedRange);
				} else {
					result = getNotAsTable(wb, namedRange);
				}
			}
		} catch (Exception e) {
			Logger.debug("Exception while reading test data: " + e.getMessage());
		} finally {
			if (testDataExel != null) {
				try {
					testDataExel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	private static Hashtable<String, ArrayList<String>> getNotAsTable(Workbook wb, Name namedRange) {
		Hashtable<String, ArrayList<String>> result = new Hashtable<String, ArrayList<String>>();
		AreaReference areaRef = new AreaReference(namedRange.getRefersToFormula());
		CellReference[] cellRef = areaRef.getAllReferencedCells();
		int firstRow = cellRef[0].getRow();
		short keyColumnIndex = cellRef[0].getCol();
		short valueColumnIndex = (short) (keyColumnIndex + 1);

		int currentRow = firstRow + 1;
		Sheet currentSheet = wb.getSheet(cellRef[0].getSheetName());
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (int j = 0; j < cellRef.length; j++) {
			int actualRow = cellRef[j].getRow();
			if (actualRow == currentRow) {
				short actualColumn = cellRef[j].getCol();
				if (actualColumn == keyColumnIndex) {
					Row row = currentSheet.getRow(actualRow);
					if (row != null) {
						Cell keyCell = row.getCell(keyColumnIndex);
						Cell valueCell = row.getCell(valueColumnIndex);

						String keyCellValue = getCellValue(keyCell, evaluator);
						String valueCellValue = getCellValue(valueCell, evaluator);
						if (!keyCellValue.equals("")) {
							ArrayList<String> value = new ArrayList<String>();
							value.add(valueCellValue);
							result.put(keyCellValue, value);
						}
					}
					currentRow++;
				}
			}
		}
		return result;
	}

	private static Hashtable<String, ArrayList<String>> getAsTable(Workbook wb, Name namedRange) {
		Hashtable<String, ArrayList<String>> result = new Hashtable<String, ArrayList<String>>();

		AreaReference areaRef = new AreaReference(namedRange.getRefersToFormula());
		CellReference[] cellRef = areaRef.getAllReferencedCells();
		Sheet currentSheet = wb.getSheet(cellRef[0].getSheetName());
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		int firstRow = cellRef[0].getRow();

		Hashtable<Short, String> headers = new Hashtable<Short, String>();

		int currentRow = firstRow;
		short i = 0;
		while (currentRow == firstRow) {
			Row row = currentSheet.getRow(currentRow);
			Cell cell = row.getCell(cellRef[i].getCol());
			headers.put(cellRef[i].getCol(), getCellValue(cell, evaluator));
			result.put(getCellValue(cell, evaluator), new ArrayList<String>());
			i++;
			currentRow = cellRef[i].getRow();
		}

		currentRow = -1;
		for (int j = 0; j < cellRef.length; j++) {
			int actualRow = cellRef[j].getRow();
			if (actualRow > firstRow) {
				Row row = currentSheet.getRow(actualRow);
				if (row != null) {
					Short colIndex = cellRef[j].getCol();
					Cell cell = row.getCell(colIndex);
					String cellValue = getCellValue(cell, evaluator);
					String colHeader = headers.get(colIndex);
					result.get(colHeader).add(cellValue);
				}
			}
		}
		return result;
	}

	private static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
		return XLSParser.getCellValue(cell, evaluator);
	}

	private static Name getNamedRangeForSheet(Workbook wb, String sheetName, String tableName) {
		int nameRangesCount = wb.getNumberOfNames();
		Name tempNameRange;
		Name resultNameRange = null;
		String tempLabel;
		String tempSheetName;
		for (int i = 0; i < nameRangesCount; i++) {
			tempNameRange = wb.getNameAt(i);
			tempSheetName = tempNameRange.getSheetName();
			if (tempSheetName.equals(sheetName)) {
				tempLabel = tempNameRange.getNameName();
				if (tempLabel.equals(tableName)) {
					resultNameRange = tempNameRange;
					break;
				}
			}
		}
		return resultNameRange;
	}

	public boolean containsKey(String key) throws Exception {
		if (isTable)
			throw new Exception("Trying to get key-value from table data");

		return internalData.containsKey(key);
	}

	public int size() {
		if (isTable)
			return internalData.elements().nextElement().size();
		else
			return internalData.size();
	}

	public String get(String key) throws Exception {
		if (isTable)
			throw new Exception("Trying to get key-value from table data");

		try {
			return internalData.get(key).get(0);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + " on retrieving data by key " + key + ".");
		}
	}

	public String get(String columnName, int rowIndex) throws Exception {
		if (!isTable)
			throw new Exception("Trying to get table data from key-value");

		try {
			return internalData.get(columnName).get(rowIndex);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + " on retrieving data from column " + columnName + " and row #" + rowIndex + ".");
		}
	}

	public ArrayList<String> getAsArray(String key) throws Exception {
		return getAsArray(key, DEFAULT_SEPARATOR);
	}


	public ArrayList<String> getAsArray(String key, String separator) throws Exception {
		ArrayList<String> dataArray;
		try {
			if (isTable) {
				dataArray = extract().get(key);
			} else {
				dataArray = getArrayByKey(key, separator);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage() + " on retrieving data as array by key " + key + ".");
		}
		return dataArray;
	}


	private ArrayList<String> getArrayByKey(String key, String separator) throws Exception {
		List<String> array = Arrays.asList(this.get(key).split(separator, -1));
		if (array.size() == 1 && array.get(0).trim().length() == 0) {
			return new ArrayList<String>();
		} else {
			return new ArrayList<String>(array);
		}
	}

	
	public ArrayList<String> getAsArray(String key, int rowIndex) throws Exception {
		return getAsArray(key, rowIndex, DEFAULT_SEPARATOR);
	}

	public ArrayList<String> getAsArray(String key, int rowIndex, String separator) throws Exception {
		ArrayList<String> dataArray = new ArrayList<String>(Arrays.asList(this.get(key, rowIndex).split(separator, -1)));
		return dataArray;
	}

	private Hashtable<String, ArrayList<String>> extract() {
		return internalData;
	}


	public void append(TestData data) {
		internalData.putAll(data.extract());
	}

	public void append(String key, String value) {
		ArrayList<String> tmp = new ArrayList<String>();
		Hashtable<String, ArrayList<String>> rawData = new Hashtable<String, ArrayList<String>>();
		tmp.add(value);
		rawData.put(key, tmp);
		TestData dataToAppend = TestData.loadTestData(rawData);
		internalData.putAll(dataToAppend.extract());
	}

	public static TestData loadTestData(Hashtable<String, ArrayList<String>> rawData) {
		return new TestData(rawData, false);
	}

	public static TestData loadTestData(HashMap<String, String> hashData) {
		Hashtable<String, ArrayList<String>> rawData = new Hashtable<String, ArrayList<String>>();
		for (Map.Entry<String, String> entry : hashData.entrySet()) {
			rawData.put(entry.getKey(), new ArrayList<String>(Arrays.asList(entry.getValue())));
		}
		return TestData.loadTestData(rawData);
	}

	public static TestData loadTestDataTable(Hashtable<String, ArrayList<String>> rawData) {
		return new TestData(rawData, true);
	}

	public boolean equals(TestData expected) throws Exception {
		boolean isEqual = true;
		String key;

		if (isEqual == true && this.size() != expected.size()) {
			isEqual = false;
		}

		if (isEqual == true && this.isTable != expected.isTable) {
			isEqual = false;
		}

		if (isEqual == true && this.isTable) {
			for (Enumeration<String> e = internalData.keys(); e.hasMoreElements();) {
				key = e.nextElement();
				if (expected.containsKey(key) != true) {
					isEqual = false;
					break;
				}
				for (int i = 0; i < this.size(); i++) {
					if (this.get(key, i) != expected.get(key, i)) {
						isEqual = false;
						break;
					}
				}
			}
		} else {
			for (Enumeration<String> e = internalData.keys(); e.hasMoreElements();) {
				key = e.nextElement();
				if (expected.containsKey(key) != true || !this.get(key).equals(expected.get(key))) {
					isEqual = false;
					break;
				}
			}
		}

		return isEqual;
	}

	public boolean equals(Object expected) {
		try {
			TestData expectedTable = (TestData) expected;
			return equals(expectedTable);
		} catch (Exception e) {
		}
		return false;
	}

	public ArrayList<String> getKeyNames() throws Exception {
		ArrayList<String> keyNames = new ArrayList<String>();
		for (Enumeration<String> e = internalData.keys(); e.hasMoreElements();) {
			keyNames.add(e.nextElement().toString());
		}
		return keyNames;
	}

	public TestData slice(String... keys) {
		Hashtable<String, ArrayList<String>> rawResultData = new Hashtable<String, ArrayList<String>>(keys.length);
		for (String key : keys) {
			if (this.internalData.containsKey(key)) {
				ArrayList<String> value = new ArrayList<String>(1);
				for (String subvalue : this.internalData.get(key)) {
					value.add(new String(subvalue));
				}
				rawResultData.put(key, value);
			}
		}
		return new TestData(rawResultData, this.isTable);
	}

	public Map<String,String> getSubsetAsMap(String... keys) {
		HashMap<String, String> retMap = new HashMap<String,String>(keys.length);
		String value=null;
		for (String key : keys) {
			if (this.internalData.containsKey(key)) {
				value = this.internalData.get(key).get(0);
			}
			retMap.put(key, value);
		}
		return retMap;
	}

	public boolean getAsBoolean(String key) throws Exception {
		return Boolean.parseBoolean(get(key).trim());
	}

	public int getAsInteger(String key) throws Exception {
		return Integer.parseInt(get(key).trim());
	}

	public ArrayList<String> getTableColumnData(String columnName) throws Exception {

		if (!isTable)
			throw new Exception("Trying to get table data from key-value");

		ArrayList<String> result = new ArrayList<String>();
		for (int row = 0; row < size(); row++) {
			result.add(get(columnName, row));
		}
		return result;
	}
}
