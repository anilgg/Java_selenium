package selenium.core;

import static java.io.File.separator;

import java.lang.annotation.Annotation;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Parameterized;

import selenium.core.utils.MetaData;

public class CustomTestRunner extends Parameterized {
	private Class<?> cls;
	private static boolean skipCurrentTest = false;
	
	public CustomTestRunner(Class<?> arg0) throws Throwable {
		super(arg0);
		cls = arg0;
		Logger.setPropertyConfigurator(Config.getCommonFolderPath() + separator + "log4j.properties");
	}
	
	private boolean isLevelMatching(String testRunLevel, int testCaseLevel){
		String [] testRunLevels;
		int tmpLevel;
		
		if(testRunLevel == null || testRunLevel.isEmpty()){
			return true;	
		}
		else{
			testRunLevels = testRunLevel.split(",");
			for(int i = 0; i < testRunLevels.length; i++){
				try{
					tmpLevel = Integer.parseInt(testRunLevels[i]);
				}
				catch(NumberFormatException e){
					Logger.warning("One or more non-integer values were specified in 'level' property: " + testRunLevel
							+ ". Test run level will be ignored.");
					return true;
				}
				if(tmpLevel < 0) return true;	//	if at least one entry is negative, treat level as matching 
				if(tmpLevel == testCaseLevel) return true;	//	found matching level
			}
		}
		return false;	//	if we are here, no matching level was found
	}
	
	public void run(RunNotifier notifier) {
		int testCaseLevel = -1;
		boolean run = true;
		CustomTestRunner.skipCurrentTest = false;
		
		String tmp = System.getProperty("level");
		
		Annotation a = cls.getAnnotation(MetaData.class);
		if(a == null){
			//	run test ONLY if testRunLevel is undefined (<0)
			run = isLevelMatching(tmp, -1);
		}
		else{
			CustomTestRunner.skipCurrentTest = ((MetaData)a).skip();
			testCaseLevel = ((MetaData)a).level();
			//	if testRunLevel is defined (>=0), run test only if testCaseLevel equals testRunLevel
			run = isLevelMatching(tmp, testCaseLevel);
		}
		if(run){
			CustomListener.createAndAddToNotifier(notifier);
			super.run(notifier);
		}
		else{
			Logger.warning("Test " + cls.getCanonicalName() + " was skipped: " +
					"test run level = " + tmp + 
					"; test case level = " + ((testCaseLevel < 0) ? "undefined" : testCaseLevel));
		}
	}
	
	public static boolean isSkipCurrentTest(){
		return skipCurrentTest;
	}
}