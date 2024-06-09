package restApi_ClickUp.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import restApi_ClickUp.domain.*;
import restApi_ClickUp.helpers.TestCaseContext;

import static restApi_ClickUp.clients.ClickUpClient.*;
import static restApi_ClickUp.constants.ClickUpConstants.SPACE_ID;

public class ClickUpSteps {

    @Given("The created space {string} exists and contains the correct information")
    public void getSpaceAndCheckInfo(String title) {
        Response response =  getSpaceInfo(SPACE_ID);
        Space space = response.as(Space.class);
        TestCaseContext.setSpace(space);

        Assertions.assertThat(space.getId())
                .as("We assert that space ID is correct")
                .isEqualTo(SPACE_ID);

        Assertions.assertThat(space.getName())
                .as("We assert that the space name is correct")
                .isEqualTo(title);
    }

    @When("User creates new Folder with folder name {string} and confirms that folder exists in space with correct name")
    public void userCreatesNewFolderAndChecksInfo(String folderName) {
        Response response = createFolder(folderName, TestCaseContext.getSpace().getId());
        Folder folder = response.as(Folder.class);
        TestCaseContext.setFolder(folder);
        Assertions.assertThat(folder.getName(folderName))
                .as("The folder was created with name " + folderName)
                .isEqualTo(folderName);
        TestCaseContext.getScenario().log("The folder ID is: " + folder.getId());
    }

    @And("User creates new List in the Folder with name {string}")
    public void userCreatesNewList(String listName) {
        Response response = createList(listName, TestCaseContext.getFolder().getId());
        List list = response.as(List.class);
        TestCaseContext.setList(list);
        System.out.println("The list name is: " + TestCaseContext.getList().getName());
        TestCaseContext.getScenario().log("The List ID is: " + list.getId());
    }

    @And("User confirms that created List name is {string} and added to correct Folder - {string}")
    public void checkListNameAndIfIsInCorrectFolder(String listName, String folderName) {
        Assertions.assertThat(TestCaseContext.getList().getName())
                .as("We assert that list name is " + listName)
                .isEqualTo(listName);

        String response = getList(TestCaseContext.getList().getId())
                .jsonPath().getString("folder.name");
        Assert.assertEquals("List has been added in correct folder ", folderName, response );
    }

    @And("User adds one Comment in created List and checks that comment is added to the List")
    public void userAddsCommentToTheListAndConfirmsIt() {
        Response response = addCommentToTheList(TestCaseContext.getList().getId());
        CommentOfTheList commentOfTheList = response.as(CommentOfTheList.class);
        TestCaseContext.setCommentOfTheList(commentOfTheList);

        Assertions.assertThat(commentOfTheList.getComment_text())
                .as("We have added comment to the list")
                .isEqualTo(commentOfTheList.getComment_text());
    }

    @And("User creates one Task with unique name inside created List")
    public void userAddsTask() {
        Response response = createTaskToTheList(TestCaseContext.getList().getId());
        TaskOfTheList taskOfTheList = response.as(TaskOfTheList.class);
        TestCaseContext.setTaskOfTheList(taskOfTheList);

        TestCaseContext.getScenario().log("The Task name is: " +  taskOfTheList.getName());
        TestCaseContext.getScenario().log("The Task ID is: " + taskOfTheList.getId());
    }

    @And("User confirms that Task name is correct")
    public void checkTaskNameIsCorrect() {
        String response = confirmTaskName(TestCaseContext.getTaskOfTheList().getId())
                .jsonPath().getString("name");
        Assert.assertEquals("The task name is correct", TestCaseContext.getTaskOfTheList().getName(), response);
    }

    @And("User adds one Comment in created Task and checks that comment is added to the Task")
    public void userAddsCommentToTheTaskAndConfirmIt() {
        Response response = addCommentToTheTask(TestCaseContext.getTaskOfTheList().getId());
        CommentOfTheTask commentOfTheTask = response.as(CommentOfTheTask.class);
        TestCaseContext.setCommentOfTheTask(commentOfTheTask);

        Assertions.assertThat(commentOfTheTask.getComment_text())
                .as("We have added comment to the task")
                .isEqualTo(commentOfTheTask.getComment_text());
    }

    @Then("User removes the Task from the created List")
    public void removesTheTaskFromTheList() {
        removingTaskFromTheList(TestCaseContext.getTaskOfTheList().getId());
        TestCaseContext.getScenario().log("The Task has been deleted");
    }
}
