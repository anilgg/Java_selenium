package Smart_view;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



import jxl.write.WriteException;

public class WriteToWorkbook extends ReadFromWorkbook{
@AfterSuite

                public void output() throws IOException, WriteException{
                copyfromexcel.write();
                copyfromexcel.close();
}
}
