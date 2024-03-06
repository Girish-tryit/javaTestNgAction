package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.modals.User;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.ToDoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ToDoTest extends BaseTest {
    @Test (description = "Should be able to create a task")
    public void shouldBeAbleToCreateAToDo() {

        String expectedToDoTest = "Test To Do something";
        User user = new User();

        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUserUsingApi(driver.get(), user);
        ToDoPage.getInstance().addTask(driver.get());
        NewTodoPage.getInstance().addNewTask(driver.get(), expectedToDoTest);
        String todoText = ToDoPage.getInstance().getToDoText(driver.get());
        Assert.assertEquals(todoText, expectedToDoTest, "To Do text is not as expected");

    }

    @Test (description = "Should be able to delete a task")
    public void shouldBeAbleToDeleteTask() {
        String expectedToDoTest = "Test To Do something";
        User user = new User();

        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUserUsingApi(driver.get(), user);
        NewTodoPage.getInstance().addNewTaskUsingApi(user, expectedToDoTest);
        ToDoPage.getInstance().load(driver.get());
        ToDoPage.getInstance().deleteTask(driver.get());
        boolean isToDoDisplayed = ToDoPage.getInstance().isNoTodoMessageDisplayed(driver.get());
        Assert.assertTrue(isToDoDisplayed, "To Do is not displayed");

    }
}
