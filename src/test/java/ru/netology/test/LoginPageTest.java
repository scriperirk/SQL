package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.User;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest {

    @Test
    void shouldAuthorizeSuccessfully_1stUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User1();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verifyInfo = User.getValidVerificationCode();
        var dashboardPage = verificationPage.validCode(verifyInfo);
        dashboardPage.login();
    }

    @Test
    void shouldAuthorizeSuccessfully_2ndUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User2();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verifyInfo = User.getValidVerificationCode();
        var dashboardPage = verificationPage.validCode(verifyInfo);
        dashboardPage.login();
    }

    @Test
    void shouldNotValidateWithBothEmptyFields() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        loginPage.emptyAuthInfo();
    }

    @Test
    void shouldNotValidateWithEmptyPassword_1stUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndEmptyPassword_User1();
        loginPage.validLoginAndEmptyPassword(authInfo);
    }

    @Test
    void shouldNotValidateWithEmptyPassword_2ndUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndEmptyPassword_User2();
        loginPage.validLoginAndEmptyPassword(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidPassword_1stUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndInvalidPassword_User1();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidPassword_2ndUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndInvalidPassword_User2();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidLogin_1stUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getInvalidLoginAndValidPassword_User1();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotAuthorizeWithInvalidLogin_2ndUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getInvalidLoginAndValidPassword_User2();
        loginPage.invalidAuthInfo(authInfo);
    }

    @Test
    void shouldNotLoginWithEmptyCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User1();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        verificationPage.emptyCode();
    }

    @Test
    void shouldNotLoginWithInvalidCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidAuthInfo_User2();
        var verificationPage = loginPage.validAuthInfo(authInfo);
        var verifyInfo = DataHelper.getInvalidVerificationCode();
        verificationPage.invalidCode(verifyInfo);
    }

    @Test
    void shouldBlockAfterThreeAttemptsWithInvalidPassword() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getValidLoginAndInvalidPassword_User1();
        loginPage.blockSystem(authInfo);
    }
}
