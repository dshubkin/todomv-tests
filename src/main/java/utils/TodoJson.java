package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TodoJson {
    private String id;
    private String title;
    private Boolean completed;
    private JsonObject json;

    public TodoJson(String id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        json = JsonParser.parseString(String.format("{\"id\":%s,\"title\":%s,\"completed\":%s}", id, title, completed.toString())).getAsJsonObject();
    }

    public TodoJson(String jsonString) {
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        json = JsonParser.parseString(jsonString).getAsJsonObject();
        this.id = json.get("id").toString();
        this.title = json.get("title").toString();
        this.completed = Boolean.valueOf(json.get("completed").toString());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public JsonObject getJsonObject() {
        return json;
    }
}
