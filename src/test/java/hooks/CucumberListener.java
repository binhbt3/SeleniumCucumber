package hooks;


import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

public class CucumberListener implements EventListener {

    public static int count_totalTCs = 0;
    public static int count_passedTCs = 0;
    public static int count_failedTCs = 0;
    public static int count_skippedTCs = 0;
    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {

        eventPublisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        eventPublisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        eventPublisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::ScenarioFinished);
        eventPublisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
    }

    private void runStarted(TestRunStarted event){

//        System.out.println("Test Run Started");
    }

    private void  runFinished(TestRunFinished event){
//        System.out.println("RUN FINISH");
//        System.out.println("count_totalTCs: " + count_totalTCs);
//        System.out.println("count_passedTCs: " + count_passedTCs);
//        System.out.println("count_failedTCs: " + count_failedTCs);

    }

    private void featureRead(TestSourceRead event){
//        String featureSource = event.getUri().toString();
//        String featureName = featureSource.split(".*/")[1];
//        System.out.println("featureSource: " + featureSource);
//        System.out.println("featureName: " + featureName);
    }

    private void ScenarioStarted(TestCaseStarted event){
//        String featureName = event.getTestCase().getUri().toString();
//        System.out.println("Scenario Started -> featureName: "+ featureName);
//        count_totalTCs +=1;
    }

    private void ScenarioFinished(TestCaseFinished event){

//        String featureName = event.getTestCase().getUri().toString();
//        System.out.println("Scenario Finished -> featureName: " + featureName);
//
//        if(event.getResult().getStatus().toString().equals("PASSED")) {
//            count_passedTCs += 1;
//        }
//        if(event.getResult().getStatus().toString().equals("FAILED")) {
//            count_failedTCs += 1;
//        }
//        if(event.getResult().getStatus().toString().equals("SKIPPED")) {
//            count_skippedTCs += 1;
//        }

    }

    private void stepStarted(TestStepStarted event){

//        String stepName;
//        String keyword;
//
//        System.out.println("Step started");
//        if(event.getTestStep() instanceof PickleStepTestStep){
//            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
//            stepName = steps.getStep().getText();
//            keyword = steps.getStep().getKeyword();
//        } else {
//            HookTestStep hoo = (HookTestStep) event.getTestStep();
//            stepName = hoo.getHookType().name();
//        }

    }

    private void stepFinished(TestStepFinished event){

//        String stepName;
//        String keyword;
//
//        System.out.println("Step Finished");
//        if(event.getTestStep() instanceof PickleStepTestStep){
//            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
//            stepName = steps.getStep().getText();
//            keyword = steps.getStep().getKeyword();
//        } else {
//            HookTestStep hoo = (HookTestStep) event.getTestStep();
//            stepName = hoo.getHookType().name();
//        }
//
//        if(event.getResult().getStatus().toString().equals("PASSED")){
//
//        }
//        if(event.getResult().getStatus().toString().equals("FAILED")){
//            System.out.println("------------------- After Step " + stepName + " failed-----------------");
//            System.out.println(event.getResult().getError());
//
//        }
//        if(event.getResult().getStatus().toString().equals("SKIPPED")){
//            System.out.println("------------------- After Step " + stepName + " is skipped -------------------");
//        }
    }
}
