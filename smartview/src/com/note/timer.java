package com.note;


import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import jxl.write.DateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class timer {
	
	public static String sName = "TCResult";
	
	public static ArrayList<String> startList = new ArrayList<String>();
	public static ArrayList<String> endList = new ArrayList<String>();
	public static ArrayList<String> elapseList =  new ArrayList<String>();
//	public static ArrayList<ArrayList<String>,ArrayList<String>,ArrayList<String>> last = new ArrayList<>(startList,endList,elapseList);

	public static void main(String[] args) throws Exception {
		String spath = "C:/Users/anigan/Downloads/AnilAnnaProject_"+dateTimePicker()+".xls";
		for(int i=0;i<25;i++){
			
			WebDriver driver = new FirefoxDriver();
			driver.get("https://foundationia.alpha.live.qa.oceanwidebridge.com/Foundation.IAS.WebClient/Default.aspx");
			String startTime = dateTimePicker();
			startList.add(startTime);
			System.out.println("Start time is: "+ startTime );
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='dvContent_4']/div[2]/table/tbody/tr[1]/td[3]")));
			String endTime = dateTimePicker();
			endList.add(endTime);
			System.out.println("End time is: "+ endTime );
			String total = String.valueOf(Long.parseLong(endTime) - Long.parseLong(startTime));
			elapseList.add(total);
			//		Double totalTime = (double) (Long.parseLong(endTime) - Long.parseLong(startTime));
			//		String total = String.valueOf(totalTime);
			System.out.println("Total Page Load Time: " + total+ "	seconds");
			driver.close();
		}	
			writeXL(spath, sName, startList, endList,elapseList);
			
		
	}

	public static String dateTimePicker()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		Date date = new Date();
		String date1= dateFormat.format(date);
		return date1;
	}
	public static void writeXL(String sPath, String iSheet, ArrayList<String> start,ArrayList<String> end,ArrayList<String>  elapse)
			throws Exception{
			    File outFile = new File(sPath);
			       HSSFWorkbook wb = new HSSFWorkbook();
			       HSSFSheet osheet = wb.createSheet(iSheet);
//			       int xR_TS = xData.length;
//			       int xC_TS = xData[0].length;
			    for (int myrow = 0; myrow < start.size(); myrow++) {
			       HSSFRow row = osheet.createRow(myrow);
			       for (int mycol = 0; mycol < 3; mycol++) {
			        HSSFCell cell = row.createCell(mycol);
			        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			        if(myrow==0){
			        	if(mycol==0) cell.setCellValue("Start Time");
				        if(mycol==1) cell.setCellValue("End Time");
				        if(mycol==2) cell.setCellValue("elapse Time");
			        }
			        if(mycol==0) cell.setCellValue(start.get(myrow));
			        if(mycol==1) cell.setCellValue(end.get(myrow));
			        if(mycol==2) cell.setCellValue(elapse.get(myrow));
			       }
			       FileOutputStream fOut = new FileOutputStream(outFile);
			       wb.write(fOut);
			       fOut.flush();
			       fOut.close();
			    }
			}


}


