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

	public class Workbooksheet {
		
		public static Sheet oldexcel;
		public static WritableSheet newexcel;
		public static WritableWorkbook copyfromexcel;
		
	@BeforeSuite
		public void fileess() throws BiffException, IOException,WriteException,FileNotFoundException {
		
		FileInputStream readingbillinfo = new FileInputStream("prototyperead.xls");
		
		 Workbook w = Workbook.getWorkbook(readingbillinfo);

		  oldexcel = w.getSheet(0);
		 Workbook workbook1 = Workbook.getWorkbook(new File("prototyperead.xls"));
		  copyfromexcel = Workbook.createWorkbook(new File("prototypewrite11.xls"), workbook1);
		  newexcel = copyfromexcel.getSheet(0);
			
		
		     
		     
		} 


	}
		


