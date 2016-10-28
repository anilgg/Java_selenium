
package sprint_overall;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import jxl.write.WriteException;

 public class Workbooksheet_close extends Workbooksheet{
@AfterSuite

	public void output() throws IOException, WriteException{
	copyfromexcel.write();
	copyfromexcel.close();

}
}