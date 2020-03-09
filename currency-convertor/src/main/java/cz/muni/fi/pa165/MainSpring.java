package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.MainConfiguration;
import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainSpring {
    private static final Currency CZK = Currency.getInstance("CZK");
    private static final Currency EUR = Currency.getInstance("EUR");

    public static void main(String ... args) {
        ApplicationContext applicationContect = new AnnotationConfigApplicationContext(SpringJavaConfig.class);

        CurrencyConvertor currencyConvertor = applicationContect.getBean("example", CurrencyConvertor.class);

        BigDecimal result = currencyConvertor.convert(EUR, CZK, BigDecimal.ONE);
        System.out.println(result);
    }
}
