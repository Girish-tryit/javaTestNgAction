package com.qacart.todo.pages;

import com.qacart.todo.testcases.ToDoTest;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToDoPage {

    private static ToDoPage toDoPage;
    private final By welcomeMessage = By.cssSelector("[data-testid=welcome]");

    private final By addButton = By.cssSelector("[data-testid=add]");

    private final By todoItem = By.cssSelector("[data-testid=todo-item]");

    private final By deleteButton = By.cssSelector("[data-testid=delete]");

    private final By noTodoMessage = By.cssSelector("[data-testid=no-todos]");


    private ToDoPage() {
    }

    public static ToDoPage getInstance() {
        if (toDoPage == null) {
            toDoPage = new ToDoPage();
        }
        return toDoPage;
    }

    public boolean isWelcomeMessageDisplayed(WebDriver driver) {
       return driver.findElement(welcomeMessage).isDisplayed();
    }

    public void addTask(WebDriver driver) {
        driver.findElement(addButton).click();
    }

    @Step("Delete task")
    public void deleteTask(WebDriver driver) {
        driver.findElement(deleteButton).click();
    }

    public String getToDoText(WebDriver driver) {
        return driver.findElement(todoItem).getText();
    }

    public boolean isNoTodoMessageDisplayed(WebDriver driver) {
        return driver.findElement(noTodoMessage).isDisplayed();
    }

    @Step("Load To Do Page")
    public void load(WebDriver driver) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/todo");
    }
}
