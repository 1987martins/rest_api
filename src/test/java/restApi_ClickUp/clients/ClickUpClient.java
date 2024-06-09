package restApi_ClickUp.clients;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restApi_ClickUp.domain.*;

import static io.restassured.http.ContentType.JSON;
import static restApi_ClickUp.constants.ClickUpConstants.*;

public class ClickUpClient {

    private static RequestSpecification clickUpSpec() {
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .header("Authorization", API_TOKEN);
    }

    public static Response getSpaceInfo(String spaceId){
        return RestAssured
                .given(clickUpSpec())
                .when()
                .get("https://api.clickup.com/api/v2/space/" + spaceId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createFolder(String folderName, String spaceId) {

        Folder name = new Folder();
        name.setName(folderName);
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .body(name)
                .header("Authorization", API_TOKEN)
                .when()
                .post("https://api.clickup.com/api/v2/space/" + spaceId + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createList(String listName, String folderId) {
        List name = new List();
        name.setName(listName);

        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .body(name)
                .header("Authorization", API_TOKEN)
                .when()
                .post("https://api.clickup.com/api/v2/folder/" + folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response getList(String listId) {
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/list/" + listId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response addCommentToTheList(String listId) {

        CommentOfTheList commentOfTheList = new CommentOfTheList();
        commentOfTheList.setComment_text(LIST_COMMENT_TEXT);
        commentOfTheList.setAssignee(LIST_ASSIGNEE_NUMBER);
        commentOfTheList.setNotify_all(LIST_NOTIFY_ALL);

        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .body(commentOfTheList)
                .header("Authorization", API_TOKEN)
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createTaskToTheList(String listId) {

        Faker faker = new Faker();
        TaskOfTheList taskOfTheList = new TaskOfTheList();
        taskOfTheList.setName(faker.funnyName().name());

        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .body(taskOfTheList)
                .header("Authorization", API_TOKEN)
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response confirmTaskName(String taskId) {
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response addCommentToTheTask(String taskId) {

        CommentOfTheTask commentOfTheTask = new CommentOfTheTask();
        commentOfTheTask.setComment_text(TASK_COMMENT_TEXT);
        commentOfTheTask.setAssignee(TASK_ASSIGNEE_NUMBER);
        commentOfTheTask.setNotify_all(TASK_NOTIFY_ALL);

        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .body(commentOfTheTask)
                .header("Authorization", API_TOKEN)
                .when()
                .post("https://api.clickup.com/api/v2/task/" + taskId + "/comment")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response removingTaskFromTheList (String taskId) {
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .header("Authorization", API_TOKEN)
                .header("Content-Type", "application/json")
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    public static Response deleteFolder (String folderId) {
        return RestAssured
                .given().log().all()
                .contentType(JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + folderId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
