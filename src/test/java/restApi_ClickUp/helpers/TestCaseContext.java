package restApi_ClickUp.helpers;

import io.cucumber.java.Scenario;
import restApi_ClickUp.domain.*;

public class TestCaseContext {
    private static Space space;
    private static Folder folder;
    private static Scenario scenario;
    private static List list;
    private static CommentOfTheList commentOfTheList;
    private static TaskOfTheList taskOfTheList;
    private static CommentOfTheTask commentOfTheTask;

    public static void init(){
        space = null;
        folder = null;
        scenario = null;
        list = null;
        commentOfTheList = null;
        taskOfTheList = null;
        commentOfTheTask = null;
    }

    public static Space getSpace() {
        return space;
    }

    public static void setSpace(Space space) {
        TestCaseContext.space = space;
    }

    public static Folder getFolder() {
        return folder;
    }

    public static String setFolder(Folder folder) {
        TestCaseContext.folder = folder;
        return null;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void setScenario(Scenario scenario) {
        TestCaseContext.scenario = scenario;
    }

    public static List getList() {
        return list;
    }

    public static void setList(List list) {
        TestCaseContext.list = list;
    }

    public static List getList(List list) {
        TestCaseContext.list = list;
        return list;
    }

    public static CommentOfTheList getCommentOfTheList() {
        return commentOfTheList;
    }

    public static void setCommentOfTheList(CommentOfTheList commentOfTheList) {
        TestCaseContext.commentOfTheList = commentOfTheList;
    }

    public static TaskOfTheList getTaskOfTheList() {
        return taskOfTheList;
    }

    public static void setTaskOfTheList(TaskOfTheList taskOfTheList) {
        TestCaseContext.taskOfTheList = taskOfTheList;
    }

    public static CommentOfTheTask getCommentOfTheTask() {
        return commentOfTheTask;
    }

    public static void setCommentOfTheTask(CommentOfTheTask commentOfThetask) {
        TestCaseContext.commentOfTheTask = commentOfThetask;
    }
}
