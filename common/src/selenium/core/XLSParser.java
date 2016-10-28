package selenium.core;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;

public class XLSParser {
	
	public static LinkedHashMap<String, ArrayList<String>> getTableWithHeader(HSSFCell headerFirstCell, int lastCellNum, int lastRowNum){
		LinkedHashMap<String, ArrayList<String>> table = new LinkedHashMap<String, ArrayList<String>>();
		HSSFSheet sheet = headerFirstCell.getSheet();
		HSSFRow headerRow = headerFirstCell.getRow();
		for (int i = headerFirstCell.getColumnIndex(); i <= lastCellNum; i++){
			String columnName = getCellValue(headerRow.getCell(i));
			HSSFRow rowAfterHeader = sheet.getRow(headerRow.getRowNum()+1);
			table.put(columnName, getColumnValueList(sheet, i, rowAfterHeader.getRowNum(), lastRowNum));
		}
		return table;
	}
	
	public static ArrayList<String> getRowValueList(HSSFRow row, int firstCellNum, int lastCellNum){
		ArrayList<String> rowValueList = new ArrayList<String>();
		for (int i = firstCellNum; i <= lastCellNum; i++){
			rowValueList.add(getCellValue(row.getCell(i)).trim());
		}
		return rowValueList;
	}
	
	public static ArrayList<String> getColumnValueList(HSSFSheet sheet, int columnIndex, int firstRowNum, int lastRowNum){
		ArrayList<String> collValueList = new ArrayList<String>();
		for (int i = firstRowNum; i <= lastRowNum; i++){			
			HSSFCell cell = sheet.getRow(i).getCell(columnIndex);			
			collValueList.add(getCellValue(cell).trim());
		}
		return collValueList;
	}
	
	public static String getCellValue(HSSFCell cell){
		if (cell != null){
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			Cell evaluatedCell = evaluator.evaluateInCell(cell);
			switch (evaluatedCell.getCellType()) {
			    case Cell.CELL_TYPE_BOOLEAN:
			        return String.valueOf(evaluatedCell.getBooleanCellValue());
			    case Cell.CELL_TYPE_NUMERIC:
			    	if(DateUtil.isCellDateFormatted(cell)) {
			            return cell.getDateCellValue().toString();
			          } else {
			            return String.valueOf(cell.getNumericCellValue());
			          }
			    case Cell.CELL_TYPE_STRING:
			        return evaluatedCell.getStringCellValue();
			    case Cell.CELL_TYPE_BLANK:
			        return "";
			    case Cell.CELL_TYPE_FORMULA: 
					return cell.getCellFormula().toString();
			}
		}
		return "";
	}
	
	public static String getCellValue(Cell cell,Workbook wb){
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		return getCellValue(cell, evaluator);
	}
	
	public static String getCellValue(Cell cell,FormulaEvaluator evaluator){
		if (cell == null) {
			return "";
		}
		
		int evaluatedCellType;
		int cellType = cell.getCellType();
		if (Cell.CELL_TYPE_FORMULA == cellType){
			evaluatedCellType = evaluator.evaluateFormulaCell(cell);
		}else {
			evaluatedCellType = cellType;
		}
		switch (evaluatedCellType) {
		case Cell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().toString();

		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else{
				//return String.valueOf(cell.getNumericCellValue());
				return new HSSFDataFormatter().formatCellValue(cell,evaluator);
			}
		
		case Cell.CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
			
		case Cell.CELL_TYPE_FORMULA: 
			return cell.getCellFormula().toString();
		
		case Cell.CELL_TYPE_BLANK:
			return "";
		default:
			return "";
		}
	}
	
	public static HSSFName getNameRange(HSSFWorkbook wb, String sheetName, String nameRange){
		for (int i=0; i< wb.getNumberOfNames(); i++){
			HSSFName currentName = wb.getNameAt(i);
			if (nameRange.equals(currentName.getNameName()) && sheetName.equals(currentName.getSheetName())){
				return currentName;
			}
		}
		return null;
	}
	
	public static HSSFRow getFirstRowInRange(HSSFSheet sheet, AreaReference areaRef){
		return sheet.getRow(areaRef.getFirstCell().getRow());
	}
	
	public static HSSFRow getLastRowInRange(HSSFSheet sheet, AreaReference areaRef){
		HSSFRow row = null;
		int lastRowNum = areaRef.getLastCell().getRow();
		while (row == null){
			row = sheet.getRow(lastRowNum);
			lastRowNum--;
		}
		return row;
	}
	
	public static HSSFCell getLastCellInRange(HSSFSheet sheet, AreaReference areaRef){
		return getLastRowInRange(sheet, areaRef).getCell((int) areaRef.getLastCell().getCol());
	}
	
	public static HSSFCell getFirstRowCellInRange(HSSFRow rowInRange, AreaReference areaRef){
		return rowInRange.getCell((int) areaRef.getFirstCell().getCol());
	}
		
	public static Name getNamedRangeForSheet(Workbook wb,String sheetName,String tableName){
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
		if (resultNameRange == null) {
			throw new NullPointerException("Named range '"+tableName+"' doesn't exist in sheet '"+sheetName+"'");
		}
		return resultNameRange;
	}

}
