package com.qacart.todo.pages;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.modals.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private static RegisterPage registerPage;
    private final By firstNameField = By.cssSelector("[data-testid=first-name]");
    private final By lastNameField = By.cssSelector("[data-testid=last-name]");
    private final By emailField = By.cssSelector("[data-testid=email]");
    private final By passwordField = By.cssSelector("[data-testid=password]");
    private final By confirmPasswordField = By.cssSelector("[data-testid=confirm-password]");
    private final By submitButton = By.cssSelector("[data-testid=submit]");


    private RegisterPage() {
    }

    public static RegisterPage getInstance() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }
    @Step("Register user using API")

    public void registerUserUsingApi(WebDriver driver, User user) {
        Response response = UserApi.getInstance().registerUser(user);
        String token = response.path("access_token");
        String firstName = response.path("firstName");
        String userID = response.path("userID");

        Cookie cookie = new Cookie("access_token", token);
        Cookie cookie1 = new Cookie("firstName", firstName);
        Cookie cookie2 = new Cookie("userID", userID);
        user.setAccessToken(token);
        driver.manage().addCookie(cookie);
        driver.manage().addCookie(cookie1);
        driver.manage().addCookie(cookie2);
        driver.navigate().refresh();
    }

    @Step("Register user using UI")
    public void registerUser(WebDriver driver, User user) {
        driver.findElement(firstNameField).sendKeys(user.getFirstName());
        driver.findElement(lastNameField).sendKeys(user.getLastName());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(confirmPasswordField).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }

    public void load(WebDriver driver   ) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/signup");
    }

}
