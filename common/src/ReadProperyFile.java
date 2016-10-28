import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;



public class ReadProperyFile {
	
	public static File file = new File("H:/Workspace/common/test.properties");
	
	public static File file1 = new File("H:/Workspace/smartview/testdata/sample.xlsx");
	
	public static String getProperty(String key) throws IOException{
		
		FileInputStream fis = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fis);
		properties.clone();
		System.out.println(properties.getProperty(key));
		return properties.getProperty(key);
	}
}