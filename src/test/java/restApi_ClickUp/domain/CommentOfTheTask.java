package restApi_ClickUp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentOfTheTask {

    @JsonProperty("comment_text")
    private String comment_text;
    @JsonProperty("assignee")
    private String assignee;
    @JsonProperty("notify_all")
    private boolean notify_all;

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public boolean isNotify_all() {
        return notify_all;
    }

    public void setNotify_all(boolean notify_all) {
        this.notify_all = notify_all;
    }
}
