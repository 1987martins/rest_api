package restApi_ClickUp.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import restApi_ClickUp.helpers.TestCaseContext;

import static restApi_ClickUp.clients.ClickUpClient.deleteFolder;

public class Hooks {

    @Before
    public void beforeHook(Scenario scenario){
        TestCaseContext.init();
        TestCaseContext.setScenario(scenario);
        System.out.println("Scenario has started");
    }

    @After
    public void afterHook(){
        deleteFolder(TestCaseContext.getFolder().getId());
        System.out.println("Scenario has ended");
    }
}
