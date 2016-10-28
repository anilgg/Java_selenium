/**
 *
 */
package selenium.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Class that provides current configuration values
 *
 */
public class Config {

	private static Properties config;
	private static Properties globalConfig;
	private static String currentClassName;
	private static String currentSuiteName;

	/**
	 * Get property value from configuration file
	 *
	 * @param key
	 *            - property key
	 * @return - property value
	 * @throws Exception
	 */
	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	/**
	 * Returns "https://" or "http://" depending on property in .property file.
	 * @return
	 */
	public static String getProtocol(){
		if (isHTTPS()) {
			return "https://";
		} else {
			return "http://";
		}
	}

	/**
	 * Returns whether test should be run over https.
	 * @return true - in case of https, false - in case of http.
	 */
	public static boolean isHTTPS(){
		String httpsPropVal = getProperty("isHTTPS", "false");
		return (httpsPropVal.equals("true"))? true:false;
	}

	/**
	 * Returns path to reports common folder
	 * @return path to common folder
	 * @throws IOException
	 */
	public static String getCommonFolderPath() {
		try{
			return new File("..").getCanonicalPath() + File.separator + "common";
		}
		catch(IOException e){
			Logger.warning("Could not get common folder path. Original exception: " + e.getMessage());
			return ".." + File.separator + "common";
		}
	}

	/**
	 * Returns path to reports folder for current project 
	 * @return path to reports folder
	 * @throws IOException
	 */
	public static String getReportsFolderPath() {
		try{
			return new File(".").getCanonicalPath() + File.separator + "reports";
		}
		catch(IOException e){
			Logger.warning("Could not get reports folder path. Original exception: " + e.getMessage());
			return "reports";
		}
	}

	/**
	 * Returns path to lib folder.
	 * @return path to lib folder.
	 * @throws IOException
	 */
	public static String getLibFolderPath(){
		return getCommonFolderPath() + File.separator + "lib";
	}

	/**
	 * Get property value from configuration file or its default value
	 *
	 * @param key
	 *            - property key
	 * @param defaultValue
	 *            - value that will be return in case of key is absent into
	 *            property file
	 * @return - property value
	 * @throws Exception
	 */
	public static String getProperty(String key, String defaultValue) {
		String propertyValue = defaultValue;

		readAllProperties();

		if (globalConfig.containsKey(key)) {
			propertyValue = globalConfig.getProperty(key);
		}
		if(config != null){
			if (config.containsKey(key)) {	//	project-specific property overrides global one
				propertyValue = config.getProperty(key);
			}
		}

		return propertyValue;
	}

	public static Properties loadProperties(String path) throws Exception {
		Properties result = new Properties();
		result.load(new FileInputStream(path));
		return result;
	}

	public static void reset()
	{
		globalConfig = null;
		config = null;
	}

