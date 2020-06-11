package ru.ozon;

import org.junit.Before;
import org.junit.Test;

public class Testes {

    @Test
    public void testLogin() {
        TestLogin tester = new TestLogin();
        tester.openFormToInputPhone();
        tester.inputPhone("9991848156");
        String result = tester.getTextAfterLogin();

        assert "Кабинет".equals(result);
        tester.close();
    }


    @Test
    public void testChangeCity()  {

        String cityName = "Вольск";

        TestChangeCity tester = new TestChangeCity();
        tester.changeCity(cityName);
        assert cityName.equals(tester.getCityText());

        TestLogin testerLogin = new TestLogin(tester.getWebDriver(), tester.getWebDriverWait());
        testerLogin.openFormToInputPhone();
        testerLogin.inputPhone("9991848156");
        String result = testerLogin.getTextAfterLogin();
        assert "Кабинет".equals(result);

        assert cityName.equals(tester.openMapAndCheck());
        tester.close();
    }

    @Test
    public void testBuyJuicer1() {
        ChooseJuicerTest tester = new ChooseJuicerTest();
        tester.getToJuicers();
        tester.setPrice("3000", "4000");
        tester.testFilter("Цена: от 3 000 до 4 000", "Соковыжималки");
        tester.sortAndAddCheapest();

        double price = tester.getPrice();
        int need = 5;
        String amount = tester.addAndReturnAmount(need);

        double totalPrice = tester.getTotalPrice();

        assert amount.equals(String.valueOf(need)) &&
                need * price == totalPrice;

        tester.close();
    }

    @Test
    public void testBuyJuicer2() {
        ChooseJuicerTest tester = new ChooseJuicerTest();
        tester.getToJuicers();
        tester.setPrice("3000", "4000");
        tester.setPower("1000");
        tester.testFilter("Цена: от 3 000 до 4 000", "Соковыжималки");
        tester.sortAndAddCheapest();

        double price = tester.getPrice();
        int need = 1;
        String amount = tester.addAndReturnAmount(need);

        double totalPrice = tester.getTotalPrice();

        assert amount.equals(String.valueOf(need)) &&
                need * price == totalPrice;
        tester.close();
    }

}