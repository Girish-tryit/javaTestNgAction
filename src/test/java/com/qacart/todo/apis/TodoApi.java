package com.qacart.todo.apis;

import com.qacart.todo.modals.User;
import com.qacart.todo.utils.ConfigUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {

    private TodoApi() {
    }

    private static TodoApi todoApi;

    public static TodoApi getInstance() {
        if (todoApi == null) {
            todoApi = new TodoApi();
        }
        return todoApi;
    }

    public Response addNewTask(User user, String taskName) {
        // Add new task
       return given()
                .baseUri(ConfigUtils.getInstance().getBaseUrl())
                .contentType(ContentType.JSON)
                .body("{\"item\":\""+ taskName+"\",\"isCompleted\":false}")
                .auth().oauth2(user.getAccessToken())
                .when()
                .post("/api/v1/tasks")
                .then()
                .extract().response();
    }
}