	/**
	 * Set/update property value in configuration file.
	 *
	 * @param key - property key
	 * @param value - value that should be set
	 * @param shouldWriteOnDisk -
	 * 		<li>if <b>true</b>, made changes will be written in Global property file alone and not in Project Properties file;
	 *  	<li>if <b>false</b> - property file remains unchanged, only loaded Properties class instance will be updated.
	 * @return - property value
	 */
	public static void setProperty(String key, String value, boolean shouldWriteOnDisk) {
		readAllProperties();

		globalConfig.setProperty(key, value);
		config.setProperty(key, value);

		if (shouldWriteOnDisk) {
			String globalPropertiesDir = getCommonFolderPath() + File.separator + "test.properties";
			if (new File(globalPropertiesDir).exists() == false) {
				Logger.error("Cannot find global test.properties file.");
			}
			try {
				globalConfig.store(new FileOutputStream(globalPropertiesDir), "");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param currentClassName the currentClassName to set
	 */
	public static void setCurrentClassName(String currentClassName) {
		Config.currentClassName = currentClassName;
	}

	public static String getCurrentSuiteName() {
		return currentSuiteName;
	}

	public static void setCurrentSuiteName(String currentSuiteName) {
		Config.currentSuiteName = currentSuiteName;
	}

	public static String getCurrentClassName() {
		return Config.currentClassName;
	}

	/**
	 * Gets the current test name.
	 *
	 * @return the current test name
	 */
	public static String getCurrentTestName() {
		String className = Config.getCurrentClassName();
		String[] classes = className.split("\\.");
		ArrayList<String> classesList = new ArrayList<String>(Arrays.asList(classes));
		return classesList.get(classesList.size() - 1);
	}

	/**
	 * Gets the current suite name.
	 *
	 * @return the current short suite name
	 */
	public static String getCurrentShortSuiteName() {
		String suiteName = Config.getCurrentSuiteName();
		String[] classes = suiteName.split("\\.");
		ArrayList<String> classesList = new ArrayList<String>(Arrays.asList(classes));
		return classesList.get(classesList.size() - 1);
	}

	/**
	 * Gets the current project name 
	 * @return the current project name
	 * @throws IOException
	 */
	public static String getCurrentProjectName() throws IOException {
		/**String className = Config.getCurrentClassName();
		String[] classes = className.split("\\.");
		ArrayList<String> classesList = new ArrayList<String>(Arrays
				.asList(classes));
		return classesList.get(classesList.indexOf("testscripts") - 1);*/
		return new File(".").getCanonicalFile().getName();
	}

	/**
	 * Gets the current requirement name.
	 *
	 * @return the current requirement name
	 */
	public static String getCurrentRequirementName() {
		String className = Config.getCurrentClassName();
		String[] classes = className.split("\\.");
		String result;
		///result=classes[classes.length-1];
		ArrayList<String> classesList = new ArrayList<String>(Arrays
				.asList(classes));
		result = classesList.get(classesList.indexOf("testscripts") + 1);
		for (int i = classesList.indexOf("testscripts") + 2; i < classesList.size()-1 ; ++i) {
			result += File.separator + classesList.get(i);
		}
		return result;
	}

	/**
	 * Gets the current requirement name.
	 *
	 * @return the current requirement name
	 */
	public static String getCurrentSuiteRequirementName() {
		String className = Config.getCurrentSuiteName();
		String[] classes = className.split("\\.");
		ArrayList<String> classesList = new ArrayList<String>(Arrays.asList(classes));
		String result = classesList.get(classesList.indexOf("testscripts") + 1);
		for (int i = classesList.indexOf("testscripts") + 2; i < classesList.size() - 1; ++i) {
			result += File.separator + classesList.get(i);
		}
		return result;
	}

	/**
	 * Read all properties for common (global) and project-specific config.
	 */
	public static void readAllProperties() {
		try {
			File tmpDir = new File(".");
			if (globalConfig == null) {
				String globalPropertiesDir = getCommonFolderPath() + File.separator + "test.properties";
				if (new File(globalPropertiesDir).exists() == false) {
					Logger.error("Cannot find global test.properties file.");
				}
				globalConfig = loadProperties(globalPropertiesDir);
			}

			String projectName = Config.getCurrentProjectName();

			if (config == null && !projectName.equals("common")) {	
				String testPropertiesDir;
				if (tmpDir.getCanonicalPath().contains(projectName)) {
					testPropertiesDir = tmpDir.getCanonicalPath() + File.separator + projectName + ".properties";
				} else {
					testPropertiesDir = tmpDir.getCanonicalPath() + File.separator + projectName + File.separator + projectName + ".properties";
				}
				config = loadProperties(testPropertiesDir);
			}

		} catch (Exception e) {
			Logger.error("Error while reading config: " + e.getMessage());
		}
	}

	/**
	 * Removes the property from common (global) and project-specific config.
	 *
	 * @param key the key
	 */
	public static void removeProperty(String key) {
		readAllProperties();

		globalConfig.remove(key);
		config.remove(key);
	}
}
