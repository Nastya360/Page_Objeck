package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
 private SelenideElement header = $("[data-test-id =dashboard]");
 private SelenideElement subtitle = $(withText("Пополнение карты"));
 private SelenideElement amount =$("[data-test-id=amount] input");
 private SelenideElement from =$("[data-test-id=from] input");
 private SelenideElement to =$("[data-test-id=to] input");
 private SelenideElement action =$("[data-test-id=action-transfer]");
 private SelenideElement cancel =$("[data-test-id=action-cancel]");
 private SelenideElement error_notification= $("[data-test-id=error-notification]");

 public MoneyTransferPage(){
  header.shouldBe(Condition.visible);
  subtitle.shouldBe(Condition.visible);
 }
 public DashboardPage transferForm(String sum, DataHelper.CardNumber secondCardNumber) {
  amount.setValue(sum);
  from.setValue(secondCardNumber.getCardNumber());
  action.click();
  return new DashboardPage();
 }
 public void error (){
  $(withText("Ошибка")).shouldBe(Condition.visible);
 }
 public DashboardPage cancel(){
  cancel.click();
  return new DashboardPage();
 }
}
