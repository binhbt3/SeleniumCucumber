package hooks;

import common.BaseTest;
import constants.FrameworkConstants;
import driver.DriverManager;
import helpers.CaptureHelpers;
import helpers.PropertiesHelpers;
import io.cucumber.core.gherkin.Step;
import io.cucumber.java.*;
import report.AllureManager;
import utils.LogUtils;


public class CucumberHooks {
    public static int count_totalTCs = 0;
    public static int count_passedTCs = 0;
    public static int count_failedTCs = 0;
    public static int count_skippedTCs = 0;


    @BeforeAll
    public static void beforeAll(){
        LogUtils.info("----------------Before All-------------------");
        PropertiesHelpers.loadAllFiles();
    }

    @AfterAll
    public static void AfterAll(){
        LogUtils.info("----------------After All--------------------");
        LogUtils.info("count_totalTCs: " + count_totalTCs);
        LogUtils.info("count_passedTCs: " + count_passedTCs);
        LogUtils.info("count_failedTCs: " + count_failedTCs);
        LogUtils.info("count_skippedTCs: " + count_skippedTCs);
    }

    @Before
    public static void beforeScenario(Scenario scenario){

        LogUtils.info("Before scenario");
        count_totalTCs +=1;
        if(FrameworkConstants.VIDEO_RECORD.equals("Yes")){
            CaptureHelpers.startRecord(scenario.getName());
        }
    }

    @After
    public static void afterScenario(Scenario scenario){
        LogUtils.info("After scenario");
        if(scenario.getStatus().equals(Status.PASSED)){
            count_passedTCs +=1;
        }
        if(scenario.getStatus().equals(Status.FAILED)){
            count_failedTCs +=1;
        }
        if(scenario.getStatus().equals(Status.SKIPPED)){
            count_skippedTCs +=1;
        }
        if(FrameworkConstants.VIDEO_RECORD.equals("Yes")){
            CaptureHelpers.stopRecord();
        }

        BaseTest.closeDriver();
    }

    @BeforeStep
    public static void beforeStep(){
        LogUtils.info("Before Step");
    }

    @AfterStep
    public static void afterStep(Scenario scenario){
        LogUtils.info("After step");
        if(scenario.getStatus().equals(Status.FAILED) && FrameworkConstants.SCREENSHOT_FAILED_STEPS.equals(FrameworkConstants.YES)){
//            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), scenario.getName());
            AllureManager.takeScreenshotToAttachOnAllureReport();
        }
        if(scenario.getStatus().equals(Status.PASSED) && FrameworkConstants.SCREENSHOT_PASSED_STEPS.equals(FrameworkConstants.YES))
        {
            AllureManager.takeScreenshotStep();
        }

        boolean bol1 = (scenario.getStatus().equals(Status.SKIPPED) && FrameworkConstants.SCREENSHOT_SKIPPED_STEPS.equals(FrameworkConstants.YES));
        if((scenario.getStatus().equals(Status.SKIPPED) && FrameworkConstants.SCREENSHOT_SKIPPED_STEPS.equals(FrameworkConstants.YES)))
        {
            AllureManager.takeScreenshotStep();
        }

        if(FrameworkConstants.SCREENSHOT_ALL_STEPS.equals(FrameworkConstants.YES)){
            AllureManager.takeScreenshotStep();
        }
    }
}
