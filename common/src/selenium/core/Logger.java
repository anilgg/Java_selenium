
package selenium.core;

import static selenium.core.Config.getProperty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.notification.Failure;
import selenium.core.CustomListener;


public class Logger {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(Logger.class);
	private static String strPrevScriptName="";
	private static int intLogSNO=0;
	private static String currentStep = "";
	private static boolean isError = false;
	private static String strPrevStepTime="00:00:00.000";
	public final static String EXT_MP4 = ".mp4";
	public final static String EXT_MOV = ".mov";
	public final static String EXT_VTT = ".vtt";
	
	private final static long secondInMillis = 1000;
	private final static long minuteInMillis = secondInMillis * 60;
	private final static long hourInMillis = minuteInMillis * 60;
	private static long startTimeMillis=0;

	public static boolean isError() {
		return isError;
	}
	
	public static void reset()	{
		currentStep = "";
		isError = false;
	}
	
	public static void stepInfo(String stepName) {
		if (!currentStep.equals("")) {
			info("STEP PASSED: " + currentStep);
			logReportToFile(Config.getCurrentClassName(),currentStep.trim(),1);
			if (Boolean.parseBoolean(getProperty("enableVideoRecording", "false")) && (Config.getCurrentSuiteName() == null))
			{
				logReportToVTTFile(Config.getCurrentClassName(),currentStep.trim(),1);
			}
			else if (Boolean.parseBoolean(getProperty("enableVideoRecording", "false")) && (Config.getCurrentSuiteName() != null))
			{
				logReportToVTTFile(Config.getCurrentSuiteName(),currentStep.trim(),1);
			}
		}
		currentStep = stepName;
	}
	
	public static void stepFailed(Failure failure, String screenShotsPlainString, String screenShotsHtmlString) {
		String stepError;
		String mappedBugs;
		String stepStringStatus = "FAILED";
		//	SG: remove all chained spaces
		String correctedMessage;

		correctedMessage = failure.getMessage();
		
		if(correctedMessage == null){
			correctedMessage = "Message is undefined";
		}
		correctedMessage = correctedMessage.replaceAll(" +", " ");
		
		int stepIntStatus = 0;
		mappedBugs = "";
			
		stepError = currentStep.trim() + "; failure message: " + correctedMessage + " " + mappedBugs;
		info("STEP " + stepStringStatus + ": " + stepError + "; " + screenShotsHtmlString);
		logReportToFile(Config.getCurrentClassName(), stepError + screenShotsPlainString, stepIntStatus);
		reset();
	}

	/**
	 * Set the Log4j configuration
	 * 
	 * @param propertiesFilePath
	 *       
	 * 
	 */
	public static void setPropertyConfigurator(String propertiesFilePath){
		PropertyConfigurator.configure(propertiesFilePath);
	}
	
	
	public static void setHtmlAppender(String htmlFilePath) throws Exception{
		FileAppender fileappender = new FileAppender(new HTMLLayout(),htmlFilePath);
		log.addAppender(fileappender);
	}
	
	
	public static void stepSuccess(String stepName, String stepMessage) {
		log.info(stepName + " - " + stepMessage);
	}

	
	public static void debug(String message) {
		log.debug(message);
	}

		public static void info(String message) {
		log.info(message);
	}

		public static void warning(String message) {
		log.warn(message);
	}
	
	public static void warning(Exception e) {
		log.warn(e.getMessage(),e);
	}

	public static void error(String message) {
		log.error(message);
		isError = true;
	}
	
		public static void error(Exception error) {
		log.error(error.getMessage(),error);
		isError = true;
	}
	
	
	public static void error(UnsatisfiedLinkError error) {
		log.error(error.getMessage(),error);
		isError = true;
	}
	
