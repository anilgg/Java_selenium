package sprint_overall;


	

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;

	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import jxl.Sheet;
	import jxl.Workbook;
	import jxl.read.biff.BiffException;
	import jxl.write.WritableSheet;
	import jxl.write.WritableWorkbook;
	import jxl.write.WriteException;

	public class filesystem_handles {
		
		public static Sheet sh;
		public static WritableSheet sheet2;
		public static WritableWorkbook copy;
		
	@BeforeSuite
		public void fileess() throws BiffException, IOException,WriteException,FileNotFoundException {
		
		FileInputStream readingbillinfo = new FileInputStream("prototype.xls");
		
		 Workbook w = Workbook.getWorkbook(readingbillinfo);

		  sh = w.getSheet(0);
		 Workbook workbook1 = Workbook.getWorkbook(new File("prototype.xls"));
		  copy = Workbook.createWorkbook(new File("prototypeshop.xls"), workbook1);
		  sheet2 = copy.getSheet(0);
			
		
		     
		     
		} 


	}
		


