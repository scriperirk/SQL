package test;

package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.User;
import ru.netology.data.LoginPage;
import ru.netology.data.VerificationCodePage;

import java.sql.SQLException;


public class LoginPage {

    @Test
    void successEnterTest() throws SQLException {
        LoginPage login = new LoginPage();
        User user = DataHelper.getValidUserFirst();
        VerificationCodePage pageVerification = login.validLogin(user);
        String verificationCode = DataHelper.getLastVerificationCode(user);
        pageVerification.validVerify(verificationCode);
    }

    @Test
    void passwordErrorTest() throws SQLException {
        LoginPage login = new LoginPage();
        User wrongUser = DataHelper.getInvalidPasswordUser();
        login.invalidUser(wrongUser);
    }

    @Test
    void invalidVerificationCodeTest() throws SQLException {
        LoginPage login = new LoginPage();
        User user = DataHelper.getValidUserFirst();
        VerificationCodePage pageVerification = login.validLogin(user);
        String verificationCode = DataHelper.getInvalidVerificationCode();
        pageVerification.invalidVerify(verificationCode);
    }

    void doMaxEnter(User user) throws SQLException {
        LoginPage login;
        String verificationCode;
        VerificationCodePage pageVerification;

        for (int i = 0; i < 3; i++) {
            login = new LoginPage();
            pageVerification = login.validLogin(user);
            verificationCode = DataHelper.getLastVerificationCode(user);
            pageVerification.validVerify(verificationCode);
        }
    }

    @Test
    void enterBlockTest() throws SQLException {
        User user = DataHelper.getValidUserSecond();
        doMaxEnter(user);

        LoginPage login = new LoginPage();
        VerificationCodePage pageVerification = login.validLogin(user);
        String verificationCode = DataHelper.getLastVerificationCode(user);
        pageVerification.isBlockedUser(verificationCode);
    }


}