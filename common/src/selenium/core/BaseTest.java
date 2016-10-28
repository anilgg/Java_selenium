package selenium.core;

import static selenium.core.Config.getProperty;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

//import selenium.controls.webui.core.FrameManager;

/**
 * Basic class for all test cases
 * 
 */
@RunWith(CustomTestRunner.class)
public class BaseTest {

	protected TestData data = null;
	private static BaseTest currentTest = null;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { {} });
	}

	public BaseTest() {
		BaseTest.currentTest = this;
		Logger.reset();
		Config.setCurrentClassName(getClass().getCanonicalName());
		Assume.assumeTrue(!CustomListener.testSkipped(this.getClass().getSimpleName()));
		data = TestData.getTestData();
	}

	/**
	 * Before class. Contains initialization procedure for Selenium start. <br>
	 * Depending if batchMode flag it could be start new browse instance or not.
	 */
	@BeforeClass
	public static void beforeClass() throws Exception {
		if (!CustomListener.getBatchMode() && CustomListener.isSuitePreconditionsPasses()) {
			BaseTest.initRun();
		}
	}

	/**
	 * Starts selenium browser
	 */
	public static void initRun() throws Exception {
		if (!CustomTestRunner.isSkipCurrentTest()) {
			Selenium.start();
		}

	}

	/**
	 * Finish run. Stops selenium browser
	 */
	public static void finishRun() throws Exception {
		Selenium.stop();
//		if (sessionExpirationThread.isAlive()) {
//			sessionExpirationThread.interrupt();
//		}
	}
	

	/**
	 * Preconditions. This method could be overridden if some preconditions are required for test case.
	 */
	public void preconditions() throws Exception {
	}

	/**
	 * Cleanup. This method could be overridden if some cleanup is required for test case.
	 */
	public void cleanup() throws Exception {
	}

	/**
	 * Preconditions to run.
	 */
	@Before
	public void preconditionsToRun() throws Exception {
		if (!CustomListener.getBatchMode()) {
			preconditions();
		}
	}

	/**
	 * After class. Contains tear down procedure for Selenium.
	 */
	@AfterClass
	public static void afterClass() throws Exception {

		boolean cleanupFailed = true;

		if ((CustomListener.isSuitePreconditionsPasses() || BaseTest.currentTest.getClass().getSimpleName().contains("Cleanup")) && !CustomListener.getBatchMode() && !CustomTestRunner.isSkipCurrentTest()) {
			try {
				//FrameManager.resetFrame();
				if (Boolean.getBoolean(getProperty("isDirectEdit"))) {
					// this is stub for Direct edit DBCopier.restore();
				} else {
					BaseTest.currentTest.cleanup();
				}
				cleanupFailed = false;
			} finally {
				// instead of calling BaseTest.finishRun() directly, we will set callback trigger in CustomListener if cleanup fails
				if (cleanupFailed) {
					CustomListener.setFinishRunOnFailure();
				} 
				assertFalse("There are errors in test output", Logger.isError());
				}
			}
		}
}
