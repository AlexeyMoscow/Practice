package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import models.User;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final static SelenideElement usersTable = $(By.xpath("//div[@class='main-page__table']"));

    //usersData
    private final static SelenideElement usersSurnameFromTable = $(By.xpath("//div[@class='main-page__table-body-item'][last()]//span[@class='main-page__table-lastname']"));
    private final static SelenideElement usersNameFromTable = $(By.xpath("//div[@class='main-page__table-body-item'][last()]//span[@class='main-page__table-firstname']"));
    //Locators for collections
    private static By usersIdLocator = By.xpath("//span[@class='main-page__table-id']");
    private static By usersNameLocator = By.xpath("//span[@class='main-page__table-firstname']");
    private static By usersSurnameLocator = By.xpath("//span[@class='main-page__table-lastname']");

    private final static SelenideElement usersId = $(usersIdLocator);
    private final static SelenideElement usersName = $(usersNameLocator);
    private final static SelenideElement usersSurname = $(usersSurnameLocator);

    //Locator for sorting

    private final static SelenideElement sortByIdButton = $(By.xpath("//div[@class='main-page__table-id main-page__header-item']"));
    private final static SelenideElement sortBySurnameButton = $(By.xpath("//div[@class='main-page__table-lastname main-page__header-item']"));
    private final static SelenideElement lastIdOnPage = $(By.xpath("//div[@class='main-page__table-body-item'][last()]/span"));

    public static By getUsersIdLocator() {
        return usersIdLocator;
    }

    public static By getUsersNameLocator() {
        return usersNameLocator;
    }

    public static By getUsersSurnameLocator() {
        return usersSurnameLocator;
    }

    public void openMainPage(String url) {
        Selenide.open(url);
    }

    public boolean isMainPageOpened() {
        return usersTable.exists();
    }


    public String getUserSurname() {
        return usersSurnameFromTable.getText();
    }

    public String getUserName() {
        return usersNameFromTable.getText();
    }


    public List<String> getUsersDataByLocator(By testLocator) {
        ElementsCollection testData = $$(testLocator);
        usersTable.shouldBe(Condition.visible);
        return testData.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public List<User> getAllUsersFromUI(MainPage mainPage) {
        ArrayList<User> allUsersFromUI = new ArrayList<>();
        lastIdOnPage.shouldNotBe(Condition.empty);
        List<String> usersName = mainPage.getUsersDataByLocator(getUsersNameLocator());
        List<String> usersSurname = mainPage.getUsersDataByLocator(getUsersSurnameLocator());
        List<Integer> usersIDs = mainPage.getUsersDataByLocator(getUsersIdLocator()).stream().map(Integer::parseInt).collect(Collectors.toList());
        for (int i = 0; i < usersName.size(); i++) {
            allUsersFromUI.add(new User(usersName.get(i), usersSurname.get(i), usersIDs.get(i)));
        }
        return allUsersFromUI;
    }

    public void sortUserByID() {
        sortByIdButton.click();
        lastIdOnPage.shouldNotBe(Condition.empty);
    }

    public void sortUserBySurname() {
        sortBySurnameButton.click();
        lastIdOnPage.shouldNotBe(Condition.empty);
    }

    public List<Integer> getUsersIdFromUserCollection(List<User> usersCollection) {
        return usersCollection.stream().map(User::getId).collect(Collectors.toList());
    }

    public List<String> getUsersSurnameFromUserCollection(List<User> usersCollection) {
        return usersCollection.stream().map(User::getSurname).collect(Collectors.toList());
    }

}