	public static void logReportToFile(String scriptName, String stepName, int intStatus){
		String strStatus="";
		
		switch(intStatus){
		case 0:
			strStatus = "Failed";
			break;
		case 1:
			strStatus = "Passed";
			break;
		case 2:
			strStatus="Warning";
			break;
		case 3:
			strStatus="Failure mapped";
			break;
		default:
			strStatus="Undefined";
		}
	 
	    //Appending log to the report
	    stepName = "\"" + stepName + "\""; 
	 
	    try{
    		File tmpDir=new File(Config.getReportsFolderPath());
    		if (!tmpDir.exists()) {
    			tmpDir.mkdir();
    		}
    		String logFilePath;
    		logFilePath=tmpDir.getCanonicalPath() + File.separator + "Resultlogs.txt";
    		
    		File file = new File(logFilePath);
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
            FileInputStream inputStream = new FileInputStream(logFilePath);
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String FileContent=br.readLine();
            in.close();
            br.close();
            inputStream.close();
            
            FileWriter fstream = new FileWriter(logFilePath,true);
            BufferedWriter out = new BufferedWriter(fstream);
            
            if (FileContent==null){
            	out.write("");
                out.write("\t" + "Automation Test Suite Log" + "\t" + "\n");
            }
            if(!scriptName.equals(strPrevScriptName)){
            	if(strPrevScriptName.length()!=0){
            		out.write("End Time: " + "\t" + Calendar.getInstance().getTime() );
            		out.write("\n");
            	}
            	InetAddress addr = InetAddress.getLocalHost();
				out.write("\n" + "Test Case Name: " + "\t" + scriptName.split("\\.")[scriptName.split("\\.").length - 1] + "\n");
				String strOSInfo=addr.getHostName() + " " + System.getProperty("os.name") + " " + System.getProperty("os.version");
				out.write("Environment Name: " + "\t" + "\"" + strOSInfo + "\"" + "\n");
				out.write("Start Time: " + "\t" + Calendar.getInstance().getTime() + "\n");
				out.write("");
				out.write("S.No" + "\t" + "Step Name" + "\t" + "Status" + "\t" +  "Date/Time" + "\n");
				Logger.strPrevScriptName = scriptName;
				Logger.intLogSNO=1;
            }
            
            out.write(Logger.intLogSNO + "\t" + stepName + "\t" + strStatus + "\t" + Calendar.getInstance().getTime()+ "\n"); 
    		Logger.intLogSNO=Logger.intLogSNO + 1;
            out.close();
            fstream.close();
	    }
	    catch (Exception e){
	          Logger.error("Error in logReportToFile: " + e.getMessage());
	    }
	
	}
	 
	
	public static void logReportToVTTFile(String scriptName, String stepName, int intStatus){	 
	 
	    try{
    		File tmpDir=new File(Config.getReportsFolderPath());
    		if (!tmpDir.exists()) {
    			tmpDir.mkdir();
    		}
    		String logFilePath;
    		String logFileFolder;

    	
    		
    		long currTimeMillis;
    		
    		long diffInMillis;
    		long diffInHours;
    		long diffInMins;
    		long diffInSecs;

    		File currentDir= new File("");
    	
if (Config.getCurrentSuiteName() != null)
	
{ 
    		currentDir = new File(Config.getReportsFolderPath() + File.separator + "videos" + File.separator  + Config.getCurrentSuiteRequirementName() + File.separator + Config.getCurrentShortSuiteName()); 
}
else
{
	        currentDir = new File(Config.getReportsFolderPath() + File.separator + "videos" + File.separator + Config.getCurrentRequirementName() + File.separator + CustomListener.getShortClassName()); 
}

    		logFileFolder = currentDir.getCanonicalPath() + File.separator;
    		
    		logFilePath = logFileFolder + "logs";
    				//+ CustomListener.getMonteFileName().replace(EXT_MOV, EXT_VTT);
    		 
    		File vttFile = new File(logFilePath);

    		if(startTimeMillis==0){
    			vttFile.createNewFile();
    			//get the absolute start time of the script

    			startTimeMillis = Calendar.getInstance().getTimeInMillis();

    		}
    		
            FileInputStream inputStream = new FileInputStream(logFilePath);
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String FileContent=br.readLine();
            in.close();
            br.close();
            inputStream.close();
            
            FileWriter fstream = new FileWriter(logFilePath,true);
            BufferedWriter out = new BufferedWriter(fstream);
            
            if (FileContent==null){
                out.write("WEBVTT"  + "\r\n");
            }
            

             currTimeMillis = Calendar.getInstance().getTimeInMillis();

           

             diffInMillis =  (currTimeMillis - startTimeMillis);
             
             diffInHours = (diffInMillis / hourInMillis);
             if (diffInHours != 0)
             {diffInMillis = diffInMillis % (((int)(diffInHours))*hourInMillis);}
             diffInMins =  (diffInMillis / minuteInMillis);
             if (diffInMins != 0)
             {diffInMillis = diffInMillis % (((int)(diffInMins))*minuteInMillis);}
             diffInSecs =  (diffInMillis / secondInMillis);
             if (diffInSecs != 0)
             {diffInMillis = diffInMillis % (((int)(diffInSecs))*secondInMillis);}

             String formattedHours = String.format("%02d",diffInHours);
             String formattedMins = String.format("%02d",diffInMins);
             String formattedSecs = String.format("%02d",diffInSecs);
             
             String formattedMillis = String.format("%03d",diffInMillis);
             
             String formattedCurrTime = formattedHours + ":" + formattedMins + ":" + formattedSecs + "." + formattedMillis;
                         
             out.write(strPrevStepTime + " --> " + formattedCurrTime + "\r\n");
             out.write( stepName + "\r\n" + "\r\n");
            
             Logger.strPrevStepTime = formattedCurrTime;
            Logger.intLogSNO=Logger.intLogSNO + 1;
            out.close();
            fstream.close();
	    }
	    catch (Exception e){
	          Logger.error("Error in logReportToVTTFile: " + e.getMessage());
	    }    
	
	}
	
	  public static void vtt(String stepName) {
			if (!currentStep.equals("")) {
				info("STEP PASSED: " + currentStep);
				logReportToVTTFile(Config.getCurrentClassName(),currentStep.trim(),1);
			}
			currentStep = stepName;
		}	
	

	
}
