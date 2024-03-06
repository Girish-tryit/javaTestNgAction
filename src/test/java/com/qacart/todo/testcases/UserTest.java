package com.qacart.todo.testcases;

import com.qacart.todo.base.BaseTest;
import com.qacart.todo.modals.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.ToDoPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserTest extends BaseTest {

    @Test (description = "Should be able to register a user")
    public void shouldBeAbleToRegisterAUser() {
        User user = new User();
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUser(driver.get(), user);
        boolean isWelcomeDisplayed = ToDoPage.getInstance().isWelcomeMessageDisplayed(driver.get());
        Assert.assertTrue(isWelcomeDisplayed, "Welcome message is not displayed");
    }
}
