package com.example.resolverqe;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement inputEmail = $("input[id='inputEmail']");
    public SelenideElement inputPassword = $("input[id='inputPassword']");
    public SelenideElement buttonLogin = $("button[type='submit']");

    public SelenideElement divListGroup = $("#test-2-div > ul");

    public SelenideElement dropdownMenuButton = $("button[id='dropdownMenuButton']");
    public SelenideElement dropdownMenu = $("#test-3-div > div > div");

    public SelenideElement buttonEnabled = $("button[class='btn btn-lg btn-primary']");
    public SelenideElement buttonDisabled= $("button[class='btn btn-lg btn-secondary']");

    public SelenideElement test5Button = $("button[id='test5-button']");

    public SelenideElement test5AlertSuccess = $("div[id='test5-alert']");
    public SelenideElement test6Table = $("#test-6-div > div > table > tbody");
    public SelenideElement test6TableRows = $("#test-6-div > div > table > tbody > tr");
}
