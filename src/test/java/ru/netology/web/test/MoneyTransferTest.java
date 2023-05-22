package ru.netology.web.test;



import com.google.errorprone.annotations.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.LoginPageV2;
import ru.netology.web.page.LoginPageV3;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
  @BeforeEach
  public void activationPage(){
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);

  }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
      var dashboardPage = new DashboardPage();
      int balanceFirstCard = dashboardPage.getFirstCardBalance();
      int balanceSecondCard = dashboardPage.getSecondCardBalance();
      var moneyTransfer = dashboardPage.secondCardButton();
      var infoCard = DataHelper.getFirstCardNumber();
      int sum = 3000;
      moneyTransfer.transferForm(String.valueOf(sum), infoCard );

      assertEquals( balanceFirstCard - sum, dashboardPage.getFirstCardBalance() );
      assertEquals( balanceSecondCard + sum, dashboardPage.getSecondCardBalance() );

    }


  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    var dashboardPage = new DashboardPage();
    int balanceFirstCard = dashboardPage.getFirstCardBalance();
    int balanceSecondCard = dashboardPage.getSecondCardBalance();
    var moneyTransfer = dashboardPage.firstCardButton();
    var infoCard = DataHelper.getSecondCardNumber();
    int sum = 1000;
    moneyTransfer.transferForm(String.valueOf(sum), infoCard );

    assertEquals( balanceFirstCard + sum, dashboardPage.getFirstCardBalance() );
    assertEquals( balanceSecondCard - sum, dashboardPage.getSecondCardBalance() );

  }

  @Test
  void shouldGetErrorWhenTransferMoreBalance() {
    var dashboardPage = new DashboardPage();
    var moneyTransfer= dashboardPage.secondCardButton();
    var infoCard= DataHelper.getFirstCardNumber();
    int sum= 100_000;
    moneyTransfer.transferForm(String.valueOf(sum),infoCard);
    moneyTransfer.error();

  }

}

