package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getValidAuthInfo_User1() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getValidAuthInfo_User2() {
        return new AuthInfo("petya", "123qwerty");
    }

    public static AuthInfo getValidLoginAndEmptyPassword_User1() {
        return new AuthInfo("vasya", "");
    }

    public static AuthInfo getValidLoginAndEmptyPassword_User2() {
        return new AuthInfo("petya", "");
    }

    public static AuthInfo getInvalidLoginAndValidPassword_User1() {
        Faker faker = new Faker();
        return new AuthInfo(faker.name().firstName(), "qwerty123");
    }

    public static AuthInfo getInvalidLoginAndValidPassword_User2() {
        Faker faker = new Faker();
        return new AuthInfo(faker.name().firstName(), "123qwerty");
    }

    public static AuthInfo getValidLoginAndInvalidPassword_User1() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", faker.internet().password());
    }

    public static AuthInfo getValidLoginAndInvalidPassword_User2() {
        Faker faker = new Faker();
        return new AuthInfo("petya", faker.internet().password());
    }

    @Value
    public static class VerificationCodePage {
        String code;
    }

    public static String getInvalidVerificationCode() {
        Faker faker = new Faker();
        return String.valueOf(faker.number());
    }
}