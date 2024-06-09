package restApi_ClickUp.constants;

public class ClickUpConstants {

    public static final String API_TOKEN = "?????????????";
    public static final String SPACE_ID = "?????????";

    //Below are constants for creating List Comment and placing in request .body()
    public static final String LIST_COMMENT_TEXT = "This is my comment for this list";
    public static final String LIST_ASSIGNEE_NUMBER = "23562";
    public static final boolean LIST_NOTIFY_ALL = false;

    //Below are constants for creating TASK Comment and placing in request .body()
    public static final String TASK_COMMENT_TEXT = "This is my comment for this task";
    public static final String TASK_ASSIGNEE_NUMBER = "232";
    public static final boolean TASK_NOTIFY_ALL = false;
}
