package com.qacart.todo.pages;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.modals.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class NewTodoPage {

    private static NewTodoPage newTodoPage;

    private By newTodoField = By.cssSelector("[data-testid=new-todo]");
    private By submitNewTask = By.cssSelector("[data-testid=submit-newTask]");

    private NewTodoPage() {
    }

    public static NewTodoPage getInstance() {
        if (newTodoPage == null) {
            newTodoPage = new NewTodoPage();
        }
        return newTodoPage;
    }
    @Step("Add new task using UI")
    public void addNewTask(WebDriver driver, String task) {
        driver.findElement(newTodoField).sendKeys(task);
        driver.findElement(submitNewTask).click();
    }
    @Step("Add new task using API")
    public void addNewTaskUsingApi(User user, String task) {
        TodoApi.getInstance().addNewTask(user, task);
    }
}
