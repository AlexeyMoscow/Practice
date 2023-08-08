import Pages.MainPage;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.SortChecker;

import java.util.List;

import static utils.PropertiesProvider.getFromProperties;

public class TestCase extends BaseTest {

    @Test
    public void test1() throws InterruptedException {
        String userName = "Иван";
        String userSurname = "Петров";
        String userBirthDate = "01021980";

        MainPage mainPage = new MainPage();
        mainPage.openMainPage(getFromProperties("url"));
        Assert.assertTrue(mainPage.isMainPageOpened(), "Users table is missing");

        //Check all ID order (Asc)

        List<User> usersFromUI = mainPage.getAllUsersFromUI(mainPage);
        Assert.assertTrue(SortChecker.isIdSortedByAsc(mainPage.getUsersIdFromUserCollection(usersFromUI)));
        System.out.println(mainPage.getUsersIdFromUserCollection(usersFromUI));

        //Check all ID order (Desc)
        mainPage.sortUserByID();
        usersFromUI = mainPage.getAllUsersFromUI(mainPage);
        Assert.assertTrue(SortChecker.isIdSortedByDesc(mainPage.getUsersIdFromUserCollection(usersFromUI)));
        System.out.println(mainPage.getUsersIdFromUserCollection(usersFromUI));

        //Check sorting of surname apl
        mainPage.sortUserBySurname();
        usersFromUI = mainPage.getAllUsersFromUI(mainPage);
        Assert.assertTrue(SortChecker.isSurnameSortedByAcs(mainPage.getUsersSurnameFromUserCollection(usersFromUI)));
        System.out.println(mainPage.getUsersSurnameFromUserCollection(usersFromUI));

        //Check sorting of surname Desc
        mainPage.sortUserBySurname();
        usersFromUI = mainPage.getAllUsersFromUI(mainPage);
        Assert.assertTrue(SortChecker.isSurnameSortedByDesc(mainPage.getUsersSurnameFromUserCollection(usersFromUI)));
        System.out.println(mainPage.getUsersSurnameFromUserCollection(usersFromUI));
    }

}
