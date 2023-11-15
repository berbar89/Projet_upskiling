package org.example.Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class HomePage {
    WebDriver driver;
    public HomePage (WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);

    }
}
